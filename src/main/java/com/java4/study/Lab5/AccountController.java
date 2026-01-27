package com.java4.study.Lab5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {
        @Autowired
        CookieService cookieService;
        @Autowired
        ParamService paramService;
        @Autowired
        SessionService sessionService;

        @GetMapping("/account/login")
        public String login1(org.springframework.ui.Model model) {
                // Đọc username từ cookie nếu có
                String savedUsername = cookieService.getValue("user");
                if (!savedUsername.isEmpty()) {
                        model.addAttribute("savedUsername", savedUsername);
                }
                return "/Lab5/login";
        }

        @PostMapping("/account/login")
        public String login2(RedirectAttributes params) {
                // Đọc các tham số từ request
                String un = paramService.getString("username", "");
                String pw = paramService.getString("password", "");
                boolean rm = paramService.getBoolean("remember", false);
                
                // Kiểm tra đăng nhập thành công (un="poly", pw="123")
                if ("poly".equals(un) && "123".equals(pw)) {
                        // Lưu username vào session
                        sessionService.set("username", un);
                        
                        // Xử lý ghi nhớ tài khoản
                        if (rm) {
                                // Ghi nhớ tài khoản 10 ngày
                                cookieService.add("user", un, 240); // 10 ngày = 240 giờ
                        } else {
                                // Xóa cookie tài khoản đã ghi nhớ trước đó
                                cookieService.remove("user");
                        }
                        
                        // Thêm thông báo thành công
                        params.addAttribute("message", "Đăng nhập thành công!");
                        return "redirect:/account/login";
                } else {
                        // Thông báo lỗi
                        params.addAttribute("message", "Sai username hoặc password!");
                        return "redirect:/account/login";
                }
        }
}
