package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Brands;

public interface BrandsService {

	List<Brands> findAll();

	Brands findById(Integer id);

	boolean existsById(Integer id);

	boolean exitsByName(String name);

	Brands create(Brands brand);

	Brands save(Brands brand, Integer id);

	void deleteById(Integer id);

	List<Brands> findByName(String name);

}
