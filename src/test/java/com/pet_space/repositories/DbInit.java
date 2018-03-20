package com.pet_space.repositories;

import com.pet_space.services.CustomUserEssenceRepository;
import org.junit.After;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Connection;
import java.sql.SQLException;

import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_CAT;
import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_DOG;
import static com.pet_space.repositories.PetRepositoryTestData.*;
import static com.pet_space.repositories.RoleEssenceRepositoryTestData.ROLE_ESSENCE_ADMIN;
import static com.pet_space.repositories.RoleEssenceRepositoryTestData.ROLE_ESSENCE_ROOT;
import static com.pet_space.repositories.RoleEssenceRepositoryTestData.ROLE_ESSENCE_USER;
import static com.pet_space.repositories.StateFriendRepositoryTestData.STATE_FRIEND_APPROVED;
import static com.pet_space.repositories.StateFriendRepositoryTestData.STATE_FRIEND_REJECTED;
import static com.pet_space.repositories.StateFriendRepositoryTestData.STATE_FRIEND_REQUESTED;
import static com.pet_space.repositories.StatusEssenceRepositoryTestData.STATUS_ESSENCE_ACTIVE;
import static com.pet_space.repositories.StatusEssenceRepositoryTestData.STATUS_ESSENCE_DELETED;
import static com.pet_space.repositories.StatusEssenceRepositoryTestData.STATUS_ESSENCE_INACTIVE;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_SIMON;

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
    private DriverManagerDataSource dataSource;

    @After
    public void cleanUp() {
        this.userEssenceRepository.deleteAll();

        this.petRepository.deleteAll();

        this.roleEssenceRepository.deleteAll();

        this.statusEssenceRepository.deleteAll();

        this.stateFriendRepository.deleteAll();

        this.genusPetRepository.deleteAll();
    }
}
