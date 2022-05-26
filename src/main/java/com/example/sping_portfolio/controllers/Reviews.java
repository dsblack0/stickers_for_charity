package com.example.sping_portfolio.controllers;

import java.io.Serializable;

public class Reviews implements Serializable {
    String name;
    String rating;

    public void createReview(String n, String r) {
        name = n;
        rating = r;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }
}