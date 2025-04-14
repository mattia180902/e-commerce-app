package com.sincon.ecommerce.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sincon.ecommerce.customer.CustomerClient;
import com.sincon.ecommerce.exception.BusinessException;
import com.sincon.ecommerce.kafka.OrderConfirmation;
import com.sincon.ecommerce.kafka.OrderProducer;
import com.sincon.ecommerce.orderline.OrderLineRequest;
import com.sincon.ecommerce.orderline.OrderLineService;
import com.sincon.ecommerce.payment.PaymentClient;
import com.sincon.ecommerce.payment.PaymentRequest;
import com.sincon.ecommerce.product.ProductClient;
import com.sincon.ecommerce.product.PurchaseRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {

        // chech the customer --> OpenFeign
        var customer = this.customerClient.findCustomerByid(request.customerId())
                .orElseThrow(
                        () -> new BusinessException("Cannot create order:: No customer exist with the provided ID"));

        // purchase the product --> product-ms (RestTemplate)
        var purchasedProducts = this.productClient.purchaseProducts(request.products());

        // persist order
        var order = this.repository.save(
                mapper.toOrder(request));

        // persist orderLines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // start payment process
        var paymentRequest = new PaymentRequest(
            request.amount(),
            request.paymentMethod(),
            order.getId(),
            order.getReference(),
            customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation --> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
            new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                purchasedProducts
            )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
            .stream()
            .map(mapper::fromOrder)
            .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
            .map(mapper::fromOrder)
            .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));
    }
}
