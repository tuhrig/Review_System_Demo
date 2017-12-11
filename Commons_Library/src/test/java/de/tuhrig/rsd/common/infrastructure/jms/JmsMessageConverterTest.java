package de.tuhrig.rsd.common.infrastructure.jms;

import de.tuhrig.rsd.common.messaging.events.ReviewApprovedEvent;
import de.tuhrig.rsd.common.messaging.events.ReviewRejectedEvent;
import de.tuhrig.rsd.common.messaging.events.ReviewSubmittedEvent;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.support.converter.MessageConversionException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class JmsMessageConverterTest {

    private JmsMessageConverter jmsMessageConverter;

    @Before
    public void setUp() throws Exception {
        jmsMessageConverter = new JmsMessageConverter();
    }

    @Test(expected = MessageConversionException.class)
    public void should_ThrowException_IfTypeIsMissing() throws Exception {
        Message messageWithoutType = new ActiveMQTextMessage();
        jmsMessageConverter.fromMessage(messageWithoutType);
    }

    @Test
    public void should_Map_ReviewSubmittedEvent_ToTextMessageWithJson() throws JMSException {
        ReviewSubmittedEvent event = new ReviewSubmittedEvent();
        event.setReviewId("some-review-id");
        event.setRating(5);
        event.setSubject("A review");
        event.setContent("This is some content");

        TextMessage message = (TextMessage) jmsMessageConverter.toMessage(event, session());

        assertThat(message.getText()).isEqualTo("{\"reviewId\":\"some-review-id\",\"subject\":\"A review\",\"content\":\"This is some content\",\"rating\":5}");
        assertThat(message.getStringProperty("_type")).isEqualTo("REVIEW_SUBMITTED_EVENT");
    }

    @Test
    public void should_Map_ReviewApprovedEvent_ToTextMessageWithJson() throws JMSException {
        ReviewApprovedEvent event = new ReviewApprovedEvent();
        event.setReviewId("some-review-id");

        TextMessage message = (TextMessage) jmsMessageConverter.toMessage(event, session());

        assertThat(message.getText()).isEqualTo("{\"reviewId\":\"some-review-id\"}");
        assertThat(message.getStringProperty("_type")).isEqualTo("REVIEW_APPROVED_EVENT");
    }

    @Test
    public void should_Map_ReviewRejectedEvent_ToTextMessageWithJson() throws JMSException {
        ReviewRejectedEvent event = new ReviewRejectedEvent();
        event.setReviewId("some-review-id");
        event.setReason("My reason");

        TextMessage message = (TextMessage) jmsMessageConverter.toMessage(event, session());

        assertThat(message.getText()).isEqualTo("{\"reviewId\":\"some-review-id\",\"reason\":\"My reason\"}");
        assertThat(message.getStringProperty("_type")).isEqualTo("REVIEW_REJECTED_EVENT");
    }

    private Session session() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }
}