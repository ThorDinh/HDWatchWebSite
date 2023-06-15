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

import com.hdwatch.entity.Brands;
import com.hdwatch.service.BrandsService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/brands")
public class BrandsRestController {
	@Autowired
	BrandsService brandsService;
	
	@GetMapping
	public List<Brands> getAll() {
		return brandsService.findAll();
	}
	
	@GetMapping("{id}")
	public Brands getOne(@PathVariable("id") Integer id) {
		
		return brandsService.findById(id);
	}
	
	@PostMapping
	public Brands createBrands(@RequestBody Brands brands) {
		brandsService.create(brands);
		return brands;
	}
	
	@PutMapping("{id}")
	public Brands saveBrands(@PathVariable("id")Integer id,@RequestBody Brands brands) {
		return brandsService.save(brands, id);
	}
	
	@DeleteMapping("{id}")
	public void deleteBrand(@PathVariable("id")Integer id) {
		brandsService.deleteById(id);
	}
}
