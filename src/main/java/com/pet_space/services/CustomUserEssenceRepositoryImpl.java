package com.pet_space.services;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.FriendsRepository;
import com.pet_space.repositories.PetRepository;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Repository
public class CustomUserEssenceRepositoryImpl implements CustomUserEssenceRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CustomUserEssenceRepositoryImpl.class);

    private final UserEssenceRepository userEssenceRepository;
    private final FriendsRepository friendsRepository;
    private final PetRepository petRepository;

    @Autowired
    public CustomUserEssenceRepositoryImpl(UserEssenceRepository userEssenceRepository, FriendsRepository friendsRepository, PetRepository petRepository) {
        this.userEssenceRepository = userEssenceRepository;
        this.friendsRepository = friendsRepository;
        this.petRepository = petRepository;
    }

    @Transactional
    @Override
    public void deleteCascadeById(UUID id) {
        this.deleteCascade(this.userEssenceRepository.findOne(id));
    }

    @Transactional
    @Override
    public void deleteCascade(UserEssence entity) {
        entity.getRequestedFriendsFrom().forEach(this.friendsRepository::delete);
        entity.getPets().forEach(this.petRepository::delete);
        this.userEssenceRepository.delete(entity);
    }
}
