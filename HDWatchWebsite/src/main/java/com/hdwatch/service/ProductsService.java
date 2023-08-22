package com.hdwatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.hdwatch.entity.Products;

public interface ProductsService {
    
    // Lấy danh sách tất cả sản phẩm (products)
    List<Products> findAll();

    // Lấy thông tin sản phẩm (products) theo ID
    Products findById(Integer id);

    // Tạo mới sản phẩm (products)
    Products create(Products products);

    // Lưu thông tin sản phẩm (products) đã chỉnh sửa
    Products save(Products products, Integer id);

    // Xóa sản phẩm (products) theo ID
    void deleteByid(Integer id);

    // Lấy danh sách sản phẩm theo ngày tạo giảm dần
    List<Products> findProductByCreateDateDESC();

    // Lấy danh sách sản phẩm theo ID thương hiệu
    List<Products> findAllByBrandId(Integer integer);

    // Lấy danh sách sản phẩm theo ID danh mục
    List<Products> findAllByCategoryId(Integer id);

    // Lấy danh sách sản phẩm được sắp xếp theo số lượng đặt hàng
    List<Products> getProductsOrderedByOrderCount();

    // Lấy danh sách sản phẩm phân trang
    Page<Products> findAllPagination(Optional<Integer> p);

    // Tìm kiếm sản phẩm theo từ khóa, danh mục và thương hiệu, phân trang
    Page<Products> searchProductsByKeyword(String keyword, Integer category, Integer brand, Optional<Integer> p);

    // Tìm sản phẩm theo tên
    List<Products> findByName(String name);

    // Xóa hình ảnh liên quan đến sản phẩm
    void deleteImage(Integer productId, String imageToDelete);

}
