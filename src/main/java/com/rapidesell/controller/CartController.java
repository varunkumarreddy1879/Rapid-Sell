package com.rapidesell.controller;

import java.util.Optional;

import com.rapidesell.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rapidesell.dao.CartDao;
import com.rapidesell.model.Cart;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	
	@GetMapping("/addToCart")
	public ModelAndView addtoCart(@ModelAttribute Cart cart) {
		ModelAndView mv = new ModelAndView();
	
		cartService.save(cart);
		mv.addObject("status", "Foods added to cart!");
		mv.setViewName("index");
		
		return mv;
	
	}
	
	@GetMapping("/deletecart")
	public ModelAndView deleteProductFromCart(@RequestParam("cartId") int  cartId) {
		ModelAndView mv = new ModelAndView();
		cartService.deleteProductFromCart(cartId);

		mv.addObject("status", "Selected Food removed from Cart!");
		mv.setViewName("index");
		
		return mv;
	}
	
	
	
	

}
