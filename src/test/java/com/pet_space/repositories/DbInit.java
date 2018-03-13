package com.pet_space.repositories;

import com.pet_space.services.CustomUserEssenceRepository;
import com.pet_space.services.CustomUserEssenceRepositoryImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
