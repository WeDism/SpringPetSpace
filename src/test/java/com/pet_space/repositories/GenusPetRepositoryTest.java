package com.pet_space.repositories;

import com.pet_space.models.GenusPet;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_CAT;

public class GenusPetRepositoryTest extends DbInit{

    @Test
    public void findById() {
        this.genusPetRepository.save(GENUS_CAT);
        GenusPet cat = this.genusPetRepository.findOne("cat");
        Assert.assertThat(cat, CoreMatchers.is(GENUS_CAT));
    }

}