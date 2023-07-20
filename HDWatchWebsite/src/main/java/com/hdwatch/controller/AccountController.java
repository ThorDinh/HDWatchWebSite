package com.hdwatch.controller;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hdwatch.entity.Accounts;
import com.hdwatch.service.AccountsService;
import com.hdwatch.utils.PasswordUtils;

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
	
	  private String generateResetCode() {
	        
	        String code = RandomStringUtils.randomAlphanumeric(6);
	        return code;
	    }
//	@PostMapping("/account/forgot-password")
//	public String forgotPassword(@RequestParam("email") String email,Model model) {
//		Accounts account = new Accounts();
//		Accounts accounts = accountService.findByEmai(email);
//		
//		if(accounts != null) {
//			model.addAttribute("error","Email không tồn tại!!!");
//			return "forgot-passwrod";
//		}
//			String resetCode = generateResetCode();
//			
//			accounts.setResetCode(resetCode);
//			
//			
//			accountService.save(resetCode, accounts);
//			
//			
//			accounts.setResetCode(resetCode);
//			
//			sendResetPasswordEmail(accounts.getEmail(),resetCode);
//			
//			return "redirect:/reset-password";
//		
//	}
	  @PostMapping("/changepassword")
	  public String changePassword(@RequestParam("username") String username,
	                               @RequestParam("oldPassword") String oldPassword,
	                               @RequestParam("newPassword") String newPassword,
	                               Model model) {
	      // Kiểm tra xem username và oldPassword có khớp với thông tin trong cơ sở dữ liệu hay không
	      if (accountService.authenticate(username, oldPassword)) {
	          // Kiểm tra tính hợp lệ của mật khẩu mới
	          if (PasswordUtils.isPasswordValid(oldPassword, newPassword)) {
	              // Thực hiện thay đổi mật khẩu
	              accountService.changePassword(username, newPassword);
	              model.addAttribute("successMessage", "Thay đổi mật khẩu thành công!");
	          } else {
	              model.addAttribute("errorMessage", "Mật khẩu mới không hợp lệ! Vui lòng nhập mật khẩu mới hợp lệ.");
	          }
	      } else {
	          model.addAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu cũ không đúng!");
	      }
	      
	      return "account/changepassword";
	  }
}
