package com.hdwatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.hdwatch.entity.Products;

public interface ProductsService {

	List<Products> findAll();

	Products findById(Integer id);

	Products create(Products products);

	Products save(Products products, Integer id);

	void deleteByid(Integer id);

	List<Products> findProductByCreateDateDESC();

	List<Products> findAllByBrandId(Integer integer);

	List<Products> findAllByCategoryId(Integer id);

	List<Products> getProductsOrderedByOrderCount();

	Page<Products> findAllPagination(Optional<Integer> p);

	Page<Products> searchProductsByKeyword(String keyword, Integer category, Integer brand, Optional<Integer> p);

	List<Products> findByName(String name);

	void deleteImage(Integer productId, String imageToDelete);
}
