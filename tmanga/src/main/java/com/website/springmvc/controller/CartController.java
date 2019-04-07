package com.website.springmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
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
	
	@RequestMapping(value = "/addtocart", method = RequestMethod.GET)
	public void getAddToCart(@RequestParam("id") int idComic, 
							@RequestParam(name = "amount", defaultValue = "1") int amount,
							HttpSession session, HttpServletResponse response) {
		String str = "";

		comic comic = comicService.get(idComic);
		cart cart = new cart();
		int amountInCart = 0;
		
		if(session.getAttribute("cart") != null) {
			ArrayList<item> items =  (ArrayList<item>) ((cart) session.getAttribute("cart")).getList();
			HashMap<Integer, item> hashcart = new HashMap<>();
			for (int i = 0; i < items.size(); i++) {
				hashcart.put(items.get(i).getComic().getId(), items.get(i));
			}
			cart.setCart(hashcart);
			amountInCart = cart.getItemForId(idComic).getAmount();
		}
				
		if(amount <= 0) {
			str = "Số lượng không thể âm hoặc bằng 0";
		}
		else if(comic.getAmount() == 0) {
				str = "Truyện này tạm hết hàng";
		}
		else if( (amount + amountInCart) > comic.getAmount()) {
			str = comic.getName() + " cần số lượng tối đa được phép mua là " + comic.getAmount();
		}
		else {						
			for(int i = 1; i <= amount; i++) {
				cart.add(idComic, comic);
			}
			
			session.setAttribute("cart", cart);
			str = "added";
		}				
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/deletecart", method = RequestMethod.GET)
	public ModelAndView getDeteleCart(HttpSession session){
		ModelAndView model = new ModelAndView();
		session.removeAttribute("cart");
		getModel.getCart(model);		
		return model;	
	}
}
