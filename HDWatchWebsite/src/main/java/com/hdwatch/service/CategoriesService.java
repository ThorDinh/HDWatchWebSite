package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Categories;



public interface CategoriesService {
	List<Categories> findAll();

	boolean existsById(Integer id);

	Categories findById(Integer id);

	Categories save(Categories cate, Integer id);

	Categories create(Categories cate);
		
	void deleteById(Integer id);

	List<Categories> findByName(String name);
}
