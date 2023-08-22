package com.hdwatch.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.hdwatch.entity.Products;
import com.hdwatch.service.ProductsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductsRestController {
	@Autowired
	ProductsService productsService;

	// Lấy danh sách tất cả các sản phẩm (product)
	@GetMapping
	public List<Products> getAll() {
		return productsService.findAll();
	}

	// Lấy thông tin sản phẩm (product) theo ID
	@GetMapping("{id}")
	public Products getOne(@PathVariable("id") Integer id) {
		return productsService.findById(id);
	}

	// Tìm kiếm sản phẩm
	@GetMapping("/search")
	public List<Products> searchProduct(@RequestParam("kw") Optional<String> kw) {
		String keyword = kw.orElse(null);
		if (keyword != null) {
			return productsService.findByName(keyword);
		} else {
			return productsService.findAll();
		}
	}

	// Tạo mới sản phẩm (product)
	@PostMapping
	public ResponseEntity<Products> createProducts(@RequestBody Products products) {
		if (products != null) {
			productsService.create(products);
			return ResponseEntity.ok(products);
		}
		return ResponseEntity.badRequest().build();
	}

	// Lưu thông tin sản phẩm (product) đã chỉnh sửa
	@PutMapping("{id}")
	public Products saveProducts(@PathVariable("id") Integer id, @RequestBody Products products) {
		return productsService.save(products, id);
	}

	// Xóa sản phẩm (product) theo ID
	@DeleteMapping("{id}")
	public void deleteProduct(@PathVariable("id") Integer id) {
		productsService.deleteByid(id);
	}

	// Xóa hình ảnh
	@PostMapping("/deleteImage/{productId}")
	public ResponseEntity<Map<String, String>> deleteImage(@PathVariable Integer productId,
			@RequestBody String imageToDelete) {
		try {
			productsService.deleteImage(productId, imageToDelete);
			Map<String, String> response = new HashMap<>();
			response.put("message", "Image deleted successfully");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			Map<String, String> response = new HashMap<>();
			response.put("error", "Error deleting image");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
