package com.example.sping_portfolio.controllers.aboutTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProfileMvcController implements WebMvcConfigurer {

    @Autowired
    private ProfileSqlRepository repository;

    @GetMapping("/aboutTeam")
    public String profile(Model model) {
        List<Profile> list = repository.listAll();
        model.addAttribute("list", list);
        return "aboutTeam/aboutTeam";
    }

    @GetMapping("/aboutTeamCreate")
    public String profileAdd(Profile profile) {
        return "aboutTeam/aboutTeamCreate";
    }

    @PostMapping("/aboutTeamCreate")
    public String profileSave(@Valid Profile profile, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "aboutTeam/aboutTeamEdit";
        }
        repository.save(profile);
        return "redirect:aboutTeam/aboutTeam";
    }

    @GetMapping ("/aboutTeamEdit/{id}")
    public String aboutTeamEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("profile", repository.get(id));
        return "aboutTeam/aboutTeamEdit";
    }
    @PostMapping("/aboutTeamEdit")
    public String aboutTeamEdit(@Valid Profile profile, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "aboutTeam/aboutTeamEdit";
        }
        repository.save(profile);
        // Redirect to next step
        return "redirect:aboutTeam/aboutTeam";
    }

    @GetMapping("/aboutTeamDelete/{id}")
    public String profileDelete(@PathVariable("id") long id) {
        repository.delete(id);
        return "redirect:aboutTeam/aboutTeam";
    }

    @RequestMapping(value = "/api/aboutTeam/get")
    public ResponseEntity<List<Profile>> getProfiles() {
        return new ResponseEntity<>( repository.listAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/aboutTeam/get/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable long id) {
        return new ResponseEntity<>( repository.get(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/aboutTeam/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProfile(@PathVariable long id) {
        repository.delete(id);
        return new ResponseEntity<>( ""+ id +" deleted", HttpStatus.OK);
    }


    /*
    POST Aa record by Requesting Parameters from URI
     */
    @RequestMapping(value = "/api/aboutTeam/post", method = RequestMethod.POST)
    public ResponseEntity<Object> postProfile(@RequestParam("name") String name,
                                             @RequestParam("role") String role,
                                             @RequestParam("bioText") String bioText) {

        // A person object WITHOUT ID will create a new record
        Profile profile = new Profile(name, role, bioText);
        repository.save(profile);
        return new ResponseEntity<>(name +" is created successfully", HttpStatus.CREATED);
    }
}
