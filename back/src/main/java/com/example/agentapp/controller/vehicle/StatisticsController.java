package com.example.agentapp.controller.vehicle;

import com.example.agentapp.dto.vehicle.statistics.StatisticsDTO;
import com.example.agentapp.model.catalogue.VehicleMake;
import com.example.agentapp.model.catalogue.VehicleModel;
import com.example.agentapp.model.review.Review;
import com.example.agentapp.service.catalogue.VehicleMakeService;
import com.example.agentapp.service.catalogue.VehicleModelService;
import com.example.agentapp.service.review.ReviewService;
import com.example.agentapp.service.vehicle.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    VehicleMakeService vehicleMakeService;

    @Autowired
    VehicleModelService vehicleModelService;

    @Autowired
    ReviewService reviewService;

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDTO> getConversations(@PathVariable Long userId) throws Exception {

        List<VehicleMake> vehicleMakeList = this.getVehicleMakes();
        List<VehicleModel> vehicleModelList = this.getVehicleModels();
        List<Review> reviewList = this.getReviews();
        StatisticsDTO stat = new StatisticsDTO();
        stat = statisticsService.getMostMileage(stat, userId, vehicleMakeList, vehicleModelList);
        stat = statisticsService.getBestRating(stat, userId, vehicleMakeList, vehicleModelList, reviewList);


        return new ResponseEntity<StatisticsDTO>(stat, HttpStatus.OK);

    }

    public List<VehicleMake> getVehicleMakes() throws Exception {
        System.out.println("Getting all vehicle makes");
        List<VehicleMake> vehicleMakes = vehicleMakeService.getAllMakes();

        return vehicleMakes;
    }

    public List<VehicleModel> getVehicleModels() throws Exception {
        System.out.println("Getting all vehicle models");
        List<VehicleModel> vehicleModels = vehicleModelService.getAllModels();

        return vehicleModels;
    }

    public List<Review> getReviews() throws Exception {
        System.out.println("Getting all reviews");
        List<Review> reviews = reviewService.getAll();

        return reviews;
    }

}
