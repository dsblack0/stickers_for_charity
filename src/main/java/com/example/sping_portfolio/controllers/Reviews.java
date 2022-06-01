package com.example.sping_portfolio.controllers;

import java.io.Serializable;

public class Reviews implements Serializable {
    Integer id;
    String name;
    String rating;

    private static Integer objCount = 0;

    public void createReview(String n, String r) {
        objCount++;
        id = objCount;
        name = n;
        rating = r;
    }

    public void updateReview(String ne) {
        rating = ne;
    }

    public  Integer getId() {return id;}

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }
}