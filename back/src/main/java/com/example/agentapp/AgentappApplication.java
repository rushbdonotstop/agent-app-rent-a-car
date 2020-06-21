package com.example.agentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgentappApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentappApplication.class, args);
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
