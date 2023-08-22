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
		Sort sort = Sort.by(Direction.ASC, "name");
		return bDao.findAll(sort);
	}

	@Override
	public Brands findById(Integer id) {
		return bDao.findById(id).get();
	}

	@Override
	public boolean existsById(Integer id) {
		return bDao.existsById(id);
	}

	@Override
	public Brands save(Brands brand, Integer id) {
		Brands existingBrand = bDao.findById(id).orElse(null);
		if (existingBrand != null) {
			existingBrand.setName(brand.getName());
			return bDao.save(existingBrand);
		}
		return bDao.save(brand);
	}

	@Override
	public void deleteById(Integer id) {
		bDao.deleteById(id);
	}

	@Override
	public Brands create(Brands brand) {
		return bDao.save(brand);
	}

	@Override
	public boolean exitsByName(String name) {
		try {
			Brands brand = bDao.findByName(name);
			if (brand.getName().equals(name)) {

			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public List<Brands> findByName(String name) {
		return bDao.findByNameList(name);
	}

}
