package com.hdwatch.service;

import java.util.List;

import com.hdwatch.report.ReportCost;
//import com.hdwatch.report.ReportProduct;

public interface ReportService {
	List<ReportCost> reportCostInMonth(Integer month);
//	List<ReportProduct> reportProductInMonth(Integer month);
}
