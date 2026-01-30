package com.java4.study.Lab6.controller;

import com.java4.study.Lab6.dao.ProductDAO;
import com.java4.study.Lab6.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController6 {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/sort")
    public String sort(@RequestParam(value = "field", defaultValue = "price") String field, Model model) {
        // Sắp xếp giảm dần theo field được chọn
        Sort sort = Sort.by(Sort.Direction.DESC, field);
        List<Product> products = productDAO.findAll(sort);
        
        model.addAttribute("products", products);
        model.addAttribute("field", field);
        return "Lab6/sort";
    }

    @GetMapping("/page")
    public String paginate(@RequestParam(value = "p", defaultValue = "0") int p, Model model) {
        // Mỗi trang 5 sản phẩm
        Pageable pageable = PageRequest.of(p, 5);
        Page<Product> page = productDAO.findAll(pageable);
        
        model.addAttribute("page", page);
        return "Lab6/page";
    }
}
