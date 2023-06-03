package com.bfcai.topjob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    @Column(nullable = false)
    private String projectName;
    @Column(length = 600, nullable = false)
    private String projectDescription;
    @Column(nullable = false)
    private String projectLink;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    private User user;

}
