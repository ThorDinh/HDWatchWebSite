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

import com.hdwatch.entity.Accounts;
import com.hdwatch.service.AccountsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountsRestController {
	@Autowired
	AccountsService accountsService;
	
	@GetMapping
	public List<Accounts> getAll(){
		return accountsService.findAll();
	}
	
	@GetMapping("{id}")
	public Accounts getOne(@PathVariable("id")String id) {
		return accountsService.findById(id);
	}
	
	@PostMapping
	public Accounts createAccounts(@RequestBody Accounts accounts) {
		return accountsService.create(accounts);
	}
	
	@PutMapping("{id}")
	public Accounts updateAccounts(@PathVariable("id")String id,@RequestBody Accounts accounts) {
		return accountsService.save(id, accounts);
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable("id")String id) {
		accountsService.deleteById(id);
	}
}
