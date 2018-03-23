package com.pet_space.jstl_tags.functions;

import com.pet_space.models.essences.Friends;
import com.pet_space.models.essences.UserEssence;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

public class GetFriendsFunction {
    private static final Logger LOG = getLogger(GetFriendsFunction.class);

    public static Set<Friends> getFriends(UserEssence userEssence) {
        Set<Friends> friendHashMap = new HashSet<>();
        friendHashMap.addAll(userEssence.getRequestedFriendsTo());
        friendHashMap.addAll(userEssence.getRequestedFriendsFrom());
        return friendHashMap;
    }
}
