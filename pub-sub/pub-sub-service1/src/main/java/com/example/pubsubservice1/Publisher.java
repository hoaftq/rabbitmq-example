package com.example.pubsubservice1;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange exchange;

    public void publish(String message) {
        template.convertAndSend(exchange.getName(), "", message);
    }
}
