package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Products;

public interface ProductsService {
	
	List<Products> findAll();
	
	Products findById(Integer id);


	
}
