package com.messagebankapi.messagebankapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.messagebankapi.messagebankapi.model.BottleMessage;
import com.messagebankapi.messagebankapi.repository.BottleMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class BottleMessageCRUDService {

            private BottleMessageRepository bottleMessageRepository;


    public void addMessageToDatabase(BottleMessage bottleMessage) {
        bottleMessageRepository.save(bottleMessage);
    }
    public String getMessageInDatabase_returnAsJson(String UID) throws JsonProcessingException {
        Optional<BottleMessage> bottleMessage = bottleMessageRepository.findById(UID);


        return bottleMessage.toString();
    }


    public void deleteMessageInDatabase(BottleMessage bottleMessage) {
        bottleMessageRepository.deleteById(bottleMessage.getUID());
    }

    public void updateMessageInDatabase(BottleMessage bottleMessage) {
        bottleMessageRepository.save(bottleMessage);
    }


}
