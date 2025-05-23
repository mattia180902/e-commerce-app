package com.sincon.ecommerce.kafka;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sincon.ecommerce.email.EmailService;
import com.sincon.ecommerce.kafka.order.OrderConfirmation;
import com.sincon.ecommerce.kafka.payment.PaymentConfirmation;
import com.sincon.ecommerce.notification.Notification;
import com.sincon.ecommerce.notification.NotificationRepository;
import com.sincon.ecommerce.notification.NotificationType;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repository.save(
            Notification.builder()
            .type(NotificationType.PAYMENT_CONFIRMATION)
            .notificationDate(LocalDateTime.now())
            .paymentConfirmation(paymentConfirmation)
            .build()
        );

        // send email
        var customerName = paymentConfirmation.customerFirstName() + 
                " " + paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(
            paymentConfirmation.customerEmail(),
            customerName,
            paymentConfirmation.amount(),
            paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        repository.save(
            Notification.builder()
            .type(NotificationType.ORDER_CONFIRMATION)
            .notificationDate(LocalDateTime.now())
            .orderConfirmation(orderConfirmation)
            .build()
        );

        // send email
        var customerName = orderConfirmation.customer().firstname() + 
                " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
            orderConfirmation.customer().email(),
            customerName,
            orderConfirmation.totalAmount(),
            orderConfirmation.orderReference(),
            orderConfirmation.products()
        );
    }
}
