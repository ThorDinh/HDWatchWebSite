package com.hdwatch.api;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
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

	// Lấy danh sách tất cả các danh mục (category)
	@GetMapping
	public List<Categories> getAll() {
		return categoriesService.findAll();
	}

	// Lấy thông tin danh mục (category) theo ID
	@GetMapping("/{id}")
	public ResponseEntity<Categories> getCategory(@PathVariable("id") Integer id) {
		if (!cateDAO.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(cateDAO.findById(id).get());
		}
	}

	// Tìm kiếm danh mục
	@GetMapping("/search")
	public List<Categories> searchCategory(@RequestParam("kw") Optional<String> kw) {
		String keyword = kw.orElse(null);
		if (keyword != null) {
			return categoriesService.findByName(keyword);
		} else {
			return categoriesService.findAll();
		}
	}

	// Tạo mới danh mục (category)
	@PostMapping("")
	public ResponseEntity<Categories> createCategory(@RequestBody Categories cate) {
		if (cate != null) {
			categoriesService.create(cate);
			return ResponseEntity.ok(cate);
		}
		return ResponseEntity.badRequest().build();
	}

	// Lưu thông tin danh mục (category) đã chỉnh sửa
	@PutMapping("{id}")
	public Categories updateCategories(@PathVariable("id") Integer id, @RequestBody Categories upCategories) {
		return categoriesService.save(upCategories, id);
	}

	// Xóa danh mục (category) theo ID
	@DeleteMapping("{id}")
	public void deleteCategory(@PathVariable("id") Integer id) {
		categoriesService.deleteById(id);
	}
}
