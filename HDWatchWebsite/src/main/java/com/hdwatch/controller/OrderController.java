package com.hdwatch.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdwatch.dao.AccountsDAO;
import com.hdwatch.dao.OrdersDAO;
import com.hdwatch.service.OrdersService;

@Controller
public class OrderController {
	@Autowired
	OrdersService orderService;
	@Autowired
	AccountsDAO accountsDAO;
	@Autowired
	OrdersDAO ordersDAO;
	
	// Trang thanh toán đơn hàng
	@RequestMapping("/order/checkout")
	public String checkout(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		//Tiêu đề trang
        model.addAttribute("pageTitle", "Đặt hàng - " + username);
		return "order/checkout";
	}
	
	// Trang chi tiết đơn hàng theo ID
	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("order", orderService.findById(id));
		//Tiêu đề trang
        model.addAttribute("pageTitle", "Lịch sử mua hàng - Ngày " + orderService.findById(id).getCreateDate());
		return "order/detail";
	}
}
