package com.pet_space.repositories.essences;

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
public class UserEssenceRepositoryImpl<T extends UserEssence, ID extends UUID> implements UserEssenceCustomRepository<T, ID> {
    private static final Logger LOG = LoggerFactory.getLogger(UserEssenceRepositoryImpl.class);

    private final UserEssenceRepository userEssenceRepository;
    private final FriendsRepository friendsRepository;
    private final PetRepository petRepository;

    @Autowired
    public UserEssenceRepositoryImpl(UserEssenceRepository userEssenceRepository, FriendsRepository friendsRepository, PetRepository petRepository) {
        this.userEssenceRepository = userEssenceRepository;
        this.friendsRepository = friendsRepository;
        this.petRepository = petRepository;
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public void deleteById(ID id) {
        this.userEssenceRepository.findById(id).ifPresent(userEssence -> this.delete((T) userEssence));
    }

    @Transactional
    @Override
    public void delete(T entity) {
        entity.getRequestedFriendsFrom().forEach(this.friendsRepository::delete);
        entity.getPets().forEach(this.petRepository::delete);
        userEssenceRepository.delete(entity);
    }
}
