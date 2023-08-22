package com.hdwatch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdwatch.dao.RoledetailsDAO;
import com.hdwatch.entity.Roledetails;
import com.hdwatch.service.RoledetailService;

@Service
public class RoledetailServiceImpl implements RoledetailService {
	@Autowired
	RoledetailsDAO roledetailDAO;

	@Override
	public Roledetails create(Roledetails roledetail) {
		return roledetailDAO.save(roledetail);
	}
}
