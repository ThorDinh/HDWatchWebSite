package com.hdwatch.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdwatch.dao.FavoritedetailsDAO;
import com.hdwatch.dao.FavoritesDAO;
import com.hdwatch.entity.Favoritedetails;
import com.hdwatch.entity.Favorites;
import com.hdwatch.service.FavoritesService;

public class FavoritesServiceImpl implements FavoritesService {
	@Autowired
	FavoritesDAO favoritesDAO;
	@Autowired
	FavoritedetailsDAO favoritedetailsDAO;
	

	@Override
	public List<Favorites> findAll() {
		// TODO Auto-generated method stub
		return favoritesDAO.findAll();
	}

	@Override
	public Favorites findById(Integer id) {
		// TODO Auto-generated method stub
		return favoritesDAO.findById(id).get();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Favorites> findByAccountId(Integer acountid) {
		// TODO Auto-generated method stub
		return favoritesDAO.findByAccountId(acountid);
	}

	@Override
	public Favorites create(JsonNode favoriteData) {
		ObjectMapper mapper = new ObjectMapper();

		Favorites fv = mapper.convertValue(favoriteData,Favorites.class);
		favoritesDAO.save(fv);

		TypeReference<List<Favoritedetails>> type = new TypeReference<List<Favoritedetails>>(){};
			List<Favoritedetails> fvdetails = mapper.convertValue(favoriteData.get("favoritedetails"), type).stream()
					.peek(d -> d.setFavorites(fv)).collect(Collectors.toList());
			favoritedetailsDAO.saveAll(fvdetails);
			return fv;
	}
	
}
