package com.example.sping_portfolio.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.Base64;

@Controller
public class CustomStickers {

    @GetMapping("/CustomStickers")
    public String CustomStickers() { return "/CustomStickers"; }

    @PostMapping("/CustomStickers")
    public String CustomStickers(@RequestParam(name="name", required=false, defaultValue = "") String name,
                                 @RequestParam(name="email", required=false, defaultValue = "") String email,
                                 @RequestParam(name="phone", required=false, defaultValue = "") String phone,
                                 @RequestParam(name="description", required=false, defaultValue = "") String description,
                                 @RequestParam(name="size", required=false, defaultValue="2 in x 2 in") String size,
                                 @RequestParam(name="quantity", required=false, defaultValue = "1") int quantity,
                                 @RequestParam(name="pic", required = false) MultipartFile file,
                                 Model model)
            throws IOException, InterruptedException, ParseException {

        String picFile = "uploads/Logo.png";
        byte [] encImage = null;
        //uploads
        if(file != null){
            String filePath = "uploads/";
            String webPath = "/" + filePath;
            picFile = webPath + file.getOriginalFilename();
            try {
                // Creating the directory to store file
                File dir = new File( filePath );
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                byte[] bytes = file.getBytes();

                encImage = Base64.getEncoder().encode(bytes);

                // File write alternatives (going with Stream for now as in theory it would be non-blocking)
                String path = filePath + file.getOriginalFilename();
                File serverFile = new File( path );
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            } catch (IOException e) {
                e.printStackTrace();        // app stays alive, errors go to run console, /var/log/syslog
            }
        }

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
                "\"subject\": \"Custom Sticker Order from "+ name + "\"}]," +
                "\"from\": {\"email\": \"from_address@example.com\"}," +
                "\"content\": [{" +
                "\"type\": \"text/plain\"," +
                "\"value\": \"Full Name: " + name +
                " Email: " + email +
                " Phone #: " + phone +
                " Description: " + description +
                " Size: " + size +
                " Quantity: " + quantity + "\"}]" +
                "\"attachments\": [{" +
                "\"type\": \"image/png\"," +
                "\"disposition\": \"attachment\"," +
                "\"content\": \"" + encImage + "\"," +
                "\"filename\"" + file + "}]" + "}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        System.out.println(response);
        System.out.println(request);

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);
        model.addAttribute("description", description);
        model.addAttribute("size", size);
        model.addAttribute("quantity", quantity);
        return "/CustomStickers";
    }
}
