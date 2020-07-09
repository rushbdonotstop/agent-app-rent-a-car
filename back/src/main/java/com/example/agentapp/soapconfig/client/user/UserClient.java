package com.example.agentapp.soapconfig.client.user;


import com.example.agentapp.xmlmodel.user.CreateUser;
import com.example.agentapp.xmlmodel.user.GetUserById;
import com.example.agentapp.xmlmodel.user.User;
import com.example.agentapp.xmlmodel.vehicle.GetVehicleById;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class UserClient extends WebServiceGatewaySupport {

    public GetUserById createUser(User user) {

        CreateUser request = new CreateUser();
        request.setUser(user);

        GetUserById response = (GetUserById) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/user/ws/user", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/createUser"));
        System.out.println(response);
        return response;
    }

}

