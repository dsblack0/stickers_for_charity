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
public class EventsController implements Serializable {
    String title;
    String date;
    String description;

    public void detailsCreate(String t, String d, String de) {
        title = t;
        date = d;
        description = de;
    }

    public String getTitle() { return title; }

    public String getDate() { return date; }

    public String getDescription() { return description; }

    @GetMapping("/events")
    public String events(Model model) {

        ArrayList<EventsController> eventsList = new ArrayList<>();

        EventsController event1 = new EventsController();
        event1.detailsCreate("OVMS Creative Arts Club meeting", "05/06/21", "Taught members of the OVMS Creative Arts Club about character drawing.");
        eventsList.add(event1);

        EventsController event2 = new EventsController();
        event1.detailsCreate("OVMS Creative Arts Club meeting", "05/06/21", "");
        eventsList.add(event2);

        model.addAttribute("eventList", eventsList);

        return "events";
    }
}
