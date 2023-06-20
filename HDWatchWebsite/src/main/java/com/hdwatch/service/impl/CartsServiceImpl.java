package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.hdwatch.dao.CartsDAO;
import com.hdwatch.entity.Carts;
import com.hdwatch.service.CartsService;

public class CartsServiceImpl implements CartsService{
	@Autowired
	CartsDAO cartsDAO;

	@Override
	public List<Carts> findAll() {
		return cartsDAO.findAll();
	}

	@Override
	public Carts findById(Integer id) {
		return cartsDAO.findById(id).get();
	}
	
	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public Carts create(Carts carts) {
		return cartsDAO.save(carts);
	}

	@Override
	public Carts save(Carts carts, Integer id) {
        Carts existingCart = cartsDAO.findById(id).orElse(null);
        if (existingCart != null) {
            existingCart.setAccountId(carts.getAccountId());
            existingCart.setId(carts.getId());

            return cartsDAO.save(existingCart);
        }
        return cartsDAO.save(carts);
    }

	
	
}
