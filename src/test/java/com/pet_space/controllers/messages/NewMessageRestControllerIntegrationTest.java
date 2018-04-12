package com.pet_space.controllers.messages;

import com.google.common.collect.Lists;
import com.pet_space.controllers.ControllerInit;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.messages.Message;
import com.pet_space.models.messages.MessageState;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

import static com.pet_space.models.messages.MessageState.MessageStateEnum.*;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_SIMON;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

public class NewMessageRestControllerIntegrationTest extends ControllerInit {
    @Override
    @Before
    public void setUp() {
        super.setUp();

        ResultActions auth = null;
        try {
            auth = this.mockMvc.perform(post("/login")
                    .param("nickname", USER_ESSENCE_SIMON.getNickname()).param("password", USER_ESSENCE_SIMON.getPassword()));
            auth.andExpect(redirectedUrl("/pet_space"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        MvcResult result = auth.andReturn();
        this.mockHttpSession = (MockHttpSession) result.getRequest().getSession();
    }

    @Test
    public void getNewMessages() {
        UserEssence userEssenceSimon = this.userEssenceRepository.findOne(USER_ESSENCE_SIMON.getUserEssenceId());
        ArrayList<Message> messages = Lists.newArrayList(this.messageRepository.findAll(
                userEssenceSimon.getMessagesTo().stream().map(m -> m.getMessage().getMessageId()).collect(Collectors.toList())));
        assertThat(messages.size(), is(1));
        assertThat(userEssenceSimon.getMessagesTo().iterator().next().getState(), is(MessageState.of(NEW)));

    }
}