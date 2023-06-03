package com.bfcai.topjob.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ExperienceDTO {

    private String experiencePosition;
    private String experiencePlace;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private String endDate;

    private Long userId;
}
