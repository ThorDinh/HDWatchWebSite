package com.hdwatch.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.hdwatch.entity.Favorites;


public interface FavoritesService {
	List<Favorites> findAll();
	
	Favorites findById(Integer id);
	
	void deleteById(Integer id);
	
//	List<Favorites> findByAccountId(Integer acountid);
	
//	Favorites create(JsonNode favoriteData);
	
}
