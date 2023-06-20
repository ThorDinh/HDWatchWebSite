package com.hdwatch.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdwatch.entity.Productimages;
import com.hdwatch.service.ProductimagesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/productimages")
public class ProductimagesRestControllerller {
//	@Autowired
//	ProductimagesService productimagesService;
//	
//	@GetMapping
//	public List<Productimages> getAll(){
//		 return productimagesService.findAll();
//	}
//	
//	@GetMapping("{id}")
//	public Productimages getOne(@PathVariable("id")Integer id) {
//		return productimagesService.findById(id);
//	}
//	
//	@PostMapping
//	public Productimages createProductimages(@RequestBody Productimages productimages) {
//		return productimagesService.create(productimages);
//	}
//	
//	@PutMapping("{id}")
//	public Productimages updateProductimages(@PathVariable("id")Integer id,@RequestBody Productimages productimages) {
//		return productimagesService.save(id, productimages);
//	}
//	
//	@DeleteMapping("{id}")
//	public void deleteById(@PathVariable("id")Integer id) {
//		productimagesService.deleteById(id);
//	}
}
