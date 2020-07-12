package com.example.agentapp.controller.user;

import com.example.agentapp.dto.user.PenaltyDTO;
import com.example.agentapp.model.user.Penalty;
import com.example.agentapp.service.user.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("penalty")
@RestController
public class PenaltyController {

    @Autowired
    private PenaltyService penaltyService;

    /**
     * POST /server/penalty
     *
     * @return returns status of new physical penalty creation
     */
    @PostMapping(value = "", produces = "application/json", consumes = "application/json")
    public ResponseEntity<PenaltyDTO> addPenalty(@RequestBody PenaltyDTO penalty) {
        try {
            Penalty newPenalty = this.penaltyService.addPenalty(penalty);
            return new ResponseEntity<>(penalty, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * GET /server/penalty/{userId}
     *
     * @return returns all unpaid penalties for user
     */

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Penalty>> userUnpaidPenalties(@PathVariable Long userId){
        try{
            return new ResponseEntity<>(this.penaltyService.unpaidPenalties(userId),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
