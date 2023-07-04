package com.hdwatch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdwatch.entity.Products;

public interface ProductsDAO extends JpaRepository<Products, Integer>{
}
