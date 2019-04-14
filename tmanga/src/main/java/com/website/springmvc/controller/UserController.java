package com.website.springmvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.CartDetailService;
import com.website.springmvc.Services.CartService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.Cart;
import com.website.springmvc.entities.CartDetail;
import com.website.springmvc.entities.Role;
import com.website.springmvc.entities.Users;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.GioHang;
import com.website.springmvc.libs.Item;
import com.website.springmvc.libs.TripleDES;

@Controller
@RequestMapping(value="/controller")
public class UserController {
	@Autowired
	private UsersService usersService;

	@Autowired
	CartService cartService;
	
	@Autowired
	CartDetailService cartDetailService;
	
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = {"/signup" , "/dang-ky"}, method = RequestMethod.GET)
	public ModelAndView getRegistrationPage(){
		ModelAndView model = new ModelAndView();
		getModel.getRegistration(model);
		return model;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView saveUsers(@ModelAttribute("Users") Users Users){
		ModelAndView model = new ModelAndView();
		if(!checkEmail(Users.getEmail())){
			Role Role = new Role();
			Role.setId(2);
			Users.setRole(Role);
			
			Users.setPassword(TripleDES.Encrypt(Users.getPassword(), "123"));
			
			usersService.add(Users);
			
			getModel.getLogin(model);
			
            model.addObject("mes", "Đăng ký thành công");
			model.addObject("alert", "success");
		}
		else{
			getModel.getRegistration(model);
			
            model.addObject("mes","Email này đã có người sử dụng");    	
			model.addObject("alert", "danger");
		}
		return model;
	}
	
	@RequestMapping(value = {"/login" , "/dang-nhap"}, method = RequestMethod.GET)
	public ModelAndView getLoginPage(@RequestParam(name = "mes", defaultValue = "") String mes,
									@RequestParam(name = "alert", defaultValue = "") String alert){
		ModelAndView model = new ModelAndView();		
		model.addObject("mes", mes);
		model.addObject("alert", alert);		
		getModel.getLogin(model);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)	
	public String login(@RequestParam("email") String email, 
						@RequestParam("password") String password,
						HttpSession session, Model model) {
		String str = "";
		int idUser = checkLogin(email, password);
		if(idUser == -1) {
            model.addAttribute("mes","Email và password của bạn sai");            			
			model.addAttribute("alert", "danger");
			str = "redirect:/controller/login";
		} 
		else {
			session.setAttribute("account", usersService.get(idUser));
			
			if(session.getAttribute("cart") != null) {				
				Cart cart = new Cart();
				if(cartService.getCartByUser(idUser) != null) {
					cart = cartService.getCartByUser(idUser);
				}
				else { 
					cart.setIdUser(((Users) session.getAttribute("account")));
					cartService.add(cart);
				}		
								
				List<CartDetail> listCartDetail = cartDetailService.getDetailByIdCart(cart.getId());
				for(int i = 0; i < listCartDetail.size(); i++) {
					cartDetailService.delete(listCartDetail.get(i).getId());
				}
				
				CartDetail cartDetail = new CartDetail();
				
				ArrayList<Item> items =  (ArrayList<Item>) ((GioHang) session.getAttribute("cart")).getList();
				int idComic;
				for (int i = 0; i < items.size(); i++) {
					idComic = items.get(i).getComic().getId();
					if(cartDetailService.getDetailByCartAndProduct(cart.getId(), idComic) != null) {
						cartDetail = cartDetailService.getDetailByCartAndProduct(cart.getId(), idComic);
					}
					else {
						cartDetail.setCart(cart);
						cartDetail.setComic(items.get(i).getComic());
					}										
					cartDetail.setAmount((byte)items.get(i).getAmount());							
					if(cartDetailService.getDetailByCartAndProduct(cart.getId(), idComic) != null) {
						cartDetailService.update(cartDetail);
					}
					else {
						cartDetailService.add(cartDetail);
					}
				}
			}			
			else if(checkCart(idUser) != 0) {
				List<CartDetail> listCartDetail = cartDetailService.getDetailByIdCart(checkCart(idUser));
				
				if(listCartDetail != null) {
					Item item;
					GioHang gioHang = new GioHang();
					HashMap<Integer, Item> hashCart = new HashMap<>();
					
					for (int i = 0; i < listCartDetail.size(); i++) {
						item = new Item();
						item.setComic(listCartDetail.get(i).getComic());
						item.setAmount(listCartDetail.get(i).getAmount());
						hashCart.put(listCartDetail.get(i).getComic().getId(), item);
					}
					gioHang.setCart(hashCart);
					session.removeAttribute("cart");
					session.setAttribute("cart", gioHang);
				}
			}
			str = "redirect:/controller/index";			
		}
		return str;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		session.removeAttribute("cart");
		return "redirect:index";
	}
	
	

	private boolean checkEmail(String email) {
		List<Users> listUsers = usersService.getAll();
		Users u = new Users();
		for (int i = 0; i < listUsers.size(); i++) {
			u = listUsers.get(i);
			if(u.getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}
	
	private int checkLogin(String email, String password) {
		List<Users> listUsers = usersService.getAll();
		Users u = new Users();
		int j = -1;
		for (int i = 0; i < listUsers.size(); i++) {
			u = listUsers.get(i);
			if(u.getEmail().equalsIgnoreCase(email)) {
				if(TripleDES.Decrypt(u.getPassword(), "123").equalsIgnoreCase(password)) {
					j = u.getId();
					break;
				}				
			}			
		}
		return j;
	}
	
	private long checkCart(int iduser) {
		long i = 0;
		if(cartService.getCartByUser(iduser) != null ) {
			i = cartService.getCartByUser(iduser).getId();
		}
		return i;
	}
}