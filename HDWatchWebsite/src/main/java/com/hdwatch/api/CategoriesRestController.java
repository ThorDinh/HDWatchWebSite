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

import com.hdwatch.dao.CategoriesDAO;
import com.hdwatch.entity.Categories;
import com.hdwatch.service.CategoriesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/rest/categories")
public class CategoriesRestController {
	@Autowired
	CategoriesService categoriesService;
	
	@Autowired
	CategoriesDAO cateDAO;
	
	@GetMapping 
	public List<Categories> getAll(){
		return categoriesService.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Categories> getCategory(@PathVariable("id") Integer id) {
		if (!cateDAO.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(cateDAO.findById(id).get());
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Categories> createCategory(@RequestBody Categories cate){
		if(cate != null) {
			categoriesService.create(cate);
			return ResponseEntity.ok(cate);
		}
		return ResponseEntity.badRequest().build();
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
