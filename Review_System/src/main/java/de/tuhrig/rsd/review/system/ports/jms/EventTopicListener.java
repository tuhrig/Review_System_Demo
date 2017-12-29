package de.tuhrig.rsd.review.system.ports.jms;

import de.tuhrig.rsd.common.application.Message;
import de.tuhrig.rsd.common.application.MessageHandler;
import de.tuhrig.rsd.common.application.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;
import java.util.List;

/**
 * This listener listens directly on the central JMS message topic
 * in oder to receive new events.  In case an event is received,
 * it will check all internal message handlers. If a message handler
 * accepts the event, the event is imported and saved to the database.
 * Otherwise the message is ignored (if no handler accepts it).
 */
@Slf4j
@Component
@AllArgsConstructor
public class EventTopicListener {

    private MessageRepository messageRepository;
    private List<MessageHandler> eventHandlers;

    @SneakyThrows
    @JmsListener(destination = "VirtualTopic.Events", containerFactory = "topicFactory")
    public void onEvent(TextMessage textMessage) {

        for(MessageHandler eventHandler: eventHandlers) {

            String type = textMessage.getStringProperty("_type");
            if (eventHandler.accepts(type)) {

                Message message = Message.createNew(
                        textMessage.getJMSMessageID(),
                        textMessage.getText(),
                        type,
                        eventHandler.getName()
                );

                log.debug("Imported new message. [listener={}, type={}]", eventHandler.getName(), type);

                messageRepository.save(message);
            }
        }
    }
}