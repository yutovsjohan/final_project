package com.website.springmvc.controller;

import java.util.List;

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
	public ModelAndView getNewsPage(@RequestParam(name = "p", defaultValue = "1") int page){		
		ModelAndView model = new ModelAndView();
		getModel.getNews(model);
		
		List<News> news = newsService.getAll();
		
		int totalPage = 0;
		int totalNews = 0;		
		
		totalNews = news.size();
		totalPage = totalNews / 10;
		
		if(totalNews % 10 != 0){
			totalPage++;
		}
		
		news = newsService.getListNews(10*(page-1), 10);
		
		model.addObject("news", news);
		model.addObject("totalpage", totalPage);
		model.addObject("pageselected", page);
		model.addObject("totalnews", totalNews);
		return model;
	}	
	
	@RequestMapping(value = "news-detail", method = RequestMethod.GET)
	public ModelAndView getNewsDetailPage(@RequestParam(name = "un", defaultValue = "") String un){
		ModelAndView model = new ModelAndView();
		News news = newsService.getNewsById(un);
		getModel.getNewsDetail(model, news);
		return model;
	}	
	
}
