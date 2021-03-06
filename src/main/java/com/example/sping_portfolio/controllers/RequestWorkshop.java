package com.example.sping_portfolio.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;

@Controller
public class RequestWorkshop {

    @GetMapping("/RequestWorkshop")
    public String CustomStickers(@RequestParam(name="name", required=false, defaultValue = "") String name,
                                 @RequestParam(name="email", required=false, defaultValue = "") String email,
                                 @RequestParam(name="phone", required=false, defaultValue = "") String phone,
                                 @RequestParam(name="description", required=false, defaultValue = "") String description,
                                 Model model)
            throws IOException, InterruptedException, ParseException {
        //api
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://rapidprod-sendgrid-v1.p.rapidapi.com/mail/send"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Host", "rapidprod-sendgrid-v1.p.rapidapi.com")
                .header("X-RapidAPI-Key", "5160560674msh71fe7bcad890509p159e0ajsnc0f73e8a4ad9")
                .method("POST", HttpRequest.BodyPublishers.ofString
                        ("{" +
                                "\"personalizations\": [ " +
                                "{\"to\": [ {\"email\": \"kkutti@outlook.com\"}]," +
                                "\"subject\": \"Custom Sticker Order from " + name + "\"}]," +
                                "\"from\": {\"email\": \"from_address@example.com\"}," +
                                "\"content\": [{" +
                                "\"type\": \"text/plain\"," +
                                "\"value\": \"Full Name: " + name +
                                " Email: " + email +
                                " Phone #: " + phone +
                                " Description: " + description + "\"}]}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        System.out.println(response);
        System.out.println(request);

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);
        model.addAttribute("description", description);
        return "RequestWorkshop";
    }
}
