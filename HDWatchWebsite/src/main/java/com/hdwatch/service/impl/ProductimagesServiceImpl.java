package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hdwatch.dao.ProductimagesDAO;
import com.hdwatch.entity.Productimages;
import com.hdwatch.service.ProductimagesService;

public class ProductimagesServiceImpl implements ProductimagesService {
	
//	@Autowired
//	ProductimagesDAO productimagesDAO;
//	
//	@Override
//	public List<Productimages> findAll() {
//		return productimagesDAO.findAll();
//	}
//
//	@Override
//	public Productimages findById(Integer id) {
//		return productimagesDAO.findById(id).get();
//	}
//
//	@Override
//	public Productimages create(Productimages productimages) {
//		return productimagesDAO.save(productimages);
//	}
//
//	@Override
//	public Productimages save(Integer id, Productimages productimages) {
//		Productimages existingProductimages = productimagesDAO.findById(id).orElse(null);
//		if(existingProductimages !=null) {
//			existingProductimages.setId(productimages.getId());
//			existingProductimages.setProductId(productimages.getProductId());
//			existingProductimages.setName(productimages.getName());
//			return productimagesDAO.save(existingProductimages);
//		}
//		return productimagesDAO.save(productimages);
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		productimagesDAO.deleteById(id);
//		
//	}
	
}
