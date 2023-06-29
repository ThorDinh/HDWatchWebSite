package com.hdwatch.service;

import java.util.List;
import java.util.Optional;

import com.hdwatch.entity.Products;

public interface ProductsService {
	
	List<Products> findAll();
	
	Optional<Products> findById(Integer id);

	Products create(Products products);
	
	Products save(Products products,Integer id);
	
	void deleteByid(Integer id);
}
