package com.rapidesell.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.rapidesell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rapidesell.dao.CartDao;
import com.rapidesell.dao.OrderDao;
import com.rapidesell.model.Cart;
import com.rapidesell.model.Orders;
import com.rapidesell.model.User;
import com.rapidesell.utility.Constants;
import com.rapidesell.utility.Helper;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;
	


	@PostMapping("/order")
	public ModelAndView orderfoods(HttpSession session) {
        ModelAndView mv = new ModelAndView();
		
        User user = (User)session.getAttribute("active-user");
		String orderId=orderService.orderfoods(user);

	    mv.addObject("status","Order placed Successfully, Order Id is "+orderId);
		mv.setViewName("index");
		return mv;
	}
	
	@GetMapping("/myorder")
	public ModelAndView goToMyOrder() {
		ModelAndView mv = new ModelAndView();
		List<Orders> orders = orderService.findAll();
		mv.addObject("orders", orders);
		mv.setViewName("myorder");
		return mv;
	}
	
	@GetMapping("/searchorderbyid")
	public ModelAndView searchByOrderId(@RequestParam("orderid") String orderId) {
		ModelAndView mv = new ModelAndView();
		List<Orders> orders = orderService.findByOrderId(orderId);
		mv.addObject("orders", orders);
		mv.setViewName("myorder");
		return mv;
	}
	
	@GetMapping("/searchorderbydate")
	public ModelAndView searchByOrderDate(@RequestParam("orderdate") String orderDate, HttpSession session) {
		User user = (User)session.getAttribute("active-user");
		ModelAndView mv = new ModelAndView();
		List<Orders> orders = orderService.findByOrderDateAndUserId(orderDate, user.getId());
		mv.addObject("orders", orders);
		mv.setViewName("myorder");
		return mv;
	}
	
	@PostMapping("/checkout")
	public ModelAndView searchByOrderDate(@RequestParam("amount") String amount) {
		
		ModelAndView mv = new ModelAndView();
	
		mv.addObject("amount", amount);
		mv.setViewName("checkout");
		return mv;
	}
	
	@GetMapping("/updatedeliverydate")
	public ModelAndView addDeliveryStatus(@RequestParam("orderId") String orderId, @RequestParam("deliveryStatus") String deliveryStatus, @RequestParam("deliveryDate") String deliveryDate ) {
		ModelAndView mv = new ModelAndView();
		orderService.addDeliveryStatus(orderId,deliveryDate,deliveryStatus);

		mv.addObject("status", "Order Delivery Status Updated.");
		mv.setViewName("index");
		return mv;
	}
	
	
	
}
