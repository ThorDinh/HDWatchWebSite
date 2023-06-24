package com.hdwatch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hdwatch.dao.ProductimagesDAO;
import com.hdwatch.entity.Productimages;
import com.hdwatch.entity.Products;
import com.hdwatch.service.ProductimagesService;
import com.hdwatch.service.ProductsService;
import com.hdwatch.utils.SessionService;

@Controller
public class ProductsController {
	@Autowired
	ProductsService productsService;
	
	@Autowired
	ProductimagesService productimagesService;
	
	@Autowired
	SessionService session;
	
	@RequestMapping(value = {"/", "/home", "/index"})
	public String index(Model model) {
		List<Productimages> list = productimagesService.findAllProductWithOneImage();
		model.addAttribute("items", list);
		model.addAttribute("pageTitle", "Home");
		return "product/home";
	}
	
//	@RequestMapping("product/search")
//	public String seachAndPage(Model model,
//			@RequestParam("keywords") Optional<String> kw,
//			@RequestParam("p") Optional<Integer> p) {
//		String kwords = kw.orElse(session.get("keywords",""));
//		session.set("keywords", kwords);
//		Pageable pageable = PageRequest.of(p.orElse(0),5);
//		Page<Products> page=dao.findByKeywords("%"+kwords+"%", pageable);
//		model.addAttribute("page",page);
//		return "/product/search-page";
//	}
	
//	@RequestMapping(/index/detail)
//	public String detail
}
