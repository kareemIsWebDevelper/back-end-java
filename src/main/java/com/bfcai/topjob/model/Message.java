package com.bfcai.topjob.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body",nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "sender_id",referencedColumnName = "id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id",referencedColumnName = "id")
    private User receiver;

    @Column(name = "date",nullable = false)
    private Date date;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", date=" + date +
                '}';
    }
}
