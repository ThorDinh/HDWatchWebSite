package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Brands;


public interface BrandsService {
	
	List<Brands> findAll();

	Brands findById(Integer id);

	boolean existsById(Integer id);
	
	Brands create(Brands brand);

	Brands save(Brands brand,Integer id);

	void deleteById(Integer id);

}
