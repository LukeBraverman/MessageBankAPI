package com.messagebankapi.messagebankapi.repository;


import com.messagebankapi.messagebankapi.model.BottleMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BottleMessageRepository extends JpaRepository<BottleMessage,String> {

}
