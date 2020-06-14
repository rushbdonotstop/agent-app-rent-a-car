package com.example.agentapp.controller;

import com.example.agentapp.model.Notification;
import com.example.agentapp.model.VehicleModel;
import com.example.agentapp.service.VehicleMakeService;
import com.example.agentapp.service.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("catalogue/vehicleModel")
public class VehicleModelController {
    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private VehicleMakeService vehicleMakeService;


    /**
     * GET server/catalogue/vehicleModel/{id}
     *
     * @return return a vehicle model
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleModel> getOneVehicleModel(@PathVariable String id) {
        try {
            return new ResponseEntity<>(vehicleModelService.findOneModel(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/byMake/{makeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleModel>> getModelsByMake(@PathVariable String makeId) {
        try {
            return new ResponseEntity<>(vehicleModelService.getModelsByMake(vehicleMakeService.findOneMake(makeId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * DELETE server/catalogue/vehicleModel/{id}
     *
     * @return return a notification
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> deleteVehicleModel(@PathVariable String id) {
        try {
            vehicleModelService.deleteOneModel(id);
            return new ResponseEntity<>(new Notification("Successfully deleted vehicle model id = " + id, true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * PUT server/catalogue/vehicleFuelType
     *
     * @return return status of creating a vehicle model request
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> putVehicleModel(@PathVariable String id, @RequestBody VehicleModel vehicleModel) {
        try {
            vehicleModelService.changeModel(id, vehicleModel);

            return new ResponseEntity<>(new Notification("Vehicle model changed to " + vehicleModel.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * GET server/catalogue/vehicleModel
     *
     * @return return all vehicle models
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getVehicleModels() {
        List<VehicleModel> vehicleModelList = vehicleModelService.getAllModels();
        if(vehicleModelList.size()==0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vehicleModelList, HttpStatus.OK);
    }

    /**
     * POST server/catalogue/vehicleModel
     *
     * @return return status of creating vehicle model request
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> postVehicleModel(@RequestBody VehicleModel vehicleModel) {
        try {
            vehicleModelService.addNewModel(vehicleModel);

            return new ResponseEntity<>(new Notification("Successfully added vehicle model " + vehicleModel.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }
}
