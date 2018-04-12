package com.pet_space.repositories;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.pets.Pet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PetRepository extends CrudRepository<Pet, UUID> {

    @Query("from Pet p join fetch p.followersPet where p.petId = ?1")
    Pet findPetWithEagerObjects(UUID petId);

    Pet findByNameAndOwner(String petName, UserEssence owner);

}
