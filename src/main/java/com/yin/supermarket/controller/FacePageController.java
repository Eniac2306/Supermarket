package com.yin.supermarket.controller;

import com.yin.supermarket.service.FacePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class FacePageController {
    /**
     * 首页
     *
     * @return
     */
    @Autowired
    FacePageService facePageService;

    @RequestMapping("/")
    public String facePage() {
        return "system/facePage";
    }


    @PostMapping(path = "/getImg")
    public String getImg(String imgData, Model model) throws Exception {
        long Time = new Date().getTime();
        String imgTime = String.valueOf(Time);

        String[] split = imgData.split(",", 2);
        String imgFilePath = "D:\\supermarket\\" + imgTime + ".jpg";
        if (!facePageService.GenerateImage(split[1], imgFilePath)) {
            throw new Exception("出错啦");
        }
        try {
            String userInfo = facePageService.getInfo(imgTime);
            if (userInfo == null) {
                userInfo = "查无此人";
            }
            model.addAttribute("vip", userInfo); //model传了一个键值对交给前台接收
            return "system/facePage";
        } catch (Exception e) {
            System.out.println(e.toString());
            return "system/facePage";
        }
    }
}