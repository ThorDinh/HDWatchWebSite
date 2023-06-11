package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Brands;


public interface BrandsService {
	List<Brands> findAll();

	Brands findById(Integer id);

	boolean existsById(Integer id);

	Brands save(Brands brand);

	void deleteById(Integer id);

	List<Brands> findNameById(Integer id);

}
