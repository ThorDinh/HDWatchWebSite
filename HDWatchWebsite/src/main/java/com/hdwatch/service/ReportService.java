package com.hdwatch.service;

import java.util.List;

import com.hdwatch.report.ReportCost;
//import com.hdwatch.report.ReportProduct;

public interface ReportService {
    
    // Báo cáo chi phí trong một tháng
    List<ReportCost> reportCostInMonth(Integer month);

    // Báo cáo sản phẩm trong một tháng
    // List<ReportProduct> reportProductInMonth(Integer month);

    // Lấy danh sách đơn hàng với tháng và tổng chi phí
    List<Object> getOrdersWithMonthAndTotalCost();

}
