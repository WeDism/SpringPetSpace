package com.pet_space.repositories;

import com.pet_space.models.essences.FriendId;
import com.pet_space.models.essences.Friends;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

public interface FriendsRepository extends CrudRepository<Friends, FriendId> {

    @Cacheable("userEssence")
    @Override
    Friends findOne(FriendId friendId);

    @Cacheable("userEssence")
    @Override
    boolean exists(FriendId friendId);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    <S extends Friends> S save(S s);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    <S extends Friends> Iterable<S> save(Iterable<S> iterable);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    void delete(FriendId friendId);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    void delete(Friends friends);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    void delete(Iterable<? extends Friends> iterable);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    void deleteAll();
}
