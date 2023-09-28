package com.example.pubsubservice1;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfiguration {

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("pub-sub.exchange");
    }

    @Bean
    Queue queue1() {
        return new Queue("pub-sub.queue1", true, false, true);
    }

    @Bean
    Queue queue2() {
        return new Queue("pub-sub.queue2", true, false, true);
    }

    @Bean
    Binding binding1() {
        return BindingBuilder.bind(queue1()).to(exchange());
    }

    @Bean
    Binding binding2() {
        return BindingBuilder.bind((queue2())).to(exchange());
    }

    @Bean
    ConnectionFactory connectionFactory() {
        var connectionFactory = new CachingConnectionFactory("localhost", 5673);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }
}
