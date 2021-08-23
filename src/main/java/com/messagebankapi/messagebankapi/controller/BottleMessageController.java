package com.messagebankapi.messagebankapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.messagebankapi.messagebankapi.model.BottleMessage;
import com.messagebankapi.messagebankapi.model.UIDtoFindAMessage;
import com.messagebankapi.messagebankapi.service.BottleMessageCRUDService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/MessageAPI")
@AllArgsConstructor
public class BottleMessageController {

    private BottleMessageCRUDService bottleMessageCRUDService;

    @PostMapping("/postMessage")
    public ResponseEntity<String> POSTMessageToDatabase(@RequestBody BottleMessage bottleMessage) {
        bottleMessageCRUDService.addMessageToDatabase(bottleMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body("Posted message to database");
    }


    @GetMapping(value = "/getMessage", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String GETMessageFORMDatabase(@RequestBody UIDtoFindAMessage uiDtoFindAMessage) throws JsonProcessingException {
        String bottleMessageAsJSON = bottleMessageCRUDService.getMessageInDatabase_returnAsJson(uiDtoFindAMessage.getUID());
        return bottleMessageAsJSON;
    }

    @DeleteMapping("/deleteMessage")
    public ResponseEntity<String> DELETEMessageINDatabase(@RequestBody BottleMessage bottleMessage) {
        bottleMessageCRUDService.deleteMessageInDatabase(bottleMessage);
        return ResponseEntity.status(HttpStatus.OK).body("Message deleted with UID: " + bottleMessage.getUID());
    }

    @PatchMapping("/updateMessage")
    public ResponseEntity<String> CHANGEMessageINDatabase(@RequestBody BottleMessage bottleMessage) {
        bottleMessageCRUDService.updateMessageInDatabase(bottleMessage);
        return ResponseEntity.status(HttpStatus.OK).body("Message updated with UID: " + bottleMessage.getUID());
    }

}

//todo: Add swagger + add error handling + add prostgresql yaml config
