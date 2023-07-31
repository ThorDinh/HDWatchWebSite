package com.hdwatch.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hdwatch.dao.OrdersDAO;
import com.hdwatch.entity.Orders;
import com.hdwatch.service.VNPayService;

@Controller
public class VNPayController {
	 	@Autowired
	    private VNPayService vnPayService;
	 	
	 	@Autowired
	 	OrdersDAO oDao;
	 	
	    @GetMapping("/vnpay")
	    public String home(){
	        return "vnpay/index";
	    }

	    @GetMapping("/submitOrder")
	    public String submidOrder(@RequestParam("amount") int orderTotal,
	                            
	                            @RequestParam("id") String id,
	                            HttpServletRequest request){
	        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	        String vnpayUrl = vnPayService.createOrder(orderTotal,String.valueOf(id), baseUrl);
	        return "redirect:" + vnpayUrl;
	    }

	    @GetMapping("/vnpay-payment")
	    public String GetMapping(HttpServletRequest request,RedirectAttributes params){
	        int paymentStatus =vnPayService.orderReturn(request);
	        int id = Integer.parseInt(request.getParameter("vnp_TxnRef"));
	        String orderInfo = request.getParameter("vnp_OrderInfo");
	        String paymentTime = request.getParameter("vnp_PayDate");
	        String transactionId = request.getParameter("vnp_TransactionNo");
	        String totalPrice = request.getParameter("vnp_Amount");
	        
	        String formattedDateTimeString = convertDateTimeString(paymentTime);
	        
	        params.addAttribute("orderId", orderInfo);
	        params.addAttribute("totalPrice", totalPrice);
	        params.addAttribute("paymentTime", formattedDateTimeString);
	        params.addAttribute("transactionId", transactionId);
	        if(paymentStatus == 1) {
	        	Orders order = oDao.findById(id).get();
	        	order.setStatus("Đã hoàn thành");
	        	oDao.save(order);
	        }
	        
	        return paymentStatus == 1? "redirect:/paysucces" : "redirect:/payfalse";
	    }
	    @GetMapping("/paysucces")
	    public String paysucces() {
	        
	    	return "vnpay/ordersuccess";
	    }
	    @GetMapping("/payfalse")
	    public String payfalse() {
	        
	    	return "vnpay/orderfail";
	    }
	    public static String convertDateTimeString(String inputDateTimeString) {
	        String formattedDateTimeString = "";

	        try {
	            // Định dạng chuỗi đầu vào là "yyyyMMddHHmmss"
	            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	            // Định dạng chuỗi đầu ra là "yyyy/MM/dd HH:mm:ss"
	            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	            // Chuyển chuỗi đầu vào thành đối tượng Date
	            Date date = inputFormat.parse(inputDateTimeString);

	            // Chuyển đối tượng Date thành chuỗi đầu ra đã định dạng
	            formattedDateTimeString = outputFormat.format(date);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

	        return formattedDateTimeString;
	    }
}
