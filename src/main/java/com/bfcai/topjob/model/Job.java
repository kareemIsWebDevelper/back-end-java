package com.bfcai.topjob.model;


import com.bfcai.topjob.constant.EmploymentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "job")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private EmploymentType employmentType;

    @Column(nullable = false,length = 1500)
    private String description;

    @Column(nullable = false,length = 1500)
    private String requirement;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id",referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    private Company company;


    @ManyToMany(mappedBy = "jobs")
    @ToString.Exclude
    private Set<User> applicants;
}
