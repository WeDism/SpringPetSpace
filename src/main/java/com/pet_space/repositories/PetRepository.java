package com.pet_space.repositories;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.pets.Pet;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PetRepository extends CrudRepository<Pet, UUID> {

    @Query("from Pet p join fetch p.followersPet where p.petId = ?1")
    Pet findPetWithEagerObjects(UUID petId);

    Pet findByNameAndOwner(String petName, UserEssence owner);

    @Cacheable("userEssence")
    @Override
    Pet findOne(UUID uuid);

    @Cacheable("userEssence")
    @Override
    boolean exists(UUID uuid);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    <S extends Pet> S save(S s);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    <S extends Pet> Iterable<S> save(Iterable<S> iterable);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    void delete(UUID uuid);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    void delete(Pet pet);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    void delete(Iterable<? extends Pet> iterable);

    @CacheEvict(value = "userEssence", allEntries = true)
    @Override
    void deleteAll();
}
