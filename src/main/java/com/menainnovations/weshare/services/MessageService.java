package com.menainnovations.weshare.services;



import com.menainnovations.weshare.model.Message;

import java.util.List;

public interface MessageService {

    public List<Message> getAllMessages(long fromUserId,long toUserId);
    public String addMessage(Message message);
    public void deleteMessageById(long id);


}
