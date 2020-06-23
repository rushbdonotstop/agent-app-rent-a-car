package com.example.agentapp;

import com.example.agentapp.model.MessageForQueue;
import com.example.agentapp.service.CoordinateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

//receiver that responds to published messages
@Component
public class Receiver {

    @Autowired
    CoordinateService coordinateService;

    // lets it signal that the message has been received
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(byte[] messageByte) throws UnsupportedEncodingException, JsonProcessingException {
            String messageStringJSON = new String(messageByte, "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            MessageForQueue messageForQueue = mapper.readValue(messageStringJSON, new TypeReference<MessageForQueue>() {
            });
            if(messageForQueue.getHeaders().getAuthorization()!=null)
                System.out.println("Received <" + messageForQueue.getBody() + ">");

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}