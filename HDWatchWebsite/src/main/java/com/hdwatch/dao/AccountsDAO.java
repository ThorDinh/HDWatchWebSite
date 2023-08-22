package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hdwatch.entity.Accounts;

public interface AccountsDAO extends JpaRepository<Accounts, String> {

	Accounts findByUsername(String username);

//	Accounts findByEmail(String email);

	@Query("SELECT COUNT(a) FROM Accounts a, Roledetails rd WHERE a.username = rd.accounts.username AND rd.roles.role = :role")
	Long countCustomer(@Param("role") String role);

	@Query(value = "SELECT a.* FROM accounts a WHERE a.username LIKE %:name% OR a.fullname LIKE %:name%", nativeQuery = true)
	public List<Accounts> findByName(String name);
}
