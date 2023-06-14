package com.hdwatch.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdwatch.entity.Orders;
import com.hdwatch.service.OrdersService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrdersRestController {
	@Autowired
	OrdersService ordersService;
	@GetMapping("{id}")
	public Orders getOne(@PathVariable("id") Integer id) {
		
		return ordersService.findById(id);
	}

}
