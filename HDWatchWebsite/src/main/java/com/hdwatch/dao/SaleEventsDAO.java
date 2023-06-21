package com.hdwatch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdwatch.entity.SaleEvents;

public interface SaleEventsDAO extends JpaRepository<SaleEvents,Integer > {

}
