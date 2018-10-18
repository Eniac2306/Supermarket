package com.yin.supermarket.controller;

import com.yin.supermarket.service.FacePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
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
        System.out.println(split.length);
        String imgFilePath = "D:\\supermarket\\" + imgTime + ".jpg";
        if (!facePageService.GenerateImage(split[1], imgFilePath)) {
            throw new Exception("出错啦");
        }
        String userInfo = facePageService.GetInfo(imgTime);
        if (userInfo == null) {
            userInfo = "查无此人";
        }
        model.addAttribute("vip", userInfo); //model传了一个键值对交给前台接收
        return "system/facePage";
    }


}