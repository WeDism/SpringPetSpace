package com.pet_space.models.essences;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.userEssence",
                joinColumns = @JoinColumn(name = "user_essence_id")),
        @AssociationOverride(name = "primaryKey.friend",
                joinColumns = @JoinColumn(name = "friend_id"))})
public class Friends implements Serializable {
    private FriendId primaryKey = new FriendId();
    private StateFriend state;

    @EmbeddedId
    public FriendId getPrimaryKey() {
        return this.primaryKey;
    }

    public Friends setPrimaryKey(FriendId friendId) {
        this.primaryKey = friendId;
        return this;
    }

    @ManyToOne()
    @JoinColumn(name = "state")
    public StateFriend getState() {
        return this.state;
    }

    public Friends setState(StateFriend state) {
        this.state = state;
        return this;
    }

    @Transient
    public UserEssence getUserEssence() {
        return this.primaryKey.getUserEssence();
    }

    public Friends setUserEssence(UserEssence userEssence) {
        this.primaryKey.setUserEssence(userEssence);
        return this;
    }

    @Transient
    public UserEssence getFriend() {
        return this.primaryKey.getFriend();
    }

    public Friends setFriend(UserEssence userEssence) {
        this.primaryKey.setFriend(userEssence);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friends)) return false;
        Friends friends = (Friends) o;
        return Objects.equals(getPrimaryKey(), friends.getPrimaryKey());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPrimaryKey());
    }
}
