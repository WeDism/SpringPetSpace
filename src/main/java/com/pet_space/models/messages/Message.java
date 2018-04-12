package com.pet_space.models.messages;

import com.pet_space.models.essences.UserEssence;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Message implements Serializable {
    private UUID messageId;
    @NotBlank
    private String text;
    private LocalDateTime date;
    private UserEssence author;
    //TODO    @NotEmpty
    private List<MessageOfUser> owners = new ArrayList<>();

    public Message() {
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "message_id", columnDefinition = "uuid")
    public UUID getMessageId() {
        return this.messageId;
    }

    public Message setMessageId(UUID messageId) {
        this.messageId = messageId;
        return this;
    }

    public String getText() {
        return this.text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public Message setDate(LocalDateTime time) {
        this.date = time;
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    public UserEssence getAuthor() {
        return this.author;
    }

    public Message setAuthor(UserEssence author) {
        this.author = author;
        return this;
    }

    @OneToMany(mappedBy = "primaryKey.message", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<MessageOfUser> getOwners() {
        return this.owners;
    }

    public Message setOwners(List<MessageOfUser> owners) {
        this.owners = owners;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message that = (Message) o;
        return Objects.equals(this.getMessageId(), that.getMessageId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getMessageId());
    }


}
