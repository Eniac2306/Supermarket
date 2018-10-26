package com.yin.supermarket.controller;

import com.yin.supermarket.entity.Face;
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
    public String facePage(Model model) {
        model.addAttribute("vip_name", "欢迎光临");
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
            Face userFace = facePageService.getInfo(imgTime);
            if (userFace == null) {
                model.addAttribute("vip_name", "查无此人哦~");
            } else {
                model.addAttribute("vip_name", userFace.getName()); //model传了一个键值对交给前台接收
//                model.addAttribute("vip_age", userFace.getAge());
//                model.addAttribute("vip_sex", userFace.getSex());
                model.addAttribute("vip_id",userFace.getId_num());
                model.addAttribute("vip_money",userFace.getMoney());
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("vip_name", "欢迎光临");
            return "system/facePage";
        }
        return "system/facePage";
    }


}