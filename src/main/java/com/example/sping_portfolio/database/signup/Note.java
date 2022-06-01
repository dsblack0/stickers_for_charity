package com.example.sping_portfolio.database.signup;

import com.example.sping_portfolio.controllers.Person;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;

    @NotNull
    @Column(columnDefinition="TEXT")
    private String text;
}