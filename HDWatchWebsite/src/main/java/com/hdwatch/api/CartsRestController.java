package com.hdwatch.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/carts")
public class CartsRestController {
//	@Autowired
//	CartsService cartsService;
//	
//	@GetMapping
//	public List<Carts> getAll() {
//		return cartsService.findAll();
//	}
//	
//	@GetMapping("{id}")
//	public Carts getOne(@PathVariable("id")Integer id) {
//		return cartsService.findById(id);
//	}
//	@PostMapping
//	public Carts createCarts(@RequestBody Carts carts) {
//		return cartsService.create(carts);
//	}
//	@PutMapping("{id}")
//	public Carts updateCarts(@PathVariable("id")Integer id,@RequestBody Carts carts) {
//		 return cartsService.save(carts, id);
//	}
//	@DeleteMapping("{id}")
//	public void deleteCart(@PathVariable("id")Integer id) {
//		cartsService.deleteById(id);
//	}
}
