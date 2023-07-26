package com.hdwatch.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdwatch.entity.Accounts;
import com.hdwatch.entity.Orders;
import com.hdwatch.service.AccountsService;
import com.hdwatch.service.OrdersService;

@Controller
public class OrderController {
	@Autowired
	OrdersService orderService;
	
	@RequestMapping("/order/checkout")
	public String checkout() {
		return "order/checkout";
	}
	
	@RequestMapping("/order/list")
	public String orderList(Model model, HttpServletRequest request) {
		
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		
        model.addAttribute("pageTitle", "Lịch sử mua hàng - " + username);
		return "order/yourorder";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("order", orderService.findById(id));
		
        model.addAttribute("pageTitle", "Lịch sử mua hàng - Ngày " + orderService.findById(id).getCreateDate());
		return "order/detail";
	}
	

}
