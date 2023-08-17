package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hdwatch.dao.AccountsDAO;
import com.hdwatch.dao.RoledetailsDAO;
import com.hdwatch.entity.Accounts;
import com.hdwatch.entity.Categories;
import com.hdwatch.service.AccountsService;

@Service
public class AccountsServiceImpl implements AccountsService {
	@Autowired
	AccountsDAO accountsDAO;

	@Override
	public List<Accounts> findAll() {
		return accountsDAO.findAll();
	}

	@Override
	public Accounts findById(String id) {
		return accountsDAO.findById(id).get();
	}

	@Override
	public Accounts create(Accounts accounts) {
		return accountsDAO.save(accounts);
	}

	@Override
	public Accounts save(String id, Accounts accounts) {
		Accounts existingAccounts = accountsDAO.findById(id).orElse(null);
		if(existingAccounts != null) {
			existingAccounts.setUsername(accounts.getUsername());
			existingAccounts.setActivated(accounts.getActivated());
			existingAccounts.setFullname(accounts.getFullname());
			existingAccounts.setEmail(accounts.getEmail());
			existingAccounts.setFacebook(accounts.getFacebook());
			existingAccounts.setGoogle(accounts.getGoogle());
			existingAccounts.setUsername(accounts.getUsername());
			existingAccounts.setPassword(accounts.getPassword());
			return accountsDAO.save(existingAccounts);
		}
		return accountsDAO.save(accounts);
	}

	@Override
	public void deleteById(String id) {
		accountsDAO.deleteById(id);
	}

//	@Override
//	public Accounts findByEmai(String email) {
//		return accountsDAO.findByEmail(email);
//	}
	
	@Override
	public boolean authenticate(String username, String password) {
	    Accounts account = accountsDAO.findByUsername(username);
	    if (account != null) {
//	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        return password.equals(account.getPassword());
	    }
	    return false;
	}
	
	@Override
    public void changePassword(String username, String newPassword) {
        Accounts account = accountsDAO.findByUsername(username);
        if (account != null) {
        	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        	String password = passwordEncoder.encode(newPassword);
            account.setPassword(password);
            accountsDAO.save(account);
        } else {
            throw new RuntimeException("Không tìm thấy tài khoản với username: " + username);
        }
    }
	
	@Autowired
	RoledetailsDAO rdDao;
	
	@Override
	public void deleteRoleDetail(Integer id) {
		// TODO Auto-generated method stub
		 rdDao.deleteById(id);
	}

	@Override
	public Accounts findByUserName(String username) {

		return accountsDAO.findByUsername(username);
	}
	
	@Override
	public List<Accounts> findByName(String name) {
		return accountsDAO.findByName(name);
	}
}
