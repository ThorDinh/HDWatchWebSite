package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdwatch.dao.AccountsDAO;
import com.hdwatch.entity.Accounts;
import com.hdwatch.service.AccountsService;
@Service
public class AccountsServiceImpl implements AccountsService {
	@Autowired
	AccountsDAO accountsDAO;

	@Override
	public List<Accounts> findAll() {
		return accountsDAO.findAll();
	}

	@Override
	public Accounts findById(Integer id) {
		return accountsDAO.findById(id).get();
	}

	@Override
	public Accounts create(Accounts accounts) {
		return accountsDAO.save(accounts);
	}

	@Override
	public Accounts save(Integer id, Accounts accounts) {
		Accounts existingAccounts = accountsDAO.findById(id).orElse(null);
		if(existingAccounts != null) {
			existingAccounts.setId(accounts.getId());
			existingAccounts.setActivated(accounts.getActivated());
			existingAccounts.setFullname(accounts.getFullname());
			existingAccounts.setEmail(accounts.getEmail());
			existingAccounts.setFacebook(accounts.getFacebook());
			existingAccounts.setGoogle(accounts.getGoogle());
			existingAccounts.setUsername(accounts.getUsername());
			existingAccounts.setPassword(accounts.getPassword());
			return accountsDAO.save(existingAccounts);
		}
		return accountsDAO.save(accounts);
	}

	@Override
	public void deleteById(Integer id) {
		accountsDAO.deleteById(id);
	}

	@Override
	public Accounts findByUsername(String username) {
	
		return accountsDAO.findByUsername(username);
	}

	
}
