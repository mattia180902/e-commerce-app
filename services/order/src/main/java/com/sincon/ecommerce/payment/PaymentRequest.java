package com.sincon.ecommerce.payment;

import java.math.BigDecimal;
import com.sincon.ecommerce.customer.CustomerResponse;
import com.sincon.ecommerce.order.PaymentMethod;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerResponse customer
)  {

}
