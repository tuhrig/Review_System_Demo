package de.tuhrig.rsd.common.infrastructure.jms;

import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import static de.tuhrig.rsd.common.infrastructure.jms.TypeIdMappingFactory.getTypeIdMapping;

public class JmsMessageConverter implements MessageConverter {

    private final MappingJackson2MessageConverter jacksonMessageConverter = new MappingJackson2MessageConverter();

    public JmsMessageConverter() {

        // We want text messages with JSON. This is easily readable, for example
        // in the UI of the broker. The default would be a byte message.
        jacksonMessageConverter.setTargetType(MessageType.TEXT);
        jacksonMessageConverter.setTypeIdPropertyName("_type");
        jacksonMessageConverter.setTypeIdMappings(getTypeIdMapping());
    }

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        return jacksonMessageConverter.toMessage(object, session);
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        return jacksonMessageConverter.fromMessage(message);
    }
}
