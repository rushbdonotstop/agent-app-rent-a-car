package com.example.agentapp.controller;


import com.example.agentapp.model.Notification;
import com.example.agentapp.model.User;
import com.example.agentapp.service.VerificationTokenService;
import com.example.agentapp.soapconfig.client.user.UserClient;
import com.example.agentapp.xmlmodel.user.GetUserById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("verification")
public class VerificationController {

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    UserClient userClient;

    @GetMapping(value = "/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> confirmVerification(@PathVariable String token) throws Exception {
        try {
            if (verificationTokenService.confirmVerification(token) == null) {
                return new ResponseEntity<>(new Notification("Wrong token!", false), HttpStatus.BAD_REQUEST);
            } else {
                User user = verificationTokenService.confirmVerification(token);
                GetUserById response = userClient.createUser(user.toXml(user));
                System.err.println(userClient);
                return new ResponseEntity<>(new Notification("User successfully verified his account", true), HttpStatus.OK);
            }
        } catch(Exception e) {
            return new ResponseEntity<>(new Notification("Server error", false), HttpStatus.BAD_REQUEST);
        }
    }
}
