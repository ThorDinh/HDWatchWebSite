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

import com.hdwatch.entity.SaleEvents;
import com.hdwatch.service.SaleEventsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/saleevents")
public class SaleEventsRestController {
//	@Autowired
//	SaleEventsService eventsService;
//	
//	@GetMapping
//	public List<SaleEvents> getAll(){
//		return eventsService.findAll();
//	}
//	
//	@GetMapping("{id}")
//	public SaleEvents getOne(@PathVariable("id")Integer id) {
//		return eventsService.findById(id);
//	}
//	
//	@PostMapping
//	public SaleEvents createSaleEvents(@RequestBody SaleEvents saleEvents) {
//		return eventsService.create(saleEvents);
//	}
//	
//	@PutMapping("{id}")
//	public SaleEvents updateSaleEvents(@PathVariable("id")Integer id,@RequestBody SaleEvents saleEvents) {
//		return eventsService.save(id, saleEvents);
//	}
//	
//	@DeleteMapping("{id}")
//	public void deleteById(@PathVariable("id")Integer id) {
//		eventsService.deleteById(id);
//	}
}
