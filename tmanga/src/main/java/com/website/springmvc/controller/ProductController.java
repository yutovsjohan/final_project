package com.website.springmvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.website.springmvc.entities.Comic;
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
	public ModelAndView getViewProductPage(@RequestParam(name = "a", defaultValue = "") String action,											
											@RequestParam(name = "q", defaultValue = "") String key, 
										   	@RequestParam(name = "un", defaultValue = "") String name,
											@RequestParam(name = "p", defaultValue = "1") int page,
											@RequestParam(name = "s", defaultValue = "1") int sort){		
		ModelAndView model = new ModelAndView();
		
		String title = "";
		int id = 1;
		String href = "";
		
		List<Comic> comics;
		
		if(action.equalsIgnoreCase("search")) {
			comics = comicService.getListComic(key, 0, 0, sort);
						
			model.addObject("title", "Tìm kiếm " + key);
			
			href = "product?a=search&q=" + key;
			
			model.addObject("key", "search");			
			model.addObject("k",key);
		}
		else {			
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
			
			comics = comicService.getListComic(key, id, 0, 0, sort);
			
			model.addObject("title",title);
			href = "product?a=pl&q=" + key + "&un=" + name;
			
			model.addObject("key", key);
			model.addObject("un", name);
		}	
		
		int totalPage = 0;
		int totalComic = 0;		
		
		totalComic = comics.size();
		totalPage = totalComic / 12;
		
		if(totalComic % 12 != 0){
			totalPage++;
		}		
				
		if(action.equalsIgnoreCase("search")) {
			comics = comicService.getListComic(key, 12*(page-1), 12, sort);
		}
		else {
			comics = comicService.getListComic(key, id, 12*(page-1), 12, sort);
		}
						
		model.addObject("comiclist", comics);
		model.addObject("totalpage", totalPage);
		model.addObject("pageselected", page);
		model.addObject("totalcomic", totalComic);
		
		model.addObject("sort", sort);
		model.addObject("href", href);
		model.addObject("views","productList");			
		
		getModel.getSideBar(model);
						
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
			
	@RequestMapping(value = "/pagination", method = RequestMethod.GET)
	public void pagination(HttpServletResponse response, 
						@RequestParam(name = "page", defaultValue = "1") int page,
						@RequestParam(name = "totalpage", defaultValue = "1") int totalpage) {
		String str = "";
		if(page != 1) {
			str += "<li data=\"-1\"> <a rel=\"next\"> << </a></li>";
			str += "<li data=\"-2\"><a rel=\"next\"> < </a></li>";
		}
				
		for (int j = 1; j <= totalpage ; j++) {
			if(j == page) {
				str += "<li class=\"active\" data=\"" + j + "\"><span>" + j + "</span></li>";
			}
			else {
				str += "<li data=\"" + j + "\"><a>" + j + "</a></li>";
			}
		}
		
		if(page != totalpage) {
			str += "<li data=\"-3\"><a rel=\"next\"> > </a></li>";
			str += "<li data=\"-4\"><a rel=\"next\"> >> </a></li>";
		}
		
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}