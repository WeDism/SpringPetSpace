package com.pet_space.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet_space.models.messages.MessageOfUser;
import com.pet_space.models.messages.MessageOfUserId;
import com.pet_space.models.messages.MessageState;
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

import java.util.HashSet;

import static com.pet_space.models.messages.MessageState.MessageStateEnum.NEW;
import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_CAT;
import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_DOG;
import static com.pet_space.repositories.MessageStateRepositoryTestData.MESSAGE_STATE_NEW;
import static com.pet_space.repositories.MessageStateRepositoryTestData.MESSAGE_STATE_VIEWED;
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
    protected MessageStateRepository messageStateRepository;
    @Autowired
    protected MessageRepository messageRepository;
    @Autowired
    protected MessageOfUserRepository messageOfUserRepository;
    @Autowired
    protected CustomMessageRepository customMessageRepository;
    @Autowired
    protected ObjectMapper objectMapper;

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

        this.messageStateRepository.save(MESSAGE_STATE_NEW);
        this.messageStateRepository.save(MESSAGE_STATE_VIEWED);

        this.genusPetRepository.save(GENUS_CAT);
        this.genusPetRepository.save(GENUS_DOG);

        this.userEssenceRepository.save(USER_ESSENCE_JOHN.setUserEssenceId(null).setFollowByPets(new HashSet<>()));
        this.userEssenceRepository.save(USER_ESSENCE_FRED.setUserEssenceId(null).setFollowByPets(new HashSet<>()));
        this.userEssenceRepository.save(USER_ESSENCE_SIMON.setUserEssenceId(null).setFollowByPets(new HashSet<>()));

        this.petRepository.save(PET_LAYMA.setPetId(null).setFollowersPet(new HashSet<>()));
        this.petRepository.save(PET_PERS.setPetId(null).setFollowersPet(new HashSet<>()));
        this.petRepository.save(PET_TIMON.setPetId(null).setFollowersPet(new HashSet<>()));
        this.petRepository.save(PET_TOSH.setPetId(null).setFollowersPet(new HashSet<>()));

        this.messageRepository.save(MESSAGE_FIRST.setMessageId(null));
        this.messageRepository.save(MESSAGE_SECOND.setMessageId(null));

        MessageOfUser messageFirstForFred = new MessageOfUser(MessageOfUserId.of(USER_ESSENCE_FRED, MESSAGE_FIRST), MessageState.of(NEW));
        MessageOfUser messageSecondForFred = new MessageOfUser(MessageOfUserId.of(USER_ESSENCE_FRED, MESSAGE_SECOND), MessageState.of(NEW));
        MessageOfUser messageSecondForSimon = new MessageOfUser(MessageOfUserId.of(USER_ESSENCE_SIMON, MESSAGE_SECOND), MessageState.of(NEW));

        this.messageOfUserRepository.save(messageFirstForFred);
        this.messageOfUserRepository.save(messageSecondForFred);
        this.messageOfUserRepository.save(messageSecondForSimon);

    }

    @After
    public void cleanUp() {
        this.messageRepository.deleteAll();

        this.userEssenceRepository.deleteAll();

        this.petRepository.deleteAll();

        this.roleEssenceRepository.deleteAll();

        this.statusEssenceRepository.deleteAll();

        this.stateFriendRepository.deleteAll();

        this.genusPetRepository.deleteAll();
    }
}
