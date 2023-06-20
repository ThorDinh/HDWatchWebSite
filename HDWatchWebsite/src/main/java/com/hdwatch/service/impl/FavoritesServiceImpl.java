package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hdwatch.dao.FavoritedetailsDAO;
import com.hdwatch.dao.FavoritesDAO;
import com.hdwatch.entity.Favorites;
import com.hdwatch.service.FavoritesService;

public class FavoritesServiceImpl implements FavoritesService {
//	@Autowired
//	FavoritesDAO favoritesDAO;
//	@Autowired
//	FavoritedetailsDAO favoritedetailsDAO;
//	
//
//	@Override
//	public List<Favorites> findAll() {
//		return favoritesDAO.findAll();
//	}
//
//	@Override
//	public Favorites findById(Integer id) {
//		return favoritesDAO.findById(id).get();
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		favoritesDAO.deleteById(id);
//		
//	}
//
//	@Override
//	public Favorites create(Favorites favorites) {
//		return favoritesDAO.save(favorites);
//	}
//
//	@Override
//	public Favorites save(Integer id, Favorites favorites) {
//		  Favorites existingFavorites = favoritesDAO.findById(id).orElse(null);
//	        if (existingFavorites != null) {
//	            existingFavorites.setId(favorites.getId());
//	            existingFavorites.setAccountId(favorites.getAccountId());
//	            return favoritesDAO.save(existingFavorites);
//	        }
//	        return favoritesDAO.save(favorites);
//	}

//	@Override
//	public List<Favorites> findByAccountId(Integer acountid) {
//		// TODO Auto-generated method stub
//		return favoritesDAO.findByAccountId(acountid);
//	}
	
//	@Override
//	public Favorites create(JsonNode favoriteData) {
//		ObjectMapper mapper = new ObjectMapper();
//
//		Favorites fv = mapper.convertValue(favoriteData,Favorites.class);
//		favoritesDAO.save(fv);
//
//		TypeReference<List<Favoritedetails>> type = new TypeReference<List<Favoritedetails>>(){};
//			List<Favoritedetails> fvdetails = mapper.convertValue(favoriteData.get("favoritedetails"), type).stream()
//					.peek(d -> d.setFavoriteId(fv)).collect(Collectors.toList());
//			favoritedetailsDAO.saveAll(fvdetails);
//			return fv;
//	}
	
}
