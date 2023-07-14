package com.hdwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdwatch.service.AccountsService;

@Controller
public class AccountController {
	@Autowired
	AccountsService accountsService;
	
	@RequestMapping("/security/login/form")
	public String getAccounts(Model model) {
		//model.addAttribute("message","Vui lòng đăng nhập");
		return "account/login";
	}
	
	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
//	        return "redirect:/home";
			return "home";
		}	
	@RequestMapping("/security/login/error")
	public String error(Model model) {
		model.addAttribute("message","Đăng nhập không thành công");
		return "forward:/login/form";
	}

	
	@RequestMapping("/security/logoff")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất!");
		return "home";
	}
	@RequestMapping("/security/register")
	public String newRegister(Model model) {
		return "account/register";
	}
}
