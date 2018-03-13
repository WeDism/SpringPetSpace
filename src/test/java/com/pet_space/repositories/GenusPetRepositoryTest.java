package com.pet_space.repositories;

import com.pet_space.models.GenusPet;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_CAT;
import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_DOG;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context.xml")
public class GenusPetRepositoryTest {

    @Autowired
    private GenusPetRepository repository;

    @Before
    public void setUp() {
        this.repository.save(GENUS_CAT);
        this.repository.save(GENUS_DOG);
    }

    @Test
    public void findById() {
        this.repository.save(GENUS_CAT);
        Optional<GenusPet> optionalCat = this.repository.findById("cat");
        Assert.assertTrue(optionalCat.isPresent());
        GenusPet cat = optionalCat.get();
        Assert.assertThat(cat, CoreMatchers.is(GENUS_CAT));
    }

    @After
    public void cleanUp() {
        this.repository.delete(GENUS_DOG);
        Assert.assertFalse(this.repository.findById("dog").isPresent());
        this.repository.delete(GENUS_CAT);
        Assert.assertFalse(this.repository.findById("cat").isPresent());
    }

}