package com.hdwatch.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hdwatch.entity.Brands;
import com.hdwatch.entity.Products;
import com.hdwatch.service.BrandsService;
import com.hdwatch.service.ProductsService;

@Controller
public class BrandController {
	@Autowired
	BrandsService brandsService;

	@Autowired
	ProductsService productsService;

	// Thương hiệu
	@RequestMapping("/brand")
	public String index(Model model, @RequestParam("bid") Optional<Integer> bid) {
		// tiêu đề trang
		model.addAttribute("pageTitle", "Thương hiệu");
		// Đổ thương hiệu
		List<Brands> brands = brandsService.findAll();
		Collections.reverse(brands);
		model.addAttribute("brands", brands);

		// Nếu bid = 0 thì đổ tất cả sản phẩm lên
		if (bid.isEmpty() || bid.get().equals(0)) {
			List<Products> list = productsService.findAll();
			model.addAttribute("items", list);
		}
		// Ngược lại thì đổ sản phẩm theo số bid
		else {
			List<Products> list = productsService.findAllByBrandId(bid.get());
			model.addAttribute("message", brandsService.findById(bid.get()).getName());
			model.addAttribute("items", list);
		}
		return "brand";
	}

}
