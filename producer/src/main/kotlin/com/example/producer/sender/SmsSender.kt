package com.example.producer.sender

import com.example.producer.Event
import com.example.producer.EventStatus
import com.example.producer.db.EventRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class SmsSender(@Qualifier("eventRepository") override val repository: EventRepository) : Sender {
    override fun send(message: Event) {
        println("sms message. Body: ${message.body}, type: ${message.type}")
        val id = message.id
        repository.updateStatus(id, EventStatus.DONE )
    }
}