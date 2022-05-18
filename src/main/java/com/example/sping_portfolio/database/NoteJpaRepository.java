package com.example.sping_portfolio.database;

import com.example.sping_portfolio.database.Person;
import com.example.sping_portfolio.database.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteJpaRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByPerson(Person p);
}
