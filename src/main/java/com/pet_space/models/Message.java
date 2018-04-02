package com.pet_space.models;

import com.pet_space.models.essences.UserEssence;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Message {
    private UUID messageId;
    private String text;
    private LocalDateTime date;
    private UserEssence author;
    private Set<UserEssence> owners = new HashSet<>();

    public Message() {
    }

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "message_of_user",
            joinColumns = {@JoinColumn(name = "message_id")}, inverseJoinColumns = {@JoinColumn(name = "owner_id")},
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    public Set<UserEssence> getOwners() {
        return this.owners;
    }

    public Message setOwners(Set<UserEssence> owners) {
        this.owners = owners;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(this.getMessageId(), message.getMessageId());
    }

    @Override
    public int hashCode() {
        return this.getMessageId().hashCode();
    }


}
