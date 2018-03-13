package com.pet_space.repositories;

import com.pet_space.models.GenusPet;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_CAT;
import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_DOG;

public class GenusPetRepositoryTest extends DbInit{

    @Before
    public void setUp() {
        this.genusPetRepository.save(GENUS_CAT);
        this.genusPetRepository.save(GENUS_DOG);
    }

    @Test
    public void findById() {
        this.genusPetRepository.save(GENUS_CAT);
        Optional<GenusPet> optionalCat = this.genusPetRepository.findById("cat");
        Assert.assertTrue(optionalCat.isPresent());
        GenusPet cat = optionalCat.get();
        Assert.assertThat(cat, CoreMatchers.is(GENUS_CAT));
    }

    @After
    public void cleanUp() {
        this.genusPetRepository.delete(GENUS_DOG);
        Assert.assertFalse(this.genusPetRepository.findById("dog").isPresent());
        this.genusPetRepository.delete(GENUS_CAT);
        Assert.assertFalse(this.genusPetRepository.findById("cat").isPresent());
    }

}