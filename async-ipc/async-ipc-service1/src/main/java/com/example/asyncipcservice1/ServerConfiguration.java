package com.example.asyncipcservice1;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {

    @Bean
    Queue requestQueue() {
        return new Queue("async-ipc.request", true);
    }

    @Bean
    Queue replyQueue() {
        return new Queue("async-ipc.reply-to", true, false, true);
    }

    @Bean
    ConnectionFactory connectionFactory() {
        var factory = new CachingConnectionFactory("localhost", 5673);
        factory.setUsername("guest");
        factory.setPassword("guest");
        return factory;
    }
}
