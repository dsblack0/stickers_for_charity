package com.example.sping_portfolio.database.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class ProfileViewController{

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
    public String postProfile(@Valid Profile profile, @RequestParam("pic") MultipartFile file, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "aboutTeam/aboutTeamCreate";
        }
        // A person object WITHOUT ID will create a new record
        String filePath = "uploads/";
        String webPath = "/" + filePath;

        String picFile = webPath + file.getOriginalFilename();

        profile.setPicFile(webPath + file.getOriginalFilename());

        try {
            // Creating the directory to store file
            File dir = new File( filePath );
            if (!dir.exists())
                dir.mkdirs();

            // Create the file on server
            byte[] bytes = file.getBytes();

            // File write alternatives (going with Stream for now as in theory it would be non-blocking)
            String path = filePath + file.getOriginalFilename();
            File serverFile = new File( path );
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            // JPA save
            repository.save(profile);

        } catch (IOException e) {
            e.printStackTrace();        // app stays alive, errors go to run console, /var/log/syslog
        }
        // Validation of Decorated PersonForm attributes

        // Redirect to next step
        return "redirect:/aboutTeam";
    }

    @GetMapping ("/aboutTeamEdit/{id}")
    public String profileEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("profile", repository.get(id));
        return "aboutTeam/aboutTeamEdit";
    }
    @PostMapping("/aboutTeamEdit")
    public String profileEditSave(@Valid Profile profile, @RequestParam("pic") MultipartFile file, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "aboutTeam/aboutTeamEdit";
        }

        String filePath = "uploads/";
        String webPath = "/" + filePath;

        String picFile = webPath + file.getOriginalFilename();

        profile.setPicFile(webPath + file.getOriginalFilename());

        try {
            // Creating the directory to store file
            File dir = new File( filePath );
            if (!dir.exists())
                dir.mkdirs();

            // Create the file on server
            byte[] bytes = file.getBytes();

            // File write alternatives (going with Stream for now as in theory it would be non-blocking)
            String path = filePath + file.getOriginalFilename();
            File serverFile = new File( path );
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            // JPA save
            repository.save(profile);

        } catch (IOException e) {
            e.printStackTrace();        // app stays alive, errors go to run console, /var/log/syslog
        }

        // Redirect to next step
        return "redirect:/aboutTeam";
    }

    @GetMapping("/aboutTeamDelete/{id}")
    public String profileDelete(@PathVariable("id") long id) {
        repository.delete(id);
        return "redirect:/aboutTeam";
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
                                              @RequestParam("bioText") String bioText,
                                              @RequestParam("pic") MultipartFile file, Model modelMap) {

        // A person object WITHOUT ID will create a new record
       /* String filePath = "uploads/";
        String webPath = "/" + filePath;

        String picFile = webPath + file.getOriginalFilename();

        Profile profile = new Profile(name, role, bioText, picFile);
        profile.setName(name);
        profile.setRole(role);
        profile.setBioText(bioText);
        profile.setPicFile(webPath + file.getOriginalFilename());

        try {
            // Creating the directory to store file
            File dir = new File( filePath );
            if (!dir.exists())
                dir.mkdirs();

            // Create the file on server
            byte[] bytes = file.getBytes();

            // File write alternatives (going with Stream for now as in theory it would be non-blocking)
                String path = filePath + file.getOriginalFilename();
                File serverFile = new File( path );
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();


            // JPA save
            repository.save(profile);

        } catch (IOException e) {
            e.printStackTrace();        // app stays alive, errors go to run console, /var/log/syslog
        } */


        return new ResponseEntity<>(name +" is created successfully", HttpStatus.CREATED);
    }
}