package com.example.syncipcservice2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SyncIpcService2Application {
    @Autowired
    private SyncIpcClient client;

    private int index = 0;

    public static void main(String[] args) {
        SpringApplication.run(SyncIpcService2Application.class, args);
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessageAndGetReply() {
        var message = String.format("Rabbit %d", index++);

        System.out.println("Sending: " + message);
        var replyMessage = client.sendMessage(message);
        System.out.println("Getting: " + replyMessage);
    }
}
