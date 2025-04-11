package com.sincon.ecommerce.order;

import org.springframework.stereotype.Service;

import com.sincon.ecommerce.customer.CustomerClient;
import com.sincon.ecommerce.exception.BusinessException;
import com.sincon.ecommerce.product.ProductClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public Integer createOrder(OrderRequest request) {

        // chech the customer --> OpenFeign
        var customer = this.customerClient.findCustomerByid(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exist with the provided ID"));

        // purchase the product --> product-ms (RestTemplate)

        // persist order

        // persist orderLines

        // start payment process

        // send the order confirmation --> notification-ms (kafka)

        return null;
    }

}
