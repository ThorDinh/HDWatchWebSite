package com.hdwatch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdwatch.entity.Orders;


public interface OrdersDAO extends JpaRepository<Orders, Integer>{

}
