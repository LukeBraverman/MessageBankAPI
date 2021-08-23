package com.messagebankapi.messagebankapi.service;


import com.messagebankapi.messagebankapi.model.BottleMessage;
import com.messagebankapi.messagebankapi.repository.BottleMessageRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class BottleMessageCRUDServiceTest {


    @Mock
    private BottleMessageRepository bottleMessageRepository;

    @InjectMocks
    private BottleMessageCRUDService bottleMessageCRUDService;
    @BeforeEach
    void setUp() {

    }

    @Test
    void addValidMessageToDatabase_thenCallDAO() {
        //given
        BottleMessage bottleMessage = new BottleMessage();
        bottleMessage.setUID("12345");
        bottleMessage.setMessage("testMessage");
        bottleMessage.setUsername("testUsername");
        doReturn(null).when(bottleMessageRepository).save(bottleMessage);
        //when
        bottleMessageCRUDService.addMessageToDatabase(bottleMessage);
        //then
        verify(bottleMessageRepository, times(1)).save(bottleMessage);
    }

    @Test
    void addInvalidMessageToDatabase_ThenThrowError() {
        //given
        BottleMessage invalidBottleMessage = new BottleMessage();
        invalidBottleMessage.setUID("12345");
        invalidBottleMessage.setMessage("");
        invalidBottleMessage.setUsername("testUsername");
        doThrow(RuntimeException.class).when(bottleMessageRepository).save(invalidBottleMessage);
        //when

        //then
        assertThrows(RuntimeException.class, () ->  bottleMessageCRUDService.addMessageToDatabase(invalidBottleMessage));

    }


    @Test
    void deleteMessageInDatabase_ThenDeleteMessageInDatabaseUID() {
        //given
        BottleMessage bottleMessage = new BottleMessage();
        bottleMessage.setUID("12345");
       // doReturn(null).when(bottleMessageRepository).deleteById(bottleMessage.getUID());
        //when
        bottleMessageCRUDService.deleteMessageInDatabase(bottleMessage);
        //then
        verify(bottleMessageRepository, times(1)).deleteById(bottleMessage.getUID());
    }

    @Test
    void updateMessageInDatabase_ThenPatchEntityInDatabase() {
        //given

        BottleMessage newBottleMessage = new BottleMessage();
        newBottleMessage.setUID("12345");
        newBottleMessage.setMessage("testNewMessage");
        newBottleMessage.setUsername("testUsername");

        doReturn(null).when(bottleMessageRepository).save(newBottleMessage);
        //when
        bottleMessageCRUDService.updateMessageInDatabase(newBottleMessage);
        //then
        verify(bottleMessageRepository, times(1)).save(newBottleMessage);
    }
}