package com.example.sping_portfolio.controllers;

import com.example.sping_portfolio.database.signup.SignUp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class MainController {
    private String signUpErrorMessage = "";
    private ArrayList<Reviews> reviewList = new ArrayList<Reviews>();

    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/signup")
    public String showSignupForm(Person person) {
        // the above signature must use a Person object as a parameter for
        // the signup.html to work with “bean-backed form” or "form-backing bean".
        return "signup"; // take us to signup.html form
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


    @GetMapping("/reviewsupdate/{id}")
    public String reviewsUpdate(@PathVariable("id") int id, Model model) {
        Reviews selectedReview = new Reviews();
        for (Reviews r: reviewList)
        {
            if ( r.id == id  )
                selectedReview = r;
        }
        model.addAttribute("Reviews", selectedReview);
        return "reviewsupdate";
    }

    @PostMapping("/reviewsupdate")
    public String reviewsUpdateSave(@RequestParam(name="name", required=false, defaultValue="Bob") String name,
                                    @RequestParam(name="rating", required=false, defaultValue="Awesome!") String rating,
                                    @RequestParam(name="id", required=true) int id,
                                    Model model) {
        // Validation of Decorated PersonForm attributes
        for (Reviews r: reviewList)
        {
            if (r.id == id)
            {
                r.rating = rating;
            }
        }
        // Redirect to next step
        return "redirect:/reviews";
    }

    @GetMapping("/reviewsdelete/{id}")
    public String reviewsDelete(@PathVariable("id") int id, Model model) {
        Reviews selectedReview = new Reviews();
        for (Reviews r: reviewList)
        {
            if ( r.id == id  )
                selectedReview = r;
        }
        reviewList.remove(selectedReview);
        return "redirect:/reviews";
    }

    @GetMapping("/login")
    public String login(Model model) {return "login";}

    @GetMapping("/personXXX")
    public String person(Model model) {
        //model.addAttribute("memberList", memberList);
        return "person";
    }

    @GetMapping("/personcreateXXX")
    public String personcreate(@RequestParam(name="email", required=false, defaultValue="example@example.com") String email,
                                @RequestParam(name="password", required=false, defaultValue="123qwerty") String password,
                                Model model) {
        SignUp member = new SignUp();
        member.createMember(email, password);
        //memberList.add(member);
        return "redirect:/person";
    }

    @GetMapping("/personupdateXXX")
    public String personupdate(Model model) {return "personupdate";}


    /*@GetMapping("/eventscreate")
    public String eventscreate(@RequestParam(name="title", required=false, defaultValue="Event Name") String title,
                               @RequestParam(name="date", required=false, defaultValue="0/0/0000") String date,
                               @RequestParam(name="description", required=true, defaultValue = "This event is about...") String description,
                               Model model) {

        Events event = new Events();
        event.detailsCreate(title, date, description);
        eventsList.add(event);
        return "eventscreate";
    }*/

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

