package de.tuhrig.rsd.common.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * This class represents a generic message received by the system (for
 * example via JMS). This message can be an event or command. Besides
 * the actual (JSON) content of the message, this class contains meta
 * information which are used to invoke the corresponding handler to
 * take care of the message.
 */
@Getter
@Slf4j
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) // For the builder!
@EqualsAndHashCode(of = "id")
public class Message {

    @NonNull
    private String id; // ID of the message, e.g. the JMS message ID

    @NonNull
    private String type; // type of the message, e.g. "ReviewSubmittedEvent"

    @NonNull
    private String content; // actual JSON content, e.g. "{ ... }"

    @NonNull
    private String listener; // listener which waits for the message

    @NonNull
    private int executionCount; // number of tries of execution of the listener

    @NonNull
    private boolean success; // flag which indicates a successful execution

    // might be null if error occurs when
    // executing the message on the listener
    private String errorMessage;

    public static Message createNew(String id,
                                    String content,
                                    String type,
                                    String listener) {
        return Message
                .builder()
                .id(id)
                .type(type)
                .content(content)
                .listener(listener)
                .success(false)
                .executionCount(0)
                .build();
    }

    public void increaseExecutionCount() {
        executionCount++;
        log.trace("Message about to be executed. [listener={}, event={}, executionCount={}]", listener, type, executionCount);
    }

    public void succeeded() {
        this.success = true;
        this.errorMessage = null;
        log.trace("Message handled successfully. [listener={}, event={}]", listener, type);
    }

    public void failed(Exception exception) {
        this.success = false;
        this.errorMessage = exception.getMessage();
        log.warn("Failed to handle message. [listener={}, event={}]", listener, type, exception);
    }
}