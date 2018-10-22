package com.yin.supermarket.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class FacePageService {
    /**
     * 对字节数组字符串进行Base64解码并生成图片
     */
    public boolean GenerateImage(String imgStr, String imgFilePath) {
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


    /**
     * 获取Python服务返回的向量
     */

    public String getVector(String imgTime) {
        String url = "http://127.0.0.1:5000/{1}";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class, imgTime); //url,返回类型，url{imgTime}
        String[] substring = result.substring(2, result.length() - 2).split(", ");
        List<Double> list = Arrays.stream(substring).map(Double::valueOf).collect(toList());


        return identify(list);
    }

    private String identify(List<Double> list) {
        return null;
    }

}
