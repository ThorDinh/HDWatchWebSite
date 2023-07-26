package com.hdwatch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hdwatch.entity.Orders;


@Service
public interface OrdersService {
	
	Orders create(JsonNode orderData);
	
	List<Orders> findAll();
	
	Orders findById(Integer id);
	
	boolean deleteById(Integer id);
	
	public List<Orders> findByUsername(String username) ;
	
	List<Orders> findOrderInMonth(Integer month);
	
	Integer countOrderInMonth(Integer month);
	
	Orders save(Integer id, Orders orders);
}
