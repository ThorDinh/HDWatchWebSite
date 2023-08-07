package com.hdwatch.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdwatch.dao.AccountsDAO;
import com.hdwatch.dao.OrdersDAO;
import com.hdwatch.entity.Orderdetails;
import com.hdwatch.entity.Orders;
import com.hdwatch.report.ReportCost;
import com.hdwatch.service.AccountsService;
import com.hdwatch.service.OrdersService;
import com.hdwatch.service.ReportService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/rest/report")
public class ReportRestController {
	@Autowired 
	AccountsService aService;
	
	@Autowired 
	OrdersService oService;
	
	@Autowired 
	ReportService rpService;
	
	@Autowired 
	OrdersDAO oDAO;
	
	@Autowired 
	AccountsDAO aDao;
	
	// Phương thức để lấy tháng hiện tại
	public Integer monthCurrent() {
		Date date = new Date();
		return date.getMonth()+1;
	}
	
	// Phương thức để tính tổng số liệu báo cáo (tổng số khách hàng, tổng doanh thu, tổng số đơn hàng) trong tháng hiện tại
	@GetMapping("/total")
	public Map<String, Object> total() {
		Integer month = this.monthCurrent();
		Map<String, Object> db = new HashMap<String, Object>();
		db.put("totalCustomer", aDao.countCustomer("Khách hàng"));
		
		List<Orders> orders = oDAO.findOrderInMonth(month);
		Double totalCost = 0.0;
		for(Orders order : orders ) {
			List<Orderdetails> orderDetail = order.getOrderDetails();
			for(Orderdetails od : orderDetail) {
				totalCost += od.getPrice() * od.getQuantity();
			}
		}
		db.put("totalCost", totalCost);
		db.put("totalOrder", oDAO.countOrderInMonth(month));
		return db;
	}
	
	// Phương thức để lấy danh sách báo cáo chi tiết về chi phí trong tháng hiện tại
	@GetMapping("/reportcost")
	public List<ReportCost> reportCostInMonth(){
		List<ReportCost> lst = rpService.reportCostInMonth(this.monthCurrent());
		return lst;
	}
	
//	@GetMapping("/bestSellerInMonth")
//	public List<ReportProduct> reportProductInMonth(){
//		List<ReportProduct> lst = rpService.reportProductInMonth(this.monthCurrent());
//		return lst;
//	}
}
