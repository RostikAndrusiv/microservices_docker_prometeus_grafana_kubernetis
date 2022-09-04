package com.rostik.andrusiv.microrecipient.controller;

import com.rostik.andrusiv.microrecipient.entity.Message;
import com.rostik.andrusiv.microrecipient.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class Controller {

    @Autowired
    private MessageRepository repository;

    @GetMapping("/message")
    public List<Message> message(){
        var messages = repository.findAll();
        log.info("get messages from db: " + messages);
        repository.deleteAll();
        log.info("db is cleaned");
        return messages;
    }
}
