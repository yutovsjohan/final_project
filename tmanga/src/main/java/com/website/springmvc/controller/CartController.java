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
							@RequestParam(name = "action", defaultValue = "add") String action,
							HttpSession session, HttpServletResponse response) {
		String str = "";
		
		if(amount < 0) {
			str = "Số lượng không thể âm hoặc bằng 0";
		}
		else {
			comic comic = comicService.get(idComic);
			cart cart = new cart();
			int amountInCart = 0;
			boolean f = false;
			
			if(session.getAttribute("cart") != null) {
				ArrayList<item> items =  (ArrayList<item>) ((cart) session.getAttribute("cart")).getList();
				HashMap<Integer, item> hashcart = new HashMap<>();
				for (int i = 0; i < items.size(); i++) {
					hashcart.put(items.get(i).getComic().getId(), items.get(i));
					if(items.get(i).getComic().getId() == idComic) {
						f = true;
					}
				}
				cart.setCart(hashcart);
				if(f) {
					amountInCart = cart.getItemForId(idComic).getAmount();
				}
			}					
			
			if(comic.getAmount() == 0) {
				cart.delete(idComic);
				str = "Truyện này tạm hết hàng";
			}
			else {	
				if(action.equalsIgnoreCase("add")) {
					if( amount + amountInCart > comic.getAmount()) {
						str = comic.getName() + " cần số lượng tối đa được phép mua là " + comic.getAmount();
					}
					else if(amount != 0){
						cart.add(idComic, comic, amount);
						session.setAttribute("cart", cart);	
						str = "added";
					}
				}
				
				else {
					if(amount == 0) {
						cart.delete(idComic);
						str = "removed";
					}
					else if( amount > comic.getAmount() || amount < 0) {
						str = "invalid";
					}
					else {
						amount -= cart.getItemForId(idComic).getAmount();
						cart.add(idComic, comic, amount);
						str = "updated";
					}
					
					if(cart.quantity() == 0) {
						session.removeAttribute("cart");
					}
					else {
						session.setAttribute("cart", cart);
					}
				}						
			}				
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
	
	@RequestMapping(value = "/removeitem", method = RequestMethod.GET)
	public void getRemoveItem(@RequestParam("id") int idComic, HttpSession session, HttpServletResponse response){
		String str = "";
		if(session.getAttribute("cart") != null) {
			cart cart = (cart) session.getAttribute("cart");
			cart.delete(idComic);
			if(cart.quantity() == 0) {
				session.removeAttribute("cart");
			}
			else {
				session.setAttribute("cart", cart);
			}
			str = "removed";
		}
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/deletecart", method = RequestMethod.GET)
	public String getDeteleCart(HttpSession session){
		session.removeAttribute("cart");
		return "redirect:/controller/cart";
	}
}
