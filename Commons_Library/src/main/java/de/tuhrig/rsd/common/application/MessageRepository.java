package de.tuhrig.rsd.common.application;

public interface MessageRepository {
    Message findUnprocessedMessages(String listenerName);
    void save(Message message);
}