package com.bfcai.topjob.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "education")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long educationId;
    @Column(nullable = false)
    private String educationDegree;
    @Column(nullable = false,name = "study_field")
    private String studyField;
    @Column(nullable = false)
    private String educationPlace;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    private User user;

}
