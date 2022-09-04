package com.rostik.andrusiv.microrecipient.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
public class RabbitMQConfig {
    private static Connection connection = null;
    public static final String QUEUE = "message_queue";
    public static final String EXCHANGE = "message_exchange";
    public static final String ROUTING_KEY = "routing_key";

    public static String MY_HOST = "rabbit";


    @Bean
    public Queue queue() {
        return new org.springframework.amqp.core.Queue(QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public ConnectionFactory factory(){
      var factory =  new ConnectionFactory();
      factory.setHost(MY_HOST);
      return factory;
    }

    public static Connection getConnection(ConnectionFactory factory) throws IOException, TimeoutException {
        if(connection == null){
            connection = factory.newConnection();
        }
        return connection;
    }



}
