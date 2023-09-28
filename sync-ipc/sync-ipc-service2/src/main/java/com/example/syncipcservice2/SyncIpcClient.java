package com.example.syncipcservice2;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyncIpcClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    @Autowired
    private Binding binding;

    public String sendMessage(String message) {
        return (String) rabbitTemplate.convertSendAndReceive(exchange.getName(), binding.getRoutingKey(), message);
    }
}
