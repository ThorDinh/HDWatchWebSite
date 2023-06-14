package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hdwatch.entity.Carts;

public interface CartsDAO extends JpaRepository<Carts, Integer>{
	@Query("SELECT c.accountid FROM Favorites c WHERE c.accountid = :accountid")
	List<Carts> findByAccountId(@Param("accountid") Integer accountid);
}
