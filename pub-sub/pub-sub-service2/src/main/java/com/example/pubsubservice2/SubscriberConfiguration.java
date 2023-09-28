package com.example.pubsubservice2;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriberConfiguration {

    @Bean
    Queue queue1() {
        return new Queue("pub-sub.queue1", true, false, true);
    }

    @Bean
    Queue queue2() {
        return new Queue("pub-sub.queue2", true, false, true);
    }

    @Bean
    ConnectionFactory connectionFactory() {
        var connectionFactory = new CachingConnectionFactory("localhost", 5673);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }
}
