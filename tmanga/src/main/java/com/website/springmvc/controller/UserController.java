package com.website.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.role;
import com.website.springmvc.entities.users;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.TripleDES;

@Controller
@RequestMapping(value="/controller")
public class UserController {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = {"/signup" , "/dang-ky"}, method = RequestMethod.GET)
	public ModelAndView getRegistrationPage(){
		ModelAndView model = new ModelAndView();
		getModel.getRegistration(model);
		return model;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView saveUsers(@ModelAttribute("users") users users){
		ModelAndView model = new ModelAndView();
		if(!checkEmail(users.getEmail())){
			role role = new role();
			role.setId(2);
			users.setRole(role);
			
			users.setPassword(TripleDES.Encrypt(users.getPassword(), "123"));
			
			usersService.add(users);
			
			getModel.getLogin(model);
			
			model.addObject("mes", "Success");
			model.addObject("alert", "success");
		}
		else{
			getModel.getRegistration(model);
			
			model.addObject("mes","Email exist");	
			model.addObject("alert", "danger");
		}
		return model;
	}
	
	@RequestMapping(value = {"/login" , "/dang-nhap"}, method = RequestMethod.GET)
	public ModelAndView getLoginPage(){
		ModelAndView model = new ModelAndView();
		getModel.getLogin(model);
		return model;
	}	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)	
	public ModelAndView login(@RequestParam("email") String email, 
						@RequestParam("password") String password,
						HttpSession session) {
		ModelAndView model = new ModelAndView();
		int check = checkLogin(email, password);
		if(check == -1) {
			model.addObject("mes","Email và password của bạn sai");			
			model.addObject("alert", "danger");
			getModel.getLogin(model);
		} 
		else {
			session.setAttribute("account", usersService.get(check));
			getModel.getHome(model);
			
		}
		return model;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		return "redirect:index";
	}
		
	private boolean checkEmail(String email) {
		List<users> listusers = usersService.getAll();
		users u = new users();
		for (int i = 0; i < listusers.size(); i++) {
			u = listusers.get(i);
			if(u.getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}
	
	private int checkLogin(String email, String password) {
		List<users> listusers = usersService.getAll();
		users u = new users();
		int j = -1;
		for (int i = 0; i < listusers.size(); i++) {
			u = listusers.get(i);
			if(u.getEmail().equalsIgnoreCase(email)) {
				if(TripleDES.Decrypt(u.getPassword(), "123").equalsIgnoreCase(password)) {
					j = u.getId();
				}				
			}			
		}
		return j;
	}
}
