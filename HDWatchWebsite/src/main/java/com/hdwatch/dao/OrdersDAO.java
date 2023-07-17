package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hdwatch.entity.Orders;


public interface OrdersDAO extends JpaRepository<Orders, Integer>{
	@Query(value="SELECT * FROM orders o WHERE o.account_id LIKE %?1% ORDER BY o.create_date DESC", nativeQuery = true)
	List<Orders> findByUsername(String username);
}
