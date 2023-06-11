package com.hdwatch.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdwatch.entity.Brands;
import com.hdwatch.service.BrandsService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/brands")
public class BrandsRestController {
	@Autowired
	BrandsService brandsService;
	@GetMapping("{id}")
	public List<Brands> findBrandNamesById(Integer id) {
	    return brandsService.findNameById(id);
	}
		
}
