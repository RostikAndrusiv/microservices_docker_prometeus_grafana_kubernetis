package com.rostik.andrusiv.microcollector.client;

import com.rostik.andrusiv.microcollector.entity.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "micro-recipient", url = "micro-recipient:9001")
public interface Client {

    @GetMapping("/message")
    List<Message> readMessages();
}
