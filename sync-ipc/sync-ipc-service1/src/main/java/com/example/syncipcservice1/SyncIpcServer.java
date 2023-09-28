package com.example.syncipcservice1;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SyncIpcServer {

    @RabbitListener(queues = "sync-ipc.request")
    public Object receiveMessage(String message) throws InterruptedException {
        Thread.sleep(1000);
        return "Hello " + message;
    }
}
