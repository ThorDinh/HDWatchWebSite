package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hdwatch.entity.Products;

public interface ProductsDAO extends JpaRepository<Products, Integer>{
	List<Products> findByBrandid(Integer brandid);
	
	List<Products> findByCategoryid(Integer categoryid);
	
	@Query(value = "SELECT p.* FROM products p " +
            "LEFT JOIN (SELECT product_id, COUNT(*) AS order_count " +
            "           FROM orderdetails " +
            "           GROUP BY product_id) od " +
            "ON p.id = od.product_id " +
            "ORDER BY ISNULL(od.order_count, 0) DESC", nativeQuery = true)
	List<Products> getProductsOrderedByOrderCount();
	
	@Query(value = "SELECT p.* FROM products p "
	        + "INNER JOIN categories c ON p.category_id = c.id "
	        + "INNER JOIN brands b ON p.brand_id = b.id " 
	        + "WHERE (LOWER(p.name) LIKE %:keyword% "
	        + "OR LOWER(p.description) LIKE %:keyword% "
	        + "OR LOWER(c.name) LIKE %:keyword% "
	        + "OR LOWER(b.name) LIKE %:keyword%) "
	        + "AND (:category IS NULL OR p.category_id = :category) "
	        + "AND (:brand IS NULL OR p.brand_id = :brand)",
	        countQuery = "SELECT count(*) FROM products p "
	        + "INNER JOIN categories c ON p.category_id = c.id "
	        + "INNER JOIN brands b ON p.brand_id = b.id " 
	        + "WHERE (LOWER(p.name) LIKE %:keyword% "
	        + "OR LOWER(p.description) LIKE %:keyword% "
	        + "OR LOWER(c.name) LIKE %:keyword% "
	        + "OR LOWER(b.name) LIKE %:keyword%) "
	        + "AND (:category IS NULL OR p.category_id = :category) "
	        + "AND (:brand IS NULL OR p.brand_id = :brand)",
	        nativeQuery = true)
	Page<Products> searchProductsByKeyword(String keyword, Integer category, Integer brand, Pageable pageable);

	@Query(value = "SELECT p.* FROM products p "
			+ "INNER JOIN categories c ON p.category_id = c.id "
	        + "INNER JOIN brands b ON p.brand_id = b.id " 
			+ "WHERE p.name LIKE %:name% OR LOWER(b.name) LIKE %:name% OR LOWER(c.name) LIKE %:name%", nativeQuery = true)
	public List<Products> findByName(String name);
}
