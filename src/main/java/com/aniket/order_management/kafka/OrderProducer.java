package com.aniket.order_management.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final String TOPIC = "order-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //Kafka async producer
    @Async
    public void sendOrderEvent(String message) {
        try {
            System.out.println("Sending Kafka event: " + message);
            kafkaTemplate.send(TOPIC, message);
        } catch (Exception e) {
            System.out.println("Kafka not available: " + e.getMessage());
        }
    }
}