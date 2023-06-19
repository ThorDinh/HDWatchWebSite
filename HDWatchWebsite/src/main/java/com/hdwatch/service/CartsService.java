package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Carts;

public interface CartsService {
	List<Carts> findAll();
	
	Carts findById(Integer id);
	
	void deleteById(Integer id);
	
	Carts create(Carts carts);
	
	Carts save(Carts carts,Integer id);

	
}
