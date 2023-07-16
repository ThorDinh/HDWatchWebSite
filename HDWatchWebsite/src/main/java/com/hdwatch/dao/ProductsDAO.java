package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
}
