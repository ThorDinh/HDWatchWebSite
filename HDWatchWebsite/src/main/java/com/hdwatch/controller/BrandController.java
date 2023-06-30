package com.hdwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdwatch.service.BrandsService;


@Controller
public class BrandController {
	@Autowired
	BrandsService brandsService;
	
	@RequestMapping("/brand")
	public String index(Model model) {
		model.addAttribute("pageTitle", "Brand");
		return "product/brand";
	}
	
//	@RequestMapping(/index/detail)
//	public String detail
}
