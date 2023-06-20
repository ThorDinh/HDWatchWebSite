package com.hdwatch.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hdwatch.entity.Favorites;

import com.hdwatch.service.FavoritesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favorites")
public class FavoritesRestController {
	
//	 @Autowired FavoritesService favoritesService;
//	 
//	 @GetMapping public List<Favorites> getAll() { return
//	 favoritesService.findAll(); }
//	 
//	 @GetMapping("{id}") public Favorites getOne(@PathVariable("id")Integer id) {
//	 return favoritesService.findById(id); }
//	 
//	 @PostMapping public Favorites createFavorites(@RequestBody Favorites
//	 favorites) { return favoritesService.create(favorites); }
//	 
//	 @PutMapping("{id}") public Favorites
//	 updateFavorites(@PathVariable("id")Integer id,@RequestBody Favorites
//	 favorites) { return favoritesService.save(id, favorites); }
//	 
//	 @DeleteMapping("{id}") public void deleteFavorites(@PathVariable("id")Integer
//	 id) { favoritesService.deleteById(id); }
	
}
	

