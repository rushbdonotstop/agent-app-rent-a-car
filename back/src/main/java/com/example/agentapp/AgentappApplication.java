package com.example.agentapp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgentappApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentappApplication.class, args);
	}


	static final String topicExchangeName = "spring-boot-exchange";

	static final String queueName = "spring-boot";

	// method creates an AMQP queue
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	//method creates a topic exchange
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	//method binds last two together, defining the behavior that occurs when RabbitTemplate publishes to an exchange
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	//registered as a message listener in the container (defined in container()). It listens for messages on
	// the spring-boot queue. Because the Receiver class is a POJO, it needs to be wrapped in the
	// MessageListenerAdapter, where you specify that it invokes receiveMessage
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

//	@Bean
//	CommandLineRunner lookup(LocationClient quoteClient) {
//		return args -> {
//			Location location = new Location();
//			City city = new City();
//			city.setId((long) 1);
//			city.setValue("Test");
//			State state = new State();
//			state.setId((long) 1);
//			state.setValue("Test");
//			Street street = new Street();
//			street.setId((long) 1);
//			street.setValue("Test");
//			location.setCity(city);
//			location.setState(state);
//			location.setStreet(street);
//
//			GetLocation response = quoteClient.createLocation(location);
//
//			System.out.println("Request from app for method create location; Server sent : " + response.getLocation().getCity().getValue());
//
//			com.example.agentapp.model.Location location1 = response.getLocation().toModel(response.getLocation());
//
//			System.err.println(location);
//
//
//		};
//	}


}
