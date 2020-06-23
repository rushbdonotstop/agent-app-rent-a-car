package com.example.agentapp.controller;

import com.example.agentapp.dto.ConversationDTO;
import com.example.agentapp.model.Conversation;
import com.example.agentapp.model.User;
import com.example.agentapp.service.ConversationService;
import com.example.agentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("conversation")
public class ConversationController {

    @Autowired
    ConversationService conversationService;

    @Autowired
    UserService userService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Conversation>> getAllConversations() throws Exception {
        System.out.println("USAO U PRVU");
        return new ResponseEntity<List<Conversation>>(conversationService.findAll(), HttpStatus.OK);

    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConversationDTO>> getConversations(@PathVariable Long userId) throws Exception {
        System.out.println("USAO U DRUGU");
        List<User> userList = userService.getUnblockedUsers();

        return new ResponseEntity<List<ConversationDTO>>(conversationService.getConversationForUser(userId, userList), HttpStatus.OK);
    }
}
