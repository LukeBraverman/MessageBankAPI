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
        doNothing().when(bottleMessageRepository).save(invalidBottleMessage);
        //when
        bottleMessageCRUDService.addMessageToDatabase(invalidBottleMessage);
        //then
        verify(bottleMessageRepository, times(0)).save(invalidBottleMessage);
    }

    @Test
    void getRandomMessageInDatabase_ThenReturnRandomBottleMessageInDatabase() {
        //given
        BottleMessage bottleMessage = new BottleMessage();
        bottleMessage.setUID("12345");
        bottleMessage.setMessage("testMessage");
        bottleMessage.setUsername("testUsername");
        doNothing().when(bottleMessageRepository).count();
        //when(bottleMessageRepository.create)
        verify(bottleMessageRepository, times(1)).count();
    }

    @Test
    void deleteMessageInDatabase_ThenDeleteMessageInDatabaseUID() {
        //given
        BottleMessage bottleMessage = new BottleMessage();
        bottleMessage.setUID("12345");
        doNothing().when(bottleMessageRepository).deleteById(bottleMessage.getUID());
        //when
        bottleMessageCRUDService.deleteMessageInDatabase(bottleMessage);
        //then
        verify(bottleMessageRepository).deleteById(bottleMessage.getUID());
    }

    @Test
    void updateMessageInDatabase_ThenPatchEntityInDatabase() {
        //given

        BottleMessage newBottleMessage = new BottleMessage();
        newBottleMessage.setUID("12345");
        newBottleMessage.setMessage("testNewMessage");
        newBottleMessage.setUsername("testUsername");

        doNothing().when(bottleMessageRepository).save(newBottleMessage);
        //when
        bottleMessageCRUDService.updateMessageInDatabase(newBottleMessage);
        //then
        verify(bottleMessageRepository, times(1)).save(newBottleMessage);
    }
}