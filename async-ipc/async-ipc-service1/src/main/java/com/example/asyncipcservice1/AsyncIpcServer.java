package com.example.asyncipcservice1;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class AsyncIpcServer {

    @RabbitListener(queues = "async-ipc.request")
    @SendTo("async-ipc.reply-to")
    public Message receive(Message message) throws InterruptedException {
        // Simulate some logics
        Thread.sleep((int)(5000 * Math.random()));

        var messageContent = String.format("Hello %s", new String(message.getBody()));
        var correlationId = message.getMessageProperties().getCorrelationId();
        return MessageBuilder.withBody(messageContent.getBytes())
                .setCorrelationId(correlationId)
                .build();
    }
}
