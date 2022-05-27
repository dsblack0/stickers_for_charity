package com.example.sping_portfolio.controllers;

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
public class stickerSale implements Serializable {
    String stickName;
    String info;
    double price;
    String pictURL;
    BufferedImage picture;

    public void addSticker(String n, String i, double p, String pic) {
        stickName = n;
        info = i;
        price = p;
        pictURL = pic;
    }

    public String getStickName() { return stickName; }

    public String getInfo() { return info; }

    public String getPrice() { return "$" + price + "0"; }

    public String getPictURL() { return pictURL; }

    public BufferedImage getPicture() throws IOException {
        picture = ImageIO.read(new URL("https://localhost:8080" + pictURL));
        return picture;
    }

    @GetMapping("/stickerSale")
    public String stickerSale(Model model) {
        ArrayList<stickerSale> stickerList = new ArrayList<>();

        stickerSale sticker2 = new stickerSale();
        sticker2.addSticker("Acacia tree", "Help support and protect animals caught in the Australian wildfires!", 5.00, "/images/IMG_5047.PNG");
        stickerList.add(sticker2);
        stickerSale sticker3 = new stickerSale();
        sticker3.addSticker("Porcupine", "Help support and protect animals caught in the Australian wildfires!", 5.00, "/images/IMG_5048.PNG");
        stickerList.add(sticker3);
        stickerSale sticker4 = new stickerSale();
        sticker4.addSticker("Kiwi", "Help support and protect animals caught in the Australian wildfires!", 5.00, "/images/IMG_5049.PNG");
        stickerList.add(sticker4);
        stickerSale sticker5 = new stickerSale();
        sticker5.addSticker("Spider Flower", "Help support and protect animals caught in the Australian wildfires!", 5.00, "/images/Spider Flower Sticker.jpeg");
        stickerList.add(sticker5);
        stickerSale sticker6 = new stickerSale();
        sticker6.addSticker("Dingo", "Help support and protect animals caught in the Australian wildfires!", 5.00, "/images/Untitled95.png");
        stickerList.add(sticker6);
        stickerSale sticker7 = new stickerSale();
        sticker7.addSticker("Crocodile", "Help support and protect animals caught in the Australian wildfires!", 5.00, "/images/Untitled96.png");
        stickerList.add(sticker7);

        model.addAttribute("stickerList", stickerList);

        return "stickerSale";
    }



}
