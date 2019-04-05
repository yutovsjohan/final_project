package com.website.springmvc.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.ComicService;
import com.website.springmvc.entities.comic;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.cart;
import com.website.springmvc.libs.item;

@Controller
@RequestMapping(value="/controller")
public class CartController {
	@Autowired
	GetModel getModel;
	
	@Autowired
	ComicService comicService;
	
	@RequestMapping(value = { "/cart", "/gio-hang" }, method = RequestMethod.GET)
	public ModelAndView getCartPage(){
		ModelAndView model = new ModelAndView();		
		getModel.getCart(model);		
		return model;	
	}
	
	@RequestMapping(value = "/addComic", method = RequestMethod.POST)
	public ModelAndView getAddComic(@RequestParam("idComic") int idComic, 
									@RequestParam("sl") int amount, HttpSession session){
		ModelAndView model = new ModelAndView();
		
		cart cart = new cart();
		
		if(session.getAttribute("cart") != null) {
			ArrayList<item> items =  (ArrayList<item>) session.getAttribute("cart");
			HashMap<Integer, item> hashcart = new HashMap<>();
			for (int i = 0; i < items.size(); i++) {
				hashcart.put(items.get(i).getComic().getId(), items.get(i));
			}
			cart.setCart(hashcart);
		}
		
		comic comic = comicService.get(idComic);
		
		if(comic.getAmount() == 0) {
			
		}
		else {
			cart.add(idComic, comic);
			session.setAttribute("cart", cart.getList());
			session.setAttribute("quantity", cart.quantity());
			session.setAttribute("total",cart.total());
		}				
		
		getModel.getCart(model);
		
		return model;
	}
}
