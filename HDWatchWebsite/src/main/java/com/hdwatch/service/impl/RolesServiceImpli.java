package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdwatch.dao.RolesDAO;
import com.hdwatch.entity.Roles;
import com.hdwatch.service.RolesService;

@Service
public class RolesServiceImpli implements RolesService {
	@Autowired
	RolesDAO rolesDAO;

	@Override
	public List<Roles> findAll() {
		return rolesDAO.findAll();
	}

	@Override
	public Roles findById(String id) {
		return rolesDAO.findById(id).get();
	}

}
