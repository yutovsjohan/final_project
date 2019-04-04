package com.website.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.AuthorService;
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.PublishCompanyService;
import com.website.springmvc.entities.comic;
import com.website.springmvc.libs.GetModel;

@Controller
@RequestMapping(value="/controller")
public class ProductController {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private PublishCompanyService publishCompanyService;
	
	@Autowired
	private ComicService comicService;	
	
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView getViewProductPage(@RequestParam(name = "q") String key, @RequestParam(name = "un") String name){		
		ModelAndView model = new ModelAndView();
		
		String title = "";
		int id = 1;
		
		if(key.equalsIgnoreCase("category")) {
			title = categoryService.get(name).getName();
			id = categoryService.get(name).getId();
		}	
		else if(key.equalsIgnoreCase("author")) {
			title = authorService.get(name).getName();
			id = authorService.get(name).getId();
		}
		else if(key.equalsIgnoreCase("publishing-company")) {
			title = publishCompanyService.get(name).getName();
			id = publishCompanyService.get(name).getId();
		}
		
		List<comic> comics = comicService.getListComic(key, id);
		
		int totalComic = comics.size();
		int totalPage = totalComic / 12;
		
		getModel.getSideBar(model);
		
		model.addObject("views","productList");
		model.addObject("title",title);
		model.addObject("comiclist", comics);
		return model;
	}	
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView getViewProductDetailPage(@RequestParam(name = "c") String name){
		ModelAndView model = new ModelAndView();
		
		int idAuthor = comicService.get(name).getAuthor().getId();
		int idCategory = comicService.get(name).getCategory().getId();
		int idPC = comicService.get(name).getPublishcompany().getId();
		
		getModel.getSideBar(model);
		
		model.addObject("views","productDetail");
		model.addObject("title", comicService.get(name).getName());
		model.addObject("author",authorService.get(idAuthor));
		model.addObject("category",categoryService.get(idCategory));
		model.addObject("publishcompany",publishCompanyService.get(idPC));
		model.addObject("comic", comicService.get(name));
		model.addObject("listComicForAuthor", comicService.getListForAuthor(idAuthor));
		
		return model;
	}	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView getTest(){
		ModelAndView model = new ModelAndView();
		
		model.setViewName("../index");
		
		model.addObject("totalpage",getTotalPage());
		
		return model;
	}	
	
	public int getTotalPage(){
		List<comic> comics = comicService.getListComic("category", 1);
		
		int totalPage = 0;
		
		int totalComic = comics.size();
		totalPage = totalComic / 10;
		
		if(totalComic % 10 != 0){
			totalPage++;
		}
						
		return totalPage;
	}
}