package com.messagebankapi.messagebankapi.repository;


import com.messagebankapi.messagebankapi.model.BottleMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BottleMessageRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindByUID_thenReturnMessage() {
        // given (placing data into database)
        BottleMessage bottleMessage = new BottleMessage();
        bottleMessage.setUID("1");
        bottleMessage.setMessage("Hello");
        bottleMessage.setUsername("Test");
        entityManager.persist(bottleMessage);
        entityManager.flush();
    }
}