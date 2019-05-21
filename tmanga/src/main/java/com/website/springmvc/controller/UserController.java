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
import com.website.springmvc.Services.RoleService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.config.MyConstants;
import com.website.springmvc.entities.Author;
import com.website.springmvc.entities.Bill;
import com.website.springmvc.entities.Cart;
import com.website.springmvc.entities.CartDetail;
import com.website.springmvc.entities.Role;
import com.website.springmvc.entities.Users;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.GioHang;
import com.website.springmvc.libs.Item;
import com.website.springmvc.libs.RemoveAccent;
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
	RoleService roleService;
	
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = {"/signup" , "/dang-ky"}, method = RequestMethod.GET)
	public ModelAndView getRegistrationPage(@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "") String alert,
										HttpSession session){
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") != null) {
			getModel.getHome(model, session);			
		}
		else {
			getModel.getRegistration(model);
			model.addObject("mes", mes);
			model.addObject("alert", alert);
		}		
		return model;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String saveUsers(@ModelAttribute("Users") Users Users, HttpSession session, Model model,
							@RequestParam("repassword") String repassword){
		String str = "redirect:signup";
		if(!Users.getPassword().equalsIgnoreCase(repassword)) {
			model.addAttribute("mes","Mật khẩu không khớp nhau");    	
			model.addAttribute("alert", "danger");
		}
		else {
			if(!checkEmail(Users.getEmail())){
				Role Role = new Role();
				Role.setId((long) 2);
				Users.setRole(Role);
				Users.setActive((byte) 2);
				
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
		}
		return str;
	}
	
	@RequestMapping(value = {"/login" , "/dang-nhap"}, method = RequestMethod.GET)
	public ModelAndView getLoginPage(@RequestParam(name = "mes", defaultValue = "") String mes,
									@RequestParam(name = "alert", defaultValue = "") String alert,
									HttpSession session){
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") != null) {
			getModel.getHome(model, session);			
		}
		else {
			model.addObject("mes", mes);
			model.addObject("alert", alert);		
			getModel.getLogin(model);
			if(session.getAttribute("url") == null) {
				session.setAttribute("url", "index");
			}
		}
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)	
	public String login(@RequestParam("email") String email, 
						@RequestParam("password") String password,
						HttpSession session, Model model) {
		String str = "";
		long idUser = checkLogin(email, password);
		if(idUser == -1) {
            model.addAttribute("mes","Email và password của bạn sai");            			
			model.addAttribute("alert", "danger");
			str = "redirect:/controller/login";
		} 
		else if(idUser == -2) {
			model.addAttribute("mes","Tài khoản của bạn đã bị khóa");            			
			model.addAttribute("alert", "danger");
			str = "redirect:/controller/login";
		}
		else {
			session.setAttribute("account", usersService.get(idUser));			
			getSessionCart(session, idUser);
			if(usersService.getUserRole(idUser) != 2) {
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
				model.addAttribute("email", email);
				str = "redirect:change-password";
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
									@RequestParam(name = "alert", defaultValue = "") String alert,
									@RequestParam(name = "action", defaultValue = "staff") String action) {
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			session.setAttribute("url", "userAdmin?p=" + page + "&q=" + key + "&action=" + action);
			Users u = (Users)session.getAttribute("account");
			if(u.getRole().getId() != (long) 1 && u.getRole().getId() != (long) 0) {
				action = "staff";
			}
			getModel.getUserAdmin(model, page, key, mes, alert, action, session);
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
			session.setAttribute("url", "add-user");
			Users u = (Users)session.getAttribute("account");
			if(u.getRole().getId() == (long)1) {
				getModel.getAddUser(model, mes, alert, session);
			}
			else if(u.getRole().getId() != (long)1 && u.getRole().getId() != (long)2) {
				getModel.getUserAdmin(model, 1, "", "", "", "staff", session);
			}
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
			session.setAttribute("url", "edit-user");
			String href = "edit-user";
			getModel.getCustomerEditInfo(model, href, session);
			model.addObject("mes",mes);    	
			model.addObject("alert", alert);
			model.addObject("href", href);
		}
		else {
			getModel.getHome(model, session);
		}
				
		return model;
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public ModelAndView getRolePage(@RequestParam(name = "p", defaultValue = "1") int page, 
									@RequestParam(name = "q", defaultValue = "") String key, HttpSession session,
									@RequestParam(name = "mes", defaultValue = "") String mes, 
									@RequestParam(name = "alert", defaultValue = "") String alert) {
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			session.setAttribute("url", "role?p=" + page + "&q=" + key);
			getModel.getRolePage(model, page, key, mes, alert, session);
		}
		else {
			getModel.getHome(model, session);
		}
				
		return model;
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public String saveAuthor(HttpSession session, Model model,
							@RequestParam("name") String name,
							@RequestParam(name = "mode", defaultValue = "add") String mode,
							@RequestParam(name = "id", defaultValue = "0") Long idRole){
		String str = "redirect:role";
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			if(mode.equalsIgnoreCase("add")) {
				Role role = new Role();
				role.setName(name);				
				roleService.add(role);
				model.addAttribute("mes", "Thêm thành công");
				model.addAttribute("alert", "success");
			}
			else if(mode.equalsIgnoreCase("edit")) {
				if(roleService.get(idRole) == null) {
					model.addAttribute("mes", "ID không tồn tại");
					model.addAttribute("alert", "danger");
				}
				else{
					Role role = roleService.get(idRole);
					role.setName(name);
					roleService.update(role);
					model.addAttribute("mes", "Sửa thành công");
					model.addAttribute("alert", "success");
				}
			}	
		}
		else {
			str = "redirect:index";
		}
		return str;
	}

	@RequestMapping(value = "/removeRole", method = RequestMethod.POST)
	public void removeAddress(@RequestParam(name = "id") Long idRole, HttpServletResponse response,
								HttpSession session) {
		String str = "success";
		boolean f = getModel.checkAdmin(session);
		
		
		if(f && roleService.get(idRole) != null) {
			roleService.delete(idRole);
		}
		else {
			str = "fail";
		}
		

		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/active-account", method = RequestMethod.GET)
	public String activeAccount(@RequestParam(name = "id", defaultValue = "0") Long idUser, HttpSession session, Model model) {
		String str = "redirect:login";
		if(idUser != (long)0) {
			Users u = (Users) session.getAttribute("account");	
			if(u != null) {
				if(u.getRole().getId() == (long)2 || u.getActive() != 0) {
					u.setActive((byte) 1);
					usersService.update(u);
					
					if(((String) session.getAttribute("url")).equalsIgnoreCase("customer/edit")) {
						str = "redirect:customer/edit";
					}
					else if(((String) session.getAttribute("url")).equalsIgnoreCase("cart")) {
						str = "redirect:cart";
					}					
					
					model.addAttribute("mes", "Kích hoạt thành công");
					model.addAttribute("alert", "success");
				}
				else {
					model.addAttribute("mes", "Bạn không thể thực hiện chức năng này");
					model.addAttribute("alert", "danger");
				}
			}
			else {
				model.addAttribute("mes", "Tài khoản không tồn tại");
				model.addAttribute("alert", "danger");
			}
		}
		else {
			model.addAttribute("mes", "Tài khoản không tồn tại");
			model.addAttribute("alert", "danger");
		}
		return str;
	}
	
	@RequestMapping(value = "/send-email-confirm-account", method = RequestMethod.POST)
	public void sendEmailConfirmAccount(@RequestParam(name = "id", defaultValue = "0") Long idUser, HttpServletResponse response, HttpSession session) {
		String str = "success";	
		Users u = (Users) session.getAttribute("account");
		if(u != null) {
			sendEmail(u);
		}
		else {
			str = "fail";
		}		

		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean sendEmail(Users u) {
		String content = "";
		content += "<br><p>Cám ơn quý khách đã đăng ký tài khoản tại T-Manga</p>";
		content += "<p>Để kích hoạt tài khoản, vui lòng bấm vào link bên dưới:</p>";
		content += "<p><a href='http://localhost:8080/tmanga/controller/active-account?id=" + u.getId() + "'> http://localhost:8080/tmanga/controller/active-account?id=" + u.getId() + " </a></p>";
		content += "<p>All the best,</p>";
		content += "<p><a href='http://localhost:8080/tmanga/controller/'><b>T-Manga.vn</b></a></p>";
		
//		content += "<br><p>Thank you for registering at T-Manga</p>";
//		content += "<p>To activate your registration, please visit this URL:</p>";
//		content += "<p><a href='http://localhost:8080/tmanga/controller/active-account?id=" + u.getId() + "'> http://localhost:8080/tmanga/controller/active-account?id=" + u.getId() + " </a></p>";
//		content += "<br><p>All the best,</p>";
//		content += "<p><a href='http://localhost:8080/tmanga/controller/'><b>T-Manga.vn</b></a></p>";
				
		return SendEmail.sendGrid(MyConstants.EMAIL, u.getEmail(), "Xác nhận tài khoản", content, true);
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
			if(u.getActive() == 0) {
				j = -2;
			}
			else if(TripleDES.Decrypt(u.getPassword(), MyConstants.DES_KEY).equalsIgnoreCase(password)) {
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