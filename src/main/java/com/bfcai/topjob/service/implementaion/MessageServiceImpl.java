package com.bfcai.topjob.service.implementaion;

import com.bfcai.topjob.dto.MessageSaveDTO;
import com.bfcai.topjob.model.Message;
import com.bfcai.topjob.model.User;
import com.bfcai.topjob.repository.MessageRepository;
import com.bfcai.topjob.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public List<Message> getChat(Long senderId, Long receiverId) {
        return this.messageRepository.fetchChat(senderId, receiverId);
    }

    @Override
    public Boolean saveMessage(MessageSaveDTO messageSaveDTO) {
        try {
            User sender = new User();
            sender.setId(messageSaveDTO.getSenderId());
            User receiver = new User();
            receiver.setId(messageSaveDTO.getReceiverId());

            Message message = new Message();
            message.setSender(sender);
            message.setReceiver(receiver);
            message.setBody(messageSaveDTO.getBody());
            message.setDate(messageSaveDTO.getDate());

            this.messageRepository.save(message);

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
