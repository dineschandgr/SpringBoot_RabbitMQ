package io.springboot.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.springboot.rabbitmq.config.RabbitMQConfiguration;
import io.springboot.rabbitmq.model.Product;

@SpringBootApplication
public class RabbitMQPublisher{
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitMQPublisher.class, args);
	}

	

}
