package com.website.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.AuthorService;
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.NewsService;
import com.website.springmvc.Services.PublishCompanyService;
import com.website.springmvc.libs.GetModel;

@Controller
@RequestMapping(value="/controller")
public class HomeController {
	
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = {"/", "trang-chu", "/index"}, method = RequestMethod.GET)
	public ModelAndView getHomePage(){		
		ModelAndView model = new ModelAndView();
		getModel.getHome(model);						
		return model;
	}	
	
	
}
