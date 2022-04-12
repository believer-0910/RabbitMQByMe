package com.example.rabbitmqbyme.controller;

import com.example.rabbitmqbyme.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    @Value("${rabbitMq.topic.exchange.name}")
    private String topicExchangeName;

    @Value("${rabbitMq.topic.routing.key}")
    private String topicRoutingKey;

    private final RabbitTemplate rabbitTemplate;


    @PostMapping("/order/send")
    public void send(@RequestBody Order order) {
        rabbitTemplate.convertAndSend(topicExchangeName, topicRoutingKey, order);
    }

}
