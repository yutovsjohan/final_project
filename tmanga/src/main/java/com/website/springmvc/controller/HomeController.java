package com.website.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.service.AuthorService;
import com.website.springmvc.service.CategoryService;
import com.website.springmvc.service.NewsService;
import com.website.springmvc.service.PublishCompanyService;

@Controller
@RequestMapping(value="/controller")
public class HomeController {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private PublishCompanyService publishCompanyService;

	@Autowired
	private NewsService newsService;
	
	ModelAndView model = new ModelAndView();
	
	private void getModel() {
		model.setViewName("layout");
		model.addObject("sb","sidebar");
	}
	
	@RequestMapping(value = {"/", "trang-chu", "/index"}, method = RequestMethod.GET)
	public ModelAndView getHome(){		
		getModel();
		
		model.addObject("categories", categoryService.getAll());
		model.addObject("authors", authorService.getAll());
		model.addObject("pcs", publishCompanyService.getAll());
		model.addObject("news", newsService.getNewsForBanner());
		
		model.addObject("views","content");
		model.addObject("title","T-Manga");
		
		return model;
	}
	
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView getLogin(){
		getModel();
		
		model.addObject("views","login");
		model.addObject("title","Đăng nhập");
		
		return model;
	}
}
