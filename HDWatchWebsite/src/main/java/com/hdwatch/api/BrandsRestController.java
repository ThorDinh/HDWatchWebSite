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

import com.hdwatch.dao.BrandsDAO;
import com.hdwatch.entity.Brands;
import com.hdwatch.service.BrandsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/rest/brands")
public class BrandsRestController {
	@Autowired
	BrandsService brandsService;

	@Autowired
	BrandsDAO bDao;

	// Lấy danh sách tất cả các thương hiệu (brands)
	@GetMapping
	public List<Brands> getAll() {
		return brandsService.findAll();
	}

	// Lấy thông tin thương hiệu theo ID
	@GetMapping("/{id}")
	public ResponseEntity<Brands> getBrand(@PathVariable("id") Integer id) {
		if (!bDao.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(brandsService.findById(id));
		}
	}

	// Tìm kiếm thương hiệu
	@GetMapping("/search")
	public List<Brands> searchCategory(@RequestParam("kw") Optional<String> kw) {
		String keyword = kw.orElse(null);
		if (keyword != null) {
			return brandsService.findByName(keyword);
		} else {
			return brandsService.findAll();
		}
	}

	// Tạo mới thương hiệu
	@PostMapping
	public ResponseEntity<Brands> createBrand(@RequestBody Brands brands) {
		if (brands != null) {
			brandsService.create(brands);
			return ResponseEntity.ok(brands);
		}
		return ResponseEntity.badRequest().build();
	}

	// Lưu thông tin thương hiệu đã chỉnh sửa
	@PutMapping("{id}")
	public Brands saveBrands(@PathVariable("id") Integer id, @RequestBody Brands brands) {
		return brandsService.save(brands, id);
	}

	// Xóa thương hiệu theo ID
	@DeleteMapping("{id}")
	public void deleteBrand(@PathVariable("id") Integer id) {
		brandsService.deleteById(id);
	}
}
