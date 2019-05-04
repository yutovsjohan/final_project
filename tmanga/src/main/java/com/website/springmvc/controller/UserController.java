package com.website.springmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
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
import com.website.springmvc.config.MyConstants;
import com.website.springmvc.entities.Cart;
import com.website.springmvc.entities.CartDetail;
import com.website.springmvc.entities.Role;
import com.website.springmvc.entities.Users;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.GioHang;
import com.website.springmvc.libs.Item;
import com.website.springmvc.libs.SendEmail;
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
	public ModelAndView getRegistrationPage(@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "") String alert){
		ModelAndView model = new ModelAndView();
		getModel.getRegistration(model);
		model.addObject("mes", mes);
		model.addObject("alert", alert);
		return model;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String saveUsers(@ModelAttribute("Users") Users Users, HttpSession session, Model model){
		String str = "redirect:signup";
		if(!checkEmail(Users.getEmail())){
			Role Role = new Role();
			Role.setId((long) 2);
			Users.setRole(Role);
			Users.setActive((byte) 1);
			
			Users.setPassword(TripleDES.Encrypt(Users.getPassword(), MyConstants.DES_KEY));
			
			String passcode = UUID.randomUUID().toString();
			Users.setPasscode(passcode);
			
			usersService.add(Users);
			
			session.setAttribute("account", usersService.get(Users.getId()));			
			getSessionCart(session, Users.getId());
			
			if(session.getAttribute("url") == null) {
				str = "redirect:index";
			}
			else {
				str = "redirect:" + session.getAttribute("url");
			}
		}
		else{
            model.addAttribute("mes","Email này đã có người sử dụng");    	
			model.addAttribute("alert", "danger");
		}
		return str;
	}
	
	@RequestMapping(value = {"/login" , "/dang-nhap"}, method = RequestMethod.GET)
	public ModelAndView getLoginPage(@RequestParam(name = "mes", defaultValue = "") String mes,
									@RequestParam(name = "alert", defaultValue = "") String alert,
									HttpSession session){
		ModelAndView model = new ModelAndView();		
		model.addObject("mes", mes);
		model.addObject("alert", alert);		
		getModel.getLogin(model);
		if(session.getAttribute("url") == null) {
			session.setAttribute("url", "index");
		}
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)	
	public String login(@RequestParam("email") String email, 
						@RequestParam("password") String password,
						HttpSession session, Model model) {
		String str = "";
		Long idUser = checkLogin(email, password);
		if(idUser == -1) {
            model.addAttribute("mes","Email và password của bạn sai");            			
			model.addAttribute("alert", "danger");
			str = "redirect:/controller/login";
		} 
		else {
			session.setAttribute("account", usersService.get(idUser));			
			getSessionCart(session, idUser);
			if(usersService.getUserRole(idUser)==1) {
				str = "redirect:adminHome";
			}
			else if(session.getAttribute("url") == null) {
				str = "redirect:index";
			}
			else {
				str = "redirect:" + session.getAttribute("url");
			}		
		}
		return str;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		session.removeAttribute("cart");
		return "redirect:index";
	}
	
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
	public String sendMailforgetPassword(@RequestParam("email") String email, Model model){
		String str = "redirect:login";
		if(usersService.get(email) != null){
			Users u = usersService.get(email);

			String passcode = UUID.randomUUID().toString();
			u.setPasscode(passcode);
			usersService.update(u);
			String content = "";

			content += "<p>Xin chào " + u.getName() + ", chúng tôi thấy là quý khách đang gặp vấn đề về đăng nhập tài khoản. Để lấy lại tài khoản, xin quý khách vui lòng bấm vào link bên dưới, điền đầy đủ thông tin cùng với mã xác nhận để lấy lại tài khoản.</p>";
			content += "<p>Mã xác nhận của quý khách là: " + u.getPasscode() + "</p>";
			content += "<a href='http://localhost:8080/tmanga/controller/change-password?email=" + email + "'>Nhấn vào đây để đổi mật khẩu của bạn.</a>";
			content += "<p>Kính chúc quý khách có một ngày tốt lành.</p>";
			content += "<h3 style='color: blue; font-weight: bold;''><a href='http://localhost:8080/tmanga/controller/'>T-Manga.vn</a></h3>";

			if(SendEmail.sendGrid("yutovsjohan@gmail.com", email, "Quên mật khẩu", content, true)) {
				model.addAttribute("mes", "Vui lòng kiểm tra email để lấy lại tài khoản");
				model.addAttribute("alert", "success");
			}
			else {
				model.addAttribute("mes", "Gửi mail không thành công");
				model.addAttribute("alert", "danger");
			}
		}
		else {
			model.addAttribute("mes", "Email không tồn tại");
			model.addAttribute("alert", "danger");
		}
		return str;
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.GET)
	public ModelAndView getForgetPassword(@RequestParam(name = "email", defaultValue = "") String email,
								@RequestParam(name = "alert", defaultValue = "") String alert,
								@RequestParam(name = "mes", defaultValue = "") String mes,
								HttpSession session){
		ModelAndView model = new ModelAndView();		
		model.addObject("mes", mes);
		model.addObject("alert", alert);	

		getModel.getForgetPassword(model, email);

		return model;
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public String changePassword(@RequestParam("email") String email, Model model,
								@RequestParam("password") String password,
								@RequestParam("passcode") String passcode){
		String str = "redirect:change-password";
		Users u = usersService.get(email);
		if(u == null) {
			model.addAttribute("mes", "Email không tộn tại");
			model.addAttribute("alert", "danger");
		}
		else if(u.getPasscode() == null) {
			String psc = UUID.randomUUID().toString();
			u.setPasscode(psc);
			usersService.update(u);
			
			model.addAttribute("mes", "Mã xác nhận không hợp lệ");
			model.addAttribute("alert", "danger");
		}
		else if(u.getPasscode().equalsIgnoreCase(passcode)) {
			u.setPassword(TripleDES.Encrypt(password, MyConstants.DES_KEY));
			usersService.update(u);
			
			str = "redirect:login";
			model.addAttribute("mes", "Đổi mật khẩu thành công");
			model.addAttribute("alert", "success");
		}
		else {			
			model.addAttribute("mes", "Mã xác nhận sai");
			model.addAttribute("alert", "danger");
		}
		model.addAttribute("email", email);
		return str;
	}
	
	private void getSessionCart(HttpSession session, Long idUser) {
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
			Long idComic;
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
			if(countCartDetailByCart(checkCart(idUser)) != null) {
				List<CartDetail> listCartDetail = cartDetailService.getDetailByIdCart(checkCart(idUser));
				
				if(listCartDetail != null) {
					Item item;
					GioHang gioHang = new GioHang();
					HashMap<Long, Item> hashCart = new HashMap<>();
					
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
		}
	}
	
	@RequestMapping(value = "/check-email", method = RequestMethod.GET)
	public void checkMail(@RequestParam("email") String email, HttpServletResponse response){
		String str = "success";
		if(checkEmail(email)) {
			str = "fail";
		}
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/userAdmin", method = RequestMethod.GET)
	public ModelAndView getUserAdmin(@RequestParam(name = "p", defaultValue = "1") int page, 
									@RequestParam(name = "q", defaultValue = "") String key, HttpSession session,
									@RequestParam(name = "mes", defaultValue = "") String mes, 
									@RequestParam(name = "alert", defaultValue = "") String alert) {
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			getModel.getUserAdmin(model, page, key, mes, alert);
		}
		else {
			getModel.getHome(model, session);
		}
				
		return model;
	}
	
	@RequestMapping(value = "/ban-acc", method = RequestMethod.POST)
	public void getBanOpenAcc(@RequestParam("id") Long id, HttpServletResponse response, HttpSession session){
		String str = "fail";
		boolean f = getModel.checkAdmin(session);
		if(f) {
			if(usersService.get(id) != null) {
				Users u = usersService.get(id);
				if(u.getActive() == 1) {
					u.setActive((byte) 0);
				}
				else if(u.getActive() == 0) {
					u.setActive((byte) 1);
				}
				usersService.update(u);
				str = Byte.toString(u.getActive());
			}
		}
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/setup-pw", method = RequestMethod.POST)
	public void setupPassword(@RequestParam("id") Long id, HttpServletResponse response, HttpSession session){
		String str = "fail";
		boolean f = getModel.checkAdmin(session);
		if(f) {
			if(usersService.get(id) != null) {
				Users u = usersService.get(id);
				u.setPassword(TripleDES.Encrypt("123", MyConstants.DES_KEY));
				usersService.update(u);
				str = "success";
			}
		}
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/add-user", method = RequestMethod.GET)
	public ModelAndView getAddUser(@RequestParam(name = "mes", defaultValue = "") String mes, 
									@RequestParam(name = "alert", defaultValue = "") String alert, HttpSession session) {
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			getModel.getAddUser(model, mes, alert);
		}
		else {
			getModel.getHome(model, session);
		}
				
		return model;
	}
	
	@RequestMapping(value = "/user-signup", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("Users") Users Users, HttpSession session, Model model){
		String str = "redirect:add-user";
		if(!checkEmail(Users.getEmail())){			
			Users.setActive((byte) 1);
			
			Users.setPassword(TripleDES.Encrypt(Users.getPassword(), MyConstants.DES_KEY));
			
			String passcode = UUID.randomUUID().toString();
			Users.setPasscode(passcode);
			
			usersService.add(Users);
			str = "redirect:userAdmin";
			
			model.addAttribute("mes","Thêm thành công");    	
			model.addAttribute("alert", "success");
		}
		else{
            model.addAttribute("mes","Email này đã có người sử dụng");    	
			model.addAttribute("alert", "danger");
		}
		return str;
	}
	
	@RequestMapping(value = "/edit-user", method = RequestMethod.GET)
	public ModelAndView getEditUser(@RequestParam(name = "mes", defaultValue = "") String mes, 
									@RequestParam(name = "alert", defaultValue = "") String alert, HttpSession session) {
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			String href = "edit-user";
			getModel.getCustomerEditInfo(model, href);
			model.addObject("mes",mes);    	
			model.addObject("alert", alert);
			model.addObject("href", href);
		}
		else {
			getModel.getHome(model, session);
		}
				
		return model;
	}
		
	private boolean checkEmail(String email) {
		if(usersService.get(email) == null)
			return false;
		return true;
	}
	
	private long checkLogin(String email, String password) {
		Users u = usersService.get(email);
		long j = -1;
		if(u != null){
			if(TripleDES.Decrypt(u.getPassword(), MyConstants.DES_KEY).equalsIgnoreCase(password)) {
				j = u.getId();
			}
		}
		return j;
	}
	
	private long checkCart(Long iduser) {
		long i = 0;
		if(cartService.getCartByUser(iduser) != null ) {
			i = cartService.getCartByUser(iduser).getId();
		}
		return i;
	}
	
	private Long countCartDetailByCart(Long idCart) {
		if(cartDetailService.countCartDetailByCart(idCart) != null) {
			return cartDetailService.countCartDetailByCart(idCart);
		}
		return null;
	}
}