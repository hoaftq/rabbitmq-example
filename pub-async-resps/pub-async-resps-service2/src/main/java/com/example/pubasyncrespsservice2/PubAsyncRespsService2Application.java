package com.example.pubasyncrespsservice2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class PubAsyncRespsService2Application {

    @Autowired
    private Client client;

    private int index = 0;

    public static void main(String[] args) {
        SpringApplication.run(PubAsyncRespsService2Application.class, args);
    }

    @Scheduled(fixedDelay = 2000)
    private void sendMessages() {
        client.sendMessage("Rabbit " + index++);
    }
}
