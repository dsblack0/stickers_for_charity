package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@Controller
public class aboutTeam implements Serializable {
    String name;
    String role;
    String bioText;
    String picURL;
    BufferedImage pic;

    public void profileCreate(String n, String r, String b, String pu) {
        name = n;
        role = r;
        bioText = b;
        picURL = pu;
    }

    public String getName() { return name; }

    public String getRole() { return role; }

    public String getBioText() { return bioText; }

    public BufferedImage getPic() throws IOException {
        pic = ImageIO.read(new URL(picURL));
        return pic; }

    @GetMapping("/aboutTeam")
    public String aboutTeam(Model model) {

        aboutTeam profile1 = new aboutTeam();
     //   profile1.profileCreate("Arushi Bharadwaj", "President", "Hi! I’m responsible for coordinating SFC events and making sure our club is able to serve as many people as possible through art. In my free time, I like to write, go to the beach, and read. My favorite things about SFC are the open-minded atmosphere and everybody’s willingness to collaborate with each other.", ImageIO.read(new URL(this.url)));
        aboutTeam profile2 = new aboutTeam();
        aboutTeam profile3 = new aboutTeam();
        aboutTeam profile4 = new aboutTeam();
        aboutTeam profile5 = new aboutTeam();

        return "aboutTeam";
    }
}
