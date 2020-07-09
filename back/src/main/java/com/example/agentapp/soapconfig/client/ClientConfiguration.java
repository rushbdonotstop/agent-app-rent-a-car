package com.example.agentapp.soapconfig.client;

import com.example.agentapp.soapconfig.client.user.UserClient;
import com.example.agentapp.soapconfig.client.vehicle.VehicleClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.example.agentapp.xmlmodel");
//        marshaller.setContextPath("com.example.agentapp.xmlmodel.location");
        return marshaller;
    }

    @Bean
    public VehicleClient vehicleClient(Jaxb2Marshaller marshaller) {
        VehicleClient client = new VehicleClient();
        client.setDefaultUri("http://localhost:8081/vehicle/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public UserClient userClient(Jaxb2Marshaller marshaller) {
        UserClient client = new UserClient();
        client.setDefaultUri("http://localhost:8081/user/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }


}
