package com.hdwatch.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdwatch.dao.FavoritedetailsDAO;
import com.hdwatch.dao.FavoritesDAO;
import com.hdwatch.entity.Favoritedetails;
import com.hdwatch.entity.Favorites;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favorites")
public class FavoritesRestController {
    @Autowired
    private FavoritesDAO favoritesRepository;

    @Autowired
    private FavoritedetailsDAO favoriteDetailRepository;

    @PostMapping
    public ResponseEntity<Favoritedetails> addToFavorites(@RequestBody Favoritedetails favoritedetails) {
        Favoritedetails savedFavoriteDetails = favoriteDetailRepository.save(favoritedetails);
        return ResponseEntity.ok(savedFavoriteDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromFavorites(@PathVariable Integer id) {
        favoriteDetailRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{accountId}")
    public ResponseEntity<List<Favorites>> getUserFavorites(@PathVariable String accountId) {
        List<Favorites> favorites = favoritesRepository.findByAccountId(accountId);
        return ResponseEntity.ok(favorites);
    }

    @GetMapping("/details/{favoriteId}")
    public ResponseEntity<List<Favoritedetails>> getFavoriteDetails(@PathVariable Integer favoriteId) {
        List<Favoritedetails> favoriteDetails = favoriteDetailRepository.findByFavoriteId(favoriteId);
        return ResponseEntity.ok(favoriteDetails);
    }
}

	

