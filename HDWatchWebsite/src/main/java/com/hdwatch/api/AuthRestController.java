package com.hdwatch.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdwatch.entity.Accounts;
import com.hdwatch.service.AccountsService;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
	@Autowired
	AccountsService accountsService;

	// Phương thức kiểm tra xem người dùng đã được đăng nhập hay chưa
	@GetMapping("/check")
	public ResponseEntity<?> checkAuthentication(Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			// Assuming your UserDetails has a method getUsername() to get the username
			String username = userDetails.getUsername();

			// Fetch account details based on the username
			// Replace the following line with your actual account retrieval logic
			Accounts account = accountsService.findByUserName(username);

			if (account != null) {
				// User is logged in and account details are available
				return ResponseEntity.ok(account);
			} else {
				// User is logged in but account details not found (unexpected)
				return ResponseEntity.badRequest().body("Account details not found.");
			}
		} else {
			// User is not authenticated
			return ResponseEntity.ok("{\"authenticated\": false}");
		}
	}
}
