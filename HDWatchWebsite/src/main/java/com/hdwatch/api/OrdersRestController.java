package com.hdwatch.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hdwatch.entity.Orders;
import com.hdwatch.service.OrdersService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrdersRestController {
	@Autowired
	OrdersService ordersService;

	@PostMapping()
	public Orders create(@RequestBody JsonNode orderData) {
		return ordersService.create(orderData);
	}
}