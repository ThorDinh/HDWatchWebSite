package com.hdwatch.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
