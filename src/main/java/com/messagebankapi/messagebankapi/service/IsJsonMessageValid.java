package com.messagebankapi.messagebankapi.service;

import com.messagebankapi.messagebankapi.model.BottleMessage;
import com.messagebankapi.messagebankapi.model.UIDtoFindAMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IsJsonMessageValid {



    public boolean isJsonBottleMessageValid(BottleMessage bottleMessage) {
        boolean valid = true;

        if (bottleMessage.getMessage().isEmpty()) {
            valid = false;
        }

        if (bottleMessage.getUID().isEmpty()) {
            valid = false;
        }

        if (bottleMessage.getUsername().isEmpty()) {
            valid = false;
        }


        return valid;

    }

    public boolean isUIDtoFindAMessageValid(UIDtoFindAMessage UID) {
        boolean valid = true;

        if (UID.getMessagecode().isEmpty()) {
            valid = false;
        }
        return valid;

    }
}
