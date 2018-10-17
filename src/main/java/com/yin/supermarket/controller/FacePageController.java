package com.yin.supermarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;

@Controller
public class FacePageController {
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/")
    public String facePage() {
        return "system/facePage";
    }

    @PostMapping(path = "/getImg")
    public String getImg(String imgData) throws Exception {
        String[] split = imgData.split(",", 2);
        System.out.println(split.length);
        if (!GenerateImage(split[1], "D:\\test\\" + "test.jpg")) {
            throw new Exception("出错啦啦啦啦");
        }
        return "system/facePage";
    }

    boolean GenerateImage(String imgStr, String imgFilePath) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}