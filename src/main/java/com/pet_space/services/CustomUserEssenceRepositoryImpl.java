package com.pet_space.services;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.FriendsRepository;
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

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomUserEssenceRepositoryImpl(JpaTransactionManager entityManager) {
        this.sessionFactory = entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
    }

    @Transactional
    @Override
    public void deleteCascadeById(UUID id) {
        this.deleteCascade(new UserEssence().setUserEssenceId(id));
    }

    @Transactional
    @Override
    public void deleteCascade(UserEssence entity) {
        try (Session session = sessionFactory.openSession()) {
            UserEssence userEssence = session.find(UserEssence.class, entity.getUserEssenceId());
            session.beginTransaction();
            session.delete(userEssence);
            session.getTransaction().commit();
        }
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
