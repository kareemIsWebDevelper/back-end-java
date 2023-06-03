package com.bfcai.topjob.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectDTO {

    private String projectName;
    private String projectDescription;
    private String projectLink;

    private Long userId;

}
