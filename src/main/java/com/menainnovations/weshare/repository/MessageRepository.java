package com.menainnovations.weshare.repository;

import com.menainnovations.weshare.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message,Long> {
    @Query(value = "select * from Message m where m.from_user_id IN (:fromUserId , :toUserId) ORDER BY message_date", nativeQuery = true)
    public List<Message> getChatMessages(@Param("fromUserId") long fromUserId,@Param("toUserId") long toUserId);
}
