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
	
	public OrdersServiceImpl(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }
	
	@Autowired
	OrderdetailsDAO o2Dao;
	

	@Override
	public Orders findById(Integer id) {
		return ordersDAO.findById(id).get();
	}


	@Override
	public List<Orders> findAll() {

		return ordersDAO.findAll();
	}


	@Override
	public Orders save(Integer id, Orders orders) {
		 Orders existingOrders = ordersDAO.findById(id).orElse(null);
	        if (existingOrders != null) {
	            existingOrders.setId(orders.getId());
	            existingOrders.setAccountId(orders.getAccountId());
	            existingOrders.setCreateDate(orders.getCreateDate());
	            existingOrders.setAddress(orders.getAddress());
	            existingOrders.setStatus(orders.getStatus());
	            return ordersDAO.save(existingOrders);
	        }
	        return ordersDAO.save(orders);
	}


	@Override
	public boolean deleteById(Integer id) {
		ordersDAO.deleteById(id);
		return false;
		
	}

	@Override
	public List<Orders> findOrderInMonth(Integer createdate) {
		// TODO Auto-generated method stub
		return ordersDAO.findOrderInMonth(createdate);
	}

	@Override
	public Integer countOrderInMonth(Integer createdate) {
		// TODO Auto-generated method stub
		return ordersDAO.countOrderInMonth(createdate);
	}

	
	
	@Override
	public List<Orders> findByUsername(String username) {
		return ordersDAO.findByUsername(username);
	}

	@Override
	public Orders create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		
		Orders orders = mapper.convertValue(orderData, Orders.class);
		
		ordersDAO.save(orders);
		
		TypeReference<List<Orderdetails>> type = new TypeReference<List<Orderdetails>>() {};
		List<Orderdetails> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d -> d.setOrders(orders)).collect(Collectors.toList());
		o2Dao.saveAll(details);
		
		return orders;
	
	}
}
