package com.bfcai.topjob.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String bio;

    @Column(nullable = false)
    private String industry;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    @Column(unique = true, nullable = false)
    private String phone;


    @Column(nullable = false)
    private String website;


    @Column(nullable = false, length = 2000)
    private String about;


    @Column(nullable = false, name = "founded_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date foundedDate;

    @Column(nullable = false, name = "country")
    private String country;

    @Column(nullable = false, name = "city")
    private String city;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "company_follower",
            joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    @JsonIgnore
    private Set<User> followers;


    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<User> employees;


    @OneToMany(mappedBy = "company", orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Job> jobs;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    @JsonIgnore
    private Set<CompanyPost> posts;
}
