package com.pet_space.services;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.FriendsRepository;
import com.pet_space.repositories.MessageRepository;
import com.pet_space.repositories.PetRepository;
import com.pet_space.repositories.UserEssenceRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Transactional(propagation = Propagation.SUPPORTS)
@Repository
public class CustomUserEssenceRepositoryImpl implements CustomUserEssenceRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CustomUserEssenceRepositoryImpl.class);

    private final UserEssenceRepository userEssenceRepository;
    private final FriendsRepository friendsRepository;
    private final PetRepository petRepository;
    private final MessageRepository messageRepository;
    private final SessionFactory sessionFactory;

    @Autowired
    public CustomUserEssenceRepositoryImpl(UserEssenceRepository userEssenceRepository, FriendsRepository friendsRepository, JpaTransactionManager entityManager, PetRepository petRepository, MessageRepository messageRepository) {
        this.userEssenceRepository = userEssenceRepository;
        this.friendsRepository = friendsRepository;
        this.sessionFactory = entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
        this.petRepository = petRepository;
        this.messageRepository = messageRepository;
    }

    @CacheEvict(value = "userEssence", allEntries = true)
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void deleteCascadeById(UUID id) {
        this.deleteCascade(new UserEssence().setUserEssenceId(id));
    }

    @CacheEvict(value = "userEssence", allEntries = true)
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void deleteCascade(UserEssence entity) {
        UserEssence userEssence = this.userEssenceRepository.findOne(entity.getUserEssenceId());
        this.friendsRepository.delete(userEssence.getRequestedFriendsFrom());
        this.friendsRepository.delete(userEssence.getRequestedFriendsTo());
        this.petRepository.delete(userEssence.getPets());
        this.messageRepository.delete(userEssence.getMessagesFrom());
        this.userEssenceRepository.delete(userEssence);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEssence> fiendFriend(UserEssence userEssence, String name, String surname, String patronymic) {
        try (Session session = sessionFactory.openSession()) {
            Iterable pathEssenceForSearchFriend = new PathEssenceForSearchFriend(name, surname, patronymic);
            Iterator<String> iterator = pathEssenceForSearchFriend.iterator();
            Query query = session.createQuery("from UserEssence ue where " + pathEssenceForSearchFriend + " and ue.userEssenceId != :userEssenceId");
            if (iterator.hasNext()) query.setParameter(0, iterator.next());
            if (iterator.hasNext()) query.setParameter(1, iterator.next());
            if (iterator.hasNext()) query.setParameter(2, iterator.next());
            query.setParameter("userEssenceId", userEssence.getUserEssenceId());
            return query.<UserEssence>getResultList();
        }
    }
}
