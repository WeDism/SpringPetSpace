package com.pet_space.models.messages;

import com.pet_space.models.essences.UserEssence;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MessageOfUserId implements Serializable {
    private UserEssence owner;
    private Message message;

    public MessageOfUserId() {
    }

    private MessageOfUserId(UserEssence owner, Message message) {
        this();
        this.owner = owner;
        this.message = message;
    }

    public static MessageOfUserId of(UserEssence owner, Message message) {
        return new MessageOfUserId(owner, message);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public UserEssence getOwner() {
        return owner;
    }

    public MessageOfUserId setOwner(UserEssence owner) {
        this.owner = owner;
        return this;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Message getMessage() {
        return message;
    }

    public MessageOfUserId setMessage(Message message) {
        this.message = message;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageOfUserId that = (MessageOfUserId) o;
        return Objects.equals(this.getOwner(), that.getOwner()) && Objects.equals(this.getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getOwner(), this.getMessage());
    }
}
