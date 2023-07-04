package com.hdwatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdwatch.entity.Products;
import com.hdwatch.service.ProductsService;


@Controller
public class ProductsController {
	@Autowired
	ProductsService productsService;
	
	@RequestMapping(value = {"/", "/home", "/index"})
	public String index(Model model) {
		//sản phẩm mới nhất
		List<Products> list = productsService.findProductByCreateDateDESC();
		model.addAttribute("items", list);
		//sản phẩm bán chạy
		model.addAttribute("list", list);
		model.addAttribute("pageTitle", "Trang chủ");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("pageTitle", "Giới thiệu");
		return "about";
	}
	
	@RequestMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("pageTitle", "Liên lạc");
		return "contact";
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
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		return "product/detail";
	}
}
