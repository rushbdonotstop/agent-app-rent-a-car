package com.example.agentapp.controller;

import com.example.agentapp.dto.MessageDTO;
import com.example.agentapp.dto.NewMessageDTO;
import com.example.agentapp.model.Message;
import com.example.agentapp.model.Notification;
import com.example.agentapp.model.Request;
import com.example.agentapp.model.User;
import com.example.agentapp.service.MessageService;
import com.example.agentapp.service.RequestService;
import com.example.agentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    RequestService requestService;

    @Autowired
    UserService userService;

    /**
     * GET /server/request
     *
     * @return return all requests
     */
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<Message>> getAll() {
        return new ResponseEntity<List<Message>>(this.messageService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/inbox", produces = "application/json")
    public ResponseEntity<List<MessageDTO>> getAllMessagesFromConversation(@RequestParam(value = "userId") Long userId, @RequestParam(value = "conversationId") Long conversationId) {

        return new ResponseEntity<List<MessageDTO>>(this.messageService.getAllMessagesForUser(userId, conversationId), HttpStatus.OK);
    }

    /**
     * POST /server/request/
     *
     * @return returns status of new request creation
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> newMessage(@RequestBody MessageDTO message) throws Exception {
        List<Request> requestList = requestService.findAll();
        boolean status = this.messageService.sendMessage(message, requestList);
        Notification not = new Notification();

        if (status) {
            not.setText("Message successfully sent.");
            return new ResponseEntity<Notification>(not, HttpStatus.OK);
        } else {
            not.setText("You dont have any reserved or paid requests from this user.");
            return new ResponseEntity<Notification>(not, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/newMessage", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> newMessage(@RequestBody NewMessageDTO message) throws Exception {
        List<User> userList = userService.getUsers();
        List<Request>  requestList = requestService.findAll();
        boolean status = this.messageService.sendMessage(this.messageService.convertMessage(message, userList), requestList);

        Notification not = new Notification();

        if (status){
            not.setText("Message successfully sent.");
            return new ResponseEntity<Notification>(not, HttpStatus.OK);
        }
        else{
            not.setText("You dont have any reserved or paid requests from this user.");
            return new ResponseEntity<Notification>(not, HttpStatus.BAD_REQUEST);
        }
    }
}
