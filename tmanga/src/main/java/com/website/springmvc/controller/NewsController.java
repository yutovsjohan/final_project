package com.website.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.NewsService;
import com.website.springmvc.entities.News;
import com.website.springmvc.libs.GetModel;

@Controller
@RequestMapping(value="/controller")
public class NewsController {
	@Autowired
	GetModel getModel;
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value = {"news", "tin-tuc"}, method = RequestMethod.GET)
	public ModelAndView getNewsPage(@RequestParam(name = "p", defaultValue = "1") int page,
									HttpSession session){		
		ModelAndView model = new ModelAndView();
		getModel.getNews(model, page);
		
		session.setAttribute("url", "news");
		return model;
	}	
	
	@RequestMapping(value = "news-detail", method = RequestMethod.GET)
	public ModelAndView getNewsDetailPage(@RequestParam(name = "un", defaultValue = "") String un,
											HttpSession session){
		ModelAndView model = new ModelAndView();
		News news = newsService.getNewsById(un);
		getModel.getNewsDetail(model, news);
		
		session.setAttribute("url", "news-detail?un=" + un);
		return model;
	}	
	
}
