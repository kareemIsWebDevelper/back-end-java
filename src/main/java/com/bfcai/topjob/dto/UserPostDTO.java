package com.bfcai.topjob.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserPostDTO {

    protected Date date;

    protected String text;

    private Long userId;
}
