package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hdwatch.entity.Categories;

public interface CategoriesDAO extends JpaRepository<Categories, Integer>{
	@Query("SELECT c.name FROM categories c WHERE c.id =:id")
	List<Categories> findByNameId(@Param("id") Integer id);
}
