package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;

@Controller
public class CustomStickers {

    @GetMapping("/CustomStickers")
    public String CustomStickers(@RequestParam(name="name", required=false, defaultValue = "") String name,
                                 @RequestParam(name="email", required=false, defaultValue = "") String email,
                                 @RequestParam(name="phone", required=false, defaultValue = "") String phone,
                                 @RequestParam(name="description", required=false, defaultValue = "") String description,
                                 Model model)
            throws IOException, InterruptedException, ParseException {

        return "/CustomStickers";
    }
}
