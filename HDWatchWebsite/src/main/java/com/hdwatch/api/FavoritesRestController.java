package com.hdwatch.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hdwatch.entity.Favorites;

import com.hdwatch.service.FavoritesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favorites")
public class FavoritesRestController {
}
	

