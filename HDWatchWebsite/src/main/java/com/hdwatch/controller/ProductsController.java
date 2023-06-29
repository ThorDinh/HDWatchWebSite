package com.hdwatch.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	ProductimagesDAO piDAO;
	
	@Autowired
	SessionService session;
	
	@RequestMapping(value = {"/", "/home", "/index"})
	public String index(Model model) {
		List<Productimages> list = productimagesService.findAllProductWithOneImage();
		model.addAttribute("items", list);
		model.addAttribute("pageTitle", "Trang chủ");
		return "product/home";
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
		Optional<Products> product = productsService.findById(id);
		if (product.isPresent()) {
			model.addAttribute("pageTitle", "Sản phẩm "+ product.get().getName());
			model.addAttribute("item", product.get());
			List<Productimages> listImage = productimagesService.findAllImagesByProductId(id);
			model.addAttribute("images", listImage);
	    } else {
	        // Product not found, handle the error appropriately
	        throw new NoSuchElementException("Product not found with ID: " + id);
	    }
		return "product/detail";
	}
}
