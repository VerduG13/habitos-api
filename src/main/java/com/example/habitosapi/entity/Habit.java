package com.example.habitosapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "habits")
public class Habit {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int water;
    @Column(nullable = false)
    private int exercise;
    @Column(nullable = false)
    private int sleep;
    @Column(nullable = false)
    private int moodScore;
    @Column(nullable = false)
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
}
