package com.yin.supermarket.controller;

import com.yin.supermarket.service.RegisterPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import java.util.Date;

@Controller
public class RegisterPageController extends HttpServlet {

    @Autowired
    RegisterPageService registerPageService;

    long time = 0;

    @GetMapping("/register")
    public String registerPage() {
        return "system/register";
    }

    @PostMapping("/submit")
    public String submit(String name, String card, String psw, Model model) {

        try {
            registerPageService.saveUserInfo(name, card, psw, time);
        } catch (Exception e) {
            return "system/register";
        }
        model.addAttribute("vip_name", "再试试？");
        return "system/facePage";
    }

    @PostMapping("/facesubmit")
    public String saveFace(String faceInfo, Model model) throws Exception {

        time = new Date().getTime();
        String imgTime = String.valueOf(time);

        String[] split = faceInfo.split(",", 2);
        String imgFilePath = "D:\\supermarket\\" + imgTime + ".jpg";
        if (!registerPageService.GenerateImage(split[1], imgFilePath)) {
            throw new Exception("出错啦");
        }
        String result = registerPageService.readFaceInfo(imgTime);
        model.addAttribute("result", result);
        return "system/register";
    }

    @GetMapping("/deleteAndRegister")
    public String deleteAndRegisterFace(String deleteAndRegister,Model model) {
//        System.out.println(deleteAndRegister);
        if (deleteAndRegister == "") {
            model.addAttribute("vip_name", "请先识别");
            return "system/facePage";
        }else {
            registerPageService.deleteFace(deleteAndRegister);
            return "system/register";
        }
    }
}
