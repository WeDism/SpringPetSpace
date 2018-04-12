package com.pet_space.models.messages;

import com.pet_space.models.essences.UserEssence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "message_of_user")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.owner",
                joinColumns = @JoinColumn(name = "owner_id")),
        @AssociationOverride(name = "primaryKey.message",
                joinColumns = @JoinColumn(name = "message_id"))})
public class MessageOfUser implements Serializable {
    private MessageOfUserId primaryKey = new MessageOfUserId();
    private MessageState state;

    public MessageOfUser() {
    }

    public MessageOfUser(MessageOfUserId primaryKey, MessageState state) {
        this();
        this.primaryKey = primaryKey;
        this.state = state;
    }

    @EmbeddedId
    public MessageOfUserId getPrimaryKey() {
        return primaryKey;
    }

    public MessageOfUser setPrimaryKey(MessageOfUserId primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    @ManyToOne()
    @JoinColumn(name = "state")
    public MessageState getState() {
        return state;
    }

    public MessageOfUser setState(MessageState state) {
        this.state = state;
        return this;
    }

    @Transient
    public UserEssence getUserEssence() {
        return this.primaryKey.getOwner();
    }

    public MessageOfUser setUserEssence(UserEssence userEssence) {
        this.primaryKey.setOwner(userEssence);
        return this;
    }

    @Transient
    public Message getMessage() {
        return this.primaryKey.getMessage();
    }

    public MessageOfUser setMessage(Message message) {
        this.primaryKey.setMessage(message);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageOfUser that = (MessageOfUser) o;
        return Objects.equals(this.getPrimaryKey(), that.getPrimaryKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getPrimaryKey());
    }
}

