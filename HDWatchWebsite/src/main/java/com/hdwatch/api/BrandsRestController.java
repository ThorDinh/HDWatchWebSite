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

import com.hdwatch.dao.BrandsDAO;
import com.hdwatch.entity.Accounts;
import com.hdwatch.entity.Brands;
import com.hdwatch.service.BrandsService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/brands")
public class BrandsRestController {
	@Autowired
	BrandsService brandsService;
	
	@Autowired
	BrandsDAO bDao;
	
	@GetMapping
	public List<Brands> getAll() {
		return brandsService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Brands> getAccount(@PathVariable("id") Integer id) {
		if (!bDao.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(brandsService.findById(id));
		}
	}
	
	@PostMapping
	public ResponseEntity<Brands> createBrands(@RequestBody Brands brands) {
		if(brandsService.exitsByName(brands.getName())) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(brandsService.create(brands));
		}
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
