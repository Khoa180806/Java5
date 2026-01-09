package com.java4.study.Lab2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("product", new Product());
        return "Lab2/formProduct";
    }
    
    @PostMapping("/save")
    public String save(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("product", product);
        return "Lab2/formProduct";
    }
}
