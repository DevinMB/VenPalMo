package org.devinbutts.VenPalMo.service;

import org.devinbutts.VenPalMo.dao.MessageDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.Message;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {


    @Autowired
    UserDAO userDAO;

    @Autowired
    MessageDAO messageDAO;


    public List<MessageDTO> getUserMessageDTOs(Principal principal){
        User loggedInUser = userDAO.findByEmail(principal.getName());
        List<Message> userMessages = messageDAO.findUserMessages(loggedInUser.getId());
        List<MessageDTO> messageDTOS = new ArrayList<>();

        for (Message message: userMessages){
            MessageDTO messageDTO = convertMessageToMessageDTO(loggedInUser,message);
            messageDTOS.add(messageDTO);
        }

        return messageDTOS;
    }

    public List<MessageDTO> getUserMessageDTOs(Principal principal, Integer otherUserId){

        User loggedInUser = userDAO.findByEmail(principal.getName());
        List<Message> userMessages = messageDAO.findUserConversation(loggedInUser.getId(), otherUserId);
        List<MessageDTO> messageDTOS = new ArrayList<>();

        for (Message message: userMessages){
            MessageDTO messageDTO = convertMessageToMessageDTO(loggedInUser,message);
            messageDTOS.add(messageDTO);
        }
        return messageDTOS;
    }


    public MessageDTO convertMessageToMessageDTO(User loggedInUser, Message message){

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setId(message.getId());
        messageDTO.setCreatedDate(message.getCreatedDate());
        messageDTO.setText(message.getText());

        String email = "";
        String name = "";
        String messageType = "";

        if(message.getFromUser().getEmail().equals(loggedInUser.getEmail())){
            email = loggedInUser.getEmail();
            name = "You";
            messageType = "Sent";
        }else {
            email = message.getFromUser().getEmail();
            name = message.getFromUser().getFirstName() + " " + message.getFromUser().getLastName();
            messageType = "Received";
        }

        messageDTO.setEmail(email);
        messageDTO.setName(name);
        messageDTO.setMessageType(messageType);

        return messageDTO;
    }


    public void saveMessage(MessageDTO messageDTO, Principal principal){
        User loggedInUser = userDAO.findByEmail(principal.getName());

        Message message = convertMessageDTOToMessage(loggedInUser,messageDTO);

        messageDAO.save(message);

    }


    public Message convertMessageDTOToMessage(User loggedInUser, MessageDTO messageDTO){

        Message message = new Message();

        message.setCreatedDate(new Date());
        message.setText(messageDTO.getText());

        if(messageDTO.getMessageType().equals("Sent")){
            message.setFromUser(loggedInUser);
            message.setToUser(userDAO.findByEmail(messageDTO.getEmail()));

        }else{
            message.setFromUser(userDAO.findByEmail(messageDTO.getEmail()));
            message.setToUser(loggedInUser);
        }

        return message;
    }





}
