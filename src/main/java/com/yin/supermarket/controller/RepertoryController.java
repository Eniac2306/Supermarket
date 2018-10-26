package com.yin.supermarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/repertory")
public class RepertoryController {
    @GetMapping("/page")
    public String getPage(){
        return "system/facerepertory";
    }
}
