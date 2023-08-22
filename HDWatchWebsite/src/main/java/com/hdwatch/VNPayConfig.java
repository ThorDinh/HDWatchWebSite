package com.hdwatch;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class VNPayConfig {
	public static String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
	public static String vnp_Returnurl = "/vnpay-payment"; // Đường dẫn trả về sau khi thanh toán thành công
	public static String vnp_TmnCode = "EDTBSAXV"; // Mã cửa hàng thương mại (merchant)
	public static String vnp_HashSecret = "JCLYMNFLZCXNFVIGGXTNPRGADNWSIQJJ"; // Khóa bí mật dùng để tạo chữ ký (hash)
	public static String vnp_apiUrl = "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction"; // URL API thanh toán của VNPay

	// Hàm tính toán giá trị hash MD5 của một chuỗi đầu vào
	public static String md5(String message) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			digest = sb.toString();
		} catch (UnsupportedEncodingException ex) {
			digest = "";
		} catch (NoSuchAlgorithmException ex) {
			digest = "";
		}
		return digest;
	}

	// Hàm tính toán giá trị hash SHA-256 của một chuỗi đầu vào
	public static String Sha256(String message) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(message.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			digest = sb.toString();
		} catch (UnsupportedEncodingException ex) {
			digest = "";
		} catch (NoSuchAlgorithmException ex) {
			digest = "";
		}
		return digest;
	}

	// Utility cho VNPay: Hàm tạo chữ ký (hash) cho tất cả các trường dữ liệu gửi
	// tới VNPay
	public static String hashAllFields(Map fields) {
		List fieldNames = new ArrayList(fields.keySet());
		Collections.sort(fieldNames);
		StringBuilder sb = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) fields.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				sb.append(fieldName);
				sb.append("=");
				sb.append(fieldValue);
			}
			if (itr.hasNext()) {
				sb.append("&");
			}
		}
		return hmacSHA512(vnp_HashSecret, sb.toString());
	}

	// Hàm tạo chữ ký HMAC-SHA512
	public static String hmacSHA512(final String key, final String data) {
		try {
			if (key == null || data == null) {
				throw new NullPointerException();
			}
			final Mac hmac512 = Mac.getInstance("HmacSHA512");
			byte[] hmacKeyBytes = key.getBytes();
			final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
			hmac512.init(secretKey);
			byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
			byte[] result = hmac512.doFinal(dataBytes);
			StringBuilder sb = new StringBuilder(2 * result.length);
			for (byte b : result) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return sb.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	// Lấy địa chỉ IP của client từ request
	public static String getIpAddress(HttpServletRequest request) {
		String ipAdress;
		try {
			ipAdress = request.getHeader("X-FORWARDED-FOR");
			if (ipAdress == null) {
				ipAdress = request.getLocalAddr();
			}
		} catch (Exception e) {
			ipAdress = "Địa chỉ IP không hợp lệ: " + e.getMessage();
		}
		return ipAdress;
	}

	// Hàm tạo số ngẫu nhiên có độ dài len
	public static String getRandomNumber(int len) {
		Random rnd = new Random();
		String chars = "0123456789";
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		}
		return sb.toString();
	}
}
