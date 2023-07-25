package com.hdwatch.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	//Trang đăng nhập
	@RequestMapping("/login/form")
	public String loginForm(Model model,Principal principal) {
		//Tiêu đề trang
		model.addAttribute("pageTitle", "Đăng nhập");
		// Kiểm tra xem người dùng đã đăng nhập chưa
	    if (principal != null) {
	        // Nếu đã đăng nhập, hiển thị thông báo khác
	        return "redirect:/home";
	    } else {
	        // Nếu chưa đăng nhập, hiển thị thông báo mặc định
	        model.addAttribute("message", "Vui lòng đăng nhập");
	    }

		return "account/login";
	}
	
	//Đăng nhập thành công
	@RequestMapping("/login/success")
	public String loginSuccess(Model model,Principal principal) {
		//Tiêu đề trang
		model.addAttribute("pageTitle", "Đăng nhập thành công!");
		// Kiểm tra xem người dùng đã đăng nhập chưa
	    if (principal != null) {
	        // Nếu đã đăng nhập, hiển thị thông báo khác
	        return "redirect:/home";
	    } else {
	        // Nếu chưa đăng nhập, hiển thị thông báo mặc định
	        model.addAttribute("message", "Đăng nhập thành công!");
	    }
		return "redirect:/home";
	}
	
	
	//Đăng nhập thất bại 
	@RequestMapping("/login/error")
	public String loginError(Model model, Principal principal) {
		//Tiêu đề trang
		model.addAttribute("pageTitle", "Đăng nhập thất bại!");
		// Kiểm tra xem người dùng đã đăng nhập chưa
	    if (principal != null) {
	        // Nếu đã đăng nhập, hiển thị thông báo khác
	        return "redirect:/home";
	    } else {
	        // Nếu chưa đăng nhập, hiển thị thông báo mặc định
	        model.addAttribute("message", "Đăng nhập thất bại!");
	    }
		return "account/login";
	}
	
	
	//Không có quyền truy xuất
	@RequestMapping("/unauthoried")
	public String unauthoried(Model model) {
		//Thông báo
		model.addAttribute("message", "Không có quyền truy xuất!");
		return "account/login";
	}
	
	//Đăng xuất
	@RequestMapping("/logoff/success")
	public String logoffSuccess(Model model, Principal principal) {
		//Tiêu đề trang
		model.addAttribute("pageTitle", "Đăng xuất thành công!");
		if (principal != null) {
	        // Nếu đã đăng nhập, hiển thị thông báo khác
	        return "redirect:/home";
	    } else {
	        // Nếu chưa đăng nhập, hiển thị thông báo mặc định
	    	model.addAttribute("message", "Bạn đã đăng xuất!");
	    }
		return "account/login";
	}
}
