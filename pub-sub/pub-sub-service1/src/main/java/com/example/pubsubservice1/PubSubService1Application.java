package com.example.pubsubservice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class PubSubService1Application {

    @Autowired
    private Publisher publisher;

    private int index = 0;

    public static void main(String[] args) {
        SpringApplication.run(PubSubService1Application.class, args);
    }

    @Scheduled(fixedDelay = 2000)
    public void publishMessage() {
        publisher.publish("Rabbit " + index++);
    }
}