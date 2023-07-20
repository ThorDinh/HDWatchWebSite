package com.hdwatch.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrdersRestController {
//	@Autowired
//	OrdersService ordersService;
//	
//	@GetMapping
//	public List<Orders> getAll(){
//		return ordersService.findAll();
//	}
//	
//	@GetMapping("{id}")
//	public Orders getOne(@PathVariable("id")Integer id) {
//		return ordersService.findById(id);
//	}
//	
//	@PostMapping
//	public Orders create(@RequestBody Orders orders) {
//		return ordersService.create(orders);
//	}
//	
//	@PutMapping("{id}")
//	public Orders updateOrders(@PathVariable("id")Integer id,@RequestBody Orders orders) {
//		return ordersService.save(id, orders);
//	}
//	
//	@DeleteMapping("{id}")
//	public void deleteById(@PathVariable("id")Integer id) {
//		ordersService.deleteById(id);
//	}
}
