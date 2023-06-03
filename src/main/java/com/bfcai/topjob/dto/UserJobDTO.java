package com.bfcai.topjob.dto;

import com.bfcai.topjob.constant.EmploymentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserJobDTO {
    private Long id;
    private String title;
    private String location;
    private EmploymentType employmentType;
    private String description;
    private String requirement;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    private String companyName;
}
