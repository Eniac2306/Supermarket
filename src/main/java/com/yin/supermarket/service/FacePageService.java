package com.yin.supermarket.service;

import com.yin.supermarket.dao.IFaceRepository;
import com.yin.supermarket.entity.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class FacePageService {

    @Autowired
    IFaceRepository faceRepository;

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

    public Face getInfo(String imgTime) throws HttpServerErrorException {
        String url = "http://127.0.0.1:5000/{1}";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class, imgTime); //这里是get方法，post为postForObject()
        String[] substring = result.substring(2, result.length() - 2).split(", ");
        List<Double> getfeatures = Arrays.stream(substring).map(Double::valueOf).collect(toList());

        return identify(getfeatures);
    }

    private Face identify(List<Double> getfeatures) {
        List<Face> features = faceRepository.findAll();
        double min = 1;
        Face userFace = null;
        for (Face face : features) {
            String[] feature = face.getFeature().split(", ");
            List<Double> savedfeatures = Arrays.stream(feature).map(Double::valueOf).collect(toList());
            double temp = 0;
            for (int i = 0; i < savedfeatures.size(); i++) {
                double v = savedfeatures.get(i) - getfeatures.get(i);
                temp += v * v;
            }
            temp = Math.sqrt(temp);
            if (temp < 0.9 && temp < min) {
                min = temp;
//                name = face.getName();
                userFace = face;
            }
            System.out.println(temp);
        }
        return userFace;
    }
}
