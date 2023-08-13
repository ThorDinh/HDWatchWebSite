package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdwatch.entity.Favorites;

public interface FavoritesDAO extends JpaRepository<Favorites, Integer>{

	List<Favorites> findByAccountId(String accountId);

}
