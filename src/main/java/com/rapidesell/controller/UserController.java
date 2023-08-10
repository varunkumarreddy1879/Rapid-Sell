package com.rapidesell.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rapidesell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.rapidesell.model.User;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	

	@GetMapping("/userlogin")
	public String goToLoginPage() {
		return "userlogin";
	}
	
	@GetMapping("/userregister")
	public String goToRegisterPage() {
		return "userregister";
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		session.removeAttribute("active-user");
		session.removeAttribute("user-login");
		mv.addObject("status","Logged out Successfully");
		mv.setViewName("index");
		
		return mv;
	}
	
	@PostMapping("/userregister")
	public ModelAndView registerAdmin(@ModelAttribute User user) {
		ModelAndView mv = new ModelAndView();
		User user1 = userService.registerAdmin(user);
		if(user1!=null) {
			mv.addObject("status", user.getFirstname()+" Successfully Registered!");
			mv.setViewName("userlogin");
		}
		
		else {
			mv.addObject("status", user.getFirstname()+" Failed to Registered User!");
			mv.setViewName("userregister");
	
		}
		
		return mv;
	}
	
	@PostMapping("/forgetpassword")
	public ModelAndView forgetpassword(@RequestParam("email") String email, @RequestParam("pass") String password,
			@RequestParam("phone") String phone) {
		ModelAndView mv = new ModelAndView();
		User user=userService.resetPassword(email,phone,password);

		
		if(user==null){
			mv.addObject("status", "No User found!");
			mv.setViewName("userregister");
		}
		
		return mv;
	}
	
	@PostMapping("/userlogin")
	public ModelAndView loginAdmin(HttpServletRequest request, @RequestParam("emailid") String emailId, @RequestParam("password") String password ) {
		ModelAndView mv = new ModelAndView();
		
		User user = userService.findByEmailidAndPassword(emailId, password);
		
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("active-user", user);
			session.setAttribute("user-login","user");
			mv.addObject("status", user.getFirstname()+" Successfully Logged In!");
			mv.setViewName("index");
		}
		
		else {
			mv.addObject("status","Failed to login!");
			mv.setViewName("index");
		}
		
		return mv;
	}
	

}
