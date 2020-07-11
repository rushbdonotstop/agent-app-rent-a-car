package com.example.agentapp.controller.pricelist;

import com.example.agentapp.dto.pricelist.MinAndMaxPricesDTO;
import com.example.agentapp.model.Notification;
import com.example.agentapp.model.pricelist.Pricelist;
import com.example.agentapp.model.vehicle.Vehicle;
import com.example.agentapp.service.pricelist.PricelistService;
import com.example.agentapp.service.vehicle.VehicleService;
import com.example.agentapp.soapconfig.client.vehicle.VehicleClient;
import com.example.agentapp.xmlmodel.vehicle.GetVehicleById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("pricelist")
public class PricelistController {

    @Autowired
    private PricelistService priceListService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleClient vehicleClient;

    /**
     * GET /server/pricelist
     *
     * @return return all pricelist for vehicle
     */
    @GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pricelist>> getAllByVehicle(@PathVariable Long vehicleId) throws Exception {
        List<Pricelist> pricelists = priceListService.getAllByVehicle(vehicleId);
        return new ResponseEntity<List<Pricelist>>(pricelists, HttpStatus.OK);
    }

    /**
     * GET /server/pricelist
     *
     * @return return all pricelist
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pricelist>> getAll() throws Exception {
        List<Pricelist> pricelists = priceListService.getAll();
        return new ResponseEntity<List<Pricelist>>(pricelists, HttpStatus.OK);
    }

    /**
     * PUT /server/pricelist/
     *
     * @return status of saving updated list of pricelists
     */
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> update(@RequestBody List<Pricelist> pricelists, @RequestParam(value="startDate", required = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @RequestParam(value="endDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) throws Exception {
        Boolean exists = vehicleService.exists(pricelists.get(0).getVehicleId());
        Notification notification = new Notification("Vehicle id does not exist.");
        if (exists){
            notification = priceListService.savePricelists(pricelists, startDate, endDate);
        }

        Vehicle vehicle = vehicleService.get(pricelists.get(0).getVehicleId());

        System.out.println(vehicleClient);

        GetVehicleById response = vehicleClient.createEverythingVehicle(vehicle.toXML(vehicle));

        System.err.println(response.getVehicleId());

        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * DELETE /server/pricelist
     *
     * @return status of deleting pricelist
     */
    @DeleteMapping(value = "/{pricelistId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> delete(@PathVariable Long pricelistId) throws Exception {
        Notification notification = priceListService.deletePricelist(pricelistId);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    @GetMapping(value = "/minAndMax", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MinAndMaxPricesDTO> getMinAndMax() throws Exception {
        return new ResponseEntity<>(priceListService.getMinAndMax(), HttpStatus.OK);
    }

    @PostMapping(value = "/validatePricelists", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pricelist>> validatePricelists(@RequestBody List<Pricelist> pricelists, @RequestParam(value="startDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate, @RequestParam(value="endDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate){
        return new ResponseEntity<List<Pricelist>>(priceListService.validatePricelistsDate(pricelists, startDate, endDate), HttpStatus.OK);
    }
}
