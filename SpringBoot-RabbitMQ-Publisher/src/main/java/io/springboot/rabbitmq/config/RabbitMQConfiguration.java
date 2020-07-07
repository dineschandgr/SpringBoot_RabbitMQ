package io.springboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQConfiguration {
	
	public static final String topicExchangeName = "message_queue_exchange";
	public static final String fanoutExchangeName = "fanout_queue_exchange";
	public static final String queueName1 = "message_queue_1";
	public static final String queueName2 = "message_queue_2";
	
	@Bean
	Queue queue1() {
		return new Queue(queueName1,false);
	}
	
	@Bean
	Queue queue2() {
		return new Queue(queueName2,false);
	}
	
	/*
	 * @Bean TopicExchange topicExchange() { return new
	 * TopicExchange(topicExchangeName); }
	 */
	
	@Bean
	FanoutExchange FanoutExchange() {
		return new FanoutExchange(fanoutExchangeName);
	}
	
	/*
	 * @Bean Binding binding(Queue queue1, TopicExchange topicExchange) { 
	 * return
	 * BindingBuilder.bind(queue1).to(topicExchange).with("message_routing_key"); }
	 */
	
	@Bean
	public Binding binding1(final Queue queue1, final Exchange fanoutExchange) {
	 return BindingBuilder
	 .bind(queue1).to(fanoutExchange).with("*").noargs();
	}
	
	@Bean
	public Binding binding2(final Queue queue2, final Exchange fanoutExchange) {
	 return BindingBuilder
	 .bind(queue2).to(fanoutExchange).with("*").noargs();
	}
	
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("192.168.99.100");
		cachingConnectionFactory.setPort(5672);
		cachingConnectionFactory.setUsername("guest");
		cachingConnectionFactory.setPassword("guest");
		return cachingConnectionFactory;
		
	}
	
}
