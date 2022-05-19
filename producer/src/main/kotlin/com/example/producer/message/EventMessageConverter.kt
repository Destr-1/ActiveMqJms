package com.example.producer.message


import com.example.producer.Event
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.stereotype.Component
import javax.jms.JMSException
import javax.jms.Message
import javax.jms.Session
import javax.jms.TextMessage


@Component
class EventMessageConverter : MessageConverter {
    var mapper: ObjectMapper = ObjectMapper()

    companion object {
        private val LOGGER = LoggerFactory.getLogger(EventMessageConverter::class.java)
    }

    @Throws(JMSException::class)
    override fun toMessage(`object`: Any, session: Session): Message {
        if (`object` !is Event)
            throw IllegalArgumentException("must be Event")
        val event: Event = `object` as Event
        var payload: String? = null
        try {
            payload = mapper.writeValueAsString(event)
            LOGGER.info("outbound json='{}'", payload)
        } catch (e: JsonProcessingException) {
            LOGGER.error("error converting from event", e)
        }
        val message = session.createTextMessage()
        message.text = payload
        return message
    }

    @Throws(JMSException::class)
    override fun fromMessage(message: Message): Event {
        val textMessage = message as TextMessage
        val payload = textMessage.text
        LOGGER.info("inbound json='{}'", payload)
        var event: Event? = null
        try {
            event = mapper.readValue(payload, Event::class.java)
        } catch (e: Exception) {
            LOGGER.error("error converting to event", e)
        }
        return event!!
    }
}