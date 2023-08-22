package com.hdwatch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdwatch.dao.FavoritedetailsDAO;
import com.hdwatch.dao.FavoritesDAO;
import com.hdwatch.entity.Favorites;
import com.hdwatch.service.FavoritesService;

@Service
public class FavoritesServiceImpl implements FavoritesService {
	@Autowired
	FavoritesDAO favoritesDAO;
	@Autowired
	FavoritedetailsDAO favoritedetailsDAO;

	@Override
	public Favorites addFavorite(Favorites favorite) {
		return favoritesDAO.save(favorite);
	}

}
