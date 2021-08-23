package com.messagebankapi.messagebankapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.messagebankapi.messagebankapi.model.BottleMessage;
import com.messagebankapi.messagebankapi.model.UIDtoFindAMessage;
import com.messagebankapi.messagebankapi.service.BottleMessageCRUDService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/BMAPI")
@AllArgsConstructor
public class BottleMessageController {

    private BottleMessageCRUDService bottleMessageCRUDService;

    @ApiOperation(value = "Accepts Json string that has the same fields as the BottleMessage model object." +
            "The values are then stored in a postgres database.")
    @PostMapping("/postMessage")
    public ResponseEntity<String> POSTMessageToDatabase(@RequestBody BottleMessage bottleMessage) {
        bottleMessageCRUDService.addMessageToDatabase(bottleMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body("Posted message to database");
    }

    @ApiOperation(value = "Accepts a Json string that has the same fields as the UIDtoFindMessage model object." +
            "Then retrieves a BottleMessage object from the postgres database, maps the object as a " +
            "Json string and returns this string.")
    @GetMapping(value = "/getMessage", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String GETMessageFORMDatabase(@RequestBody UIDtoFindAMessage uiDtoFindAMessage) throws JsonProcessingException {
        String bottleMessageAsJSON = bottleMessageCRUDService.getMessageInDatabase_returnAsJson(uiDtoFindAMessage.getUID());
        return bottleMessageAsJSON;
    }

    @ApiOperation(value = "Accepts Json string that has the same fields as the BottleMessage model object." +
            "The message will then be deleted from the postgres database."
    )
    @DeleteMapping("/deleteMessage")
    public ResponseEntity<String> DELETEMessageINDatabase(@RequestBody BottleMessage bottleMessage) {
        bottleMessageCRUDService.deleteMessageInDatabase(bottleMessage);
        return ResponseEntity.status(HttpStatus.OK).body("Message deleted with UID: " + bottleMessage.getUID());
    }

    @ApiOperation(value = "Accepts Json string that has the same fields as the BottleMessage model object." +
            "This message UID can allready exist in the postgres database," +
            "and sp the message will then be updated."
    )
    @PatchMapping("/updateMessage")
    public ResponseEntity<String> CHANGEMessageINDatabase(@RequestBody BottleMessage bottleMessage) {
        bottleMessageCRUDService.updateMessageInDatabase(bottleMessage);
        return ResponseEntity.status(HttpStatus.OK).body("Message updated with UID: " + bottleMessage.getUID());
    }

}

//todo:  add error handling
