package com.bfcai.topjob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "skill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;
    @Column(nullable = false)
    private String skillName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    private User user;
}
