package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Accounts;

public interface AccountsService {

    // Lấy danh sách tất cả tài khoản (accounts)
    List<Accounts> findAll();

    // Lấy thông tin tài khoản (accounts) theo ID
    Accounts findById(String id);

    // Tạo mới tài khoản (accounts)
    Accounts create(Accounts accounts);

    // Lưu thông tin tài khoản (accounts) đã chỉnh sửa
    Accounts save(String id, Accounts accounts);

    // Xóa tài khoản (accounts) theo ID
    void deleteById(String id);

    // Xác thực tài khoản
    boolean authenticate(String username, String password);

    // Thay đổi mật khẩu
    void changePassword(String username, String newPassword);

    // Xóa chi tiết vai trò liên quan đến tài khoản
    void deleteRoleDetail(Integer id);

    // Tìm tài khoản theo tên người dùng
    Accounts findByUserName(String username);

    // Tìm danh sách tài khoản theo tên
    List<Accounts> findByName(String name);

}
