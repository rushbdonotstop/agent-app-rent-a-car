package com.example.agentapp.controller.request;

import com.example.agentapp.dto.user.PenaltyDTO;
import com.example.agentapp.model.request.Report;
import com.example.agentapp.model.vehicle.Vehicle;
import com.example.agentapp.service.request.ReportService;
import com.example.agentapp.service.user.PenaltyService;
import com.example.agentapp.service.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("report")
public class ReportController {


    @Autowired
    ReportService reportService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    PenaltyService penaltyService;

    /**
     * GET /server/report/vehicle/{vehicleId}
     *
     * @return return all reports
     */
    @GetMapping(value = "vehicle/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Report>> vehicleReports(@PathVariable Long vehicleId) {
        return new ResponseEntity<>(this.reportService.findAllByVehicleId(vehicleId), HttpStatus.OK);
    }

    /**
     * GET /server/report
     *
     * @return return all reports
     */
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<Report>> allReports() {
        return new ResponseEntity<>(this.reportService.findAll(), HttpStatus.OK);
    }

    /**
     * POST /server/report/
     *
     * @return returns status of new report creation
     */
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Report> addReport(@RequestBody Report report) {
        Report newReport=null;
        try {
            if(report.getStartDate()!=null && report.getEndDate()!=null && report.getVehicleId()!=null)
                newReport = this.reportService.addReport(report);
            if(newReport!=null) {
                Vehicle vehicle = this.getVehicle(report.getVehicleId());
                if (vehicle != null) {
                    vehicle.setMileage((int) (vehicle.getMileage() + report.getMileage()));
                    sendMileage(vehicle);
                }
                if (report.getMileage() > vehicle.getMileageLimit()) {
                    System.out.println("Mileage limit exceded.");
                    PenaltyDTO penalty = new PenaltyDTO();
                    penalty.setUserId(report.getUserId());
                    penalty.setTotal(report.getMileage() - vehicle.getMileageLimit());
                    this.addPenalty(penalty);
                }
                return new ResponseEntity<>(newReport, HttpStatus.OK);
            }
            else   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * GET /server/report/{id}
     *
     * @return returns a single report by id
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Report> getReport(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(this.reportService.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public Vehicle getVehicle(Long id) throws Exception {
        System.out.println("Getting vehicle to check mileage limit");
        Vehicle vehicle = vehicleService.get(id);

        return vehicle;
    }

    public PenaltyDTO addPenalty(PenaltyDTO penalty) throws Exception {
        System.out.println("Adding penalty");
        penaltyService.addPenalty(penalty);
        return penalty;
    }

    public void sendMileage(Vehicle vehicle) throws Exception {
        System.out.println("Increasing mileage");
        vehicleService.update(vehicle);
    }

}
