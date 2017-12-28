package de.tuhrig.rsd.common.application;

import java.util.HashSet;
import java.util.Set;

public class MessageStubRepository implements MessageRepository {

    private Set<Message> messages = new HashSet<>();

    @Override
    public Message findUnprocessedMessages(String listenerName) {
        return messages
                .stream()
                .filter(message -> message.getListener().equals(listenerName))
                .filter(message -> !message.isSuccess())
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Message message) {
        messages.add(message);
    }
}
