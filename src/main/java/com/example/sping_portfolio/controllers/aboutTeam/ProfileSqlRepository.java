package com.example.sping_portfolio.controllers.aboutTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class    ProfileSqlRepository {

    @Autowired
    private ProfileJpaRepository jpa;

    public  List<Profile>listAll() {
        return jpa.findAll();
    }

    public void save(Profile profile) {
        jpa.save(profile);
    }

    public Profile get(long id) {
        return jpa.findById(id).get();
    }

    public void delete(long id) {
        jpa.deleteById(id);
    }
}
