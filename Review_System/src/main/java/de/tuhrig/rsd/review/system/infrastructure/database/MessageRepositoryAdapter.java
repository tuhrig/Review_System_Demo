package de.tuhrig.rsd.review.system.infrastructure.database;

import de.tuhrig.rsd.common.application.Message;
import de.tuhrig.rsd.common.application.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Adapter that protects the domain layer from Spring Data specific implementations.
 */
@Component
@AllArgsConstructor
public class MessageRepositoryAdapter implements MessageRepository {

    private final MessageSpringDataRepository springDataRepository;
    private final MessageEntityMapper messageEntityMapper;

    @Override
    public Message findUnprocessedMessages(String listenerName) {
        MessageEntity messageEntity =  springDataRepository.findFirstByListenerAndSuccessAndExecutionCountLessThan(listenerName, false, 2);
        return messageEntityMapper.toDomain(messageEntity);
    }

    @Override
    public void save(Message message) {
        MessageEntity messageEntity = messageEntityMapper.fromDomain(message);
        springDataRepository.save(messageEntity);
    }
}