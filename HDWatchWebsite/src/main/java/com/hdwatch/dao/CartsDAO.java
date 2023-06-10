package com.hdwatch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdwatch.entity.Carts;

public interface CartsDAO extends JpaRepository<Carts, Integer>{

}
