package com.example.producer

import com.example.producer.db.EventRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.util.*

open class EventRepositoryImpl : EventRepository {
    @Modifying
    @Transactional
    @Query("update Event e set e.status = :status where e.id = :id")
    override fun updateStatus(@Param("id") id: Int, @Param("status") status : EventStatus) {
    }

    override fun findAll():List<Event> =  this.findAll()


    override fun findById(id: Int): Event = this.findById(id)

    override fun findAllByStatus(type: EventStatus): List<Event> = this.findAllByStatus(type)

    override fun <S : Event?> save(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun <S : Event?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Long>): MutableIterable<Event> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Event) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Long>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<Event>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long): Optional<Event> {
        TODO("Not yet implemented")
    }
}