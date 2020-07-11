package com.example.agentapp.controller.vehicle;

import com.example.agentapp.dto.vehicle.VehicleDetailsDTO;
import com.example.agentapp.dto.vehicle.VehicleMainViewDTO;
import com.example.agentapp.dto.request.RequestForVehicleDTO;
import com.example.agentapp.dto.user.UserDTO;
import com.example.agentapp.model.catalogue.VehicleMake;
import com.example.agentapp.model.catalogue.VehicleModel;
import com.example.agentapp.model.location.Location;
import com.example.agentapp.model.pricelist.Pricelist;
import com.example.agentapp.model.review.Review;
import com.example.agentapp.model.user.User;
import com.example.agentapp.model.vehicle.Vehicle;
import com.example.agentapp.service.catalogue.VehicleMakeService;
import com.example.agentapp.service.catalogue.VehicleModelService;
import com.example.agentapp.service.location.LocationService;
import com.example.agentapp.service.pricelist.PricelistService;
import com.example.agentapp.service.request.RequestService;
import com.example.agentapp.service.review.ReviewService;
import com.example.agentapp.service.user.UserService;
import com.example.agentapp.service.vehicle.SearchVehicleService;
import com.example.agentapp.service.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("search")
public class SearchVehicleController {

    @Autowired
    private SearchVehicleService searchVehicleService;

    @Autowired
    private PricelistService priceListService;

    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private VehicleService vehicleService;

    /**
     * GET /server/vehicle/user/{userId}
     *
     * @return return all vehicle from a user
     */
    @GetMapping(value = "user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleMainViewDTO>> getAllFromUser(@PathVariable Long userId) throws Exception {
        List<Vehicle> vehicles = vehicleService.getAllFromUser(userId);

        List<Pricelist> pricelist = (this.getPricelists()).getBody();

        List<VehicleMake> vehicleMakeList = (this.getVehicleMakes()).getBody();

        List<VehicleModel> vehicleModelsList = (this.getVehicleModels()).getBody();

        List<UserDTO> usersList = (this.getUsernames()).getBody();

        List<Review> reviewList = (this.getReviews()).getBody();

        List<VehicleMainViewDTO> vehicleDTOList = searchVehicleService.getAllVehicleMainViewDTO(vehicles, vehicleMakeList, pricelist, vehicleModelsList, usersList, reviewList);

        return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleMainViewDTO>> parameterizedSearch(@RequestParam(value = "vehicleMake") Long vehicleMake, @RequestParam(value = "vehicleModel") Long vehicleModel, @RequestParam(value = "vehicleStyle") Long vehicleStyle, @RequestParam(value = "vehicleFuel") Long vehicleFuel, @RequestParam(value = "vehicleTransmission") Long vehicleTransmission, @RequestParam(value = "priceLowerLimit") float priceLowerLimit, @RequestParam(value = "priceUpperLimit") float priceUpperLimit, @RequestParam(value = "maxMileage") int maxMileage, @RequestParam(value = "mileageLimit") int mileageLimit, @RequestParam(value = "collisionProtection") Boolean collisionProtection, @RequestParam(value = "childrenSeats") int childrenSeats, @RequestParam(value = "state") String state, @RequestParam(value = "city") String city, @RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) throws Exception {
        List<Vehicle> vehicleList = searchVehicleService.findAll();

        List<Pricelist> pricelist = (this.getPricelists()).getBody();

        List<VehicleMake> vehicleMakeList = (this.getVehicleMakes()).getBody();

        List<VehicleModel> vehicleModelsList = (this.getVehicleModels()).getBody();

        List<UserDTO> usersList = (this.getUsernames()).getBody();

        List<Location> locations = (this.getLocations()).getBody();

        List<RequestForVehicleDTO> requestsList = (this.getRequests()).getBody();

        List<Review> reviewList = (this.getReviews()).getBody();

        List<VehicleMainViewDTO> dtoList = searchVehicleService.parameterizedSearch(vehicleList, requestsList, locations, vehicleMakeList, pricelist, vehicleModelsList, usersList, reviewList, vehicleMake, vehicleModel, vehicleStyle, vehicleFuel, vehicleTransmission, maxMileage, mileageLimit, collisionProtection, childrenSeats, state, city, priceLowerLimit, priceUpperLimit, startDate, endDate);

        System.out.println("DOBRA METODA ALAL TI VERA");

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleMainViewDTO>> getAll() throws Exception {
        List<Vehicle> vehicleList = searchVehicleService.findAll();

        List<Pricelist> pricelist = (this.getPricelists()).getBody();

        List<VehicleMake> vehicleMakeList = (this.getVehicleMakes()).getBody();

        List<VehicleModel> vehicleModelsList = (this.getVehicleModels()).getBody();

        List<UserDTO> usersList = (this.getUsernames()).getBody();

        List<Review> reviewList = (this.getReviews()).getBody();

        List<VehicleMainViewDTO> vehicleDTOList = searchVehicleService.getAllVehicleMainViewDTO(vehicleList, vehicleMakeList, pricelist, vehicleModelsList, usersList, reviewList);
        return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDetailsDTO> getIdForDetails(@PathVariable Long vehicleId) throws Exception {

        Vehicle vehicle = searchVehicleService.findOneById(vehicleId);
        VehicleDetailsDTO dto = new VehicleDetailsDTO(vehicle);

        return new ResponseEntity<VehicleDetailsDTO>(dto, HttpStatus.OK);

    }

    @GetMapping(value = "make/{makeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Long>> getVehiclesByMake(@PathVariable Long makeId) {
        List<Vehicle> vehiclesList = searchVehicleService.findByVehicleMake(makeId);
        List<Long> idList = new ArrayList<>();
        for (Vehicle vehicle:
                vehiclesList) {
            idList.add(vehicle.getId());
        }
        return new ResponseEntity<>(idList, HttpStatus.OK);
    }

    @GetMapping(value = "model/{modelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Long>> getVehiclesByModel(@PathVariable Long modelId) {
        List<Vehicle> vehiclesList = searchVehicleService.findByVehicleModel(modelId);
        List<Long> idList = new ArrayList<>();
        for (Vehicle vehicle:
                vehiclesList) {
            idList.add(vehicle.getId());
        }
        return new ResponseEntity<>(idList, HttpStatus.OK);
    }

    @GetMapping(value = "fuelType/{fuelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Long>> getVehiclesByFuelType(@PathVariable Long fuelId) {
        List<Vehicle> vehiclesList = searchVehicleService.findByVehicleFuelType(fuelId);
        List<Long> idList = new ArrayList<Long>();
        for (Vehicle vehicle:
                vehiclesList) {
            idList.add(vehicle.getId());
        }
        return new ResponseEntity<>(idList, HttpStatus.OK);
    }

    @GetMapping(value = "vehicleStyle/{styleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Long>> getVehicleByStyle(@PathVariable Long styleId) {
        List<Vehicle> vehiclesList = searchVehicleService.findByVehicleStyle(styleId);
        List<Long> idList = new ArrayList<Long>();
        for (Vehicle vehicle:
                vehiclesList) {
            idList.add(vehicle.getId());
        }
        return new ResponseEntity<>(idList, HttpStatus.OK);
    }

    @GetMapping(value = "transmissionType/{transmissionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Long>> getVehicleByTransmissionType(@PathVariable Long transmissionId) {
        List<Vehicle> vehiclesList = searchVehicleService.findByVehicleTransmission(transmissionId);
        List<Long> idList = new ArrayList<Long>();
        for (Vehicle vehicle:
                vehiclesList) {
            idList.add(vehicle.getId());
        }
        return new ResponseEntity<>(idList, HttpStatus.OK);
    }

    public ResponseEntity<List<Pricelist>> getPricelists() throws Exception {
        System.out.println("Getting all pricelists");
        List<Pricelist> pricelists = priceListService.getAll();

        return new ResponseEntity(pricelists, HttpStatus.OK);
    }

    public ResponseEntity<List<VehicleMake>> getVehicleMakes() throws Exception {
        System.out.println("Getting all vehicle makes");
        List<VehicleMake> vehicleMakeList = vehicleMakeService.getAllMakes();
        if(vehicleMakeList.size()==0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(vehicleMakeList, HttpStatus.OK);
    }

    public ResponseEntity<List<VehicleModel>> getVehicleModels() throws Exception {
        List<VehicleModel> vehicleModelList = vehicleModelService.getAllModels();
        if(vehicleModelList.size()==0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(vehicleModelList, HttpStatus.OK);
    }


    public ResponseEntity<List<UserDTO>> getUsernames() throws Exception {
        System.out.println("Getting all usernames");
        List<User> users = userService.getUsers();
        System.out.println("-------- useri -------" + users);
        List<UserDTO> usernamesList = userService.convertUserToUserDTO(users);
        System.out.println("-------- usernames -------" + usernamesList);
        return new ResponseEntity(usernamesList, HttpStatus.OK);
    }

    public ResponseEntity<List<Location>> getLocations() throws Exception {
        System.out.println("Getting all locations");
        List<Location> locations = locationService.getAll();
        return new ResponseEntity(locations, HttpStatus.OK);
    }

    public ResponseEntity<List<RequestForVehicleDTO>> getRequests() throws Exception {
        System.out.println("Getting all requests");
        return new ResponseEntity(this.requestService.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Review>> getReviews() throws Exception {
        System.out.println("Getting all requests");
        return new ResponseEntity(this.reviewService.getAll(), HttpStatus.OK);
    }

}
