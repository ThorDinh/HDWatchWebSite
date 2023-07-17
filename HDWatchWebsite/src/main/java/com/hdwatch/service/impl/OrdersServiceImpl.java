package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdwatch.dao.OrdersDAO;
import com.hdwatch.entity.Orders;
import com.hdwatch.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	OrdersDAO ordersDAO;
//	
//	public OrdersServiceImpl(OrdersDAO ordersDAO) {
//        this.ordersDAO = ordersDAO;
//    }
//	
////	@Autowired
////	OrderdetailsDAO o2Dao;
//	
//
	@Override
	public Orders findById(Integer id) {
		return ordersDAO.findById(id).get();
	}
//
//
//	@Override
//	public List<Orders> findAll() {
//
//		return ordersDAO.findAll();
//	}
//
//
//	@Override
//	public Orders create(Orders orders) {
//
//		return ordersDAO.save(orders);
//	}
//
//
//	@Override
//	public Orders save(Integer id, Orders orders) {
//		 Orders existingOrders = ordersDAO.findById(id).orElse(null);
//	        if (existingOrders != null) {
//	            existingOrders.setId(orders.getId());;
//	            existingOrders.setAccountId(orders.getAccountId());
//	            existingOrders.setCreateDate(orders.getCreateDate());
//	            existingOrders.setAddress(orders.getAddress());
//	            existingOrders.setStatus(orders.getStatus());
//	            return ordersDAO.save(existingOrders);
//	        }
//	        return ordersDAO.save(orders);
//	}
//
//
//	@Override
//	public void deleteById(Integer id) {
//		ordersDAO.deleteById(id);
//		
//	}

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

//	@Override
//	public Orders create(JsonNode orderData) {
//		ObjectMapper mapper = new ObjectMapper();
//
//		Orders order = mapper.convertValue(orderData, Orders.class);
//		oDao.save(order);
//
//		TypeReference<List<Orderdetails>> type = new TypeReference<List<Orderdetails>>(){};
//			List<Orderdetails> details = mapper.convertValue(orderData.get("orderdetails"), type).stream()
//					.peek(d -> d.setOrders(order)).collect(Collectors.toList());
//			o2Dao.saveAll(details);
//			return order;
//		
//	}
	
	@Override
	public List<Orders> findByUsername(String username) {
		return ordersDAO.findByUsername(username);
	}
}
