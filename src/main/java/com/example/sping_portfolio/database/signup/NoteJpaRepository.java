package com.example.sping_portfolio.database.signup;

import com.example.sping_portfolio.controllers.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteJpaRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByPerson(Person p);
}
