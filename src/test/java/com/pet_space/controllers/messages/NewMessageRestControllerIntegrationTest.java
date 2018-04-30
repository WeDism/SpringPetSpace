package com.pet_space.controllers.messages;

import com.google.common.collect.Lists;
import com.pet_space.controllers.ControllerInit;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.messages.Message;
import com.pet_space.models.messages.MessageOfUser;
import com.pet_space.models.messages.MessageState;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.pet_space.models.messages.MessageState.MessageStateEnum.NEW;
import static com.pet_space.models.messages.MessageState.MessageStateEnum.VIEWED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_SIMON;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class NewMessageRestControllerIntegrationTest extends ControllerInit {

    private UserEssence userEssenceSimon;
    private ArrayList<Message> messages;

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
        this.updateClassFields();
    }

    private void updateClassFields() {
        this.userEssenceSimon = this.userEssenceRepository.findOneWithEagerMessagesToAndFrom(USER_ESSENCE_SIMON.getUserEssenceId());
        this.messages = Lists.newArrayList(this.messageRepository.findAll(this.userEssenceSimon.getMessagesTo().stream()
                .map(m -> m.getMessage().getMessageId()).collect(Collectors.toList())));
    }

    @Test
    public void getNewMessages() throws Exception {
        assertThat(this.messages.size(), is(1));
        MessageOfUser messageOfUser = this.userEssenceSimon.getMessagesTo().iterator().next();
        assertThat(messageOfUser.getState(), is(MessageState.of(NEW)));

        MockHttpServletRequestBuilder requestBuilder = get("/user/messages/new")
                .accept(MediaType.APPLICATION_JSON).session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isOk())
                .andExpect(content().json(
                        this.objectMapper.createArrayNode().add(
                                this.objectMapper.readTree(
                                        this.objectMapper.writeValueAsString(messageOfUser.getMessage())))
                                .toString()));

    }

    @Test
    public void putStateMessage() throws Exception {
        assertThat(this.messages.size(), is(1));
        MessageOfUser messageOfUser = this.userEssenceSimon.getMessagesTo().iterator().next();
        assertThat(messageOfUser.getState(), is(MessageState.of(NEW)));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("messageId", messageOfUser.getMessage().getMessageId().toString());
        httpHeaders.add("state", MessageState.of(VIEWED).toString());
        MockHttpServletRequestBuilder requestBuilder = put("/user/messages/new").params(httpHeaders).session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isNoContent());

        this.updateClassFields();
        assertThat(this.messages.size(), is(1));
        messageOfUser = this.userEssenceSimon.getMessagesTo().iterator().next();
        assertThat(messageOfUser.getState(), is(MessageState.of(VIEWED)));
    }
}