package com.hdwatch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hdwatch.entity.Accounts;
import com.hdwatch.entity.Roledetails;

public interface AccountsDAO extends JpaRepository<Accounts, Integer>{
	Accounts findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsById(String username);






}
