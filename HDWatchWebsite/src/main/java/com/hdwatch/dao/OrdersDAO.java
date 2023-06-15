package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hdwatch.entity.Orders;


public interface OrdersDAO extends JpaRepository<Orders, Integer>{
//	@Query("SELECT o FROM Orders o WHERE o.accountId = :acountid")
//	List<Orders> findByAccountid(@Param("accountid")  Integer accountid);
//	@Query("SELECT o FROM Orders o WHERE MONTH(o.createDate) = :month")
//	List<Orders> findOrderInMonth(@Param("month") Integer month);
//	
//	@Query("SELECT COUNT(o) FROM Orders o WHERE MONTH(o.createDate) = :month")
//	Integer countOrderInMonth(@Param("month") Integer month);

}
