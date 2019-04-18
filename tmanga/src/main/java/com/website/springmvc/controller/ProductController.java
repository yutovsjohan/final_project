package com.website.springmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.Comic;
import com.website.springmvc.entities.Users;
import com.website.springmvc.libs.GetModel;

@Controller
@RequestMapping(value="/controller")
public class ProductController {	
	@Autowired
	private ComicService comicService;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView getViewProductPage(@RequestParam(name = "a", defaultValue = "") String action,											
											@RequestParam(name = "q", defaultValue = "") String key, 
										   	@RequestParam(name = "un", defaultValue = "") String name,
											@RequestParam(name = "p", defaultValue = "1") int page,
											@RequestParam(name = "s", defaultValue = "1") int sort,
											HttpSession session){		
		ModelAndView model = new ModelAndView();
		String href = "";		
		if(action.equalsIgnoreCase("search")) {
			href = "product?a=search&q=" + key;
		}
		else if(action.equalsIgnoreCase("nc")) {
			href = "product?a=nc&q=" + key;
		}
		else {
			href = "product?a=pl&q=" + key + "&un=" + name;
		}
		
		getModel.getProductList(model, action, key, name, page, sort);
		model.addObject("href", href);
		
		if(page != 1) {
			href += "&p=" + page;
		}
		else if(sort != 1) {
			href += "&s=" + sort;
		}
		session.setAttribute("url", href);
						
		return model;
	}	
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView getViewProductDetailPage(@RequestParam(name = "c") String name,
												HttpSession session){
		ModelAndView model = new ModelAndView();
		getModel.getProductDetail(model, name, session);		
		session.setAttribute("url", "detail?c=" + name);
		
		return model;
	}	
	
	@RequestMapping(value = "/favoritelist", method = RequestMethod.POST)
	public void favoriteList(HttpServletResponse response, HttpSession session,
						@RequestParam(name = "key", defaultValue = "0") int key,
						@RequestParam(name = "id", defaultValue = "0") Long idComic) {
		String str = "success";
		
		if(idComic == 0 || key == 0) {
			str = "fail";
		}
		else if(key == 1) {
			//add favorite list
			boolean f = true;
			Users u = (Users) session.getAttribute("account");
			
			List<Object[]> listResult = userService.getList(u.getId(), 0, 0);
			Comic comic = new Comic();
			
			List<Comic> comics = new ArrayList<Comic>(listResult.size());
						
			int k = 0;
			for (Object[] aRow : listResult) {
				comic = (Comic) aRow[1];
				if((long)comic.getId() == (long)idComic) {
					f = false;
				}
				comics.add(k, (Comic) aRow[1]);
			}
			if(f) {
				comics.add(comicService.get(idComic));
				u.setComics(comics);
				userService.update(u);
			}
			else {
				str = "exist";
			}						
		}
		else if(key == 2) {
			//remove favorite list
			Users u = (Users) session.getAttribute("account");
			List<Comic> comics = u.getComics();
			for(int i = 0; i < comics.size(); i++) {
				if((long)comics.get(i).getId() == (long)idComic) {
					comics.remove(i);
					break;
				}
			}
			if(comics.size() == 0) {
				comics = null;
			}
			u.setComics(comics);
			
			userService.update(u);
		}
		
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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