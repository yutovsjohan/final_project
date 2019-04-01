package com.website.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.service.CategoryService;

@Controller
@RequestMapping(value="/controller")
public class HomeController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public ModelAndView getHome(){
		ModelAndView model = new ModelAndView();
		
		model.setViewName("home");
		model.addObject("categories", categoryService.getAll());
		
		return model;
	}
}
