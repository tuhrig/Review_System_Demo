package de.tuhrig.rsd.checking.system.infrastructure.jms;

import de.tuhrig.rsd.checking.system.domain.ReviewApprovedEvent;
import de.tuhrig.rsd.checking.system.domain.ReviewRejectedEvent;
import de.tuhrig.rsd.checking.system.domain.ReviewSubmittedEvent;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.HashMap;
import java.util.Map;

public class JmsMessageConverter implements MessageConverter {

    private final MappingJackson2MessageConverter jacksonMessageConverter = new MappingJackson2MessageConverter();

    public JmsMessageConverter() {

        // We want text messages with JSON. This is easily readable, for example
        // in the UI of the broker. The default would be a byte message.
        jacksonMessageConverter.setTargetType(MessageType.TEXT);
        jacksonMessageConverter.setTypeIdPropertyName("_type");
        jacksonMessageConverter.setTypeIdMappings(typeIdMappings());
    }

    private Map<String, Class<?>> typeIdMappings() {
        HashMap<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("REVIEW_SUBMITTED_EVENT", ReviewSubmittedEvent.class);
        typeIdMappings.put("REVIEW_APPROVED_EVENT", ReviewApprovedEvent.class);
        typeIdMappings.put("REVIEW_REJECTED_EVENT", ReviewRejectedEvent.class);
        return typeIdMappings;
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
