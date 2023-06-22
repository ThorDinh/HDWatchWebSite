package com.hdwatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdwatch.dao.ProductimagesDAO;
import com.hdwatch.entity.Productimages;
import com.hdwatch.entity.Products;
import com.hdwatch.service.BrandsService;
import com.hdwatch.service.ProductimagesService;
import com.hdwatch.service.ProductsService;


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
