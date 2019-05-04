package com.website.springmvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.Services.AuthorService;
import com.website.springmvc.Services.PublishCompanyService;


@Controller
@RequestMapping(value = "/controller")
public class ProductManagementController {
	@Autowired
	GetModel getModel;
	
	@Autowired
	ComicService comicService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/comicList", method = RequestMethod.GET)
	public ModelAndView getComicList(@RequestParam(name = "mes", defaultValue = "") String mes,
									@RequestParam(name = "alert", defaultValue = "") String alert,
									@RequestParam(name = "p", defaultValue = "1") int page,
									@RequestParam(name = "q", defaultValue = "") String q,
									@RequestParam(name = "id", defaultValue = "0") Long id,
									HttpSession session) {
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f || (!q.equalsIgnoreCase("") && id != 0) ) {
			getModel.getLayoutAdmin(model);
			model.addObject("views","ProductManList");
			model.addObject("title","Danh sách truyện");
			
			List<Comic> comics;
			
			if(q.equalsIgnoreCase("category")) {
				comics = comicService.getListComicByCategory(id, 0, 0);
			}
			else if(q.equalsIgnoreCase("author")) {
				comics = comicService.getListComicByAuthor(id, 0, 0);
			}
			else if(q.equalsIgnoreCase("publishcompany")) {
				comics = comicService.getListComicByPublishCompany(id, 0, 0);
			}
			else {
				comics = comicService.getListComic("", 0, 0, 1);
			}
	
			int totalPage = 0;
			int totalComic = 0;		
	
			totalComic = comics.size();
			totalPage = totalComic / 10;
	
			if(totalComic % 10 != 0){
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
	
			if(q.equalsIgnoreCase("category")) {
				comics = comicService.getListComicByCategory(id, 10*(page-1), 10);
			}
			else if(q.equalsIgnoreCase("author")) {
				comics = comicService.getListComicByAuthor(id, 10*(page-1), 10);
			}
			else if(q.equalsIgnoreCase("publishcompany")) {
				comics = comicService.getListComicByPublishCompany(id, 10*(page-1), 10);
			}
			else {
				comics = comicService.getListComic("", 10*(page-1), 10, 1);
			}
	
			model.addObject("comics", comics);
			model.addObject("totalpage", totalPage);
			model.addObject("pageselected", page);
			
			String href = "";
			if(!q.equalsIgnoreCase("")) {
				href = "&q=" + q + "&id=" + id;
			}
			model.addObject("href", href);
			
			List<Category> cate = categoryService.getListName();
			model.addObject("cateList", cate);
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}
	
	@RequestMapping(value = "/change-category", method = RequestMethod.POST)
	public void changeCategory(@RequestParam(name = "idComic", defaultValue = "0") Long idComic, HttpServletResponse response,
								@RequestParam(name = "idCategory", defaultValue = "0") Long idCategory,
								HttpSession session) {
		String str = "success";
		boolean f = getModel.checkAdmin(session);
		
		if(f && idComic != (long)0 && idCategory != (long)0 ) {
			Comic comic = comicService.get(idComic);
			comic.setCategory(categoryService.get(idCategory));
			comicService.update(comic);
		}
		else {
			str = "fail";
		}
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
