package com.example.sping_portfolio.controllers;

//import com.nighthawk.csa.mvc.database.ModelRepository;
//import com.nighthawk.csa.mvc.database.person.Person;

import com.example.sping_portfolio.database.signup.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// Built using article: https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html
// or similar: https://asbnotebook.com/2020/04/11/spring-boot-thymeleaf-form-validation-example/
@Controller
public class SignUpController {
    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD
    @Autowired
    private ModelRepository repository;

    @GetMapping("/clubMembers")
    public String members(Model model) {
        List<Person> list = repository.listAll();
        model.addAttribute("list", list);
        return "signup/clubMembers";
    }

    @GetMapping("/signup")
    public String memberAdd(Person person) {
        return "signup/signup";
    }

    @PostMapping("/signup")
    public String checkAndRegisterPerson(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup/signup";
        }
        repository.save(person);
        repository.addRoleToPerson(person.getName(), "Club_Member");
        return "redirect:/signupSuccess";
    }

    @GetMapping("/signupSuccess")
    public String signupSuccess(Model model) {
        return "signup/signupSuccess";
    }


    @GetMapping("/memberDelete/{id}")
    public String personDelete(@PathVariable("id") long id) {
        repository.delete(id);
        return "redirect:/clubMembers";
    }
}