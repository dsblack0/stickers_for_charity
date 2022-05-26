package com.example.sping_portfolio.database.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class ModelInit {
    // Inject repositories
    @Autowired RoleJpaRepository roleJpaRepository;
    @Autowired ModelRepository modelRepository;

    @Bean
    CommandLineRunner run() {  // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // make sure Role database is populated with defaults
            String[] roles = {"ROLE_STUDENT", "ROLE_TEACHER", "ROLE_ADMIN", "ROLE_TESTER"};
            for (String role : roles) {
                if (roleJpaRepository.findByName(role) == null)
                    roleJpaRepository.save(new Role(null, role));
            }

            // make sure every record added has a Default encrypted password and ROLE_STUDENT
            modelRepository.defaults("123querty", "ROLE_STUDENT");

            // make sure privileged roles exist for Teacher
            modelRepository.addRoleToPerson("jmort1021@gmail.com", "ROLE_TEACHER");
            modelRepository.addRoleToPerson("jmort1021@gmail.com", "ROLE_ADMIN");

            // review/validate/test by performing output to console
            System.out.println(modelRepository.listAll());
            System.out.println(modelRepository.listAllRoles());
        };
    }
}
