package com.hdwatch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdwatch.entity.Categories;

public interface CategoriesDAO extends JpaRepository<Categories, Integer>{

}
