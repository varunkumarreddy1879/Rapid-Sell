package com.rapidesell.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rapidesell.service.CategoryService;
import com.rapidesell.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rapidesell.dao.CategoryDao;
import com.rapidesell.dao.FoodDao;
import com.rapidesell.model.Category;
import com.rapidesell.model.Food;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	@Autowired
	FoodService foodService;
	
	@PostMapping("/addcategory")
	public ModelAndView addcategory(@ModelAttribute Category category) {
		ModelAndView  mv = new ModelAndView();
		
		categoryService.save(category);
		
		mv.addObject("status", "Category Added Successfully!");
		mv.setViewName("index");
		
		return mv;
	}
	
	@GetMapping("/deletecategory")
	public ModelAndView deleteCategory(@RequestParam("categoryId") int categoryId) {

        ModelAndView  mv = new ModelAndView();
		categoryService.deleteCategory(categoryId);
		foodService.deleteFoodsOfCategory(categoryId);
		mv.addObject("status", "Category Deleted Successfully!");
		mv.setViewName("index");
		
		return mv;
	}
	
	@GetMapping("/category")
	public ModelAndView category(@RequestParam("categoryId") int  categoryId) {
		ModelAndView mv = new ModelAndView();
		List<Food> foods = new ArrayList<>();
		if(categoryId == 0) {
			foods = foodService.findAll();
		}
		else {
			foods = foodService.findByCategoryId(categoryId);
		}
		
		mv.addObject("foods", foods);
		mv.addObject("sentFromOtherSource", "yes");
		mv.setViewName("index");
		
		return mv;
	}

}
