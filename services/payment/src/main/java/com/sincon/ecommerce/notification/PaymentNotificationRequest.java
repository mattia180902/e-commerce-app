package com.sincon.ecommerce.notification;

import java.math.BigDecimal;

import com.sincon.ecommerce.payment.PaymentMethod;

public record PaymentNotificationRequest(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
)  {

}
