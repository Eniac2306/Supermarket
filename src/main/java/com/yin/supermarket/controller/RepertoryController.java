package com.yin.supermarket.controller;

import com.yin.supermarket.entity.Face;
import com.yin.supermarket.service.RepertoryService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class RepertoryController {

    @Autowired
    RepertoryService repertoryService;

    @GetMapping("/repertory")
    public String getPage(Model model) {
        List<Face> allFace = repertoryService.getAllFace();
        model.addAttribute("list", allFace);
        return "system/facerepertory";
    }

    /**
     * 文件转比特流返回前端接收
     */
    @GetMapping("/showImg")
    public ResponseEntity<byte[]> getImg(String path) throws IOException {
        File file = new File("D:\\supermarket\\" + path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(String path, Model model) {
        model.addAttribute("path", path);
        return "system/test";
    }

    @GetMapping("/select")
    public String selectFace(String name_or_id_num,Model model){
        List<Face> selectedFaces = repertoryService.selectedFace(name_or_id_num);
        model.addAttribute("list",selectedFaces);
        return "system/facerepertory";
    }
}
