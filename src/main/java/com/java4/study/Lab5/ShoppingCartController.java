package com.java4.study.Lab5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;
    
    @GetMapping("/cart/index")
    public String index(Model model) {
        model.addAttribute("items", shoppingCartService.getItems());
        model.addAttribute("count", shoppingCartService.getCount());
        model.addAttribute("amount", shoppingCartService.getAmount());
        return "/Lab5/cart/index";
    }
    
    @GetMapping("/cart/add/{id}")
    public String add(@PathVariable("id") Integer id, RedirectAttributes params) {
        shoppingCartService.add(id);
        params.addAttribute("message", "Đã thêm sản phẩm vào giỏ hàng!");
        return "redirect:/cart/index";
    }
    
    @GetMapping("/cart/remove/{id}")
    public String remove(@PathVariable("id") Integer id, RedirectAttributes params) {
        shoppingCartService.remove(id);
        params.addAttribute("message", "Đã xóa sản phẩm khỏi giỏ hàng!");
        return "redirect:/cart/index";
    }
    
    @PostMapping("/cart/update")
    public String update(@RequestParam("id") Integer id, 
                        @RequestParam("qty") Integer qty,
                        RedirectAttributes params) {
        shoppingCartService.update(id, qty);
        params.addAttribute("message", "Đã cập nhật số lượng sản phẩm!");
        return "redirect:/cart/index";
    }
    
    @GetMapping("/cart/clear")
    public String clear(RedirectAttributes params) {
        shoppingCartService.clear();
        params.addAttribute("message", "Đã xóa sạch giỏ hàng!");
        return "redirect:/cart/index";
    }
}
