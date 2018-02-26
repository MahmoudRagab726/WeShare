package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.Message;
import com.menainnovations.weshare.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    MessageRepository messageRepository;


    @Override
    public List<Message> getAllMessages(long fromUserId,long toUserId) {
        return (List<Message>) messageRepository.getChatMessages(fromUserId ,toUserId);
    }

    @Override
    public String addMessage(Message message) {
        if (message.getMessageContent().trim()==null||message.getMessageContent().trim()==""){
            return "fail";
        }else {
            messageRepository.save(message);
            return "success";
        }
    }

    @Override
    public void deleteMessageById(long id) {
        messageRepository.delete(id);
    }
}