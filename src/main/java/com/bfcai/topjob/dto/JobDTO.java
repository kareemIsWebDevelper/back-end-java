package com.bfcai.topjob.dto;

import com.bfcai.topjob.constant.EmploymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobDTO {
    private String title;
    private String location;
    private EmploymentType employmentType;
    private String description;
    private String requirement;
    private Date date;
    private Long companyId;
}
