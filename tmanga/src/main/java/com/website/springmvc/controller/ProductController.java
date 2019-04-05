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
	public ModelAndView getViewProductPage(@RequestParam(name = "q") String key, 
										   	@RequestParam(name = "un") String name,
											@RequestParam(name = "p", defaultValue = "1") int page,
											@RequestParam(name = "s", defaultValue = "1") int sort){		
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
		
		List<comic> comics_pagi = comicService.getListComic(key, id, 12*(page-1), 12);
		
		int totalPage = 0;
		
		int totalComic = comics.size();
		totalPage = totalComic / 12;
		
		if(totalComic % 12 != 0){
			totalPage++;
		}		
				
		getModel.getSideBar(model);
		
		model.addObject("key", key);
		model.addObject("name", name);
		
		model.addObject("views","productList");
		model.addObject("title",title);
		model.addObject("comics_pagi", comics);
		
		model.addObject("comiclist", comics_pagi);
		model.addObject("totalpage", totalPage);
		model.addObject("pageselected", page);
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

}