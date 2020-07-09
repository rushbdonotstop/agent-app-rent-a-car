package com.example.agentapp.service;

import com.example.agentapp.dto.MessageDTO;
import com.example.agentapp.dto.NewMessageDTO;
import com.example.agentapp.dto.RequestDTO;
import com.example.agentapp.dto.UserDTO;
import com.example.agentapp.model.Conversation;
import com.example.agentapp.model.Message;
import com.example.agentapp.model.Request;
import com.example.agentapp.model.User;
import com.example.agentapp.model.enums.MessageType;
import com.example.agentapp.model.enums.Status;
import com.example.agentapp.repository.ConversationRepository;
import com.example.agentapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ConversationRepository conversationRepository;

    public List<Message> findAll() { return messageRepository.findAll(); }

    public List<Message> getMessageByConversationId(Long id) { return messageRepository.findAllByConversationId(id); }

    public boolean sendMessage(MessageDTO message, List<Request> requestList) {

        for (Request request : requestList) {
            if (((request.getOwnerId().equals(message.getSenderId()) && request.getUserId().equals(message.getReceiverId())) || (request.getOwnerId().equals(message.getReceiverId()) && request.getUserId().equals(message.getSenderId()))) && (request.getStatus().equals(Status.RESERVED) || request.getStatus().equals(Status.PAID))  ) {
                Message mess = new Message(message);
                if (getConversationId(message) != null) {
                    System.out.println("USAO U IF");
                    Long id = getConversationId(message);
                    mess.setConversationId(id);
                    Conversation conversation = conversationRepository.findOneById(id);
                    conversation.setLastMessage(mess.getText());
                    conversation.setTimeOfLastMessage(mess.getDateAndTime());
                    conversationRepository.save(conversation);
                } else {
                    System.out.println("USAO U ELSE");
                    Conversation conv = new Conversation();
                    conv.setId((long)(conversationRepository.findAll().size() + 1));
                    System.out.println("ID CONVERZACIJE JE: " + conv.getId());
                    conv.setTimeOfLastMessage(mess.getDateAndTime());
                    conv.setLastMessage(mess.getText());
                    conv.setUserOneId(mess.getSenderId());
                    conv.setUserTwoId(mess.getReceiverId());
                    conversationRepository.save(conv);
                    mess.setConversationId(conv.getId());
                }
                messageRepository.save(mess);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public Long getConversationId(MessageDTO mess) {
        List<Conversation> convList = conversationRepository.findAll();

        for(Conversation conv : convList) {
            if ((conv.getUserOneId().equals(mess.getSenderId()) && conv.getUserTwoId().equals(mess.getReceiverId())) || (conv.getUserOneId().equals(mess.getReceiverId()) && conv.getUserTwoId().equals(mess.getSenderId()))) {
                return conv.getId();
            }
        }
        return null;
    }

    public MessageDTO convertMessToDTO(Long userId, Message mess) {
        MessageDTO messDTO = new MessageDTO(mess);
        if (messDTO.getReceiverId().equals(userId)) {
            messDTO.setMessageType(MessageType.RECEIVED_MESSAGE);
        } else {
            messDTO.setMessageType(MessageType.SENT_MESSAGE);
        }
        return messDTO;
    }

    public List<MessageDTO> getAllMessagesForUser(Long userId, Long conversationId) {
        List<Message> messageList = messageRepository.findAllByConversationId(conversationId);
        List<MessageDTO> listToSend = new ArrayList<>();
        for (Message message : messageList) {
            listToSend.add(convertMessToDTO(userId, message));
        }

        Collections.sort(listToSend, Collections.reverseOrder());
        return listToSend;
    }

    public MessageDTO convertMessage(NewMessageDTO message, List<User> userList) {
        MessageDTO convertedMessage = new MessageDTO();

        for (User user : userList) {
            if (user.getUsername().equals(message.getReceiverUsername())) {
                convertedMessage.setReceiverId(user.getId());
                break;
            }
        }
        convertedMessage.setSenderId(message.getSenderId());
        convertedMessage.setMessageType(message.getMessageType());
        convertedMessage.setDateAndTime(message.getDateAndTime());
        convertedMessage.setText(message.getText());

        return convertedMessage;
    }
}
