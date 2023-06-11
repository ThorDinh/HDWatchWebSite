package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hdwatch.dao.BrandsDAO;
import com.hdwatch.entity.Brands;
import com.hdwatch.service.BrandsService;
@Service
public class BrandsServiceImpl implements BrandsService {
	@Autowired
	BrandsDAO bDao;
	@Override
	public List<Brands> findAll() {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(Direction.ASC,"name");
		return bDao.findAll(sort);
	}

	@Override
	public Brands findById(Integer id) {
		// TODO Auto-generated method stub
		return bDao.findById(id).get();
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return bDao.existsById(id);
	}

	@Override
	public Brands save(Brands brand) {
		// TODO Auto-generated method stub
		return bDao.save(brand);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		bDao.deleteById(id);
	}

	@Override
	public List<Brands> findNameById(Integer id){
		// TODO Auto-generated method stub
		
		return bDao.findByNameId(id);
	}
}
