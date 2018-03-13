package com.pet_space.repositories;

import com.pet_space.models.essences.FriendId;
import com.pet_space.models.essences.Friends;
import org.springframework.data.repository.CrudRepository;

public interface FriendsRepository extends CrudRepository<Friends, FriendId> {
}
