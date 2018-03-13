package com.pet_space.repositories;

import com.pet_space.models.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PetRepository extends CrudRepository<Pet, UUID> {
}
