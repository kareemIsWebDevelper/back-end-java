package com.bfcai.topjob.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserSaveDTO {

    private String firstName;
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;
    private String phone;
    private String jobTitle;
    private String country;
    private String city;
    private String email;
    private String password;
    private String about;
    private String profilePicturePath;
}
