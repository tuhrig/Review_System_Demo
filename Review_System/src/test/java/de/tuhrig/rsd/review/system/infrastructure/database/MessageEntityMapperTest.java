package de.tuhrig.rsd.review.system.infrastructure.database;

import de.tuhrig.rsd.common.application.Message;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class MessageEntityMapperTest {

    private MessageEntityMapper messageEntityMapper = new MessageEntityMapper();

    @Test
    public void should_ReturnNull_IfNullIsPassed_WhenMappingToDomain() {
        Message result = messageEntityMapper.toDomain(null);
        assertThat(result).isNull();
    }

    @Test
    public void should_ReturnNull_IfNullIsPassed_WhenMappingFromDomain() {
        MessageEntity result = messageEntityMapper.fromDomain(null);
        assertThat(result).isNull();
    }

    @Test
    public void should_MapToDomain() {

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId("123-abc");
        messageEntity.setContent("{}");
        messageEntity.setExecutionCount(1);
        messageEntity.setSuccess(true);
        messageEntity.setType("MyEvent");
        messageEntity.setListener("MyEventListener");
        messageEntity.setErrorMessage("NPE");

        Message message = messageEntityMapper.toDomain(messageEntity);

        assertThat(message.getId()).isEqualTo("123-abc");
        assertThat(message.getContent()).isEqualTo("{}");
        assertThat(message.getExecutionCount()).isEqualTo(1);
        assertThat(message.isSuccess()).isEqualTo(true);
        assertThat(message.getType()).isEqualTo("MyEvent");
        assertThat(message.getListener()).isEqualTo("MyEventListener");
        assertThat(message.getErrorMessage()).isEqualTo("NPE");
    }

    @Test
    public void should_MapFromDomain() {

        Message message = Message.builder()
                                 .id("123-abc")
                                 .content("{}")
                                 .executionCount(1)
                                 .success(true)
                                 .type("MyEvent")
                                 .listener("MyEventListener")
                                 .errorMessage("NPE")
                                 .build();

        MessageEntity messageEntity = messageEntityMapper.fromDomain(message);

        assertThat(messageEntity.getId()).isEqualTo("123-abc");
        assertThat(messageEntity.getContent()).isEqualTo("{}");
        assertThat(messageEntity.getExecutionCount()).isEqualTo(1);
        assertThat(messageEntity.isSuccess()).isEqualTo(true);
        assertThat(messageEntity.getType()).isEqualTo("MyEvent");
        assertThat(messageEntity.getListener()).isEqualTo("MyEventListener");
        assertThat(messageEntity.getErrorMessage()).isEqualTo("NPE");
    }
}