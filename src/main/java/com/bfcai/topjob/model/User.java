package com.bfcai.topjob.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @Column(unique = true, nullable = false)
    private String phone;
    @Column(nullable = false)
    private String jobTitle;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Column(length = 800)
    private String about;

    // Web server for chrome must listen to specific folder contains all profile pictures
    @Column(name = "profile_picture_path")
    private String profilePicturePath;


    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Course> courses;

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Skill> skills;

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Project> projects;

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Education> educations;

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Experience> experiences;


    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Company> followingCompanies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ToString.Exclude
    @JsonIgnore
    private Company company;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_connection",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    @JsonIgnore
    private Set<User> followingUsers;

    @ManyToMany(mappedBy = "followingUsers",fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<User> followedUsers;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_applied_job",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    @JsonIgnore
    private Set<Job> jobs;


    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    private Set<UserPost> posts;


}
