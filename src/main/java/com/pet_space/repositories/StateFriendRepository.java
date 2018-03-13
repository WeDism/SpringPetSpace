package com.pet_space.repositories;

import com.pet_space.models.essences.StateFriend;
import org.springframework.data.repository.CrudRepository;

public interface StateFriendRepository extends CrudRepository<StateFriend, StateFriend.StateFriendEnum> {
}
