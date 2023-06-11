package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdwatch.dao.CategoriesDAO;
import com.hdwatch.entity.Categories;
import com.hdwatch.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {
	@Autowired
	CategoriesDAO cDao;

	@Override
	public List<Categories> findAll() {
		// TODO Auto-generated method stub
		return cDao.findAll();
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return cDao.existsById(id);
	}

	@Override
	public Categories findById(Integer id) {
		// TODO Auto-generated method stub
		return cDao.findById(id).get();
	}

	@Override
	public List<Categories> findByName(Integer id) {
		// TODO Auto-generated method stub
		return cDao.findByNameId(id);
	}

	@Override
	public Categories save(Categories cate) {
		// TODO Auto-generated method stub
		return cDao.save(cate);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		cDao.deleteById(id);
	}
}
