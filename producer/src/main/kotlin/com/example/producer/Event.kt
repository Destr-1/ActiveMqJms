package com.example.producer

import javax.persistence.*

@Table(name = "events")
@Entity
@SequenceGenerator(allocationSize = 1, name ="event_seq", sequenceName = "event_seq")
data class Event(
    @Id
    @GeneratedValue(generator = "event_seq")
    @Column(name="id")
    var id: Int,

    @Column(name = "type", columnDefinition = "enum", nullable = false)
    @Enumerated(EnumType.STRING)
    var type:EventType,

    @Column(name="body")
    var body:String,

    @Column(name = "status", columnDefinition = "enum", nullable = false)
    var status: EventStatus
)
