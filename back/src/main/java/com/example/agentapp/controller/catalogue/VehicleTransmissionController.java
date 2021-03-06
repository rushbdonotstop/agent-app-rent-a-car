package com.example.agentapp.controller.catalogue;

import com.example.agentapp.model.Notification;
import com.example.agentapp.model.catalogue.VehicleTransmission;
import com.example.agentapp.service.catalogue.VehicleTransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("catalogue/vehicleTransmission")
public class VehicleTransmissionController {
    @Autowired
    private VehicleTransmissionService vehicleTransmissionService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleTransmission>> getAllTransmissions() {
        try {
            return new ResponseEntity<>(vehicleTransmissionService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * GET server/catalogue/vehicleTransmission/{id}
     *
     * @return return a vehicle transmission
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleTransmission> getOneVehicleTransmission(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(vehicleTransmissionService.findOne(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * DELETE server/catalogue/vehicleTransmission/{id}
     *
     * @return return a notification
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> deleteVehicleTransmission(@PathVariable Long id) {
        try {
            vehicleTransmissionService.deleteOne(id);
            return new ResponseEntity<>(new Notification("Successfully deleted vehicle transmission id = " + id, true), HttpStatus.OK);
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
    public ResponseEntity<Notification> putVehicleTransmission(@PathVariable Long id, @RequestBody VehicleTransmission vehicleTransmission) {
        try {
            vehicleTransmissionService.change(id, vehicleTransmission);

            return new ResponseEntity<>(new Notification("Vehicle transmission changed to " + vehicleTransmission.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * POST server/catalogue/vehicleStyle/createReturnObject
     *
     * @return return object of creating vehicle fuel type request
     */
    @PostMapping(value="/createReturnObject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleTransmission> createReturnObject(@RequestBody VehicleTransmission vehicleTransmission) {
        return new ResponseEntity<VehicleTransmission>(vehicleTransmissionService.createTransmission(vehicleTransmission), HttpStatus.OK);
    }

    /**
     * POST server/catalogue/vehicleTransmission
     *
     * @return return status of creating transmission type request
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> postVehicleFuelType(@RequestBody VehicleTransmission vehicleTransmission) {
        try {
            vehicleTransmissionService.addNew(vehicleTransmission);

            return new ResponseEntity<>(new Notification("Successfully added vehicle transmission type " + vehicleTransmission.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

}
