package com.hdwatch.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdwatch.entity.Orders;
import com.hdwatch.service.OrdersService;

@Controller
public class OrderController {
	@Autowired
	OrdersService orderService;
	
	@RequestMapping("/order/list")
	public String orderList(Model model, HttpServletRequest request) {
		//Đổ danh sách lịch sử mua hàng
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		//Tiêu đề trang
        model.addAttribute("pageTitle", "Lịch sử mua hàng - " + username);
		return "order/yourorder";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("order", orderService.findById(id));
		//Tiêu đề trang
        model.addAttribute("pageTitle", "Lịch sử mua hàng - Ngày " + orderService.findById(id).getCreateDate());
		return "order/detail";
	}
}
