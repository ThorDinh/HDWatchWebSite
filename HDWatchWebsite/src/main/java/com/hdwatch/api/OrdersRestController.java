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

import com.hdwatch.entity.Orders;
import com.hdwatch.service.OrdersService;



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
