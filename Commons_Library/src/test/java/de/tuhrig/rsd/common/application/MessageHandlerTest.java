package de.tuhrig.rsd.common.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class MessageHandlerTest {

    private static boolean HANDLER_SHOULD_FAIL = false;

    private ObjectMapper objectMapper;
    private MessageRepository messageRepository;
    private MyMessageHandler myMessageHandler;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        messageRepository = new MessageStubRepository();
        myMessageHandler = new MyMessageHandler(messageRepository, objectMapper);
        HANDLER_SHOULD_FAIL = false;
    }

    @Test
    public void should_ReturnHandlerName() {
        String handlerName = myMessageHandler.getName();
        assertThat(handlerName).isEqualTo("MyMessageHandler");
    }

    @Test
    public void should_AcceptMessageType() {
        boolean accepts = myMessageHandler.accepts("MyMessage");
        assertThat(accepts).isTrue();
    }

    @Test
    public void should_NotAcceptUnknownMessageType() {
        boolean accepts = myMessageHandler.accepts("UnknownMessageType");
        assertThat(accepts).isFalse();
    }

    @Test
    public void should_IncreaseExecutionCount() {
        Message message = Message.createNew("123-abc", "{}", "MyMessage", "MyMessageHandler");
        messageRepository.save(message);
        myMessageHandler.checkForMessages();
        assertThat(message.getExecutionCount()).isEqualTo(1);
    }

    @Test
    public void should_SetMessageToSuccess() {
        Message message = Message.createNew("123-abc", "{}", "MyMessage", "MyMessageHandler");
        messageRepository.save(message);

        myMessageHandler.checkForMessages();

        assertThat(message.isSuccess()).isTrue();
        assertThat(message.getErrorMessage()).isNull();
    }

    @Test
    public void should_SetMessageToFail() {
        Message message = Message.createNew("123-abc", "{}", "MyMessage", "MyMessageHandler");
        messageRepository.save(message);
        HANDLER_SHOULD_FAIL = true;

        myMessageHandler.checkForMessages();

        assertThat(message.isSuccess()).isFalse();
        assertThat(message.getErrorMessage()).isEqualTo("Failed");
    }

    private static class MyMessageHandler extends MessageHandler<MyMessage> {

        public MyMessageHandler(MessageRepository messageRepository, ObjectMapper objectMapper) {
            super(messageRepository, objectMapper);
        }

        @Override
        public void onMessage(MyMessage message) {
            if (HANDLER_SHOULD_FAIL) {
                throw new RuntimeException("Failed");
            }
        }
    }

    private static class MyMessage {
        // stub
    }
}