package com.example.agentapp.soapconfig.server;

import com.example.agentapp.xmlmodel.location.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class LocationEndpoint {

    private static final String NAMESPACE_URI = "http://rentacar.com/location";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createLocation")
    @ResponsePayload
    public GetLocationById createLocation(@RequestPayload CreateLocation request) {

        System.out.println("Request from app for method create location; Server sent : " + request.getLocation().getCity().getValue());

        GetLocationById getLocationById = new GetLocationById();
        getLocationById.setLocationId(request.getLocation().getId());

        return getLocationById;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateLocation")
    @ResponsePayload
    public GetLocationById updateLocation(@RequestPayload UpdateLocation request) {

        System.out.println("Request from app for method update location; Server sent : " + request.getLocation().getCity().getValue());

        GetLocationById getLocationById = new GetLocationById();
        getLocationById.setLocationId(request.getLocation().getId());

        return getLocationById;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteLocationById")
    @ResponsePayload
    public GetLocationById deleteLocationById(@RequestPayload DeleteLocationById request) {

        System.out.println("Request from app for method delete location; Server sent : " + request.getLocationId());

        GetLocationById getLocationById = new GetLocationById();
        getLocationById.setLocationId(request.getLocationId());

        return getLocationById;
    }
}