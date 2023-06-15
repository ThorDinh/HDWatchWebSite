package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hdwatch.dao.CartsDAO;
import com.hdwatch.entity.Carts;
import com.hdwatch.service.CartsService;

public class CartsServiceImpl implements CartsService{
	@Autowired
	CartsDAO cartsDAO;

	@Override
	public List<Carts> findAll() {
		// TODO Auto-generated method stub
		return cartsDAO.findAll();
	}

	@Override
	public Carts findById(Integer id) {
		// TODO Auto-generated method stub
		return cartsDAO.findById(id).get();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public List<Carts> findByAccountId(Integer id) {
//		// TODO Auto-generated method stub
//		return cartsDAO.findByAccountId(id);
//	}
	
}
