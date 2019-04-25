package com.website.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.entities.Comic;
import com.website.springmvc.entities.Comic;
import com.website.springmvc.entities.Category;
import com.website.springmvc.entities.PublishCompany;

import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.Services.AuthorService;
import com.website.springmvc.Services.PublishCompanyService;


@Controller
@RequestMapping(value = "/controller")
public class ProductManagementController {
	@Autowired
	ComicService comicService;
	
	@RequestMapping(value = "/comicList", method = RequestMethod.GET)
	public ModelAndView getComicList(@RequestParam(name = "mes", defaultValue = "") String mes,
									@RequestParam(name = "alert", defaultValue = "") String alert,
									@RequestParam(name = "p", defaultValue = "1") int page) {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/layoutAdmin");
		model.addObject("views","ProductMan");
		model.addObject("title","Danh sách truyện");
		
		List<Comic> comics = comicService.getListComic("", 0, 0, 1);

		int totalPage = 0;
		int totalAuthors = 0;		

		totalAuthors = comics.size();
		totalPage = totalAuthors / 10;

		if(totalAuthors % 10 != 0){
			totalPage++;
		}		

		int start = 1, end = 7;

		if(totalPage > 7){		
			if(page - 3 > 0){	
				if(page + 3 >= totalPage){	
					start =  totalPage - 6;
					end = totalPage;
				}	
				else{	
					start = page - 3;
					end = page + 3;
				}	
			}	
		}
		else {
			end = totalPage;
		}

		model.addObject("start", start);
		model.addObject("end", end);

		comics = comicService.getListComic("", 10*(page-1), 10, 1);

		model.addObject("authors", comics);
		model.addObject("totalpage", totalPage);
		model.addObject("pageselected", page);
		return model;
	}
}
