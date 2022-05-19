package com.example.producer.sender

import com.example.producer.Event
import com.example.producer.db.EventRepository

interface Sender {
    val repository: EventRepository

    fun send(message: Event)
}