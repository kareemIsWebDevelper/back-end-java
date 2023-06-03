package com.bfcai.topjob.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageSaveDTO {
    private String body;
    private Long senderId;
    private Long receiverId;
    private Date date;
}
