package com.hdwatch.service;

import java.util.List;

import com.hdwatch.entity.Categories;

public interface CategoriesService {
    
    // Lấy danh sách tất cả danh mục (categories)
    List<Categories> findAll();

    // Kiểm tra sự tồn tại của danh mục (categories) theo ID
    boolean existsById(Integer id);

    // Lấy thông tin danh mục (categories) theo ID
    Categories findById(Integer id);

    // Lưu thông tin danh mục (categories) đã chỉnh sửa
    Categories save(Categories cate, Integer id);

    // Tạo mới danh mục (categories)
    Categories create(Categories cate);

    // Xóa danh mục (categories) theo ID
    void deleteById(Integer id);

    // Tìm danh mục theo tên
    List<Categories> findByName(String name);

}
