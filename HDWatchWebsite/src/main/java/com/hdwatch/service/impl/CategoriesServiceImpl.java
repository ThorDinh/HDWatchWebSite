package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdwatch.dao.CategoriesDAO;
import com.hdwatch.entity.Brands;
import com.hdwatch.entity.Categories;
import com.hdwatch.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {
	@Autowired
	CategoriesDAO cDao;

	@Override
	public List<Categories> findAll() {
		return cDao.findAll();
	}

	@Override
	public boolean existsById(Integer id) {
		return cDao.existsById(id);
	}

	@Override
	public Categories findById(Integer id) {
		return cDao.findById(id).get();
	}


	@Override
	public Categories save(Categories cate,Integer id) {
		 Categories existingBrand = cDao.findById(id).orElse(null);
	        if (existingBrand != null) {
	            existingBrand.setName(cate.getName());
	            return cDao.save(existingBrand);
	        }
		return cDao.save(cate);
	}

	@Override
	public void deleteById(Integer id) {
		cDao.deleteById(id);
	}

	@Override
	public Categories create(Categories cate) {
		return cDao.save(cate);
		
	}
}
