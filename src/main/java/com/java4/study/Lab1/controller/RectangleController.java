package com.java4.study.Lab1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rectangle")
public class RectangleController {
    
    @GetMapping("/form")
    public String showForm() {
        return "/Lab1/rectangle";
    }
    
    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("length") double length,
            @RequestParam("width") double width,
            Model model) {
        
        // Tính diện tích và chu vi
        double area = length * width;
        double perimeter = 2 * (length + width);
        
        // Làm tròn đến 2 chữ số thập phân
        area = Math.round(area * 100.0) / 100.0;
        perimeter = Math.round(perimeter * 100.0) / 100.0;
        
        // Thêm kết quả vào model
        model.addAttribute("area", area);
        model.addAttribute("perimeter", perimeter);
        
        return "/Lab1/rectangle";
    }
}
