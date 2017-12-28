package de.tuhrig.rsd.common.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.lang.reflect.ParameterizedType;

@NoArgsConstructor // For Spring DI
@AllArgsConstructor // In case someone would set the dependencies manually
public abstract class MessageHandler<T> {

    @Autowired
    protected MessageRepository messageRepository;

    @Autowired
    protected ObjectMapper objectMapper;

    public boolean accepts(String type) {
        return getAcceptedType().equals(type);
    }

    /**
     * Every handler accepts a certain message. This method will return
     * the accepted message type. This is the simple class name of the
     * accepted message.
     *
     * Example: If the handler accepts "de.tuhrig.MyDomainEvent.java"
     * then this message will return "MyDomainEvent".
     */
    private String getAcceptedType() {
        return getMessageClass().getSimpleName();
    }

    /**
     * Returns the name of the handler. This is the simple class
     * name of the concrete handler class.
     *
     * Example: de.tuhrig.MyHandler.java resolves to "MyHandler".
     *
     * @return the name of the handler
     */
    public String getName() {
        return getClass().getSimpleName();
    }

    public abstract void onMessage(T message);

    @SneakyThrows
    @Scheduled(fixedDelay = 100)
    public void checkForMessages() {

        Message message = messageRepository.findUnprocessedMessages(getName());

        if (message != null) {

            Class<T> eventClass = getMessageClass();
            message.increaseExecutionCount();

            try {
                String content = message.getContent();
                T reviewApprovedEvent = objectMapper.readValue(content, eventClass);
                onMessage(reviewApprovedEvent);
                message.succeeded();
            } catch (Exception e) {
                message.failed(e);
            }

            messageRepository.save(message);
        }
    }

    /**
     * @return Returns the class of the generic type T
     */
    private Class<T> getMessageClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
