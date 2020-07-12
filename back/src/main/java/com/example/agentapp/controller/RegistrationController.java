package com.example.agentapp.controller;

import com.example.agentapp.dto.EmailDTO;
import com.example.agentapp.model.Notification;
import com.example.agentapp.model.User;
import com.example.agentapp.service.RegisterService;
import com.example.agentapp.service.UserDetailsService;
import com.example.agentapp.service.UserService;
import com.example.agentapp.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("registration")
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    RegisterService registerService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> register(@RequestBody User user) {

            if (registerService.validate(user)) {
                try {
                EmailDTO email = registerService.registerUser(user);

                String response = (this.sendVerificationMail(email).getBody());
                return new ResponseEntity<>(new Notification("You registered successfully, you will get verification mail soon!", true), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
            }
            } else {
                return new ResponseEntity<>(new Notification("User input didn't pass server valdiation!", false), HttpStatus.BAD_REQUEST);
            }

    }

    public ResponseEntity<String> sendVerificationMail(EmailDTO emailDTO) {
        try {
            String response = restTemplate.postForEntity("https://fine-email-service.herokuapp.com/email/send",
                    new HttpEntity<EmailDTO>(emailDTO), String.class).getBody();
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Server error", HttpStatus.BAD_REQUEST);
        }
    }

}
