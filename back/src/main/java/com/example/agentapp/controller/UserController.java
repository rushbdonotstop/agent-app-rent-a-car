package com.example.agentapp.controller;

import com.example.agentapp.dto.ChangeUsPwRequestDTO;
import com.example.agentapp.dto.CreateUserRequestDTO;
import com.example.agentapp.dto.LoginRequestDTO;
import com.example.agentapp.dto.UserDTO;
import com.example.agentapp.model.Notification;
import com.example.agentapp.model.User;
import com.example.agentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * GET /user/login
     *
     * @return returns logged in user
     */
    @PostMapping(value = "/loginTest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> loginTest(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        User user = userService.loginTest(loginRequestDTO);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * GET /user/usernames
     *
     * @return boolean value which indicates user existence
     */
    @GetMapping(value = "/usernames", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUsernames() throws Exception {
        System.out.println("-------- tu sam -------");
        List<User> users = userService.getUsers();
        System.out.println("-------- useri -------" + users);
        List<UserDTO> usernamesList = userService.convertUserToUserDTO(users);
        System.out.println("-------- usernames -------" + usernamesList);
        return new ResponseEntity<List<UserDTO>>(usernamesList, HttpStatus.OK);
    }


    /**
     * GET /user/userExists
     *
     * @return boolean value which indicates user existence
     */
    @PostMapping(value = "/userExists", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> userExists(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        Boolean userExists = userService.userExists(loginRequestDTO);
        return new ResponseEntity<Boolean>(userExists, HttpStatus.OK);
    }

    /**
     * GET /user/username/{userId}
     *
     * @return returns username by user id
     */
    @GetMapping(value = "/username/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUsername(@PathVariable Long userId) throws Exception {
        UserDTO userDTO = userService.getUsername(userId);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    /**
     * GET /user/all
     *
     * @return returns object of type UserDTO with user id and username
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getAllUsers() throws Exception {
        return new ResponseEntity<List>(userService.getAllUsers(), HttpStatus.OK);
    }

    /**
     * GET /user?id={id}
     *
     * @return returns object of type UserDTO with user id and username
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getOneUser(@RequestParam(value = "id", required = true) Long id) throws Exception {
        try {
            return new ResponseEntity<UserDTO>(userService.getOneUser(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * POST /user
     *
     * @return returns notification
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> postNewUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) throws Exception {
        try {
            userService.addNewUser(createUserRequestDTO);
            return new ResponseEntity<Notification>(new Notification("User " + createUserRequestDTO.getUsername() + " created.", true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * PUT /user
     *
     * @return returns notification
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> putUser(@RequestBody ChangeUsPwRequestDTO changeUsPwRequestDTO) throws Exception {
        try {
            userService.changeUsPw(changeUsPwRequestDTO);
            return new ResponseEntity<Notification>(new Notification("User " + changeUsPwRequestDTO.getUsername() + " changed.", true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * DELETE /user?id={id}
     *
     * @return returns notification
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> putUser(@RequestParam(value = "id", required = true) Long id) throws Exception {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<Notification>(new Notification("User with id " + id + " deleted.", true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * GET /user/canUserCreate/{userId}
     *
     * @return returns true if user can create vehicles
     */
    @GetMapping(value = "canUserCreate/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createVehicleValidation(@PathVariable Long userId) throws Exception {
        Boolean userInfo = userService.canUserCreate(userId);
        return new ResponseEntity<Boolean>(userInfo, HttpStatus.OK);
    }

    /**
     * PUT /user/updateUserVehicleNumAfterCreate/{userId}
     *
     * @return updates user vehicle number after create
     */
    @PutMapping(value = "updateUserVehicleNumAfterCreate/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> updateUserVehicleNumAfterCreate(@PathVariable Long userId) throws Exception {
        Notification notification = userService.updateUserVehicleNumAfterCreate(userId);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);

    }
}
