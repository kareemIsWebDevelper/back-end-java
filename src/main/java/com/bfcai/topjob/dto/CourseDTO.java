package com.bfcai.topjob.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseDTO {

    private String courseName;
    private String courseProvider;
    private Long userId;
}
