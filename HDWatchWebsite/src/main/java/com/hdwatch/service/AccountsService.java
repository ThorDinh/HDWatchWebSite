package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Accounts;

public interface AccountsService {
	
	Accounts findbyUsername(String username);
	
	List<Accounts> findAll();
	
	Accounts findById(String id);
	
	Accounts create(Accounts accounts);
	
	Accounts save(String id,Accounts accounts);
	
	void deleteById(String id);
	
//	Accounts findByEmai(String email);
	
	boolean authenticate(String username, String password);

	void changePassword(String username, String newPassword);

	
}
