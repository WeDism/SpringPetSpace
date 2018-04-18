package com.pet_space.controllers.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pet_space.controllers.ControllerInit;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.messages.MessageState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.pet_space.models.messages.MessageState.MessageStateEnum.NEW;
import static com.pet_space.models.messages.MessageState.MessageStateEnum.VIEWED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MessageControllerIntegrationTest extends ControllerInit {
    @Override
    @Before
    public void setUp() {
        super.setUp();

        ResultActions auth = null;
        try {
            auth = this.mockMvc.perform(post("/login")
                    .param("nickname", USER_ESSENCE_FRED.getNickname()).param("password", USER_ESSENCE_FRED.getPassword()));
            auth.andExpect(redirectedUrl("/pet_space"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        MvcResult result = auth.andReturn();
        this.mockHttpSession = (MockHttpSession) result.getRequest().getSession();
    }

    @Test
    public void postMessage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode
                .putNull("messageId")
                .put("text", "this is the message of json format")
                .putNull("date")
                .putNull("author")
                .putArray("owners")
                .add("jo")
                .add("sai");

        MockHttpServletRequestBuilder requestBuilder = post("/user/messages")
                .content(objectNode.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).session(this.mockHttpSession);
        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isCreated());
        Assert.assertThat(this.userEssenceRepository.findOne(USER_ESSENCE_FRED.getUserEssenceId()).getMessagesFrom().size(), is(1));

        objectNode
                .putArray("owners");
        requestBuilder = post("/user/messages")
                .content(objectNode.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).session(this.mockHttpSession);
        resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isBadRequest());

    }


    @Test
    public void getMessagesView() throws Exception{
        this.userEssenceRepository.findOne(USER_ESSENCE_FRED.getUserEssenceId()).getMessagesTo()
                .forEach(m -> assertThat(m.getState(), is(MessageState.of(NEW))));

        MockHttpServletRequestBuilder requestBuilder = get("/user/messages")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isAccepted());

        UserEssence userEssence = this.customMessageRepository.updateAllMessagesOfUserEssence(USER_ESSENCE_FRED, MessageState.of(VIEWED));
        userEssence.getMessagesTo().forEach(m -> assertThat(m.getState(), is(MessageState.of(VIEWED))));
    }
}
