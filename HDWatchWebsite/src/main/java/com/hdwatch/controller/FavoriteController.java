package com.hdwatch.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FavoriteController {
	//Xem giỏ hàng
	@RequestMapping("/account/favorite/view")
	public String cart(Model model, HttpServletRequest request) {
		model.addAttribute("pageTitle", "Danh sách yêu thích");
		return "favorite/view";
	}
}
