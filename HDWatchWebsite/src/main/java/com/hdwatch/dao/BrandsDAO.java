package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hdwatch.entity.Brands;

public interface BrandsDAO extends JpaRepository<Brands, Integer> {
	Brands findByName(String name);

	@Query(value = "SELECT b.* FROM brands b WHERE b.name LIKE %:name%", nativeQuery = true)
	public List<Brands> findByNameList(String name);
}
