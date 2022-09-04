package com.rostik.andrusiv.microcollector.service;

import com.rostik.andrusiv.microcollector.client.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Service {

    @Autowired
    private Client client;

    @Scheduled(fixedRate = 5000)
    public void logMessages(){
        var messages = client.readMessages();
        log.info(messages.toString());
    }
}
