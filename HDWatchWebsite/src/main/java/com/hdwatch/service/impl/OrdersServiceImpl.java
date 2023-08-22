package com.hdwatch.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdwatch.dao.OrderdetailsDAO;
import com.hdwatch.dao.OrdersDAO;
import com.hdwatch.entity.Orderdetails;
import com.hdwatch.entity.Orders;
import com.hdwatch.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	OrdersDAO ordersDAO;

	@Autowired
	OrderdetailsDAO o2Dao;

	@Override
	public Orders findById(Integer id) {
		return ordersDAO.findById(id).get();
	}

	@Override
	public Orders create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();

		Orders orders = mapper.convertValue(orderData, Orders.class);

		ordersDAO.save(orders);

		TypeReference<List<Orderdetails>> type = new TypeReference<List<Orderdetails>>() {
		};
		List<Orderdetails> details = mapper.convertValue(orderData.get("orderDetails"), type).stream()
				.peek(d -> d.setOrders(orders)).collect(Collectors.toList());
		o2Dao.saveAll(details);

		return orders;
	}

	@Override
	public List<Orders> findByUsername(String username) {
		return ordersDAO.findByUsername(username);
	}
}
