package com.website.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.entities.comic;
import com.website.springmvc.service.AuthorService;
import com.website.springmvc.service.CategoryService;
import com.website.springmvc.service.ComicService;
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
	
	@Autowired
	private ComicService comicService;	
	
	private void getSideBar(ModelAndView model) {
		model.setViewName("layout");
		model.addObject("sb","sidebar");
		
		model.addObject("categories", categoryService.getAll());
		model.addObject("authors", authorService.getAll());
		model.addObject("pcs", publishCompanyService.getAll());
	}
	
	@RequestMapping(value = {"/", "trang-chu", "/index"}, method = RequestMethod.GET)
	public ModelAndView getHome(){		
		ModelAndView model = new ModelAndView();
		
		getSideBar(model);	
		
		model.addObject("views","content");
		model.addObject("title","T-Manga");
		
		model.addObject("news", newsService.getNewsForBanner());		
		model.addObject("topSelling", comicService.getComicForTopSelling());
		model.addObject("newComic", comicService.getNewComicInHomePage());
		model.addObject("otherComic", comicService.getOtherComicInHomePage());
				
		return model;
	}
	
	@RequestMapping(value = {"/login" , "/dang-nhap"}, method = RequestMethod.GET)
	public ModelAndView getLogin(){
		ModelAndView model = new ModelAndView();
		
		getSideBar(model);
		
		model.addObject("views","login");
		model.addObject("title","Đăng nhập");
		
		return model;
	}
}
