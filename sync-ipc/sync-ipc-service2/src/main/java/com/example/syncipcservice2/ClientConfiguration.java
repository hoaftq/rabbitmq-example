package com.example.syncipcservice2;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ClientConfiguration {

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("sync-ipc.exchange", true, true);
    }

    @Bean
    Queue queue() {
        return new Queue("sync-ipc.request", true);
    }

    @Bean
    Binding binding(Queue queue, Exchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), "sync_ipc", Map.of());
    }

    @Bean
    ConnectionFactory connectionFactory() {
        var connectionFactory = new CachingConnectionFactory("localhost", 5673);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }
}
