package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping ("/reviews")
    public String reviews(Model model) {return "reviews";}

    @GetMapping ("/CustomStickers")
    public String CustomStickers(Model model) {return "CustomStickers";}

    /*
    @GetMapping ("/stickerSale")
    public String stickerSale(Model model) {return "stickerSale";}
     */

    @GetMapping("/login")
    public String login(Model model) {return "login";}

    @GetMapping("/person")
    public String person(Model model) {return "person";}

    @GetMapping("/personcreate")
    public String personcreate(Model model) {return "personcreate";}

    @GetMapping("/personupdate")
    public String personupdate(Model model) {return "personupdate";}

    // these are for later
    /* @GetMapping("/")
    public String process(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (messages == null) {
            messages = new ArrayList<>();
        }
        model.addAttribute("sessionMessages", messages);

        return "index";
    }

    @PostMapping("/persistMessage")
    public String persistMessage(@RequestParam("msg") String msg, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
        if (messages == null) {
            messages = new ArrayList<>();
            request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
        }
        messages.add(msg);
        request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
        return "redirect:/";
    }

    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    } */
}

