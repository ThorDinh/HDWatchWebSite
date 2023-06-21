package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hdwatch.dao.SaleEventsDAO;
import com.hdwatch.entity.SaleEvents;
import com.hdwatch.service.SaleEventsService;

public class SaleEventsServiceImpl implements SaleEventsService {
//	@Autowired
//	SaleEventsDAO saleEventsDAO;
//
//	@Override
//	public List<SaleEvents> findAll() {
//		return saleEventsDAO.findAll();
//	}
//
//	@Override
//	public SaleEvents findById(Integer id) {
//		return saleEventsDAO.findById(id).get();
//	}
//
//	@Override
//	public SaleEvents create(SaleEvents saleEvents) {
//		return saleEventsDAO.save(saleEvents);
//	}
//
//	@Override
//	public SaleEvents save(Integer id, SaleEvents saleEvents) {
//		SaleEvents existingEvents = saleEventsDAO.findById(id).orElse(null);
//		if(existingEvents != null) {
//			existingEvents.setId(saleEvents.getId());
//			existingEvents.setName(saleEvents.getName());
//			existingEvents.setPriceSale(saleEvents.getPriceSale());
//			existingEvents.setStartDate(saleEvents.getStartDate());
//			existingEvents.setEndDate(saleEvents.getEndDate());
//			return saleEventsDAO.save(existingEvents);
//		}
//		return saleEventsDAO.save(saleEvents);
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		saleEventsDAO.deleteById(id);
//		
//	}
	
}
