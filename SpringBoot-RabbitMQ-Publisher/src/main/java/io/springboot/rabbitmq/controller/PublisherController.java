package io.springboot.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.springboot.rabbitmq.config.RabbitMQConfiguration;
import io.springboot.rabbitmq.model.Product;

@RestController
public class PublisherController {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@PostMapping(value="/publish")
	public Product publishMessage(@RequestBody Product product) {
			rabbitTemplate.convertAndSend(RabbitMQConfiguration.fanoutExchangeName,"message_routing_key",product);
			System.out.println("Message sent successfully");
			return product;
	}
}
