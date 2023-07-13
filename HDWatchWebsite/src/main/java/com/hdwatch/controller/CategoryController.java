package com.hdwatch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hdwatch.entity.Products;
import com.hdwatch.service.CategoriesService;
import com.hdwatch.service.ProductsService;


@Controller
public class CategoryController {
	@Autowired
	CategoriesService categoriesService;
	
	@Autowired
	ProductsService productsService;
	
	@RequestMapping("/category")
	public String index(Model model, @RequestParam("cid") Optional<Integer> cid) {
		model.addAttribute("pageTitle", "Danh mục");
		model.addAttribute("cates", categoriesService.findAll());
		if(cid.isEmpty() || cid.get().equals(0)) {
//			List<Products> list = productsService.findAll();
//			model.addAttribute("items", list);
			List<Products> list = productsService.findAll();
			model.addAttribute("items", list);
		}
		else {
//			List<Productimages> list = productImageService.findAllProductWithCategory(cid.get());
//			model.addAttribute("items", list);
			List<Products> list = productsService.findAllByCategoryId(cid.get());
			model.addAttribute("items", list);
		}
		return "category";
	}
	
//	@RequestMapping(/index/detail)
//	public String detail
}
