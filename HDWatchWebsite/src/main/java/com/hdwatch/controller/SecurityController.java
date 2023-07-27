package com.hdwatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	//Trang đăng nhập
	@RequestMapping("/login/form")
	public String loginForm(Model model) {
		
		//Tiêu đề trang
		model.addAttribute("pageTitle", "Đăng nhập");
		//Thông báo
		model.addAttribute("message", "Vui lòng đăng nhập");
		return "account/login";
	}
	
	//Đăng nhập thành công
	@RequestMapping("/login/success")
	public String loginSuccess(Model model) {
		//Tiêu đề trang
		model.addAttribute("pageTitle", "Đăng nhập thành công!");
		//Thông báo
		model.addAttribute("message", "Đăng nhập thành công!");
		return "redirect:/home";
	}
	
	
	//Đăng nhập thất bại 
	@RequestMapping("/login/error")
	public String loginError(Model model) {
		//Tiêu đề trang
		model.addAttribute("pageTitle", "Đăng nhập thất bại!");
		//Thông báo
		model.addAttribute("message", "Đăng nhập thất bại!");
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
	public String logoffSuccess(Model model) {
		//Tiêu đề trang
		model.addAttribute("pageTitle", "Đăng xuất thành công!");
		model.addAttribute("message", "Bạn đã đăng xuất!");
		return "account/login";
	}
}
