package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Orders;

public interface OrdersService {
//	List<Orders> findAll();
//	
	Orders findById(Integer id);
//	
//	Orders create(Orders orders);
//	
//	Orders save(Integer id,Orders orders);
//	
//	void deleteById(Integer id);
	
	public List<Orders> findByUsername(String username) ;
}
