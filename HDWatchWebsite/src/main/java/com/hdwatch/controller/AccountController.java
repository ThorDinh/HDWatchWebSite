package com.hdwatch.controller;

//import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hdwatch.dao.AccountsDAO;
import com.hdwatch.entity.Accounts;
import com.hdwatch.service.AccountsService;
import com.hdwatch.service.MailerService;
import com.hdwatch.utils.PasswordUtils;



@Controller
public class AccountController {
	@Autowired
	AccountsService accountService;
	@Autowired
	MailerService mailerService;
	@Autowired
	AccountsDAO accountsDAO;
	
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
	
//	  private String generateResetCode() {
//	        
//	        String code = RandomStringUtils.randomAlphanumeric(6);
//	        return code;
//	    }
	@RequestMapping("/forgot-password")
	public String forgotPassword(@RequestParam(name = "email", required = false) String username,Model model) {
		
		model.addAttribute("pageTitle","Quên mật khẩu");
			return "account/forgotpassword";
		
	}
	@PostMapping("/forgot-password")
	public String doPostfogotPassword(@RequestParam("email") String username,Model model) {
		model.addAttribute("pageTitle","Quên mật khẩu");
		try {
			Accounts account = accountService.findByUserName(username);
			String to = account.getEmail();
			String email = to.substring(0, 2);
			
			double randomDouble = Math.random();
            randomDouble = randomDouble * 1000000 + 1;
            int randomInt = (int) randomDouble;
            System.out.println("Duy chuoi quai ");
			
			String subject = "Lấy lại mật khẩu";
			String body = "Mật khẩu của bạn là:"+randomInt;
			mailerService.send(to, subject, body);
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(String.valueOf(randomInt));
			account.setPassword(String.valueOf(encodedPassword));
			accountsDAO.save(account);
			
			model.addAttribute("message", "Mật khẩu mới đã được gửi đến mail "+email+"***");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("message", "Sai tài khoảng hoặc để trống");
		}
		return "account/forgotpassword";
	}
	  @PostMapping("/account/changepassword")
	  public String changePassword(@RequestParam("username") String username,
	                               @RequestParam("oldPassword") String oldPassword,
	                               @RequestParam("newPassword") String newPassword,
	                               @RequestParam("confirmNewPassword") String confirmNewPassword,
	                               Model model) {
		  	//Hiện thị thông tin khách hàng
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			username = authentication.getName();
			Accounts user = accountService.findById(username);
			model.addAttribute("user", user);
			// Kiểm tra xem username và oldPassword có khớp với thông tin trong cơ sở dữ liệu hay không
			if (accountService.authenticate(username, oldPassword)) {
	          // Kiểm tra tính hợp lệ của mật khẩu mới
	          if (PasswordUtils.isPasswordValid(oldPassword, newPassword) && newPassword.equals(confirmNewPassword)) {
	        		// Thực hiện thay đổi mật khẩu
		              accountService.changePassword(username, newPassword);
		              //Tiêu đề trang
		              model.addAttribute("pageTitle","Đổi mật khẩu thành công");
		              model.addAttribute("message", "Thay đổi mật khẩu thành công!");
	          } else if(!newPassword.equals(confirmNewPassword)){
	        	  //Tiêu đề trang
	      			model.addAttribute("pageTitle","Đổi mật khẩu thất bại");
	              model.addAttribute("message", "Xác nhận mật khẩu mới sai!");
	          } else {
	        	  //Tiêu đề trang
	      			model.addAttribute("pageTitle","Đổi mật khẩu thất bại");
	              model.addAttribute("message", "Mật khẩu mới không hợp lệ! Vui lòng nhập mật khẩu mới hợp lệ!");
	          }
	      } else {
	    	  	//Tiêu đề trang
	  			model.addAttribute("pageTitle","Sai mật khẩu");
	  			model.addAttribute("message", "Mật khẩu cũ không đúng!");
	      }
	      
	      return "account/changepassword";
	  }
}
