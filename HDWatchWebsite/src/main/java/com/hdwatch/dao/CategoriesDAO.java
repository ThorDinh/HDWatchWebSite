package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hdwatch.entity.Categories;

public interface CategoriesDAO extends JpaRepository<Categories, Integer> {
	@Query(value = "SELECT c.* FROM categories c WHERE c.name LIKE %:name%", nativeQuery = true)
	public List<Categories> findByName(String name);
}
