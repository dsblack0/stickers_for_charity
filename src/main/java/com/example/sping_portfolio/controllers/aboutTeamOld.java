/*package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

@Controller
public class aboutTeamOld implements Serializable {
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
        String web_server = "http://localhost:8080";
        pic = ImageIO.read(new URL(web_server+picURL));
        return pic; }

    public String getPicURL() {
        return picURL;
    }

    @GetMapping("/aboutTeam")
    public String aboutTeam(Model model) {

        ArrayList<aboutTeamOld> profileList = new ArrayList<>();

        aboutTeamOld profile1 = new aboutTeamOld();
        profile1.profileCreate("Arushi Bharadwaj", "President", "Hi! I’m responsible for coordinating SFC events and making sure our club is able to serve as many people as possible through art. In my free time, I like to write, go to the beach, and read. My favorite things about SFC are the open-minded atmosphere and everybody’s willingness to collaborate with each other.", "/images/picArushi.jpg");
        profileList.add(profile1);
        aboutTeamOld profile2 = new aboutTeamOld();
        profile2.profileCreate("Kaavya Uppala", "Secretary", "Hi, I’m Kaavya and I’m responsible for keeping track of all we accomplish during our meetings. In addition to that, I also work closely with our President to ensure that everything is going smoothly between our various departments. I love creating art for our club and teaching in our workshops. One of my favorite things about SFC is everyone's willingness to help others in need.", "/images/Logo.png");
        profileList.add(profile2);
        aboutTeamOld profile3 = new aboutTeamOld();
        profile3.profileCreate("Shreya Shahane", "Chief Marketing Executive", "Hi, I’m Shreya, I’m responsible for planning, developing, and monitoring the overall marketing strategies for our club. I also make sticker designs for fundraisers, make flyers and social media posts, as well as help organize events for the club. I was one of the core founders of the club, and my favorite thing about SFC is everyone’s enthusiasm for different events!", "/images/picShreya.png");
        profileList.add(profile3);
        aboutTeamOld profile4 = new aboutTeamOld();
        profile4.profileCreate("Anshi Boreddy", "Social Media Director", "Hello, I’m Anshi and I am responsible for spreading the news about stickers for charity using social media. Using Instagram and other platforms, I plan on spreading the beauty of stickers for charity to the public and sharing information on what’s going on inside the club. I really enjoy the enthusiasm that we share together to help others that are less fortunate than us.", "/images/Logo.png");
        profileList.add(profile4);
        aboutTeamOld profile5 = new aboutTeamOld();
        profile5.profileCreate("Prisha Boreddy", "Art Lead", "Hello, my name is Prisha and I am responsible for coordinating the art related events our club hosts. I have also helped design stickers and have led some of the workshops we have presented. One of my favorite things about this club is how friendly the members are and how willing they are to share what they can with those around them.", "/images/picPrisha.jpg");
        profileList.add(profile5);

        model.addAttribute("profileList", profileList);

        return "aboutTeam/aboutTeam";
    }
}*/
