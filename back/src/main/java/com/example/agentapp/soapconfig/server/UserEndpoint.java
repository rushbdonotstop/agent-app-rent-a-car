package com.example.agentapp.soapconfig.server;

import com.example.agentapp.xmlmodel.location.CreateLocation;
import com.example.agentapp.xmlmodel.location.DeleteLocationById;
import com.example.agentapp.xmlmodel.location.GetLocationById;
import com.example.agentapp.xmlmodel.location.UpdateLocation;
import com.example.agentapp.xmlmodel.user.CreateUser;
import com.example.agentapp.xmlmodel.user.DeleteUserById;
import com.example.agentapp.xmlmodel.user.GetUserById;
import com.example.agentapp.xmlmodel.user.UpdateUser;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://rentacar.com/user";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUser")
    @ResponsePayload
    public GetUserById createLocation(@RequestPayload CreateUser request) {

        System.out.println("Request from app for method create user; Server sent : " + request.getUser().getUsername());

        GetUserById getUserById = new GetUserById();
        getUserById.setUserId(request.getUser().getId());

        return getUserById;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUser")
    @ResponsePayload
    public GetUserById createLocation(@RequestPayload UpdateUser request) {

        System.out.println("Request from app for method update user; Server sent : " + request.getUser().getUsername());

        GetUserById getUserById = new GetUserById();
        getUserById.setUserId(request.getUser().getId());

        return getUserById;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserById")
    @ResponsePayload
    public GetUserById createLocation(@RequestPayload DeleteUserById request) {

        System.out.println("Request from app for method delete user; Server sent : " + request.getUserId());

        GetUserById getUserById = new GetUserById();
        getUserById.setUserId(request.getUserId());

        return getUserById;
    }
}
