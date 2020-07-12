package com.example.agentapp.rabbit;

import com.example.agentapp.dto.user.EmailDTO;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailProducerController {

    private MessageChannel messageChannel;

    public EmailProducerController(EmailBinding binding) {
        messageChannel = binding.publishMessage();
    }


    @PostMapping("/email")
    public void publish(@RequestBody EmailDTO emailDTO) {
        this.messageChannel.send(MessageBuilder.withPayload(emailDTO)
                .build());
    }
}
