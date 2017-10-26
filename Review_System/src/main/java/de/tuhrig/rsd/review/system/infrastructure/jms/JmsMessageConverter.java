package de.tuhrig.rsd.review.system.infrastructure.jms;

import de.tuhrig.rsd.review.system.ports.event.ReviewApprovedEvent;
import de.tuhrig.rsd.review.system.ports.event.ReviewRejectedEvent;
import de.tuhrig.rsd.review.system.ports.event.ReviewSubmittedEvent;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.HashMap;

public class JmsMessageConverter implements MessageConverter {

    private final MappingJackson2MessageConverter jacksonMessageConverter = new MappingJackson2MessageConverter();

    public JmsMessageConverter() {
        jacksonMessageConverter.setTargetType(MessageType.TEXT);
        jacksonMessageConverter.setTypeIdPropertyName("_type");
        HashMap<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("REVIEW_SUBMITTED_EVENT", ReviewSubmittedEvent.class);
        typeIdMappings.put("REVIEW_APPROVED_EVENT", ReviewApprovedEvent.class);
        typeIdMappings.put("REVIEW_REJECTED_EVENT", ReviewRejectedEvent.class);
        jacksonMessageConverter.setTypeIdMappings(typeIdMappings);
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
