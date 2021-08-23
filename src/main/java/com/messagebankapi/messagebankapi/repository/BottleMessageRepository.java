package com.messagebankapi.messagebankapi.repository;


import com.messagebankapi.messagebankapi.model.BottleMessage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BottleMessageRepository extends JpaRepository<BottleMessage,String> {


}
