package com.bfcai.topjob.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanySaveDTO {
    private String name;
    private String bio;
    private String industry;
    private String email;
    private String password;
    private String phone;
    private String website;
    private String about;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date foundedDate;
    private String country;
    private String city;

}
