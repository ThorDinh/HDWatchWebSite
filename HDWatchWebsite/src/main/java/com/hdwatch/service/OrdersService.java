package com.hdwatch.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.hdwatch.entity.Orders;

public interface OrdersService {
    
    // Tạo đơn hàng từ dữ liệu JSON
    Orders create(JsonNode orderData);

    // Lấy thông tin đơn hàng theo ID
    Orders findById(Integer id);

    // Lấy danh sách đơn hàng của một người dùng
    List<Orders> findByUsername(String username);

}
