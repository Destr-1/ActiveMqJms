package com.example.producer.db

import com.example.producer.Event
import com.example.producer.EventType
import com.example.producer.EventStatus
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface EventRepository : CrudRepository<Event, Long> {
    @Modifying
    @Transactional
    @Query("update Event e set e.status = :status where e.id = :id")
    fun updateStatus(@Param("id") id: Int, @Param("status") status: EventStatus)

    override fun findAll(): List<Event>

    fun findById(id: Int): Event

    fun findAllByStatus(type: EventStatus): List<Event>
}

