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
    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) { return "signup"; }

    @GetMapping("/donations")
    public String donations(Model model) { return "donations"; }

    @GetMapping ("/aboutTeam")
    public String aboutTeam(Model model) {return "aboutTeam";}

    @GetMapping ("/events")
    public String events(Model model) {return "events";}
}
