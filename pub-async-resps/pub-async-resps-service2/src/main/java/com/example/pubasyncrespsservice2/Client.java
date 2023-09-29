package com.example.pubasyncrespsservice2;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class Client {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanoutExchange;

    private final Map<String, String[]> responses = new HashMap<>();

    public void sendMessage(String messageContent) {
        var correlationId = UUID.randomUUID().toString();
        var message = MessageBuilder.withBody(messageContent.getBytes())
                .setCorrelationId(correlationId)
                .build();

        System.out.println("Sending: " + messageContent);
        responses.put(correlationId, new String[]{messageContent, null, null});
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
    }

    @RabbitListener(queues = "pub-async-resps.reply-to.1")
    private void receiveReply1(Message message) {
        processReply(message, 1);
    }

    @RabbitListener(queues = "pub-async-resps.reply-to.2")
    private void receiveReply2(Message message) {
        processReply(message, 2);
    }

    private void processReply(Message message, int queueIndex) {
        var reqRespArr = responses.get(message.getMessageProperties().getCorrelationId());
        if (reqRespArr == null) {
            System.out.println("Ignore message: " + new String(message.getBody()));
            return;
        }

        reqRespArr[queueIndex] = new String(message.getBody());
        checkAndProcessResponse(reqRespArr);
    }


    private void checkAndProcessResponse(String[] reqRespArr) {
        if (reqRespArr[1] == null || reqRespArr[2] == null) {
            return;
        }

        System.out.printf("Getting: %s - %s - %s\n", reqRespArr[0], reqRespArr[1], reqRespArr[2]);
    }
}
