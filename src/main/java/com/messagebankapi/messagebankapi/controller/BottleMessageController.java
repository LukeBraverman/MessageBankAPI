package com.messagebankapi.messagebankapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.messagebankapi.messagebankapi.service.BottleMessageCRUDService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

@AllArgsConstructor
public class BottleMessageController {

    private BottleMessageCRUDService bottleMessageCRUDService;


    public ResponseEntity<String> POSTMessageToDatabase() {
        return ResponseEntity.status(HttpStatus.CREATED).body("Posted message to database");
    }


    public String GETMessageFORMDatabase() throws JsonProcessingException {
        return "bottleMessageAsJSON";
    }


    public ResponseEntity<String> DELETEMessageINDatabase() {
        return ResponseEntity.status(HttpStatus.OK).body("Message deleted with UID: ");
    }


    public ResponseEntity<String> CHANGEMessageINDatabase() {
        return ResponseEntity.status(HttpStatus.OK).body("Message updated with UID: ");
    }
}
