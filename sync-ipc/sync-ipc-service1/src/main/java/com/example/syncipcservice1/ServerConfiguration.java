package com.example.syncipcservice1;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {

    @Bean
    Queue queue() {
        return new Queue("sync-ipc.request", true);
    }

    @Bean
    ConnectionFactory connectionFactory() {
        var connectionFactory = new CachingConnectionFactory("localhost", 5673);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }
}
