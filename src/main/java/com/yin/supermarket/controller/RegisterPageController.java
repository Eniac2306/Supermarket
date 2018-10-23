package com.yin.supermarket.controller;

import com.yin.supermarket.service.RegisterPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class RegisterPageController {

    @Autowired
    RegisterPageService registerPageService;

    @GetMapping("/register")
    public String registerPage(){
        return "system/register";
    }

    @PostMapping("/submit")
    public String submit(){

        return null;
    }

    @PostMapping("/facesubmit")
    public String saveFace(String faceInfo, Model model) throws Exception {

        long Time = new Date().getTime();
        String imgTime = String.valueOf(Time);

        String[] split = faceInfo.split(",", 2);
        String imgFilePath = "D:\\supermarket\\" + imgTime + ".jpg";
        if (!registerPageService.GenerateImage(split[1], imgFilePath)) {
            throw new Exception("出错啦");
        }
        String result = registerPageService.saveFaceInfo(imgTime);
        model.addAttribute("result", result);
        return "system/register";
    }
}