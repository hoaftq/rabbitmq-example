package com.example.pubasyncrespsservice1;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Server {

    @RabbitListener(queues = "pub-async-resps.queue1")
    @SendTo("pub-async-resps.reply-to.1")
    public Message receiveFromQueue1(Message message) throws InterruptedException {
        return processMessage(message, "Queue1");
    }

    @RabbitListener(queues = "pub-async-resps.queue2")
    @SendTo("pub-async-resps.reply-to.2")
    public Message receiveFromQueue2(Message message) throws InterruptedException {
        return processMessage(message, "Queue2");
    }

    private static Message processMessage(Message message, String queueName) throws InterruptedException {
        Thread.sleep((long) (Math.random() * 5000));
        var replyMessage = queueName + ": " + new String(message.getBody());
        return MessageBuilder.withBody(replyMessage.getBytes())
                .setCorrelationId(message.getMessageProperties().getCorrelationId())
                .build();
    }
}
