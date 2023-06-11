package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hdwatch.entity.Brands;


public interface BrandsDAO extends JpaRepository<Brands, Integer>{
	@Query("SELECT b.name FROM Brands b WHERE b.id =:id")
	List<Brands> findByNameId(@Param("id")Integer id);
}
