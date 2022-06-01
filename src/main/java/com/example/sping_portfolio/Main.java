package com.example.sping_portfolio;

//import com.example.sping_portfolio.controllers.database.Club.Club;
//import com.example.sping_portfolio.controllers.database.Club.ClubJpaRepository;
//import com.example.sping_portfolio.controllers.database.Club.ClubSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class Main {
    //public ClubSqlRepository ob = new ClubSqlRepository();
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
