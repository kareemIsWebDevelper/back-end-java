package com.bfcai.topjob.service;

import com.bfcai.topjob.dto.MessageSaveDTO;
import com.bfcai.topjob.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getChat(Long senderId, Long receiverId);

    Boolean saveMessage(MessageSaveDTO messageSaveDTO);
}
