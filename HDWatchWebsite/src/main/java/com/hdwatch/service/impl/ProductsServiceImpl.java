package com.hdwatch.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	public Products findById(Integer  id) {
		return pdao.findById(id).get();
	}

	@Override
	public Products create(Products products) {
		return pdao.save(products);
	}

	@Override
	public Products save(Products products, Integer id) {
		 Products existingProduct = pdao.findById(id).orElse(null);
	        if (existingProduct != null) {
	            existingProduct.setName(products.getName());
	            existingProduct.setBrandid(products.getBrandid());
	            existingProduct.setCategoryid(products.getCategoryid());
	            existingProduct.setAvailable(products.getAvailable());
	            existingProduct.setCreatedate(products.getCreatedate());
	            existingProduct.setStock(products.getStock());;
	            existingProduct.setPrice(products.getPrice());
	            existingProduct.setOldprice(products.getOldprice());
	            existingProduct.setDescription(products.getDescription());
	            return pdao.save(existingProduct);
	        }
		return pdao.save(products);
	}

	@Override
	public void deleteByid(Integer id) {
		pdao.deleteById(id);
	}
	
	@Override
	public List<Products> findProductByCreateDateDESC(){
		Sort sort = Sort.by(Direction.DESC,"id");
		List<Products> list = pdao.findAll(sort);
		return list;
	}
	
	@Override
	public List<Products> findAllByBrandId(Integer id){
		return pdao.findByBrandid(id);
	}
	
	@Override
	public List<Products> findAllByCategoryId(Integer id){
		return pdao.findByCategoryid(id);
	}
}
