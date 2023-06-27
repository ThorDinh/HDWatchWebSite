package com.hdwatch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hdwatch.dao.ProductimagesDAO;
import com.hdwatch.entity.Productimages;
import com.hdwatch.entity.Products;
import com.hdwatch.service.CategoriesService;
import com.hdwatch.service.ProductimagesService;
import com.hdwatch.service.ProductsService;


@Controller
public class CategoryController {
	@Autowired
	CategoriesService categoriesService;
	
	@Autowired
	ProductimagesService productImageService;
	
	@Autowired
	ProductimagesDAO productImageDAO;
	
	@RequestMapping("/category")
	public String index(Model model, @RequestParam("cid") Optional<Integer> cid) {
		model.addAttribute("pageTitle", "Category");
		model.addAttribute("cates", categoriesService.findAll());
		try {
			if(cid.isEmpty() || cid.get().equals(0)) {
				List<Productimages> list = productImageService.findAllProductWithOneImage();
				model.addAttribute("items", list);
			}
			else {
				List<Productimages> list = productImageDAO.findAllByCategory(cid.get());
				model.addAttribute("items", list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "category";
	}
	
//	@RequestMapping(/index/detail)
//	public String detail
}
