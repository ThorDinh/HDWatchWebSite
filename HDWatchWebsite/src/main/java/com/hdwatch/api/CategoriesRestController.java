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

import com.hdwatch.entity.Categories;
import com.hdwatch.service.CategoriesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoriesRestController {
	@Autowired
	CategoriesService categoriesService;
	
	@GetMapping 
	public List<Categories> getAll(){
		return categoriesService.findAll();
	}
	
	@GetMapping("{id}")
	public Categories getOne(@PathVariable("id")Integer id) {
		return categoriesService.findById(id);
	}
	@PostMapping
	public Categories createCategories(@RequestBody Categories categories) {
		return categoriesService.create(categories);
	}
	@PutMapping("{id}")
	public Categories updateCategories(@PathVariable("id")Integer id,@RequestBody Categories upCategories) {
		return categoriesService.save(upCategories, id);
	}
	@DeleteMapping("{id}")
	public void deleteCategory(@PathVariable("id")Integer id) {
		categoriesService.deleteById(id);
	}
}
