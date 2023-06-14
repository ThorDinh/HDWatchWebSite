package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.hdwatch.entity.Favorites;

public interface FavoritesDAO extends JpaRepository<Favorites, Integer>{
	@Query("SELECT c.accountid FROM Favorites c WHERE c.accountid = :accountid")
	List<Favorites> findByAccountId(@Param("accountid") Integer accountid);
}
