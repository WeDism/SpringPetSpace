package com.pet_space.repositories;

import com.pet_space.models.essences.StateFriend;

public interface StateFriendRepositoryTestData {
    StateFriend STATE_FRIEND_APPROVED = StateFriend.of(StateFriend.StateFriendEnum.APPROVED);
    StateFriend STATE_FRIEND_REQUESTED = StateFriend.of(StateFriend.StateFriendEnum.REQUESTED);
    StateFriend STATE_FRIEND_REJECTED = StateFriend.of(StateFriend.StateFriendEnum.REJECTED);
}
