package com.example.asyncipcservice2;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AsyncIpcClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    public void sendMessage(String messageContent) {
        var message = MessageBuilder.withBody(messageContent.getBytes())
                .setCorrelationId(UUID.randomUUID().toString())
                .build();
        System.out.println("Sending message: " + message.getMessageProperties().getCorrelationId() + " : " + messageContent);
        rabbitTemplate.convertAndSend(directExchange.getName(), "async-ipc", message);
    }

    @RabbitListener(queues = "async-ipc.reply-to")
    public void receiveReply(Message message) {
        System.out.println("Getting message: " + message.getMessageProperties().getCorrelationId() + " : " + new String(message.getBody()));
    }
}
