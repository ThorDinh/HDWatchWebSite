package com.hdwatch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdwatch.entity.Brands;


public interface BrandsDAO extends JpaRepository<Brands, Integer>{
	
}
