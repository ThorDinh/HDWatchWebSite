package com.hdwatch.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdwatch.entity.Orderdetails;
import com.hdwatch.entity.Orders;
import com.hdwatch.service.VNPayService;
import com.fasterxml.jackson.core.type.TypeReference;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrdersRestController {
	@Autowired
    private VNPayService vnPayService;
//	@Autowired
//	OrdersService ordersService;
//	
//	@GetMapping
//	public List<Orders> getAll(){
//		return ordersService.findAll();
//	}
//	
//	@GetMapping("{id}")
//	public Orders getOne(@PathVariable("id")Integer id) {
//		return ordersService.findById(id);
//	}
//	
//	@PostMapping
//	public Orders create(@RequestBody Orders orders) {
//		return ordersService.create(orders);
//	}
//	
//	@PutMapping("{id}")
//	public Orders updateOrders(@PathVariable("id")Integer id,@RequestBody Orders orders) {
//		return ordersService.save(id, orders);
//	}
//	
//	@DeleteMapping("{id}")
//	public void deleteById(@PathVariable("id")Integer id) {
//		ordersService.deleteById(id);
//	}
//	@GetMapping("/redirect-example")
//    public RedirectView redirectToAnotherPage() {
//        String targetUrl = "https://www.example.com"; // URL của trang web bạn muốn chuyển hướng đến
//        return new RedirectView(targetUrl);
//    }
//	
//	@PostMapping("/submitOrder")
//    public RedirectView submidOrder(@RequestBody JsonNode jsnode,
//                            HttpServletRequest request){
//        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//        int price =jsnode.get("orderDetails").get("price").asInt();
//        String vnpayUrl = vnPayService.createOrder(3000,"id", baseUrl);
//        return new RedirectView(vnpayUrl);
//    }
}
