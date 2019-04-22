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
import com.website.springmvc.Services.FavoriteListService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.Comic;
import com.website.springmvc.entities.FavoriteList;
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
	private FavoriteListService favoriteListService;
	
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
		
		if(session.getAttribute("account") != null) {
			String str = "success";
			
			if(idComic == 0 || key == 0) {
				str = "fail";
			}
			else if(key == 1) {
				//add favorite list
				Users u = (Users) session.getAttribute("account");
				
				FavoriteList favoriteList = favoriteListService.getByUsersAndComic(u.getId(), idComic);
				
				if(favoriteList == null) {
					favoriteList = new FavoriteList();
					favoriteList.setComic(comicService.get(idComic));
					favoriteList.setUser(u);
					favoriteListService.add(favoriteList);
				}
				else {
					str = "exist";
				}						
			}
			else if(key == 2) {
				//remove favorite list
				Users u = (Users) session.getAttribute("account");
				
				FavoriteList favoriteList = favoriteListService.getByUsersAndComic(u.getId(), idComic);
				
				if(favoriteList != null) {
					favoriteListService.delete(favoriteList);
				}			
				else {
					str = "fail";
				}
			}
			
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}