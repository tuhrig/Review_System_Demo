package de.tuhrig.rsd.review.system.infrastructure.database;

import de.tuhrig.rsd.common.application.Message;
import de.tuhrig.rsd.common.utils.DomainObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class MessageEntityMapper implements DomainObjectMapper<Message, MessageEntity> {

    public Message toDomain(MessageEntity messageEntity) {

        if (messageEntity == null) {
            return null;
        }

        return Message
                .builder()
                .id(messageEntity.getId())
                .type(messageEntity.getType())
                .content(messageEntity.getContent())
                .listener(messageEntity.getListener())
                .success(messageEntity.isSuccess())
                .executionCount(messageEntity.getExecutionCount())
                .errorMessage(messageEntity.getErrorMessage())
                .build();
    }

    public MessageEntity fromDomain(Message message) {

        if (message == null) {
            return null;
        }

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(message.getId());
        messageEntity.setType(message.getType());
        messageEntity.setContent(message.getContent());
        messageEntity.setListener(message.getListener());
        messageEntity.setExecutionCount(message.getExecutionCount());
        messageEntity.setSuccess(message.isSuccess());
        messageEntity.setErrorMessage(message.getErrorMessage());

        return messageEntity;
    }
}
