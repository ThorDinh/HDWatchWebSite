package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hdwatch.entity.Productimages;
import com.hdwatch.entity.Products;

public interface ProductimagesDAO extends JpaRepository<Productimages, Integer>{

	@Query(value = "SELECT pi.name FROM productimages pi WHERE pi.product_id = ?1 limit 1;",nativeQuery =true)
	String findOneByProductid(Integer id);
	
	@Query(value = "SELECT *\r\n"
			+ "FROM (\r\n"
			+ "  SELECT pi.id, pi.product_id, pi.name \r\n"
			+ "  FROM productImages pi\r\n"
			+ "  INNER JOIN (\r\n"
			+ "    SELECT product_id, MIN(name) AS min_name\r\n"
			+ "    FROM productImages\r\n"
			+ "    GROUP BY product_id\r\n"
			+ "  ) sub ON pi.product_id = sub.product_id AND pi.name = sub.min_name\r\n"
			+ ") result\r\n"
			+ "ORDER BY result.id DESC;",nativeQuery =true)
	List<Productimages> findAllForProductid();
	
	@Query(value = "SELECT pi.id, pi.product_id, pi.name "
	        + "FROM productimages pi "
	        + "INNER JOIN ( "
	        + "  SELECT product_id, MIN(name) AS min_name "
	        + "  FROM productimages "
	        + "  GROUP BY product_id "
	        + ") sub ON pi.product_id = sub.product_id AND pi.name = sub.min_name "
	        + "INNER JOIN products p ON p.id = pi.product_id "
	        + "WHERE p.category_id = ?1", nativeQuery = true)
	List<Productimages> findAllByCategory(Integer id);
	
	@Query(value = "SELECT pi.id, pi.product_id, pi.name "
	        + "FROM productimages pi "
	        + "INNER JOIN ( "
	        + "  SELECT product_id, MIN(name) AS min_name "
	        + "  FROM productimages "
	        + "  GROUP BY product_id "
	        + ") sub ON pi.product_id = sub.product_id AND pi.name = sub.min_name "
	        + "INNER JOIN products p ON p.id = pi.product_id "
	        + "WHERE p.brand_id = ?1", nativeQuery = true)
	List<Productimages> findAllByBrand(Integer id);
}
