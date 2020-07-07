package io.springboot.rabbitmq.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.springboot.rabbitmq.receiver.JMSReceiver;
import io.springboot.rabbitmq.repository.ProductRepository;


@Component
public class RabbitMQConfiguration {
	
	//public static final String topicExchangeName = "message_queue_exchange";
	public static final String fanoutExchangeName = "fanout_queue_exchange";
	public static final String queueName1 = "message_queue_1";
	public static final String queueName2 = "message_queue_2";
	
	/*
	 * @Bean Queue queue() { return new Queue(queueName,false); }
	 */
	
	/*
	 * @Bean TopicExchange topicExchange() { return new
	 * TopicExchange(topicExchangeName); }
	 */
	
	/*
	 * @Bean Binding binding(Queue queue, TopicExchange topicExchange) { return
	 * BindingBuilder.bind(queue).to(topicExchange).with("message_routing_key"); }
	 */
	
	/*
	 * public ConnectionFactory connectionFactory() { CachingConnectionFactory
	 * cachingConnectionFactory = new CachingConnectionFactory("192.168.99.100");
	 * cachingConnectionFactory.setPort(5672);
	 * cachingConnectionFactory.setUsername("guest");
	 * cachingConnectionFactory.setPassword("guest"); return
	 * cachingConnectionFactory;
	 * 
	 * }
	 */
	
	@Bean
	Queue queue1() {
		return new Queue(queueName1,false);
	}
	
	@Bean
	Queue queue2() {
		return new Queue(queueName2,false);
	}
	
	@Bean 
	Exchange fanoutExchange() {
		return new FanoutExchange(fanoutExchangeName); 
	}
	/*
	 * @Bean TopicExchange topicExchange() { return new
	 * TopicExchange(topicExchangeName); }
	 */
	
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
	
	//register a receiver with message listener container to receive messages
	
	@Bean
	SimpleMessageListenerContainer container1(ConnectionFactory connectionFactory,MessageListenerAdapter listenerAdapter1) {
		
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
		simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		simpleMessageListenerContainer.setQueueNames(queueName1);
		simpleMessageListenerContainer.setMessageListener(listenerAdapter1);
		return simpleMessageListenerContainer;
	}
	
	@Bean
	SimpleMessageListenerContainer container2(ConnectionFactory connectionFactory,MessageListenerAdapter listenerAdapter2) {
		
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
		simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		simpleMessageListenerContainer.setQueueNames(queueName2);
		simpleMessageListenerContainer.setMessageListener(listenerAdapter2);
		return simpleMessageListenerContainer;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter1(JMSReceiver jmsReceiver) {
		return new MessageListenerAdapter(jmsReceiver,"receiveMessage");
	}
	

	@Bean
	MessageListenerAdapter listenerAdapter2(JMSReceiver jmsReceiver) {
		return new MessageListenerAdapter(jmsReceiver,"receiveMessage");
	}
	
}
