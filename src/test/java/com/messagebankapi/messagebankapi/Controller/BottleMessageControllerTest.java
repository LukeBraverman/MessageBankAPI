package com.messagebankapi.messagebankapi.Controller;


import com.messagebankapi.messagebankapi.controller.BottleMessageController;
import com.messagebankapi.messagebankapi.model.BottleMessage;
import com.messagebankapi.messagebankapi.service.BottleMessageCRUDService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//readup on ssl security
@ExtendWith(MockitoExtension.class)
class BottleMessageControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    BottleMessageController bottleMessageController;
    @Mock
    BottleMessageCRUDService bottleMessageCRUDService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bottleMessageController)
                .build();
    }

    @Test
    void POSTMessageToDatabase_Return201Created() throws Exception {
        //given
        String validJsonPayload = "{\"UID\":\"12345\"," +
                " \"message\": \"testMessage\"" +
                ",\"username\":" + "\"testUsername\"}";
        doNothing().when(bottleMessageCRUDService).addMessageToDatabase(any(BottleMessage.class));
        //when
        mockMvc.perform(
                post("/BMAPI/postMessage")
                        .content(validJsonPayload)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated())
                .andReturn();
        //then
        verify(bottleMessageCRUDService, times(1)).addMessageToDatabase(any(BottleMessage.class));
    }

    @Test
    void POSTInvalidMessageToDatabase_Return400BadRequest() throws Exception {
        //given
        String InvalidJsonPayload = "{\"UID\":\"12345\"," +
                "\"INVALID\":" + "\"testUsername\"}";
        doNothing().when(bottleMessageCRUDService).addMessageToDatabase(any(BottleMessage.class));
        //when
        mockMvc.perform(
                post("/BMAPI/postMessage")
                        .content(InvalidJsonPayload)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest())
                .andReturn();
        //then
        verify(bottleMessageCRUDService, times(0)).addMessageToDatabase(any(BottleMessage.class));
    }

    @Test
    void GETMessageFORMDatabase_returnRandomMessageInDatabase() throws Exception {
        //given
        String testBottleMessageJSON = "{\"UID\":\"12345\"," +
                        " \"message\": \"testMessage\"" +
                        ",\"username\":" + "\"testUsername\"}";
        BottleMessage testBottleMessage = new BottleMessage();
        testBottleMessage.setUID("12345");
        testBottleMessage.setMessage("testMessage");
        testBottleMessage.setUsername("testUsername");
        when(bottleMessageCRUDService.getMessageInDatabase_returnAsJson()).thenReturn(testBottleMessageJSON);

        //when
        final MvcResult mvcResult = mockMvc.perform(
                get("/BMAPI/getMessage"))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();

        //then
        assertEquals(testBottleMessageJSON,content,"Response body not as expected");
        verify(bottleMessageCRUDService,times(1)).getMessageInDatabase_returnAsJson();
    }

    @Test
    void DELETEMessageINDatabase_Return200OKAndUID() throws Exception {
        //given
        String JSONPayloadWithUIDToDelete = "{\"UID\":\"12345\"}";

        BottleMessage testBottleMessage = new BottleMessage();
        testBottleMessage.setUID("12345");
        testBottleMessage.setMessage("testMessage");
        testBottleMessage.setUsername("testUsername");
        doNothing().when(bottleMessageCRUDService).deleteMessageInDatabase(any(BottleMessage.class));

        //when
        mockMvc.perform(
                delete("/BMAPI/deleteMessage")
                        .content(JSONPayloadWithUIDToDelete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(bottleMessageCRUDService,times(1)).deleteMessageInDatabase(any(BottleMessage.class));
    }

    @Test
    void DELETEMessageWithInvalidJSON_Return400BadRequest() throws Exception {
        //given
        String INVALIDJSONPayloadWithUIDToDelete = "{\"INVALID\":\"12345\"}";
        doNothing().when(bottleMessageCRUDService).deleteMessageInDatabase(any(BottleMessage.class));

        //when
        mockMvc.perform(
                delete("/BMAPI/deleteMessage")
                        .content(INVALIDJSONPayloadWithUIDToDelete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        //then
        verify(bottleMessageCRUDService,times(0)).deleteMessageInDatabase(any(BottleMessage.class));
    }

    @Test
    void CHANGEMessageINDatabase_Return200OK() throws Exception {
        //given
        String JSONPayloadWithUIDToUpdate = "{\"UID\":\"12345\", " +
                "\"message\": \"updatedMSG\"}";
        doNothing().when(bottleMessageCRUDService).updateMessageInDatabase(any(BottleMessage.class));

        //when
        mockMvc.perform(
                patch("/BMAPI/updateMessage")
                        .content(JSONPayloadWithUIDToUpdate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(bottleMessageCRUDService,times(1)).updateMessageInDatabase(any(BottleMessage.class));
    }

    @Test
    void CHANGEInvalidMessageINDatabase_Return() throws Exception {
        //given
        String JSONPayloadWithUIDToUpdate = "{\"INVALID\":\"12345\", " +
                "\"INVALID\": \"updatedMSG\"}";
        doNothing().when(bottleMessageCRUDService).updateMessageInDatabase(any(BottleMessage.class));

        //when
        mockMvc.perform(
                patch("/BMAPI/updateMessage")
                        .content(JSONPayloadWithUIDToUpdate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        //then
        verify(bottleMessageCRUDService,times(0)).updateMessageInDatabase(any(BottleMessage.class));
    }
}