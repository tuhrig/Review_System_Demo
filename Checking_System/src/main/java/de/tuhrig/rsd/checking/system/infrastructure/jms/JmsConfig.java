package de.tuhrig.rsd.checking.system.infrastructure.jms;

import de.tuhrig.rsd.common.infrastructure.jms.JmsEventPublisher;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
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

import javax.jms.ConnectionFactory;

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

    /**
     * We can configure ActiveMQ to redeliver failed messages automatically. The
     * configuration can contain properties such as a delay or the number of retries.
     * However, for testing purposes in this demo, we want to turn of the default
     * redelivery policy and don't do any redelivery.
     *
     * Note that for production a redelivery policy might be very useful!
     *
     * @return Turned off redelivery policy for demo purposes
     */
    @Bean
    public RedeliveryPolicy redeliveryPolicy() {
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(0);

        // Note: We must set the queue name here. This name will be part of the error message
        // shown in ActiveMQ for a failed message. If we don't set the queue name, we cannot
        // see which listener has failed.
        redeliveryPolicy.setQueue("Consumer.checking_system.VirtualTopic.Events");
        return redeliveryPolicy;
    }

    @Bean
    public ConnectionFactory connectionFactory(@Value("${spring.activemq.user}") String username,
                                               @Value("${spring.activemq.password}") String password,
                                               @Value("${spring.activemq.broker-url}") String brokerUrl) {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, brokerUrl);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        connectionFactory.setWatchTopicAdvisories(false);
        return connectionFactory;
    }
}