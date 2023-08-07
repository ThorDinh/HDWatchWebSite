package com.hdwatch.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

	// Phương thức kiểm tra mật khẩu cũ
	public static boolean checkOldPassword(String oldPassword, String hashedOldPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(oldPassword, hashedOldPassword);
	}
	
	// Phương thức kiểm tra tính hợp lệ của mật khẩu mới
	public static boolean isPasswordValid(String oldPassword, String newPassword) {
		// Kiểm tra nếu mật khẩu cũ và mật khẩu mới giống nhau thì trả về false
		if (oldPassword.equals(newPassword)) {
			return false;
		}
		return true;
	}
}
