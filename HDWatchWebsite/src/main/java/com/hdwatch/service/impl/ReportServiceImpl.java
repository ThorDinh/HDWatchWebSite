package com.hdwatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdwatch.dao.OrdersDAO;
import com.hdwatch.dao.ReportCostRepo;

import com.hdwatch.report.ReportCost;

import com.hdwatch.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	ReportCostRepo rpcRepo;
	@Autowired
	OrdersDAO oDAO;

	@Override
	public List<ReportCost> reportCostInMonth(Integer month) {
		List<ReportCost> lst = rpcRepo.reportCost(month);
		return lst;
	}

//	@Override
//	public List<ReportProduct> reportProductInMonth(Integer month) {
//		List<ReportProduct> lst = rprRepo.reportProduct(month);
//		return lst;
//	}
	@Override
	public List<Object> getOrdersWithMonthAndTotalCost() {
		return oDAO.getOrdersWithMonthAndTotalCost();
	}
}
