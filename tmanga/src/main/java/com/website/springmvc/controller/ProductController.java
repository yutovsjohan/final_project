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

		int totalPage = 0;
		
		int totalComic = comics.size();
		totalPage = totalComic / 12;
		
		if(totalComic % 12 != 0){
			totalPage++;
		}		
				
		comics = comicService.getListComic(key, id, 12*(page-1), 12);
		
		getModel.getSideBar(model);
		
		String href = "product?q=" + key + "&un=" + name;
		
		model.addObject("key", key);
		model.addObject("href", href);
		
		model.addObject("views","productList");
		model.addObject("title",title);
		
		model.addObject("comiclist", comics);
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
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView getViewProductPage(@RequestParam(name = "k") String key,
										@RequestParam(name = "p", defaultValue = "1") int page,
										@RequestParam(name = "s", defaultValue = "1") int sort) {
		ModelAndView model = new ModelAndView();
		
		List<comic> comics = comicService.getListComic(key);

		int totalPage = 0;
		
		int totalComic = comics.size();
		totalPage = totalComic / 12;
		
		if(totalComic % 12 != 0){
			totalPage++;
		}		
		
		comics = comicService.getListComic(key, 12*(page-1), 12);
		
		getModel.getSideBar(model);
				
		model.addObject("views","productList");
		model.addObject("title", "Tìm kiếm " + key);
		
		String href = "search?k=" + key;
		model.addObject("k",key);
		model.addObject("key","search");
		model.addObject("href", href);
		
		model.addObject("comiclist", comics);
		model.addObject("totalpage", totalPage);
		model.addObject("pageselected", page);
		model.addObject("totalcomic", totalComic);
		return model;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test(@RequestParam(name = "q", defaultValue = "") String key, 
										   	@RequestParam(name = "un",defaultValue = "") String name,
											@RequestParam(name = "p", defaultValue = "1") int page,
											@RequestParam(name = "s", defaultValue = "1") int sort){		
		ModelAndView model = new ModelAndView();		
		
		getModel.getSideBar(model);
		
		key = "category";
		name = "pokemon-dac-biet";		
		
		pagination(model, name, key, page);
		
		String href = "product?q=" + key + "&un=" + name;
		
		model.addObject("key", key);
		model.addObject("href", href);
		
		model.addObject("views","../test");		
		model.addObject("pageselected", page);
				
		return model;
	}	
	
	public void pagination(ModelAndView model, String name, String key, int page) {
		String str = "";
		
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

		int totalPage = 0;
		
		int totalComic = comics.size();
		totalPage = totalComic / 12;
		
		if(totalComic % 12 != 0){
			totalPage++;
		}		
				
		comics = comicService.getListComic(key, id, 12*(page-1), 12);
		
		str += "<ul class=\"pagination\">";
		
		if(page == 1) {
			str += "<li class=\"disabled\" data=\"<<\"><span> << </span></li>";
			str += "<li class=\"disabled\" data=\"<\"><span> < </span></li>";
		}
		else {
			str += "<li data=\"<<\"> <a rel=\"next\"> << </a></li>";
			str += "<li data=\"<\"><a rel=\"next\"> < </a></li>";
		}
		
		for (int j = 1; j <= totalPage ; j++) {
			if(j == page) {
				str += "<li class=\"active\" data=\"" + j + "\"><span>" + j + "</span></li>";
			}
			else {
				str += "<li data=\"" + j + "\"><a>" + j + "</a></li>";
			}
		}
		
		if(page == totalPage) {
			str += "<li class=\"disabled\" data=\">\"><span> > </span></li>";
			str += "<li class=\"disabled\" data=\">>\"><span> >> </span></li>";
		}
		else {
			str += "<li data=\">\"><a rel=\"next\"> > </a></li>";
			str += "<li data=\">>\"><a rel=\"next\"> >> </a></li>";
		}
		
		str += "</ul>";
		
		model.addObject("title",title);
		
		model.addObject("comiclist", comics);
		model.addObject("totalpage", totalPage);
		model.addObject("pagination", str);
	}
}