package com.example.sping_portfolio.controllers;

//import com.nighthawk.csa.mvc.database.ModelRepository;
//import com.nighthawk.csa.mvc.database.person.Person;

import com.example.sping_portfolio.database.signup.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/signupAction")
    public String checkAndRegisterPerson(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        repository.save(person);
        repository.addRoleToPerson(person.getEmail(), "Club_Member");
        return "redirect:/signupsuccess";
    }

    @GetMapping("/signupsuccess")
    public String signupSuccess(Model model) {
        return "signupsuccess";
    }

    @GetMapping("/person")
    public String person(Model model) {
        List<Person> list = repository.listAll();
        model.addAttribute("list", list);
        return "person";
    }

    /*  The HTML template Forms and PersonForm attributes are bound
        @return - template for person form
        @param - Person Class
    */
    @GetMapping("/personcreate")
    public String personAdd(Person person) {
        return "mvc/database/personcreate";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param - Person object with @Valid
    @param - BindingResult object
     */
    @PostMapping("/personcreate")
    public String personSave(@Valid Person person, BindingResult bindingResult) {
        // Validation of Decorated PersonForm attributes
        if (bindingResult.hasErrors()) {
            return "mvc/database/personcreate";
        }
        repository.save(person);
        repository.addRoleToPerson(person.getEmail(), "ROLE_STUDENT");
        // Redirect to next step
        return "redirect:/database/person";
    }

    @GetMapping("/database/personupdate/{id}")
    public String personUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", repository.get(id));
        return "mvc/database/personupdate";
    }

    @PostMapping("/database/personupdate")
    public String personUpdateSave(@Valid Person person, BindingResult bindingResult) {
        // Validation of Decorated PersonForm attributes
        if (bindingResult.hasErrors()) {
            return "mvc/database/personupdate";
        }
        repository.save(person);
        repository.addRoleToPerson(person.getEmail(), "ROLE_STUDENT");

        // Redirect to next step
        return "redirect:/database/person";
    }

    @GetMapping("/database/persondelete/{id}")
    public String personDelete(@PathVariable("id") long id) {
        repository.delete(id);
        return "redirect:/database/person";
    }

    @GetMapping("/database/person/search")
    public String person() {
        return "mvc/database/person_search";
    }

}