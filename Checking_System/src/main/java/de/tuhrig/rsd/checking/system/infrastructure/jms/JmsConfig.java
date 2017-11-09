package de.tuhrig.rsd.checking.system.infrastructure.jms;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
@EnableJms
@ImportAutoConfiguration(classes = {
        JmsAutoConfiguration.class,
        ActiveMQAutoConfiguration.class
})
public class JmsConfig {

    @Value("${destinationName.publish.events:VirtualTopic.Events}")
    private String eventTopic;

    @Bean
    public JmsEventPublisher eventPublisher(JmsTemplate jmsTemplate) {
        return new JmsEventPublisher(jmsTemplate, new ActiveMQTopic(eventTopic));
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JmsMessageConverter();
    }
}