package com.pet_space.repositories;

import com.pet_space.services.CustomMessageRepository;
import com.pet_space.services.CustomUserEssenceRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_CAT;
import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_DOG;
import static com.pet_space.repositories.MessageTestData.MESSAGE_FIRST;
import static com.pet_space.repositories.MessageTestData.MESSAGE_SECOND;
import static com.pet_space.repositories.PetRepositoryTestData.*;
import static com.pet_space.repositories.RoleEssenceRepositoryTestData.*;
import static com.pet_space.repositories.StateFriendRepositoryTestData.*;
import static com.pet_space.repositories.StatusEssenceRepositoryTestData.*;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.*;

@Ignore
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context.xml")
public class DbInit {
    @Autowired
    protected GenusPetRepository genusPetRepository;
    @Autowired
    protected UserEssenceRepository userEssenceRepository;
    @Autowired
    protected CustomUserEssenceRepository customUserEssenceRepository;
    @Autowired
    protected RoleEssenceRepository roleEssenceRepository;
    @Autowired
    protected StatusEssenceRepository statusEssenceRepository;
    @Autowired
    protected StateFriendRepository stateFriendRepository;
    @Autowired
    protected PetRepository petRepository;
    @Autowired
    protected FriendsRepository friendsRepository;
    @Autowired
    protected MessageRepository messageRepository;
    @Autowired
    protected CustomMessageRepository customMessageRepository;

    @Before
    public void setUp() {
        this.roleEssenceRepository.save(ROLE_ESSENCE_ROOT);
        this.roleEssenceRepository.save(ROLE_ESSENCE_ADMIN);
        this.roleEssenceRepository.save(ROLE_ESSENCE_USER);

        this.statusEssenceRepository.save(STATUS_ESSENCE_ACTIVE);
        this.statusEssenceRepository.save(STATUS_ESSENCE_DELETED);
        this.statusEssenceRepository.save(STATUS_ESSENCE_INACTIVE);

        this.stateFriendRepository.save(STATE_FRIEND_APPROVED);
        this.stateFriendRepository.save(STATE_FRIEND_REJECTED);
        this.stateFriendRepository.save(STATE_FRIEND_REQUESTED);

        this.genusPetRepository.save(GENUS_CAT);
        this.genusPetRepository.save(GENUS_DOG);

        this.userEssenceRepository.save(USER_ESSENCE_JOHN.setUserEssenceId(null));
        this.userEssenceRepository.save(USER_ESSENCE_FRED.setUserEssenceId(null));
        this.userEssenceRepository.save(USER_ESSENCE_SIMON.setUserEssenceId(null));

        this.petRepository.save(PET_LAYMA.setPetId(null));
        this.petRepository.save(PET_PERS.setPetId(null));
        this.petRepository.save(PET_TIMON.setPetId(null));
        this.petRepository.save(PET_TOSH.setPetId(null));

        this.customMessageRepository.save(MESSAGE_FIRST.setMessageId(null));
        this.customMessageRepository.save(MESSAGE_SECOND.setMessageId(null));

    }

    @After
    public void cleanUp() {
        this.customMessageRepository.deleteAll();

        this.userEssenceRepository.deleteAll();

        this.petRepository.deleteAll();

        this.roleEssenceRepository.deleteAll();

        this.statusEssenceRepository.deleteAll();

        this.stateFriendRepository.deleteAll();

        this.genusPetRepository.deleteAll();
    }
}
