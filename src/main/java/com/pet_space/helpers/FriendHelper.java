package com.pet_space.helpers;

import com.pet_space.models.essences.Friends;
import com.pet_space.models.essences.StateFriend;
import com.pet_space.models.essences.UserEssence;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.pet_space.models.essences.StateFriend.StateFriendEnum.*;

public final class FriendHelper {
    private FriendHelper() {
    }

    public static Set<UserEssence> getApprovedFriends(UserEssence user) {
        return FriendHelper.getAllFriendsWithConcreteState(user, StateFriend.of(APPROVED));
    }

    public static Set<UserEssence> getRequestedFriends(UserEssence user) {
        return FriendHelper.getAllFriendsWithConcreteState(user, StateFriend.of(REQUESTED));
    }

    public static Set<UserEssence> getRejectedFriends(UserEssence user) {
        return FriendHelper.getAllFriendsWithConcreteState(user, StateFriend.of(REJECTED));
    }

    private static Set<UserEssence> getAllFriendsWithConcreteState(UserEssence user, StateFriend stateFriend) {
        Set<UserEssence> userEssences = new HashSet<>();
        Predicate<Friends> predicate = e -> e.getState().equals(stateFriend);
        userEssences.addAll(user.getRequestedFriendsFrom().stream()
                .filter(predicate)
                .map(Friends::getFriend)
                .collect(Collectors.toSet()));
        userEssences.addAll(user.getRequestedFriendsTo().stream()
                .filter(predicate)
                .map(Friends::getUserEssence)
                .collect(Collectors.toSet()));
        return userEssences;
    }

}
