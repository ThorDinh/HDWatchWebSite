package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Roles;

public interface RolesService {
	
	List<Roles> findAll();
	
	Roles findById(String id);
//	
//	Roles create(Roles roles);
//	
//	Roles save(Integer id, Roles roles);
//	
//	void deleteById(Integer id);
}
