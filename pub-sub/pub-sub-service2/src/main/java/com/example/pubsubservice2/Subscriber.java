package com.example.pubsubservice2;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Subscriber {

    @RabbitListener(queues = "pub-sub.queue1")
    public void receiveFromQueue1(String message) {
        System.out.println("-Queue1: " + message);
    }

    @RabbitListener(queues = "pub-sub.queue2")
    public void receiveFromQueue2(String message) {
        System.out.println("*Queue2: " + message);
    }
}
