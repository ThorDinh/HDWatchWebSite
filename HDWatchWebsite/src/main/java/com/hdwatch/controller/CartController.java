package com.hdwatch.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdwatch.service.OrdersService;

@Controller
public class CartController {
	@Autowired
	OrdersService orderService;

	// Xem giỏ hàng
	@RequestMapping("/cart/view")
	public String cart(Model model, HttpServletRequest request) {
		// Tiêu đề trang
		model.addAttribute("pageTitle", "Giỏ hàng");

		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "cart/view";
	}
}
