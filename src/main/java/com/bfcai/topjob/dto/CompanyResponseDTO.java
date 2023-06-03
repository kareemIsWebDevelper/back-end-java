package com.bfcai.topjob.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyResponseDTO {

    private Long id;
    private String name;
    private String phone;
    private String industry;
    private String bio;
    private String about;
    private String website;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date foundedDate;
    private String country;
    private String city;
}
