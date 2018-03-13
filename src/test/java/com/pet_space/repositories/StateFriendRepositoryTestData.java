package com.pet_space.repositories;

import com.pet_space.models.essences.StateFriend;

public interface StateFriendRepositoryTestData {
    StateFriend STATE_FRIEND_APPROVED = new StateFriend(StateFriend.StateFriendEnum.APPROVED);
    StateFriend STATE_FRIEND_REQUESTED = new StateFriend(StateFriend.StateFriendEnum.REQUESTED);
    StateFriend STATE_FRIEND_REJECTED = new StateFriend(StateFriend.StateFriendEnum.REJECTED);
}
