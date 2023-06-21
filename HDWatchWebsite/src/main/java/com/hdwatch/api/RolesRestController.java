package com.hdwatch.api;

import java.util.List;

import javax.management.relation.Role;

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

import com.hdwatch.entity.Roles;
import com.hdwatch.service.RolesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RolesRestController {
//	@Autowired
//	RolesService rolesService;
//	
//	@GetMapping
//	public List<Roles> getAll(){
//		return rolesService.findAll();
//	}
//	
//	@GetMapping("{id}")
//	public Roles getOne(@PathVariable("id")Integer id){
//		return rolesService.findById(id);
//	}
//	
//	@PostMapping
//	public Roles createRoles(@RequestBody Roles roles) {
//		return rolesService.create(roles);
//	}
//	
//	@PutMapping("{id}")
//	public Roles updateRoles(@PathVariable("id")Integer id,@RequestBody Roles roles) {
//		return rolesService.save(id, roles);
//	}
//	
//	@DeleteMapping("{id}")
//	public void deleteById(@PathVariable("id")Integer id) {
//		rolesService.deleteById(id);
//	}
}
