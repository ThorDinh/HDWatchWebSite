package com.hdwatch.api;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
	// Phương thức kiểm tra xem người dùng đã được đăng nhập hay chưa
	@GetMapping("/check")
    public ResponseEntity<?> checkAuthentication(Principal principal) {
        if (principal != null) {
        	// Người dùng đã được đăng nhập
            return ResponseEntity.ok("{\"authenticated\": true}");
        } else {
        	// Người dùng chưa đăng nhập
            return ResponseEntity.ok("{\"authenticated\": false}");
        }
    }
}
