package com.rostik.andrusiv.microrecipient.listener;

import com.rostik.andrusiv.microrecipient.config.RabbitMQConfig;
import com.rostik.andrusiv.microrecipient.entity.Message;
import com.rostik.andrusiv.microrecipient.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;


@Slf4j
//@Component
public class NotificationListenerSpringRabbit {

    @Autowired
    private MessageRepository repository;

    private BlockingDeque<Message> queue = new LinkedBlockingDeque<>(1);

//TODO try/catch
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void notificationListener(Message message) {
        log.info("got message from rabbitMQ: " + message);
        try {
            queue.put(message);
        } catch (InterruptedException e) {
            log.info("Thread interrupted");
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        log.info("message " + message + "is added to database");
    }

//    @Scheduled(fixedRate = 2000)
    private void abc(){
        Optional.ofNullable(queue.poll()).ifPresentOrElse(msg-> repository.save(msg),
                ()-> log.info("waiting for message"));
    }
}
