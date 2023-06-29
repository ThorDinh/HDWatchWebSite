package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Productimages;

public interface ProductimagesService {
	
	List<Productimages> findAll();
	
	Productimages findById(Integer id);
	
	Productimages create(Productimages productimages);
	
	Productimages save(Integer id,Productimages productimages);
	
	void deleteById(Integer id);

	List<Productimages> findAllProductWithOneImage();

	List<Productimages> findAllProductWithCategory(Integer id);

	List<Productimages> findAllProductWithBrand(Integer id);

	List<Productimages> findAllImagesByProductId(Integer id);
}