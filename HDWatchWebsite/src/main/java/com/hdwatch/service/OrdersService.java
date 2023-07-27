package com.hdwatch.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.hdwatch.entity.Orders;

public interface OrdersService {
	Orders create(JsonNode orderData);

	Orders findById(Integer id);

	List<Orders> findByUsername(String username);

}
