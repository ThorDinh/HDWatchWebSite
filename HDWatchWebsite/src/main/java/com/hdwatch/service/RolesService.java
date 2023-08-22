package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Roles;

public interface RolesService {
    
    // Lấy danh sách tất cả vai trò (roles)
    List<Roles> findAll();

    // Lấy thông tin vai trò (roles) theo ID
    Roles findById(String id);

}
