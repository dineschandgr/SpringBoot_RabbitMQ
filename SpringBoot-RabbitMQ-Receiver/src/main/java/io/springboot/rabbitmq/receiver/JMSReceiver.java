package io.springboot.rabbitmq.receiver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import io.springboot.rabbitmq.model.Product;
import io.springboot.rabbitmq.model.ProductEntity;
import io.springboot.rabbitmq.repository.ProductRepository;

@Component
public class JMSReceiver implements ChannelAwareMessageListener{
	

	@Autowired
	ProductRepository productRepository;

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {

		System.out.println("Received < "+message + ">");
		byte[] byteArray = message.getBody();
		Product product = (Product)getObject(byteArray);
		System.out.println("product = "+product);
		ProductEntity productEntity = new ProductEntity(product.getProductId(),product.getQuantity(),product.getName());
		productRepository.save(productEntity);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

	private static Object getObject(byte[] byteArray) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
		ObjectInput in = new ObjectInputStream(bis);
		return in.readObject();
	}

}
