package com.example.pubasyncrespsservice1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {

    @Bean
    Queue queue1() {
        return new Queue("pub-async-resps.queue1", true);
    }

    @Bean
    Queue queue2() {
        return new Queue("pub-async-resps.queue2", true);
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
