package com.rostik.andrusiv.microrecipient.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
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
