package com.bfcai.topjob.api;


import com.bfcai.topjob.dto.MessageSaveDTO;
import com.bfcai.topjob.model.Message;
import com.bfcai.topjob.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/messages")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class MessageResource {

    private final MessageService messageService;


    @GetMapping(path = "/{senderId}/chat/{receiverId}")
    public List<Message> getChat(
            @PathVariable(value = "senderId") Long senderId,
            @PathVariable(value = "receiverId") Long receiverId) {
        System.out.println(this.messageService.getChat(senderId, receiverId));
        return this.messageService.getChat(senderId, receiverId);
    }


    @PostMapping
    public Boolean saveMessage(@RequestBody MessageSaveDTO messageSaveDTO) {
        return this.messageService.saveMessage(messageSaveDTO);
    }
}
