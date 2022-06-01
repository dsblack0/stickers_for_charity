package com.example.sping_portfolio.controllers;

import com.example.sping_portfolio.database.signup.SignUp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private String signUpErrorMessage = "";

    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/donations")
    public String donations(Model model) { return "donations"; }

    @GetMapping ("/reviews")
    public String reviews(Model model) {return "reviews";}

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

