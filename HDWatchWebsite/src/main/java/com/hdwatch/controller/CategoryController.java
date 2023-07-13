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
		//tiêu đề trang
		model.addAttribute("pageTitle", "Danh mục");
		//Đổ danh mục
		model.addAttribute("cates", categoriesService.findAll());
		
		//Nếu cid = 0 thì đổ tất cả sản phẩm
		if(cid.isEmpty() || cid.get().equals(0)) {
			List<Products> list = productsService.findAll();
			model.addAttribute("items", list);
		}
		// ngược lại thì đổ dữ liệu theo số cid 
		else {
			List<Products> list = productsService.findAllByCategoryId(cid.get());
			model.addAttribute("items", list);
		}
		return "category";
	}
	
}
