package com.pet_space.repositories;

import org.junit.Test;

import static com.pet_space.repositories.UserEssenceRepositoryTestData.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CustomMessageTest extends DbInit {

    @Test
    public void checkMessagesOfUser() {
        assertThat(this.userEssenceRepository.findOne(USER_ESSENCE_FRED.getUserEssenceId()).getMessagesTo().size(), is(2));
        assertThat(this.userEssenceRepository.findOne(USER_ESSENCE_SIMON.getUserEssenceId()).getMessagesTo().size(), is(1));
        assertThat(this.userEssenceRepository.findOne(USER_ESSENCE_JOHN.getUserEssenceId()).getMessagesFrom().size(), is(2));
    }
}
