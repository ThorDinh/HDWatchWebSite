package com.hdwatch.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hdwatch.entity.Accounts;


public interface AccountsService {
	
	List<Accounts> findAll();
	
	Accounts findById(Integer id);
	
	Accounts create(Accounts accounts);
	
	Accounts save(Integer id,Accounts accounts);
	
	Accounts findByUsername(String username);
	
	
	
	void deleteById(Integer id);

	Accounts loadUserByUsername(String username);

}
