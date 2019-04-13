package com.website.springmvc.controller;

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

import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.Role;
import com.website.springmvc.entities.Users;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.TripleDES;

@Controller
@RequestMapping(value="/controller")
public class UserController {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private BilldetailService billdetailService; 
	
	@Autowired
	private BillService billService; 
	
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
		if(mes != ""){
			model.addObject("mes", mes);
			model.addObject("alert", alert);
		}
		getModel.getLogin(model);
		return model;
	}

	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)	
//	public ModelAndView login(@RequestParam("email") String email, 
//						@RequestParam("password") String password,
//						HttpSession session) {
//		ModelAndView model = new ModelAndView();
//		long check = checkLogin(email, password);
//		if(check == -1) {
//			model.addObject("mes","Email và password của bạn sai");			
//			model.addObject("alert", "danger");
//			getModel.getLogin(model);
//		} 
//		else {
//			session.setAttribute("account", usersService.get(check));
//			getModel.getHome(model);
//			
//		}
//		return model;
//	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)	
	public String login(@RequestParam("email") String email, 
						@RequestParam("password") String password,
						HttpSession session, Model model) {
		//ModelAndView model = new ModelAndView();
		String str = "";
		long check = checkLogin(email, password);
		if(check == -1) {
            model.addAttribute("mes","Email và password của bạn sai");            			
			model.addAttribute("alert", "danger");
//			getModel.getLogin(model);
			str = "redirect:/controller/login";
		} 
		else {
			session.setAttribute("account", usersService.get(check));
//			getModel.getHome(model);
			str = "redirect:/controller/index";			
		}
		return str;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("account");
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
	
<<<<<<< HEAD
	private int checkLogin(String email, String password) {
		List<Users> listUsers = usersService.getAll();
		Users u = new Users();
		int j = -1;
		for (int i = 0; i < listUsers.size(); i++) {
			u = listUsers.get(i);
=======
	private Long checkLogin(String email, String password) {
		List<users> listusers = usersService.getAll();
		users u = new users();
		long j = -1;
		for (int i = 0; i < listusers.size(); i++) {
			u = listusers.get(i);
>>>>>>> parent of fba3364... fix error, update project part 2
			if(u.getEmail().equalsIgnoreCase(email)) {
				if(TripleDES.Decrypt(u.getPassword(), "123").equalsIgnoreCase(password)) {
					j = u.getId();
					break;
				}				
			}			
		}
		return j;
	}
}
