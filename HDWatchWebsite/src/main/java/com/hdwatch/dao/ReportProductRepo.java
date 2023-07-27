package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hdwatch.report.ReportProduct;

@Repository
public interface ReportProductRepo extends JpaRepository<ReportProduct, Long>{
//	@Query("SELECT NEW ReportProduct(p.id, p.name, COUNT(od.products.id)) "
//			+ "FROM Products p, Orderdetails od "
//			+ "WHERE p.id = od.products.id AND MONTH(p.createDate) = :month "
//			+ "GROUP BY p.id, p.name ORDER BY COUNT(od.products.id) DESC")
//	List<ReportProduct> reportProduct(@Param("month") Integer month);
}
