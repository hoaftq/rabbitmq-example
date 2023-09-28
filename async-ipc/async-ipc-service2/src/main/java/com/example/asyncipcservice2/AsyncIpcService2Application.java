package com.example.asyncipcservice2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class AsyncIpcService2Application {

    @Autowired
    private AsyncIpcClient client;

    private int index = 0;

    public static void main(String[] args) {
        SpringApplication.run(AsyncIpcService2Application.class, args);
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        client.sendMessage("Peter " + index++);
    }
}
