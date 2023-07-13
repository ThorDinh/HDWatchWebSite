package com.hdwatch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdwatch.entity.Favorites;

public interface FavoritesDAO extends JpaRepository<Favorites, Integer>{

}
