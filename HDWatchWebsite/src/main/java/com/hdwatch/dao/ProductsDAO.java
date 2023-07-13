package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdwatch.entity.Products;

public interface ProductsDAO extends JpaRepository<Products, Integer>{
	List<Products> findByBrandid(Integer brandid);
	
	List<Products> findByCategoryid(Integer categoryid);
}
