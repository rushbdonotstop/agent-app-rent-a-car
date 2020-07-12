package com.example.agentapp.controller.message;

import com.example.agentapp.dto.message.ConversationDTO;
import com.example.agentapp.dto.user.UserDTO;
import com.example.agentapp.model.message.Conversation;
import com.example.agentapp.model.user.User;
import com.example.agentapp.service.message.ConversationService;
import com.example.agentapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<User> unblockedUsers = userService.getUnblockedUsers();
        List<UserDTO> users = userService.convertUserToUserDTO(unblockedUsers);

        return new ResponseEntity<List<ConversationDTO>>(conversationService.getConversationForUser(userId, users), HttpStatus.OK);
    }
}
