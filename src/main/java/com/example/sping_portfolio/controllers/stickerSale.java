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

    public double getPrice() { return price; }

    public String getPictURL() { return pictURL; }

    public BufferedImage getPicture() throws IOException {
        picture = ImageIO.read(new URL("https://localhost:8080" + pictURL));
        return picture;
    }

    @GetMapping("/stickerSale")
    public String stickerSale(Model model) {
        ArrayList<stickerSale> stickerList = new ArrayList<>();

        stickerSale sticker1 = new stickerSale();
        sticker1.addSticker("Koala", "Help support and protect animals caught in the Australian wildfires!", 5.00, "/images/KoalaSticker.tif");
        stickerList.add(sticker1);
        stickerSale sticker2 = new stickerSale();
        sticker1.addSticker("Acacia tree", "Help support and protect animals caught in the Australian wildfires!", 5.00, "/images/IMG_5047.PNG");
        stickerList.add(sticker2);
        stickerSale sticker3 = new stickerSale();
        sticker1.addSticker("Porcupine", "Help support and protect animals caught in the Australian wildfires!", 5.00, "/images/IMG_5048.PNG");
        stickerList.add(sticker3);

        model.addAttribute("stickerList", stickerList);

        return "stickerSale";
    }



}
