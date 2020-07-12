package com.example.agentapp.soapconfig.server;

import com.example.agentapp.xmlmodel.vehicle.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class VehicleEndpoint {
    private static final String NAMESPACE_URI = "http://rentacar.com/vehicle";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createVehicle")
    @ResponsePayload
    public GetVehicleById createVehicle(@RequestPayload CreateVehicle request) {

        System.out.println("Request from app for method create vehicle; Server sent : " + request.getVehicle().getMileage());

        GetVehicleById getVehicleById = new GetVehicleById();
        getVehicleById.setVehicleId(request.getVehicle().getId());

        return getVehicleById;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createEverythingVehicle")
    @ResponsePayload
    public GetVehicleById createEverythingVehicle(@RequestPayload CreateEverythingVehicle request) {

        System.out.println("Request from app for method create vehicle; Server sent : " + request.getVehicle().getMileage());

        GetVehicleById getVehicleById = new GetVehicleById();
        getVehicleById.setVehicleId(request.getVehicle().getId());

        return getVehicleById;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateVehicle")
    @ResponsePayload
    public GetVehicleById updateVehicle(@RequestPayload UpdateVehicle request) {

        System.out.println("Request from app for method update vehicle; Server sent : " + request.getVehicle().getMileage());

        GetVehicleById getVehicleById = new GetVehicleById();
        getVehicleById.setVehicleId(request.getVehicle().getId());

        return getVehicleById;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserById")
    @ResponsePayload
    public GetVehicleById deleteVehicle(@RequestPayload DeleteVehicleById request) {

        System.out.println("Request from app for method delete user; Server sent : " + request.getVehicleId());

        GetVehicleById getVehicleById = new GetVehicleById();
        getVehicleById.setVehicleId(request.getVehicleId());

        return getVehicleById;
    }
}
