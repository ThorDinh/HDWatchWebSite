package com.hdwatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdwatch.entity.Products;
import com.hdwatch.service.ProductsService;

@Controller
public class HomeController {
	@Autowired
	ProductsService productsService;

	// Trang chủ
	@RequestMapping(value = { "/home", "/index" })
	public String index(Model model) {
		// Tiêu đề trang
		model.addAttribute("pageTitle", "Trang chủ");
		// sản phẩm mới nhất
		List<Products> list = productsService.findProductByCreateDateDESC();
		model.addAttribute("items", list);
		// sản phẩm bán chạy
		List<Products> list1 = productsService.getProductsOrderedByOrderCount();
		model.addAttribute("list", list1);
		return "home";
	}

	// Giới thiệu
	@RequestMapping("/about")
	public String about(Model model) {
		// Tiêu đề trang
		model.addAttribute("pageTitle", "Giới thiệu");
		return "about";
	}

	// Liên hệ
	@RequestMapping("/contact")
	public String contact(Model model) {
		// Tiêu đề trang
		model.addAttribute("pageTitle", "Liên lạc");
		return "contact";
	}

	// Trang quản lí
	@RequestMapping("/admin")
	public String admin() {
		return "admin/index";
	}
}
