package de.tuhrig.rsd.review.system.ports.event;

import de.tuhrig.rsd.common.application.Message;
import de.tuhrig.rsd.common.application.MessageHandler;
import de.tuhrig.rsd.common.application.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;
import java.util.List;

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

                messageRepository.save(message);
            }
        }
    }
}