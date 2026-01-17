package com.java4.study.Lab4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping({"/", "/home/index"})
    public String index(
            @RequestParam(name = "lang", required = false) String lang,
            Model model
    ) {
        model.addAttribute("title", "#{menu.home} - #{header.name}");
        model.addAttribute("content", "home");
        return "/Lab4/layout";
    }

    @GetMapping("/home/about")
    public String about(
            @RequestParam(name = "lang", required = false) String lang,
            Model model
    ) {
        model.addAttribute("title", "#{menu.about} - #{header.name}");
        model.addAttribute("content", "about");
        return "/Lab4/layout";
    }
}