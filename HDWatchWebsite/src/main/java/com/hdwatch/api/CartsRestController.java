package com.hdwatch.api;

import java.util.List;

import javax.websocket.server.PathParam;

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

import com.hdwatch.entity.Carts;
import com.hdwatch.service.CartsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/carts")
public class CartsRestController {
	@Autowired
	CartsService cartsService;
	
	@GetMapping
	public List<Carts> getAll() {
		return cartsService.findAll();
	}
	
	@GetMapping("{id}")
	public Carts getOne(@PathVariable("id")Integer id) {
		return cartsService.findById(id);
	}
	@PostMapping
	public Carts createCarts(@RequestBody Carts carts) {
		return cartsService.create(carts);
	}
	@PutMapping("{id}")
	public Carts updateCarts(@PathVariable("id")Integer id,@RequestBody Carts carts) {
		 return cartsService.save(carts, id);
	}
	@DeleteMapping("{id}")
	public void deleteCart(@PathVariable("id")Integer id) {
		cartsService.deleteById(id);
	}
}
