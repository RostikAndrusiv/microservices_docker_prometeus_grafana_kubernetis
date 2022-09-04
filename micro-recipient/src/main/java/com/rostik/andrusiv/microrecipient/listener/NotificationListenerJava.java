package com.rostik.andrusiv.microrecipient.listener;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rostik.andrusiv.microrecipient.config.RabbitMQConfig;
import com.rostik.andrusiv.microrecipient.entity.Message;
import com.rostik.andrusiv.microrecipient.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.rostik.andrusiv.microrecipient.config.RabbitMQConfig.*;

@Component
@Slf4j
public class NotificationListenerJava {

    @Autowired
    private ConnectionFactory factory;

    private final Gson gson = new Gson();

    @Autowired
    private MessageRepository repository;

    @Scheduled(fixedRate = 2000)
    public void listener() throws IOException, TimeoutException {

        Connection connection = RabbitMQConfig.getConnection(factory);
        try (Channel ch = connection.createChannel()) {
            ch.exchangeDeclare(EXCHANGE, "topic", true);
            ch.queueBind(QUEUE, EXCHANGE, ROUTING_KEY);
            String msg = new String(ch.basicGet(QUEUE, true).getBody());
            repository.save(gson.fromJson(msg, Message.class));
            log.info("saved" + msg);
        }
    }
}
