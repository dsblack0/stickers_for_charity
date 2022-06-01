package com.example.sping_portfolio.controllers;

import com.example.sping_portfolio.database.signup.SignUp;
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
    private String signUpErrorMessage = "";
    private ArrayList<Reviews> reviewList = new ArrayList<Reviews>();

    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/signup")
    public String signup(@RequestParam(name="fullname", required=true) String fullname,
                         @RequestParam(name="email", required=true) String email,
                         @RequestParam(name="password", required=true) String password,
                         @RequestParam(name="passwordagain", required=true) String passwordagain,
                         Model model) {
        //model.addAttribute("signUpErrorMessage", "Sorry, cannot sign you up.");
        if(! SignUp.validateSignUpInputs(fullname, email, password, passwordagain)) {
            model.addAttribute("signUpErrorMessage", "Sorry, cannot sign you up.");
        }
        return "signup";
    }

    @GetMapping("/donations")
    public String donations(Model model) { return "donations"; }

    @GetMapping ("/reviews")
    public String reviews(Model model) {
        model.addAttribute("reviewList", reviewList);
        return "reviews";
    }

    @GetMapping ("/reviewscreate")
    public String reviewscreate(@RequestParam(name="name", required=false, defaultValue="Bob") String name,
                                @RequestParam(name="rating", required=false, defaultValue="Awesome!") String rating,
                                Model model) {
        Reviews review = new Reviews();
        review.createReview(name, rating);
        reviewList.add(review);
        return "redirect:/reviews";}

    /*@GetMapping ("/reviewsupdate")
    public String reviewsupdate(@RequestParam(name="newrating", required=false, defaultValue="Cool!") String newrating,
                                Model model) {
        if (newrating != "") {
            Reviews review = new Reviews();
            review.updateReview(newrating);
            return "redirect:/reviews";
        }
        return "reviewsupdate";}*/

    /*@GetMapping ("/reviewsdelete")
    public String reviewsdelete() {
        Reviews review = new Reviews();
        review.deleteReview();
        return "redirect:/reviews";}*/

    @GetMapping("/login")
    public String login(Model model) {return "login";}

    @GetMapping("/person")
    public String person(Model model) {
        //model.addAttribute("memberList", memberList);
        return "person";
    }

    @GetMapping("/personcreate")
    public String personcreate(@RequestParam(name="email", required=false, defaultValue="example@example.com") String email,
                                @RequestParam(name="password", required=false, defaultValue="123qwerty") String password,
                                Model model) {
        SignUp member = new SignUp();
        member.createMember(email, password);
        //memberList.add(member);
        return "redirect:/person";
    }

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

