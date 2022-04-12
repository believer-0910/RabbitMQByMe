package com.example.rabbitmqbyme.consumer;

import com.example.rabbitmqbyme.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class ConsumerRabbitMq {
    @RabbitListener(queues = "rabbitMq-topic-queue")
    public void receiveTopic(@RequestBody Order order) {
        try {
            System.out.println(new ObjectMapper().writeValueAsString(order));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
