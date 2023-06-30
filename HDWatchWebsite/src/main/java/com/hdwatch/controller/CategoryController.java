package com.hdwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdwatch.service.CategoriesService;


@Controller
public class CategoryController {
	@Autowired
	CategoriesService categoriesService;
	
	@RequestMapping("/category")
	public String index(Model model) {
		model.addAttribute("pageTitle", "Category");
		return "product/category";
	}
	
//	@RequestMapping(/index/detail)
//	public String detail
}
