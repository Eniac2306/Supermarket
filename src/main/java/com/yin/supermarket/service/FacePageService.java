package com.yin.supermarket.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class FacePageService {

    public boolean GenerateImage(String imgStr, String imgFilePath) {   //对字节数组字符串进行Base64解码并生成图片
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

    //todo
    public String GetInfo(String imgTime){
        String url = "127.0.0.1:5000/{1}";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class, imgTime);

        return result;

    }
}
