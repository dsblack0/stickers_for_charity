package com.example.sping_portfolio.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Objects;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) { return "signup"; }

    @GetMapping("/donations")
    public String donations(Model model) { return "donations"; }

    @GetMapping ("/reviews")
    public String reviews(Model model) {return "reviews";}

    /*
    @GetMapping ("/stickerSale")
    public String stickerSale(Model model) {return "stickerSale";}
     */

    @GetMapping ("/login")
    public String Login(Model model) {return "login";}

}

