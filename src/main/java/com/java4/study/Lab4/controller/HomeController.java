package com.java4.study.Lab4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/home/index")
    public String index(Model model) {
        model.addAttribute("title", "Trang chủ - Online Shopping");
        model.addAttribute("content", "home");
        return "/Lab4/layout";
    }

    @GetMapping("/home/about")
    public String about(Model model) {
        model.addAttribute("title", "Giới thiệu - Online Shopping");
        model.addAttribute("content", "about");
        return "/Lab4/layout";
    }
}