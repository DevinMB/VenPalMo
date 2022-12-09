package org.devinbutts.VenPalMo.controller;

import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.dto.MessageDTO;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.devinbutts.VenPalMo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return getSearchPageModelAndView(principal, id);
    }

    @PostMapping(value = "/{id}")
    public ModelAndView getConversation(Principal principal, @PathVariable int id,@ModelAttribute(value="messageDTO") MessageDTO messageDTO){
        messageDTO.setMessageType("Sent");
        messageDTO.setEmail(userDAO.findByUserId(id).getEmail());
        messageService.saveMessage(messageDTO,principal);

        ModelAndView modelAndView = getSearchPageModelAndView(principal, id);

        return modelAndView;
    }

    private ModelAndView getSearchPageModelAndView(Principal principal, int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("message");

        List<MessageDTO> messages = messageService.getUserMessageDTOs(principal, id);
        modelAndView.addObject("messages",messages);

        UserDTO loggedInUser =  displayUserDAO.findUserByEmail(principal.getName());
        modelAndView.addObject("loggedInUser",loggedInUser);

        UserDTO otherUser = displayUserDAO.findByUserId(id);
        modelAndView.addObject("otherUser",otherUser);

        modelAndView.addObject("messageDTO",new MessageDTO());
        return modelAndView;
    }

}
