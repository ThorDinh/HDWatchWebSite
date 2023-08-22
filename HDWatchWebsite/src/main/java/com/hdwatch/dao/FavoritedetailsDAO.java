package com.hdwatch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdwatch.entity.Favoritedetails;

public interface FavoritedetailsDAO extends JpaRepository<Favoritedetails, Integer> {

	List<Favoritedetails> findByFavoriteId(Integer favoriteId);

}
