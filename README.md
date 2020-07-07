# SpringBoot_RabbitMQ
Spring Boot Producer and Consumer using RabbitMQ as a broker

#Notes

RabbitMQ uses jms sepcification with AMQP protocol
CloudAMQP provides RabbitMQ as a fully managed service

Port 15672 is sued for http
port 5672 is used for AQMP messaging

#PubSub model
A publisher application publishes message to the Fanout Exchange with 2 queues to be consumed by 2 different consumers

#PointToPoint model(PTP)
A publisher application publishes message to the Topic Exchange with 1 queue to be consumed by 1 consumer

Prerequisities

1. Start RabbitMQ in docker using the command
docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3-management


#Steps
1. Create a FanoutExchange with a name
2. Create 2 Queues with separate names 
3. Create a binding for each queue to bind the queue with the exchange
4. Create a connection factory with the rabbitmq config. This is optional as Spring boot automatically creates the connection factory
    with config from the application.properties
5. When a rest post api called is made with the payload, the controller uses the RabbitTemplate to send the message to the exchange.
6. The exchange routes the message to the configured queues using the routing key. For Fanout exchange, routing key is ignored as it publishes to all queues
7. Make a post request to http://localhost:8080/publish method with the payload and the producer puts the message in the RabbitMQ
8. Open RabbitMQ console at http://192.168.99.100:15672 port. Use guest/guest to login
9. Verify the messages in the queues
10. The receiver application also follows the same config as producer with FanoutExchange, 2 queues, bindings
11. Aditionally, the receiver needs separate SimpleMessageListenerContainers and listerner adapter for every queue
12. JMS receiver implements ChannelAwareMessageListener and overrides onMessage method to process the method and send ack to RabbitMQ
12. Upon sending ack, RabitMQ deletes the message from the queue
13. Upon receiving the nessage from rabbitMQ, the application puts the data in the Mysql DB running in AWS RDS
