package com.hdwatch.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdwatch.dao.OrderdetailsDAO;
import com.hdwatch.dao.OrdersDAO;
import com.hdwatch.entity.Orderdetails;
import com.hdwatch.entity.Orders;
import com.hdwatch.service.OrdersService;


public class OrdersServiceImpl implements OrdersService {
	@Autowired
	OrdersDAO oDao;
	
	@Autowired
	OrderdetailsDAO o2Dao;
	

	@Override
	public Orders findById(Integer id) {
		// TODO Auto-generated method stub
		return oDao.findById(id).get();
	}

//	@Override
//	public List<Orders> findByAccountId(Integer accountid) {
//		// TODO Auto-generated method stub
//		return oDao.findByAccountid(accountid);
//	}
//
//	@Override
//	public List<Orders> findOrderInMonth(Integer createdate) {
//		// TODO Auto-generated method stub
//		return oDao.findOrderInMonth(createdate);
//	}
//
//	@Override
//	public Integer countOrderInMonth(Integer createdate) {
//		// TODO Auto-generated method stub
//		return oDao.countOrderInMonth(createdate);
//	}

	@Override
	public Orders create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();

		Orders order = mapper.convertValue(orderData, Orders.class);
		oDao.save(order);

		TypeReference<List<Orderdetails>> type = new TypeReference<List<Orderdetails>>(){};
			List<Orderdetails> details = mapper.convertValue(orderData.get("orderdetails"), type).stream()
					.peek(d -> d.setOrders(order)).collect(Collectors.toList());
			o2Dao.saveAll(details);
			return order;
		
	}
}
