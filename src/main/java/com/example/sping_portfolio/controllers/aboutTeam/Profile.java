package com.example.sping_portfolio.controllers.aboutTeam;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Size(min=2, max = 40, message = "Name (2 to 40 chars)")
    private String name;

    @NonNull
    @Size(min=2, max = 40, message = "Name (2 to 40 chars)")
    private String role;

    @NonNull
    @Size(min=2, max = 40, message = "Name (2 to 40 chars)")
    private String bioText;

    public Profile(String name, String role, String bioText) {
        this.name = name;
        this.role = role;
        this.bioText = bioText;
    }


}
