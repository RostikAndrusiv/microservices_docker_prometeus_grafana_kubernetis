package com.rostik.andrusiv.microsender.controller;

import com.rostik.andrusiv.microsender.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.rostik.andrusiv.microsender.config.RabbitMQConfig.EXCHANGE;
import static com.rostik.andrusiv.microsender.config.RabbitMQConfig.ROUTING_KEY;

@RestController
@Slf4j
public class Controller {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/notification")
    public String notification(@RequestBody Message message){
        log.info("sending message :" + message);
        template.convertAndSend(EXCHANGE, ROUTING_KEY, message);
        log.info("message: " + message + " is sent to rabbitMQ");
        return "message is published";
    }
}
