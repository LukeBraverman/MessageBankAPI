package com.messagebankapi.messagebankapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.messagebankapi.messagebankapi.model.BottleMessage;
import com.messagebankapi.messagebankapi.repository.BottleMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BottleMessageCRUDService {

            BottleMessageRepository bottleMessageRepository;


    public void addMessageToDatabase(BottleMessage bottleMessage) {

    }
    public String getMessageInDatabase_returnAsJson() throws JsonProcessingException {

        return "json";
    }


    public void deleteMessageInDatabase(BottleMessage bottleMessage) {

    }

    public void updateMessageInDatabase(BottleMessage bottleMessage) {

    }


}
