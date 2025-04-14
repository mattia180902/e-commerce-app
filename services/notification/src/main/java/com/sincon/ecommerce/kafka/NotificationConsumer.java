package com.sincon.ecommerce.kafka;

import org.springframework.stereotype.Service;

import com.sincon.ecommerce.kafka.payment.PaymentConfirmation;
import com.sincon.ecommerce.notification.NotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    // private final EmailService emailService;

    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {

    }
}
