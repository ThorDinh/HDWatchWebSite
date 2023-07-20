package com.hdwatch.api;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
	@GetMapping("/check")
    public ResponseEntity<?> checkAuthentication(Principal principal) {
        if (principal != null) {
            // User is authenticated
            return ResponseEntity.ok("{\"authenticated\": true}");
        } else {
            // User is not authenticated
            return ResponseEntity.ok("{\"authenticated\": false}");
        }
    }
}
