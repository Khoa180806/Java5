package com.java4.study.Lab5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemController {
    @Autowired
    ShoppingCartService shoppingCartService;
    
    @GetMapping("/item/index")
    public String index(Model model) {
        model.addAttribute("items", DB.items.values());
        model.addAttribute("cartCount", shoppingCartService.getCount());
        return "/Lab5/item/index";
    }
    
    @GetMapping("/item/add/{id}")
    public String add(@PathVariable("id") Integer id, RedirectAttributes params) {
        shoppingCartService.add(id);
        params.addAttribute("message", "Đã thêm sản phẩm vào giỏ hàng!");
        return "redirect:/item/index";
    }
}
