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

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) { return "signup"; }
}

