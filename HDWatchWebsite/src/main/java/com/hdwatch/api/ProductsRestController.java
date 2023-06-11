package com.hdwatch.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdwatch.entity.Products;
import com.hdwatch.service.ProductsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductsRestController {
	@Autowired
	ProductsService productsService;
	@GetMapping("{id}")
	public Products getOne(@PathVariable("id") Integer id) {
		
		return productsService.findById(id);
	}
}
