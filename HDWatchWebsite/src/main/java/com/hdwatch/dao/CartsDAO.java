package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import com.hdwatch.entity.Carts;

public interface CartsDAO extends JpaRepository<Carts, Integer>{

	Object status(HttpStatus badRequest);
//	@Query("SELECT c.accountid FROM Favorites c WHERE c.accountid = :accountid")
//	List<Carts> findByAccountId(@Param("accountid") Integer accountid);
}
