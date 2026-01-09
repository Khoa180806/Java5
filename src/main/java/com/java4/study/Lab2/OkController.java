package com.java4.study.Lab2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ctrl/ok")
public class OkController {

    @GetMapping
    public String m2(Model model) {
        model.addAttribute("methodName", "m2");
        return "Lab2/ok";
    }

    @PostMapping
    public String m1(Model model) {
        model.addAttribute("methodName", "m1");
        return "Lab2/ok";
    }

    @PostMapping(params = "x")
    public String m3(Model model) {
        model.addAttribute("methodName", "m3");
        return "Lab2/ok";
    }
}