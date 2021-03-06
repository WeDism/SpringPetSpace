package com.pet_space.repositories;

import com.pet_space.models.essences.UserEssence;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserEssenceRepository extends CrudRepository<UserEssence, UUID> {

    @Query("from UserEssence ue join fetch ue.followByPets where ue.userEssenceId = :userEssenceId")
    UserEssence findOneWithEagerFollowedPets(@Param("userEssenceId") UUID userEssenceId);

    @Query("from UserEssence ue left join fetch ue.messagesTo left join fetch ue.messagesFrom where ue.userEssenceId = :userEssenceId")
    UserEssence findOneWithEagerMessagesToAndFrom(@Param("userEssenceId") UUID userEssenceId);

    @Query("from UserEssence ue left join fetch ue.messagesTo left join fetch ue.messagesFrom where ue.nickname = :nickname")
    UserEssence findByNicknameWithEagerMessagesToAndFrom(@Param("nickname") String nickname);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    @Cacheable("userEssence")
    UserEssence findByNickname(String nickname);

    @Cacheable("userEssence")
    @Override
    UserEssence findOne(UUID uuid);

    @Cacheable("userEssence")
    @Override
    boolean exists(UUID uuid);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    <S extends UserEssence> S save(S s);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    <S extends UserEssence> Iterable<S> save(Iterable<S> iterable);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    void delete(UUID uuid);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    void delete(UserEssence userEssence);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    void delete(Iterable<? extends UserEssence> iterable);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    void deleteAll();
}
