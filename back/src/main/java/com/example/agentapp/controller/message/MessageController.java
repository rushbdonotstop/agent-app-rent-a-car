package com.example.agentapp.controller.message;

import com.example.agentapp.dto.message.MessageDTO;
import com.example.agentapp.dto.message.NewMessageDTO;
import com.example.agentapp.dto.user.UserDTO;
import com.example.agentapp.model.Notification;
import com.example.agentapp.model.message.Message;
import com.example.agentapp.model.request.Request;
import com.example.agentapp.model.user.User;
import com.example.agentapp.service.message.MessageService;
import com.example.agentapp.service.request.RequestService;
import com.example.agentapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Notification> sendMessage(@RequestBody MessageDTO message) throws Exception {
        List<Request> requestList = requestService.findAll();
        boolean status = this.messageService.sendMessage(message, requestList);
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

    @PostMapping(value = "/newMessage", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> newMessage(@RequestBody NewMessageDTO message) throws Exception {
        List<User> unblockedUsers = userService.getUnblockedUsers();
        List<UserDTO> users = userService.convertUserToUserDTO(unblockedUsers);
        List<Request>  requestList = requestService.findAll();
        boolean status = this.messageService.sendMessage(this.messageService.convertMessage(message, users), requestList);

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
