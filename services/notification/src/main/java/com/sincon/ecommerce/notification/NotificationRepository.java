package com.sincon.ecommerce.notification;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sincon.ecommerce.kafka.payment.PaymentConfirmation;

public interface NotificationRepository extends MongoRepository<PaymentConfirmation, String> {

}
