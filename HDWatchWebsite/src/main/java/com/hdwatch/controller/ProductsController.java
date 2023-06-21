package com.hdwatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdwatch.entity.Productimages;
import com.hdwatch.entity.Products;
import com.hdwatch.service.ProductsService;


@Controller
public class ProductsController {
	@Autowired
	ProductsService productsService;

	
	//productImages
		@ModelAttribute("productImage")
		public List<Productimages> setImg() {
			List<Productimages> productImage;
			return null;
		}
	
	@RequestMapping("/")
	public String index(Model model) {
		List<Products> list = productsService.findAll();
		model.addAttribute("items", list);
		return "product/home";
	}
	
//	@RequestMapping(/index/detail)
//	public String detail
}
