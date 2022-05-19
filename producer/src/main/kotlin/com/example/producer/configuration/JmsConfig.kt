package com.example.producer.configuration


import org.apache.activemq.command.ActiveMQQueue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import javax.jms.Queue

@Configuration
@EnableJms
class JmsConfig{
    @Bean
    fun queue() : Queue {
        return ActiveMQQueue("jms-queue")
    }
}
