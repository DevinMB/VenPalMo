package org.devinbutts.VenPalMo.controller;


import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.MessageDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.Message;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.dto.MessageDTO;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.devinbutts.VenPalMo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    DisplayUserDAO displayUserDAO;

    @ResponseBody
    @GetMapping
    public List<MessageDTO> getMessages(Principal principal) {

        return messageService.getUserMessageDTOs(principal);

    }

    @GetMapping(value = "/{id}")
    public ModelAndView getConversation(Principal principal, @PathVariable int id){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("message");

        List<MessageDTO> messages = messageService.getUserMessageDTOs(principal,id);
        modelAndView.addObject("messages",messages);

        UserDTO loggedInUser =  displayUserDAO.findUserByEmail(principal.getName());
        modelAndView.addObject("loggedInUser",loggedInUser);

        UserDTO otherUser = displayUserDAO.findByUserId(id);
        modelAndView.addObject("otherUser",otherUser);

        modelAndView.addObject("messageDTO",new MessageDTO());

        return modelAndView;

    }

    @PostMapping(value = "/{id}")
    public ModelAndView getConversation(Principal principal, @PathVariable int id,@ModelAttribute(value="messageDTO") MessageDTO messageDTO){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("message");
        messageDTO.setMessageType("Sent");
        messageDTO.setEmail(userDAO.findByUserId(id).getEmail());
        messageService.saveMessage(messageDTO,principal);

        List<MessageDTO> messages = messageService.getUserMessageDTOs(principal,id);
        modelAndView.addObject("messages",messages);

        UserDTO loggedInUser =  displayUserDAO.findUserByEmail(principal.getName());
        modelAndView.addObject("loggedInUser",loggedInUser);

        UserDTO otherUser = displayUserDAO.findByUserId(id);
        modelAndView.addObject("otherUser",otherUser);

        modelAndView.addObject("messageDTO",new MessageDTO());

        return modelAndView;
    }





}
