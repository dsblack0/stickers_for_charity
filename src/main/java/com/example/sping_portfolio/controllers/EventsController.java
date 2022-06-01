package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;
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
    public String events(Model model){
        ArrayList<EventsController> eventsList = new ArrayList<>();

        EventsController event1 = new EventsController();
        event1.detailsCreate("Perspective Drawing Workshop", "04/12/21, 04/14/21, and 04/16/21", "Organized a workshop to teach kids about perspective drawing. Workshop included a mini-lesson about perspective drawing and live instruction to guide the kids to sketch and color their own perspective drawing.");
        eventsList.add(event1);

        EventsController event2 = new EventsController();
        event2.detailsCreate("Comic Drawing Workshop", "04/12/21, 04/14/21, and 04/16/21", "Organized a workshop to teach kids about comics. Each workshop day included a mini-lesson about comics and live instruction to guide the kids to sketch and color their own drawings. The sub-topics for each day were backgrounds, cartoons, and coloring.");
        eventsList.add(event2);

        EventsController event3 = new EventsController();
        event3.detailsCreate("The A.C.E. Club Collab", "05/05/21", "Drew a drawing that inspires people to reach out and help each other out and submitted it to The A.C.E. Club, so they could use it to promote their club’s message about mental health.");
        eventsList.add(event3);

        EventsController event4 = new EventsController();
        event4.detailsCreate("Feeding America Fundraiser", "12/20/21 to 1/27/22", "Drew winter themed sticker designs and sold at Monterey Ridge, Baskin Robbins, Sprouts, and Stone Ranch. Also had an online Google Form to place orders. All proceeds went to Feeding America.");
        eventsList.add(event4);

        EventsController event5 = new EventsController();
        event5.detailsCreate("OVMS Creative Arts Club Meeting", "05/06/21", "Taught members of the OVMS Creative Arts Club about character drawing.");
        eventsList.add(event5);

        model.addAttribute("eventsList",eventsList);
        return "events";}
}
