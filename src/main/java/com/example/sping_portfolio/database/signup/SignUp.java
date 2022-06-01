package com.example.sping_portfolio.database.signup;

import java.io.Serializable;

public class SignUp implements Serializable {
    String fullname;
    String email;
    String password;

    public void createMember(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static boolean validateSignUpInputs(String fullname, String email, String password, String passwordagain) {
        return false; // for now
    }
}