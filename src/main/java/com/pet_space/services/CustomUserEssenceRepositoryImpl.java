package com.pet_space.services;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.FriendsRepository;
import com.pet_space.repositories.PetRepository;
import com.pet_space.repositories.UserEssenceRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public class CustomUserEssenceRepositoryImpl implements CustomUserEssenceRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CustomUserEssenceRepositoryImpl.class);

    private final UserEssenceRepository userEssenceRepository;
    private final FriendsRepository friendsRepository;
    private final PetRepository petRepository;
    private final JpaTransactionManager entityManager;

    @Autowired
    public CustomUserEssenceRepositoryImpl
            (UserEssenceRepository userEssenceRepository, FriendsRepository friendsRepository, PetRepository petRepository, JpaTransactionManager entityManager) {
        this.userEssenceRepository = userEssenceRepository;
        this.friendsRepository = friendsRepository;
        this.petRepository = petRepository;
        this.entityManager = entityManager;
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

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEssence> fiendFriend(UserEssence userEssence, String name, String surname, String patronymic) {
        SessionFactory sessionFactory = this.entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
        try (Session session = sessionFactory.openSession()) {
            EssenceForSearchFriend essenceForSearchFriend = new EssenceForSearchFriend(name, surname, patronymic);
            Iterator<String> iterator = essenceForSearchFriend.iterator();
            Query query = session.createQuery("from UserEssence ue where " + essenceForSearchFriend.resultPath() + " and ue.userEssenceId != :userEssenceId");
            if (iterator.hasNext()) query.setParameter(0, iterator.next());
            if (iterator.hasNext()) query.setParameter(1, iterator.next());
            if (iterator.hasNext()) query.setParameter(2, iterator.next());
            query.setParameter("userEssenceId", userEssence.getUserEssenceId());
            return query.<UserEssence>getResultList();
        }
    }
}
