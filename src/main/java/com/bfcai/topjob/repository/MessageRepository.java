package com.bfcai.topjob.repository;

import com.bfcai.topjob.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "select distinct m from Message m "
            + "where m.sender.id=:senderId and m.receiver.id=:receiverId "
            + "or m.receiver.id=:senderId and m.sender.id=:receiverId " +
            "order by m.date asc")
    List<Message> fetchChat(
            @Param(value = "senderId") Long senderId,
            @Param(value = "receiverId") Long receiverId);
}
