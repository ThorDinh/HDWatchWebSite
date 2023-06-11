package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdwatch.dao.ProductsDAO;
import com.hdwatch.entity.Products;
import com.hdwatch.service.ProductsService;

@Service
public class  ProductsServiceImpl implements ProductsService  {
	@Autowired
	ProductsDAO pdao;
	
	@Override
	public List<Products> findAll(){
		return pdao.findAll();
	}
	@Override
	public Products findById(Integer id) {
		return pdao.findById(id).get();
	}
		
}
