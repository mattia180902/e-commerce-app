package com.sincon.ecommerce.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.sincon.ecommerce.customer.CustomerResponse;
import com.sincon.ecommerce.order.PaymentMethod;
import com.sincon.ecommerce.product.PurchaseResponse;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    CustomerResponse customer,
    List<PurchaseResponse> products
)  {}
