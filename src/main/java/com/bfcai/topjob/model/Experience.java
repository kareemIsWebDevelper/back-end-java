package com.bfcai.topjob.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "experience")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long experienceId;
    @Column(nullable = false)
    private String experiencePosition;
    @Column(nullable = false)
    private String experiencePlace;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private String endDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id",referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    private User user;
}
