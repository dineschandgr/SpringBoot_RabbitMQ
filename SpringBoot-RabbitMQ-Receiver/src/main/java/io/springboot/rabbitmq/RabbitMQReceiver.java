package io.springboot.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMQReceiver {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMQReceiver.class, args);
	}

}
