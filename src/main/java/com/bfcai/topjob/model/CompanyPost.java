package com.bfcai.topjob.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "company_post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date",nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @Column(name = "text",nullable = false,length = 600)
    private String text;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id",nullable = false)
    @ToString.Exclude
    private Company company;
}
