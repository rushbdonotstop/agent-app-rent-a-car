package com.example.agentapp.controller.catalogue;

import com.example.agentapp.model.Notification;
import com.example.agentapp.model.catalogue.VehicleFuelType;
import com.example.agentapp.model.vehicle.Vehicle;
import com.example.agentapp.service.catalogue.VehicleFuelTypeService;
import com.example.agentapp.service.vehicle.SearchVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("catalogue/vehicleFuelType")
public class VehicleFuelTypeController {
    @Autowired
    private VehicleFuelTypeService vehicleFuelTypeService;

    @Autowired
    SearchVehicleService searchVehicleService;

    /**
     * GET server/catalogue/vehicleFuelType/{id}
     *
     * @return return a vehicle fuel types
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleFuelType> getOneVehicleFuelType(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(vehicleFuelTypeService.findOneFuelType(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * DELETE server/catalogue/vehicleFuelType/{id}
     *
     * @return return notification
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> deleteVehicleFuelType(@PathVariable Long id) {
        try {
            List<Vehicle> vehicleList = searchVehicleService.getVehiclesByFuel(searchVehicleService.findAll(), id);
            if (vehicleList.size() != 0) {
                return new ResponseEntity<>(new Notification("There is a vehicle registered with fuel type id " + id + "\nFuel type wasn't deleted.", false), HttpStatus.CONFLICT);
            }
            vehicleFuelTypeService.deleteOneFuelType(id);
            return new ResponseEntity<>(new Notification("Successfully deleted fuel type id = " + id, true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * PUT server/catalogue/vehicleFuelType
     *
     * @return return status of creating vehicle request
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> putVehicleFuelType(@PathVariable Long id, @RequestBody VehicleFuelType vehicleFuelType) {
        try {
            vehicleFuelTypeService.changeFuelType(id, vehicleFuelType);

            return new ResponseEntity<>(new Notification("Vehicle fuel type changed to " + vehicleFuelType.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * GET server/catalogue/vehicleFuelType
     *
     * @return return all vehicle fuel types
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getVehicleFuelType() {
        List<VehicleFuelType> vehicleFuelTypeList = vehicleFuelTypeService.getAllFuelType();
        if(vehicleFuelTypeList.size()==0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vehicleFuelTypeList, HttpStatus.OK);
    }


    /**
     * POST server/catalogue/vehicleFuelType
     *
     * @return return status of creating vehicle fuel type request
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> postVehicleFuelType(@RequestBody VehicleFuelType vehicleFuelType) {
        try {
            vehicleFuelTypeService.addNewFuelType(vehicleFuelType);

            return new ResponseEntity<>(new Notification("Successfully added vehicle fuel type " + vehicleFuelType.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value="/createReturnObject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleFuelType> createReturnObject(@RequestBody VehicleFuelType vehicleFuelType) {
        return new ResponseEntity<VehicleFuelType>(vehicleFuelTypeService.createFuelType(vehicleFuelType), HttpStatus.OK);
    }
}
