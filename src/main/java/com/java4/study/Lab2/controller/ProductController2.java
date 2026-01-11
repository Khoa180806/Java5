package com.java4.study.Lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController2 {
    @GetMapping("/product/form2")
    public String form(Model model) {
        Product p = new Product();
        p.setName("iPhone 30");
        p.setPrice(5000);
        model.addAttribute("product", p);
        return "Lab2/form2";
    }
    @PostMapping("/product/save2")
    public String save(@ModelAttribute("product") Product p, Model model) {
        model.addAttribute("savedProduct", p);
        return "Lab2/form2";
    }
    @ModelAttribute("items")
    public List<Product> getItems() {
        return Arrays.asList(new Product("A", 1), new Product("B", 12));
    }
}
