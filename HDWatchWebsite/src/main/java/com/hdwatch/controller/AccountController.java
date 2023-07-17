package com.hdwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hdwatch.entity.Accounts;
import com.hdwatch.service.AccountsService;

@Controller
public class AccountController {
	@Autowired
	AccountsService accountService;
	
	@RequestMapping("/account/info")
	public String showAccountInfomation(Model model) {
		//Hiện thị thông tin khách hàng
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Accounts user = accountService.findById(username);
        model.addAttribute("user", user);
        //Tiêu đề trang
        model.addAttribute("pageTitle", "Thông tin cá nhân - " + user.getUsername());
		return "account/account";
	}
	
	@RequestMapping("/account/changepassword")
	public String changePassword(Model model) {
		//Hiện thị thông tin khách hàng
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Accounts user = accountService.findById(username);
		model.addAttribute("user", user);
        //Tiêu đề trang
        model.addAttribute("pageTitle", "Đổi mật khẩu - " + user.getUsername());
		return "account/changepassword";
	}
}
