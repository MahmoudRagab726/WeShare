package com.menainnovations.weshare.controller;

import com.menainnovations.weshare.model.Message;
import com.menainnovations.weshare.services.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MessageController {
    @Autowired
    MessageServiceImpl messageService;

    @RequestMapping(value = "/user/{fromId}/messages/{toId}",method = RequestMethod.GET)
    public List<Message> getChatMessages(@PathVariable long fromId, @PathVariable long toId ){
        return messageService.getAllMessages(fromId,toId);
    }

    @RequestMapping(value = "/user/{fromId}/message/{toId}", method = RequestMethod.POST ,consumes = "application/json")
    public String addMessage(@PathVariable long fromId, @PathVariable long toId , @RequestBody Message message){
            try {
                message.setFromUserId(fromId);
                message.setToUserId(toId);
                message.setMessageDate(new Date());
                messageService.addMessage(message);
            }catch (Exception e){
                return "fail";
            }

        return "success";
    }
    @RequestMapping(value = "/message/{messageId}" , method = RequestMethod.DELETE)
    public String deleteMessageById(@PathVariable long messageId){
        try {
            messageService.deleteMessageById(messageId);
        }catch (Exception e){
            return "fail";
        }
        return "success";
    }





}
