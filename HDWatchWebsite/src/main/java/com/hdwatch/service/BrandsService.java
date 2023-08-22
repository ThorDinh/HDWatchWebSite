package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Brands;

public interface BrandsService {

    // Lấy danh sách tất cả thương hiệu (brands)
    List<Brands> findAll();

    // Lấy thông tin thương hiệu (brands) theo ID
    Brands findById(Integer id);

    // Kiểm tra sự tồn tại của thương hiệu (brands) theo ID
    boolean existsById(Integer id);

    // Kiểm tra sự tồn tại của thương hiệu (brands) theo tên
    boolean exitsByName(String name);

    // Tạo mới thương hiệu (brands)
    Brands create(Brands brand);

    // Lưu thông tin thương hiệu (brands) đã chỉnh sửa
    Brands save(Brands brand, Integer id);

    // Xóa thương hiệu (brands) theo ID
    void deleteById(Integer id);

    // Tìm thương hiệu theo tên
    List<Brands> findByName(String name);

    // Bạn có thể thêm các phương thức khác liên quan đến quản lý thương hiệu ở đây
}
