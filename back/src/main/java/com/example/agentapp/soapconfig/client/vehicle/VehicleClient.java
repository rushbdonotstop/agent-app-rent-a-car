package com.example.agentapp.soapconfig.client.vehicle;

import com.example.agentapp.model.pricelist.Pricelist;
import com.example.agentapp.model.vehicle.Vehicle;
import com.example.agentapp.service.catalogue.*;
import com.example.agentapp.service.location.LocationService;
import com.example.agentapp.service.pricelist.PricelistService;
import com.example.agentapp.xmlmodel.location.CreateLocation;
import com.example.agentapp.xmlmodel.location.GetLocationById;
import com.example.agentapp.xmlmodel.vehicle.CreateEverythingVehicle;
import com.example.agentapp.xmlmodel.vehicle.CreateVehicle;
import com.example.agentapp.xmlmodel.vehicle.GetVehicleById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.ArrayList;
import java.util.List;

public class VehicleClient extends WebServiceGatewaySupport {

    @Autowired
    LocationService locationService;

    @Autowired
    PricelistService pricelistService;

    @Autowired
    VehicleMakeService vehicleMakeService;

    @Autowired
    VehicleModelService vehicleModelService;

    @Autowired
    VehicleStyleService vehicleStyleService;

    @Autowired
    VehicleFuelTypeService vehicleFuelTypeService;

    @Autowired
    VehicleTransmissionService vehicleTransmissionService;


    public GetVehicleById createVehicle(Vehicle vehicle) {

        CreateVehicle request = new CreateVehicle();
        request.setVehicle(vehicle);

        GetVehicleById response = (GetVehicleById) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/vehicle/ws/vehicle", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/createVehicle"));
        System.out.println(response);
        return response;
    }

    public GetVehicleById createEverythingVehicle(Vehicle vehicle) throws Exception {

        CreateEverythingVehicle request = new CreateEverythingVehicle();

        Location location = locationService.findById(vehicle.getLocationId());
        List<Pricelist> pricelistList = pricelistService.getAllByVehicle(vehicle.getId());
        VehicleMake vehicleMake = vehicleMakeService.findOneMake(vehicle.getMakeId());
        VehicleModel vehicleModel = vehicleModelService.findOneModel(vehicle.getModelId());
        VehicleStyle vehicleStyle = vehicleStyleService.findOneStyle(vehicle.getStyleId());
        VehicleTransmission vehicleTransmission = vehicleTransmissionService.findOne(vehicle.getTransmissionId());
        VehicleFuelType vehicleFuelType = vehicleFuelTypeService.findOneFuelType(vehicle.getFuelTypeId());

        CreateEverythingVehicle.Catalogue catalogue = new CreateEverythingVehicle.Catalogue();
        catalogue.setVehicleFuelType(vehicleFuelType.toXML(vehicleFuelType));
        catalogue.setVehicleMake(vehicleMake.toXML(vehicleMake));
        catalogue.setVehicleModel(vehicleModel.toXML(vehicleModel));
        catalogue.setVehicleStyle(vehicleStyle.toXML(vehicleStyle));
        catalogue.setVehicleTransmission(vehicleTransmission.toXML(vehicleTransmission));

        /// convert every pricelist in list to XML
        List<com.example.agentapp.xmlmodel.pricelist.Pricelist> pricelistListXML = new ArrayList<>();
        for(Pricelist pricelist : pricelistList){
            pricelistListXML.add(pricelist.toXML(pricelist));
        }

        request.setCatalogue(catalogue);
        request.setLocation(location.toXML(location));
        request.setPricelist(pricelistListXML);
        request.setVehicle(vehicle);

        System.err.println(catalogue);
        System.err.println(request.getLocation());
        System.err.println(pricelistListXML);
        System.err.println(vehicle);

        GetVehicleById response = (GetVehicleById) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/vehicle/ws/vehicle", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/createEverythingVehicle"));
        System.err.println(response);
        return response;
    }

}
