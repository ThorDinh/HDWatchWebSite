package com.hdwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdwatch.dao.AccountsDAO;
import com.hdwatch.entity.Accounts;
import com.hdwatch.entity.Roledetails;
import com.hdwatch.entity.Roles;
import com.hdwatch.service.AccountsService;

@Controller
public class AccountController {
	@Autowired
	AccountsService accountsService;
	@Autowired
	AccountsDAO accountsDAO;
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
		Accounts ac = new Accounts();
		model.addAttribute("accounts", ac);
		return "account/register";
	}
	@PostMapping("/security/register")
	public String signup(Model model,
			@ModelAttribute Accounts account) {
		if(accountsDAO.existsById(account.getUsername())) {
			model.addAttribute("error", "Đã tồn tại username "+account.getUsername());
			return "account/register";
		}else {
			account.setActivated(true);
			String password = account.getPassword();
			if(password !=null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(password);
				account.setPassword(encodedPassword);
			}else {
				model.addAttribute("error", "Mật khẩu không được để trống"); // Hiển thị thông báo lỗi
				return "account/register";
			}
			Roles r = new Roles();
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(account.getPassword());
			account.setPassword(encodedPassword);
			r.setRole("user");
			Roledetails rd = new Roledetails();
			rd.setAccountId(account.getId());
			rd.setRoleid(1);
			accountsDAO.save(account);
			return "redirect:/register/success";
		}
	}
	@RequestMapping("/security/register/success")
	public String registerSuccess(Model model) {
		model.addAttribute("message", "Đăng ký thành công");
		return "account/login";
	}
}
