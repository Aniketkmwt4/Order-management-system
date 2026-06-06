package com.aniket.order_management.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {


    @KafkaListener(
            topics = "order-topic",
            groupId = "order-group"
    )
    public void consumerOrderEvent(String message){
        System.out.println("Order event Recived: "+message);
    }
}
