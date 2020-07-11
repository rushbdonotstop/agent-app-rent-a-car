package com.example.agentapp.controller.request;

import com.example.agentapp.dto.request.BundleDTO;
import com.example.agentapp.dto.request.RequestDTO;
import com.example.agentapp.dto.request.RequestForFrontDTO;
import com.example.agentapp.dto.user.UserDTO;
import com.example.agentapp.dto.vehicle.VehicleMainViewDTO;
import com.example.agentapp.model.catalogue.VehicleMake;
import com.example.agentapp.model.catalogue.VehicleModel;
import com.example.agentapp.model.pricelist.Pricelist;
import com.example.agentapp.model.request.Request;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("request")
public class RequestController {

    @Autowired
    RequestService requestService;

    @Autowired
    UserService userService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    PricelistService pricelistService;

    @Autowired
    VehicleMakeService vehicleMakeService;

    @Autowired
    VehicleModelService vehicleModelService;

    @Autowired
    LocationService locationService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    SearchVehicleService searchVehicleService;

    /**
     * GET /server/request
     *
     * @return return all requests
     */
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<Request>> getAll() {
        return new ResponseEntity<>(this.requestService.findAll(), HttpStatus.OK);
    }

    /**
     * PUT /server/request/{id}
     *
     * @return returns status of request update
     */
    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Request> updateRequest(@PathVariable("id") Long id, @RequestBody Request request) {
        Request newRequest = this.requestService.update(id, request);
        if (newRequest != null)
            return new ResponseEntity<>(newRequest, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * POST /server/request/
     *
     * @return returns status of new request creation
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> newRequest(@RequestBody RequestDTO requests) {
        System.out.println(requests);
        boolean status = this.requestService.addRequest(requests);
        if (status){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * DELETE /server/request/{id}
     *
     * @return returns status of request deletion
     */
    @DeleteMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> deleteRequest(@PathVariable("id") Long id) {
        boolean status = this.requestService.delete(id);
        if (status)
            return new ResponseEntity<>("Success", HttpStatus.OK);
        else
            return new ResponseEntity<>("Request not found", HttpStatus.NOT_FOUND);
    }

    /**
     * POST /server/request/physicalRent
     *
     * @return returns status of new physical request creation
     */
    @PostMapping(value = "/physicalRent", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Boolean> physicalRenting(@RequestBody Request request) {
        System.out.println(request);
        boolean status = this.requestService.addPhysicalRenting(request);
        if (status){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/ownerRequestHistory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BundleDTO>> ownerRequestHistory(@RequestParam(value = "ownerId") Long ownerId) throws Exception {
        List<User> unblockedUsers = userService.getUnblockedUsers();
        List<UserDTO> users = userService.convertUserToUserDTO(unblockedUsers);

        List<Vehicle> vehicleList = searchVehicleService.findAll();
        List<Pricelist> pricelist = pricelistService.getAll();
        List<VehicleMake> vehicleMakeList = vehicleMakeService.getAllMakes();
        List<VehicleModel> vehicleModelsList = vehicleModelService.getAllModels();
        List<Review> reviewList = reviewService.getAll();

        List<VehicleMainViewDTO> vehicleDTOList = searchVehicleService.getAllVehicleMainViewDTO(vehicleList, vehicleMakeList, pricelist, vehicleModelsList, users, reviewList);
        List<VehicleMainViewDTO> vehicleDtoListFinal = searchVehicleService.getNotBlocked(vehicleDTOList, users);

        List<Request> requestList = requestService.getAllRequestsForOwner(ownerId);
        List<RequestForFrontDTO> requestDTOList = requestService.getDTOListForOwner(requestList, users, vehicleDtoListFinal);
        List<BundleDTO> bundleList = requestService.getBundles(requestDTOList);

        return new ResponseEntity<List<BundleDTO>>(bundleList, HttpStatus.OK);
    }

    @GetMapping(value = "/buyerRequestHistory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BundleDTO>> buyerRequestHistory(@RequestParam(value = "userId") Long userId) throws Exception {
        List<User> unblockedUsers = userService.getUnblockedUsers();
        List<UserDTO> users = userService.convertUserToUserDTO(unblockedUsers);

        List<Vehicle> vehicleList = searchVehicleService.findAll();
        List<Pricelist> pricelist = pricelistService.getAll();
        List<VehicleMake> vehicleMakeList = vehicleMakeService.getAllMakes();
        List<VehicleModel> vehicleModelsList = vehicleModelService.getAllModels();
        List<Review> reviewList = reviewService.getAll();

        List<VehicleMainViewDTO> vehicleDTOList = searchVehicleService.getAllVehicleMainViewDTO(vehicleList, vehicleMakeList, pricelist, vehicleModelsList, users, reviewList);
        List<VehicleMainViewDTO> vehicleDtoListFinal = searchVehicleService.getNotBlocked(vehicleDTOList, users);

        List<Request> requestList = requestService.getAllRequestsForUser(userId);
        List<RequestForFrontDTO> requestDTOList = requestService.getDTOListForUser(requestList, users, vehicleDtoListFinal);
        List<BundleDTO> bundleList = requestService.getBundles(requestDTOList);

        return new ResponseEntity<List<BundleDTO>>(bundleList, HttpStatus.OK);
    }

    @GetMapping(value = "/ownerSingleRequests", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RequestForFrontDTO>> ownerSingleRequets(@RequestParam(value = "ownerId") Long ownerId) throws Exception {
        List<User> unblockedUsers = userService.getUnblockedUsers();
        List<UserDTO> users = userService.convertUserToUserDTO(unblockedUsers);

        List<Vehicle> vehicleList = searchVehicleService.findAll();
        List<Pricelist> pricelist = pricelistService.getAll();
        List<VehicleMake> vehicleMakeList = vehicleMakeService.getAllMakes();
        List<VehicleModel> vehicleModelsList = vehicleModelService.getAllModels();
        List<Review> reviewList = reviewService.getAll();

        List<VehicleMainViewDTO> vehicleDTOList = searchVehicleService.getAllVehicleMainViewDTO(vehicleList, vehicleMakeList, pricelist, vehicleModelsList, users, reviewList);
        List<VehicleMainViewDTO> vehicleDtoListFinal = searchVehicleService.getNotBlocked(vehicleDTOList, users);

        List<Request> requestList = requestService.getSingleRequestsForOwner(ownerId);
        List<RequestForFrontDTO> requestDTOList = requestService.getDTOListForOwner(requestList, users, vehicleDtoListFinal);

        return new ResponseEntity<List<RequestForFrontDTO>>(requestDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/buyerSingleRequests", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RequestForFrontDTO>> buyerSingleRequests(@RequestParam(value = "userId") Long ownerId) throws Exception {
        List<User> unblockedUsers = userService.getUnblockedUsers();
        List<UserDTO> users = userService.convertUserToUserDTO(unblockedUsers);

        List<Vehicle> vehicleList = searchVehicleService.findAll();
        List<Pricelist> pricelist = pricelistService.getAll();
        List<VehicleMake> vehicleMakeList = vehicleMakeService.getAllMakes();
        List<VehicleModel> vehicleModelsList = vehicleModelService.getAllModels();
        List<Review> reviewList = reviewService.getAll();

        List<VehicleMainViewDTO> vehicleDTOList = searchVehicleService.getAllVehicleMainViewDTO(vehicleList, vehicleMakeList, pricelist, vehicleModelsList, users, reviewList);
        List<VehicleMainViewDTO> vehicleDtoListFinal = searchVehicleService.getNotBlocked(vehicleDTOList, users);

        List<Request> requestList = requestService.getSingleRequestsForUser(ownerId);
        List<RequestForFrontDTO> requestDTOList = requestService.getDTOListForOwner(requestList, users, vehicleDtoListFinal);

        return new ResponseEntity<List<RequestForFrontDTO>>(requestDTOList, HttpStatus.OK);
    }

    //TYPE IS FOR ACCEPTING REQUEST, TYPE 2 IS FOR CANCELING
    @GetMapping(value = "/changeStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> changeStaus(@RequestParam(value = "bundleId") Long bundleId, @RequestParam(value = "changeType") Long changeType) throws Exception {
        Boolean value;
        if (changeType == 1) {
            value = requestService.changeBundleStatusToPaid(bundleId);
        } else if (changeType == 2) {
            value = requestService.changeBundleStatusToCancelled(bundleId);
        } else {
            value = false;
        }
        return new ResponseEntity<Boolean>(value, HttpStatus.OK);
    }

    @GetMapping(value = "canUserPostReview/{vehicleId}+{userId}")
    public ResponseEntity<Boolean> canUserPostReview(@PathVariable Long userId, @PathVariable Long vehicleId) {
        return new ResponseEntity<Boolean>(this.requestService.canUserPostReview(vehicleId, userId), HttpStatus.OK);
    }

    /**
     * GET /server/request/rentingFinished
     *
     * @return return true if user can post review
     */
    @GetMapping(value = "/rentingFinished", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RequestForFrontDTO>> rentingFinishedRequests(@RequestParam(value = "ownerId") Long ownerId) {
        try {
            List<User> unblockedUsers = userService.getUnblockedUsers();
            List<UserDTO> users = userService.convertUserToUserDTO(unblockedUsers);

            List<Vehicle> vehicleList = searchVehicleService.findAll();
            List<Pricelist> pricelist = pricelistService.getAll();
            List<VehicleMake> vehicleMakeList = vehicleMakeService.getAllMakes();
            List<VehicleModel> vehicleModelsList = vehicleModelService.getAllModels();
            List<Review> reviewList = reviewService.getAll();

            List<VehicleMainViewDTO> vehicleDTOList = searchVehicleService.getAllVehicleMainViewDTO(vehicleList, vehicleMakeList, pricelist, vehicleModelsList, users, reviewList);
            List<VehicleMainViewDTO> vehicleDtoListFinal = searchVehicleService.getNotBlocked(vehicleDTOList, users);

            List<Request> requestList = requestService.rentingFinishedRequests(ownerId);
            List<RequestForFrontDTO> requestDTOList = requestService.getDTOListForOwner(requestList, users, vehicleDtoListFinal);
            System.out.println(requestDTOList);

            return new ResponseEntity<>(requestDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/rentingFinishedBundle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BundleDTO>> finishedBundles(@RequestParam(value = "ownerId") Long ownerId) throws Exception {
        try {
            List<User> unblockedUsers = userService.getUnblockedUsers();
            List<UserDTO> users = userService.convertUserToUserDTO(unblockedUsers);

            List<Vehicle> vehicleList = searchVehicleService.findAll();
            List<Pricelist> pricelist = pricelistService.getAll();
            List<VehicleMake> vehicleMakeList = vehicleMakeService.getAllMakes();
            List<VehicleModel> vehicleModelsList = vehicleModelService.getAllModels();
            List<Review> reviewList = reviewService.getAll();

            List<VehicleMainViewDTO> vehicleDTOList = searchVehicleService.getAllVehicleMainViewDTO(vehicleList, vehicleMakeList, pricelist, vehicleModelsList, users, reviewList);
            List<VehicleMainViewDTO> vehicleDtoListFinal = searchVehicleService.getNotBlocked(vehicleDTOList, users);

            List<Request> requestList = requestService.rentingFinishedRequestsInBundle(ownerId);
            List<RequestForFrontDTO> requestDTOList = requestService.getDTOListForOwner(requestList, users, vehicleDtoListFinal);
            List<BundleDTO> bundleList = requestService.getBundles(requestDTOList);
            System.out.println(bundleList);
            return new ResponseEntity<>(bundleList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Scheduled(initialDelayString = "${request.initialdelay}", fixedRateString = "${request.fixedrate}")
    public void fixedRateJobWithInitialDelay() throws InterruptedException {

        System.out.println("Prošlo 5 minuta, vidi zahteve!");
        requestService.startScheduledTask();

    }
}
