package com.pet_space.models.messages;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "message_state")
public class MessageState implements Serializable {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private MessageStateEnum messageStateEnum;

    public MessageState() {
    }

    public MessageState(MessageStateEnum messageStateEnum) {
        this();
        this.messageStateEnum = messageStateEnum;
    }

    public static MessageState of(MessageStateEnum messageStateEnum) {
        return new MessageState(messageStateEnum);
    }

    public MessageStateEnum getMessageStateEnum() {
        return messageStateEnum;
    }

    public MessageState setMessageStateEnum(MessageStateEnum messageStateEnum) {
        this.messageStateEnum = messageStateEnum;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageState that = (MessageState) o;
        return this.getMessageStateEnum() == that.getMessageStateEnum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getMessageStateEnum());
    }

    @Override
    public String toString() {
        return this.messageStateEnum.name();
    }

    public enum MessageStateEnum {
        NEW, VIEWED
    }
}
