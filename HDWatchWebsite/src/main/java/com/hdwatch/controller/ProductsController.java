package com.hdwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdwatch.service.ProductsService;


@Controller
public class ProductsController {
	@Autowired
	ProductsService productsService;
	
	@RequestMapping(value = {"/", "/home", "/index"})
	public String index(Model model) {
		model.addAttribute("pageTitle", "Home");
		return "product/home";
	}
	
//	@RequestMapping(/index/detail)
//	public String detail
}
