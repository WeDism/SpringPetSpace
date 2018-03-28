package com.pet_space.models.essences;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FriendId implements Serializable {
    private UserEssence userEssence;
    private UserEssence friend;

    public FriendId() {
    }

    public FriendId(UserEssence userEssence, UserEssence friend) {
        this();
        this.userEssence = userEssence;
        this.friend = friend;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public UserEssence getUserEssence() {
        return this.userEssence;
    }

    public FriendId setUserEssence(UserEssence userEssence) {
        this.userEssence = userEssence;
        return this;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public UserEssence getFriend() {
        return this.friend;
    }

    public FriendId setFriend(UserEssence friend) {
        this.friend = friend;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendId)) return false;
        FriendId friendId = (FriendId) o;
        return Objects.equals(this.getUserEssence(), friendId.getUserEssence()) &&
                Objects.equals(this.getFriend(), friendId.getFriend());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserEssence().getUserEssenceId(), this.getFriend().getUserEssenceId());
    }
}
