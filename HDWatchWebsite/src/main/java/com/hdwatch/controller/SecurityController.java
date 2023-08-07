package com.hdwatch.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdwatch.dao.AccountsDAO;
import com.hdwatch.entity.Accounts;
import com.hdwatch.entity.Roledetails;
import com.hdwatch.entity.Roles;
import com.hdwatch.service.AccountsService;
import com.hdwatch.service.RoledetailService;
import com.hdwatch.service.RolesService;

@Controller
public class SecurityController {
	@Autowired
	AccountsService accountService;
	
	@Autowired
	AccountsDAO adao;
	
	@Autowired
	RolesService roleService;
	
	@Autowired
	RoledetailService roledetailService;
	
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
			return "account/login";
	    }
	}
	
	//Đăng nhập thành công
	@RequestMapping("/login/success")
	public String loginSuccess(Model model, Authentication authentication) {
		//Tiêu đề trang
		model.addAttribute("pageTitle", "Đăng nhập thành công");
		// Kiểm tra xem người dùng đã đăng nhập chưa
	    if (authentication != null && authentication.isAuthenticated()) {
	        // Lấy danh sách các quyền của người dùng
	        List<String> roles = authentication.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.toList());

	        // Kiểm tra các quyền và thực hiện redirect tương ứng
	        if (roles.contains("ROLE_CUS")) {
	            // Nếu người dùng có quyền CUS, chuyển hướng đến /home
	            return "redirect:/home";
	        } else if (roles.contains("ROLE_STA") || roles.contains("ROLE_DIR")) {
	            // Nếu người dùng có quyền STA hoặc DIR, chuyển hướng đến /admin
	            return "redirect:/admin";
	        } else {
	            // Trường hợp người dùng có các quyền khác, xử lý theo ý muốn
	            // Ví dụ: chuyển hướng đến một trang lỗi hoặc trang chính
	            return "redirect:/some-other-page";
	        }
	    } else {
	        // Nếu chưa đăng nhập, hiển thị thông báo mặc định
	        model.addAttribute("message", "Vui lòng đăng nhập!");
	        return "redirect:/login";
	    }
	}
	
	
	//Đăng nhập thất bại 
	@RequestMapping("/login/error")
	public String loginError(Model model, Principal principal) {
		//Tiêu đề trang
		model.addAttribute("pageTitle", "Đăng nhập thất bại");
		// Kiểm tra xem người dùng đã đăng nhập chưa
	    if (principal != null) {
	        // Nếu đã đăng nhập, hiển thị thông báo khác
	        return "redirect:/home";
	    } else {
	        // Nếu chưa đăng nhập, hiển thị thông báo mặc định
	        model.addAttribute("message", "Đăng nhập thất bại");
	    }
		return "account/login";
	}
	
	
	//Không có quyền truy xuất
	@RequestMapping("/unauthorized")
	public String unauthorized(Model model) {
		//Tiêu đề trang
		model.addAttribute("pageTitle", "Không có quyền truy xuất");
		//Thông báo
		model.addAttribute("message", "Không có quyền truy xuất!");
		return "account/unauthorized";
	}
	
	//Đăng xuất
	@RequestMapping("/logout/success")
	public String logoffSuccess(Model model, Principal principal) {
		//Tiêu đề trang
		model.addAttribute("pageTitle", "Đăng xuất thành công");
		if (principal != null) {
	        // Nếu đã đăng nhập, hiển thị thông báo khác
	        return "redirect:/home";
	    } else {
	        // Nếu chưa đăng nhập, hiển thị thông báo mặc định
	    	model.addAttribute("message", "Bạn đã đăng xuất");
	    }
		return "account/login";
	}
	
	//Đăng kí
	@GetMapping("/register")
	public String register(Model model, Principal principal) {
		//Tiêu đề trang
		model.addAttribute("pageTitle", "Đăng kí tài khoản");
		if (principal != null) {
	        // Nếu đã đăng nhập, hiển thị thông báo khác
	        return "redirect:/home";
	    } else {
	        // Nếu chưa đăng nhập, trả về trang đăng kí
	    	model.addAttribute("user", new Accounts());
	    	return "account/register";

	    }
	}
	
	@PostMapping("/register")
    public String processRegistrationForm(Model model,@ModelAttribute("user") @Valid Accounts user, 
    		BindingResult result, @RequestParam("confirmPassword") String confirmPassword) {
		//Bắt lỗi trùng 
        List<Accounts> list = accountService.findAll();
        for(int i =0; i < list.size();i++) {
        	if(user.getUsername().equals(list.get(i).getUsername())) {
        		result.rejectValue("username", "error.user", "Tên tài khoản đã tồn tại");
        		//Tiêu đề trang
        		model.addAttribute("pageTitle", "Đăng kí thất bại");
                return "account/register";
        	}
        }
		//Bắt lỗi khi nhập sai
        if (result.hasErrors() || !user.isPasswordConfirmed(confirmPassword)) {
            if (!user.isPasswordConfirmed(confirmPassword)) {
                result.rejectValue("password", "error.user", "Mật khẩu xác nhận không trùng khớp");
            }
            //Tiêu đề trang
    		model.addAttribute("pageTitle", "Đăng kí thất bại");
            return "account/register";
        }

		// Tạo tài khoản
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setActivated(true);
        accountService.create(user);
        //Phân quyền cho tài khoản
        Roles defaultRole = roleService.findById("CUS");

        Roledetails roledetails = new Roledetails();
        roledetails.setAccounts(user);
        roledetails.setRoles(defaultRole);

        roledetailService.create(roledetails);
        
        // Đăng kí thành công hiển thị message
        model.addAttribute("message", "Đăng kí thành công");
        return "account/login";
    }
	
	//API security 
	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/security/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}
}
