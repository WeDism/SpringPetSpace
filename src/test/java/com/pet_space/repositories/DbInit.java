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
