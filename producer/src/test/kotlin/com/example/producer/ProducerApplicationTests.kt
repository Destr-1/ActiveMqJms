package com.example.producer

import com.example.producer.db.EventRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

@SpringBootTest
@AutoConfigureMockMvc
class ProducerApplicationTests() {
    @Qualifier("eventRepository")
    @Autowired
    private val repository:EventRepository = EventRepositoryImpl()


    @Test
    fun checkSizeAfterConsuming() {
        val ev = repository.findAll()
        assertEquals(5, ev.size)
    }

    @Test
    fun checkStatusAfterConsumingForNew(){
        val ev = repository.findAllByStatus(EventStatus.NEW)
        assertEquals(0, ev.size)
    }

    @Test
    fun checkStatusAfterConsumingForDone(){
        val ev = repository.findAllByStatus(EventStatus.DONE)
        assertEquals(5, ev.size)
    }




}
