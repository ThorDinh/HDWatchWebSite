package com.hdwatch.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdwatch.entity.Products;
import com.hdwatch.service.ProductsService;



@CrossOrigin("*")
@RestController
@RequestMapping("/admin/rest/products")
public class ProductsRestController {
	@Autowired
	ProductsService productsService;
	
	@GetMapping
	public List<Products> getAll() {
		return productsService.findAll();
	}
	
	@GetMapping("{id}")
	public Products getOne(@PathVariable("id") Integer id) {
		return productsService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Products> createProducts(@RequestBody Products products) {
		if(products != null) {
			productsService.create(products);
			return ResponseEntity.ok(products);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("{id}")
	public Products saveProducts(@PathVariable("id")Integer id,@RequestBody Products products) {
		return productsService.save(products, id);
	}
	
	@DeleteMapping("{id}")
	public void deleteProduct(@PathVariable("id")Integer id) {
		productsService.deleteByid(id);
	}
}
