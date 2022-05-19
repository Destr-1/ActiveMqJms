package com.example.producer.message

import com.example.producer.Event
import com.example.producer.EventType
import com.example.producer.sender.EmailServiceSender
import com.example.producer.sender.ErrorSender
import com.example.producer.sender.PushSender
import com.example.producer.sender.SmsSender
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component


@Component
class MessageConsumer(
    val smsSender: SmsSender,
    val pushSender: PushSender,
    val emailServiceSender: EmailServiceSender,
    val errorSender: ErrorSender
) {
    @JmsListener(destination = "jms-queue")
    fun listener(message: Event) {
        when (message.type) {
            EventType.SMS -> smsSender.send(message)
            EventType.EMAIL -> emailServiceSender.send(message)
            EventType.PUSH -> pushSender.send(message)
            else -> errorSender.send(message)
        }
    }
}


