package com.example.producer.message

import com.example.producer.Event
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component
import javax.jms.Queue

@Component
class MessageProducer(val jmsTemplate: JmsTemplate, val queue: Queue){
    fun sendMessageToDestination(message: Event){
        jmsTemplate.convertAndSend(queue, message)
    }
}





