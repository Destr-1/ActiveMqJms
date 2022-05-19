package com.example.producer

import com.example.producer.db.EventRepository
import com.example.producer.message.MessageProducer
import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jms.annotation.EnableJms
import org.springframework.scheduling.annotation.Scheduled


@SpringBootApplication
@EnableJms
class SpringJmsApacheActiveMqApp(
    @Qualifier("eventRepository") private val repository: EventRepository,
    val messageProducer: MessageProducer
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        sendNew()
    }

    @Scheduled(cron = "0 0 * * * *")
    fun sendNew() {
        val evs = repository.findAllByStatus(EventStatus.NEW)
        for (ev in evs) {
            messageProducer.sendMessageToDestination(ev)
            val id = ev.id
            repository.updateStatus(id, EventStatus.IN_PROGRESS)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringJmsApacheActiveMqApp>(*args)
}



