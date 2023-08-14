package com.hdwatch.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hdwatch.entity.Brands;
import com.hdwatch.entity.Products;
import com.hdwatch.service.BrandsService;
import com.hdwatch.service.CategoriesService;
import com.hdwatch.service.ProductsService;


@Controller
public class ProductsController {
	@Autowired
	ProductsService productsService;
	
	@Autowired
	CategoriesService categoriesService;
	
	@Autowired
	BrandsService brandsService;
	
	//Sản phẩm chi tiết
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		//hiển thị 1 sản phẩm
		Products product = productsService.findById(id);
		model.addAttribute("item", product);
		//Tiêu đề trang
		model.addAttribute("pageTitle",product.getBrands().getName()+ " - " + product.getName());
		//sản phẩm liên quan
		List<Products> list = productsService.findAllByBrandId(product.getBrandid());
		model.addAttribute("list", list);
		return "product/detail";
	}
	
	//Tìm kiếm sản phẩm
	@GetMapping("/product/search")
	public String search(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "category", required = false) Integer category,
			@RequestParam(name = "brand", required = false) Integer brand,
			@RequestParam("p") Optional<Integer> p, Model model) {
		//Tiêu đề trang
		model.addAttribute("pageTitle","Tìm kiếm sản phẩm: " + keyword);
		
		//Đổ danh mục
		model.addAttribute("cates", categoriesService.findAll());
		
		//Đổ thương hiệu
		List<Brands> brands = brandsService.findAll();
		Collections.reverse(brands);
		model.addAttribute("brands", brands);
		
		
		//tìm kiếm sản phẩm theo keyword
		Page<Products> filteredProducts;
        if (keyword != null && !keyword.trim().isEmpty() || category != null || brand != null) {
        	filteredProducts = productsService.searchProductsByKeyword(keyword, category, brand, p);
        	if(keyword.equalsIgnoreCase("")) {
        		//Tiêu đề trang
        		model.addAttribute("pageTitle","Tất cả sản phẩm");
        		model.addAttribute("message", "Có "+ filteredProducts.getTotalElements() +" kết quả trả về");
        	} else {
        		model.addAttribute("message", "Có "+ filteredProducts.getTotalElements() +" kết quả trả về cho từ khóa: " + keyword);
        	}
        } else {
        	//Tiêu đề trang
    		model.addAttribute("pageTitle","Tất cả sản phẩm");
        	filteredProducts = productsService.findAllPagination(p);
        }
        //Nếu danh sách sản phẩm tìm kiếm trả về null thì hiện thông báo và ngược lại
        if (filteredProducts.isEmpty()) {
            model.addAttribute("message", "Không có kết quả trả về cho từ khóa: " + keyword);
        } else {
            model.addAttribute("items", filteredProducts);
        }
     	
        //Đặt giá trị category và brand vào radio
        model.addAttribute("keyword", keyword);
        model.addAttribute("category", category);
        model.addAttribute("brand", brand);
		return "product/search";
	}
}
