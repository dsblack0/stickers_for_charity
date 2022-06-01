package com.example.sping_portfolio.database.profile;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
    @Size(min=2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @NonNull
    @Size(min=2, max = 30, message = "Name (2 to 30 chars)")
    private String role;

    @NonNull
    @Size(min=2, max = 500, message = "Name (2 to 500 chars)")
    private String bioText;

    @NonNull
    private String picFile;

    public Profile(String name, String role, String bioText, String picFile) {
        this.name = name;
        this.role = role;
        this.bioText = bioText;
        this.picFile = picFile;
    }


}