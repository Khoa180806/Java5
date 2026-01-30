package com.java4.study.Lab6.controller;

import com.java4.study.Lab6.dao.CategoryDAO;
import com.java4.study.Lab6.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryDAO categoryDAO;

    // Hiển thị trang chính với danh sách và form
    @GetMapping("/index")
    public String index(Model model) {
        List<Category> categories = categoryDAO.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());
        return "Lab6/index";
    }

    // Hiển thị form tạo mới
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryDAO.findAll());
        return "Lab6/index";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Optional<Category> category = categoryDAO.findById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
        } else {
            model.addAttribute("category", new Category());
            model.addAttribute("error", "Không tìm thấy loại hàng!");
        }
        model.addAttribute("categories", categoryDAO.findAll());
        return "Lab6/index";
    }

    // Xử lý tạo mới hoặc cập nhật
    @PostMapping("/save")
    public String save(@ModelAttribute("category") Category category, Model model) {
        try {
            categoryDAO.save(category);
            model.addAttribute("message", "Lưu thành công!");
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi: " + e.getMessage());
        }
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryDAO.findAll());
        return "Lab6/index";
    }

    // Xử lý xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        try {
            categoryDAO.deleteById(id);
            model.addAttribute("message", "Xóa thành công!");
        } catch (Exception e) {
            model.addAttribute("error", "Không thể xóa! Loại hàng đang được sử dụng.");
        }
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryDAO.findAll());
        return "Lab6/index";
    }
}
