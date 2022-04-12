package com.example.rabbitmqbyme.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    @Value("${rabbitMq.topic.exchange.name}")
    private String topicExchangeName;

    @Value("${rabbitMq.topic.queue.name}")
    private String topicQueueName;

    @Value("${rabbitMq.topic.routing.key}")
    private String topicRoutingKey;

    @Bean
    public Queue queue(){
        return new Queue(topicQueueName);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    public String routingKey(){
        return topicRoutingKey;
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange, String routingKey){
        return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
