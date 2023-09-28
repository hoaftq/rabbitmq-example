package com.example.asyncipcservice2;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("async-ipc.exchange", true, true);
    }

    @Bean
    Queue requestQueue() {
        return new Queue("async-ipc.request", true);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(requestQueue())
                .to(exchange())
                .with("async-ipc");
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
