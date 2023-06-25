package com.hdwatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
	@RequestMapping("/cart-view")
	public String cart(Model model) {
		model.addAttribute("pageTitle", "Cart");
		return "cart/view";
	}
}
