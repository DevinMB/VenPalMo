package org.devinbutts.VenPalMo.service;

import org.devinbutts.VenPalMo.model.Account;
import org.devinbutts.VenPalMo.model.Message;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.dto.MessageDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @Test
    public void convertMessageTests(){
        //create user
        User testUser = createTestUser();
        User secondTestUser = createTestUser();
        secondTestUser.setId(105);
        //create message
        Message newMessage = createTestMessage(testUser,secondTestUser);

        //create expected Message DTO

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setMessageType("Sent");
        messageDTO.setId(55);
        messageDTO.setEmail("Test@gmail.com");
        messageDTO.setName("You");
        messageDTO.setCreatedDate(newMessage.getCreatedDate());
        messageDTO.setText("test message");

        MessageDTO testMessageDTO = messageService.convertMessageToMessageDTO(testUser,newMessage);

        Assertions.assertEquals(messageDTO.getId(),testMessageDTO.getId());
        Assertions.assertEquals(messageDTO.getMessageType(),testMessageDTO.getMessageType());
        Assertions.assertEquals(messageDTO.getCreatedDate(),testMessageDTO.getCreatedDate());

        Message testMessage = messageService.convertMessageDTOToMessage(testUser,messageDTO);

        Assertions.assertEquals(newMessage.getFromUser(),testMessage.getFromUser());
        Assertions.assertEquals(newMessage.getToUser(),testMessage.getToUser());
        Assertions.assertEquals(newMessage.getText(),testMessage.getText());


    }

    public Message createTestMessage(User user,User secondUser){
        Message newMessage = new Message();

        newMessage.setText("test message");
        newMessage.setToUser(secondUser);
        newMessage.setFromUser(user);
        newMessage.setId(55);
        newMessage.setCreatedDate(new Date());

        return newMessage;
    }

    public User createTestUser(){
        //create user
        Account account = new Account();
        account.setId(50);
        account.setUserId(1);
        account.setCurrency("USD");
        account.setAvailableBalance(new BigDecimal(5000000));
        account.setDefaultAccount(1);
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
        List<Account> accts = new ArrayList<>();
        accts.add(account);
        User user = new User(1,"Tod","Test","Test@gmail.com","testPass","5055053913","123 Rd","Test","TI","49534", new Date(),new Date(), "ROLE_USER",1,accts);
        return user;
    }
}
