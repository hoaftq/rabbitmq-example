package com.example.pubasyncrespsservice2;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("pub-async-resps.exchange");
    }

    @Bean
    Queue queue1() {
        return new Queue("pub-async-resps.queue1", true);
    }

    @Bean
    Queue queue2() {
        return new Queue("pub-async-resps.queue2", true);
    }

    @Bean
    Binding binding1() {
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }

    @Bean
    Binding binding2() {
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }

    @Bean
    Queue replyToQueue1() {
        return new Queue("pub-async-resps.reply-to.1", true, false, true);
    }

    @Bean
    Queue replyToQueue2() {
        return new Queue("pub-async-resps.reply-to.2", true, false, true);
    }
}
