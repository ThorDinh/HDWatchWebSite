package com.hdwatch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hdwatch.entity.Productimages;
import com.hdwatch.service.BrandsService;
import com.hdwatch.service.ProductimagesService;


@Controller
public class BrandController {
	@Autowired
	BrandsService brandsService;
	
	@Autowired
	ProductimagesService productImageService;
	
	@RequestMapping("/brand")
	public String index(Model model, @RequestParam("bid") Optional<Integer> bid) {
		model.addAttribute("pageTitle", "Thương hiệu");
		model.addAttribute("brands", brandsService.findAll());
		if(bid.isEmpty() || bid.get().equals(0)) {
			List<Productimages> list = productImageService.findAllProductWithOneImage();
			model.addAttribute("items", list);
		}
		else {
			List<Productimages> list = productImageService.findAllProductWithBrand(bid.get());
			model.addAttribute("items", list);
		}
		return "brand";
	}
	
//	@RequestMapping(/index/detail)
//	public String detail
}
