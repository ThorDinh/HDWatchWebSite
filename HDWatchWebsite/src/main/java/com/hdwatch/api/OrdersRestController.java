package com.hdwatch.api;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hdwatch.dao.OrdersDAO;
import com.hdwatch.entity.Orders;
import com.hdwatch.service.OrdersService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrdersRestController {
	@Autowired
	OrdersService ordersService;

	
	@GetMapping
	public List<Orders> getAll(){
		return ordersService.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Orders> getOne(@PathVariable("id")Integer id) {
		
		Orders orders = ordersService.findById(id);
		if(orders != null) {
			return ResponseEntity.ok(orders); 
		}else {
		return ResponseEntity.notFound().build();
		}
	}
	@PutMapping("/{id}")
    public ResponseEntity<Orders> updateProduct(@PathVariable Integer id, @RequestBody Orders orders) {
        
       Orders updatedOrders = ordersService.save(id, orders);
        if (updatedOrders != null) {
            return ResponseEntity.ok(updatedOrders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
	@PutMapping("{id}")
	public ResponseEntity<Orders> updateOrders(@RequestParam("id") Integer id,@RequestBody Orders orders) {
		 
        Orders updatedOrders = ordersService.save(id, orders);
        if (updatedOrders != null) {
            return ResponseEntity.ok(updatedOrders);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Orders> deleteById(@PathVariable("id")Integer id) {
		   boolean deleted = ordersService.deleteById(id);
	        if (deleted) {
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
		
	}
	@PostMapping
	public Orders  purcharse(@RequestBody JsonNode jsonNode) {
		
		return ordersService.create(jsonNode);
	}
}
