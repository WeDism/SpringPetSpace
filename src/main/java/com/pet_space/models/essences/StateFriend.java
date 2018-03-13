package com.pet_space.models.essences;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "state_friend")
public class StateFriend implements Serializable {
    public enum StateFriendEnum {
        REQUESTED, REJECTED, APPROVED
    }

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private StateFriendEnum stateFriendEnum;

    public StateFriend() {
    }

    public StateFriend(StateFriendEnum stateFriendEnum) {
        super();
        this.stateFriendEnum = stateFriendEnum;
    }

    public StateFriendEnum getStateFriendEnum() {
        return this.stateFriendEnum;
    }

    public StateFriend setStateFriendEnum(StateFriendEnum stateFriendEnum) {
        this.stateFriendEnum = stateFriendEnum;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateFriend)) return false;
        StateFriend that = (StateFriend) o;
        return getStateFriendEnum() == that.getStateFriendEnum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStateFriendEnum());
    }
}