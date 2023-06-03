package com.bfcai.topjob.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date",nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    @Column(name = "text",nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
    @ToString.Exclude
    private User user;
}
