package com.yin.supermarket.service;


import com.yin.supermarket.dao.IFaceRepository;
import com.yin.supermarket.entity.Face;
import org.bson.internal.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class RegisterPageService {

    @Autowired
    IFaceRepository faceRepository;

    String result = "";

    public boolean GenerateImage(String imgStr, String imgFilePath) {
        if (imgStr == null) //图像数据为空
            return false;
        try {
            //Base64解码
            byte[] b = Base64.decode(imgStr);
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


    public String readFaceInfo(String imgTime) throws HttpServerErrorException {
        String url = "http://127.0.0.1:5000/{1}";
        RestTemplate restTemplate = new RestTemplate();
        String temp = restTemplate.getForObject(url, String.class, imgTime); //url,返回类型，url{imgTime}
        result = temp.substring(2, temp.length() - 2);
//        System.out.println(result);
        return "人像记录成功!请继续填写信息->";
    }

    public void saveUserInfo(String name, String cardNum, String psw, long filename) {

        faceRepository.save(new Face(name,cardNum,psw,result,filename+".jpg"));
        System.out.println(result);
    }
}
