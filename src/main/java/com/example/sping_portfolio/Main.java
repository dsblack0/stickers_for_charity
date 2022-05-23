package com.example.sping_portfolio;

//import com.example.sping_portfolio.controllers.database.Club.Club;
//import com.example.sping_portfolio.controllers.database.Club.ClubJpaRepository;
//import com.example.sping_portfolio.controllers.database.Club.ClubSqlRepository;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.StringType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Types;

@SpringBootApplication
public class Main {
    //public ClubSqlRepository ob = new ClubSqlRepository();
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
