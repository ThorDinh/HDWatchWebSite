package com.hdwatch.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.hdwatch.entity.Roles;
import com.hdwatch.service.AccountsService;
import com.hdwatch.service.RoledetailService;
import com.hdwatch.service.RolesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountsRestController {
	@Autowired
	AccountsService accountsService;
	
	@Autowired
	AccountsDAO aDao;
	
	@Autowired
	RolesDAO rDao;
	
	@Autowired
	RoledetailsDAO rdDao;
	
	@Autowired
	RolesService roleService;
	
	@Autowired
	RoledetailService roledetailService;
	
	// Lấy danh sách tất cả tài khoản
	@GetMapping
	public List<Accounts> getAll(){
		return accountsService.findAll();
	}
	
	// Lấy thông tin tài khoản theo tên đăng nhập (username)
	@GetMapping("/{username}")
	public ResponseEntity<Accounts> getAccount(@PathVariable("username") String username) {
		if (!aDao.existsById(username)) {
			return ResponseEntity.notFound().build();
		} else {
			Accounts accounts = accountsService.findByUserName(username);
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			return ResponseEntity.ok(accountsService.findByUserName(username));
		}
	}
	
	// Tạo mới tài khoản
	@PostMapping("")
	public ResponseEntity<Accounts> postAccount(@RequestBody Accounts Account){
		if(aDao.existsById(Account.getUsername())) {
			return ResponseEntity.badRequest().build();
		}else {
			//Mã hóa mật khẩu khi tạo mới
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(Account.getPassword());
			Account.setPassword(encodedPassword);
			return ResponseEntity.ok(accountsService.create(Account));
		}
	}
	
	// Cập nhật thông tin tài khoản
	@PutMapping("/{username}")
	public ResponseEntity<Accounts> putAccount(@PathVariable("username") String username, @RequestBody Accounts Account){
		if(!aDao.existsById(username)) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(accountsService.save(username,Account));
		}
	}
	
	// Lấy danh sách tất cả các quyền (authorities) của tài khoản
	@GetMapping("/authorities")
	public Map<String, Object> getAuthority(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accounts",accountsService.findAll());
		map.put("roles", rDao.findAll());
		map.put("authorities",rdDao.findAll());
		return map;
	}
	
	// Tạo mới một quyền (authority) cho tài khoản
	@PostMapping("/authorities")
	public Roledetails postAuthorities(@RequestBody Roledetails authority) {
		return rdDao.save(authority);
	}
	
	// Xóa một quyền (authority) khỏi tài khoản theo ID
	@DeleteMapping("/authorities/{id}")
	public void deleteAuthorities(@PathVariable("id") Integer id) {
		accountsService.deleteRoleDetail(id);
	}
}
