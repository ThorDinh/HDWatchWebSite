package com.hdwatch.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdwatch.dao.AccountsDAO;
import com.hdwatch.dao.RoledetailsDAO;
import com.hdwatch.dao.RolesDAO;
import com.hdwatch.entity.Accounts;
import com.hdwatch.entity.Roledetails;
import com.hdwatch.service.AccountsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/rest/accounts")
public class AccountsRestController {
	@Autowired
	AccountsService accountsService;
	
	@Autowired
	AccountsDAO aDao;
	
	@Autowired
	RolesDAO rDao;
	
	@Autowired
	RoledetailsDAO rdDao;
	@GetMapping
	public List<Accounts> getAll(){
		return accountsService.findAll();
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<Accounts> getAccount(@PathVariable("username") String username) {
		if (!aDao.existsById(username)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(accountsService.findByUserName(username));
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Accounts> postAccount(@RequestBody Accounts Account){
		if(aDao.existsById(Account.getUsername())) {
			return ResponseEntity.badRequest().build();
		}else {
			
			return ResponseEntity.ok(accountsService.create(Account));
		}
	}
	
	@PutMapping("/{username}")
	public ResponseEntity<Accounts> putAccount(@PathVariable("username") String username, @RequestBody Accounts Account){
		if(!aDao.existsById(username)) {
			return ResponseEntity.notFound().build();
		}else {
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//			String encodedPassword = passwordEncoder.encode(Account.getPassword());
//			Account.setPassword(encodedPassword);
			return ResponseEntity.ok(accountsService.save(username,Account));
		}
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteAccount(@PathVariable("username") String username){
		if(!aDao.existsById(username)) {
			return ResponseEntity.notFound().build();
		}else {
			accountsService.deleteById(username);
			return ResponseEntity.ok().build();
		}
	}
	@GetMapping("/authorities")
	public Map<String, Object> getAuthority(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accounts",accountsService.findAll());
		map.put("roles", rDao.findAll());
		map.put("authorities",rdDao.findAll());
		return map;
	}
	@PostMapping("/authorities")
	public Roledetails postAuthorities(@RequestBody Roledetails authority) {
		return rdDao.save(authority);
	}
	@DeleteMapping("/authorities/{id}")
	public void deleteAuthorities(@PathVariable("id") Integer id) {
		accountsService.deleteRoleDetail(id);
	}
}
