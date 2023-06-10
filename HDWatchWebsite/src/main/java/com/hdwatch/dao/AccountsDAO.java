package com.hdwatch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdwatch.entity.Accounts;

public interface AccountsDAO extends JpaRepository<Accounts, Integer>{

}
