package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Products;

public interface ProductsService {
	
	List<Products> findAll();
	
	Products findById(Integer id);

	Products create(Products products);
	
	Products save(Products products,Integer id);
	
	void deleteByid(Integer id);
}
