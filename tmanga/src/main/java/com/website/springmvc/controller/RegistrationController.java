package com.website.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.AuthorService;
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.Services.PublishCompanyService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.role;
import com.website.springmvc.entities.users;
import com.website.springmvc.libs.TripleDES;

@Controller
@RequestMapping(value="/controller")
public class RegistrationController {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private PublishCompanyService publishCompanyService;
	
	@RequestMapping(value = {"/signup" , "/dang-ky"}, method = RequestMethod.GET)
	public ModelAndView getRegistrationPage(){
		ModelAndView model = new ModelAndView();
		getRegistration(model);
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
			
			getLogin(model);
			
			model.addObject("mes", "Success");
			model.addObject("alert", "success");
		}
		else{
			getRegistration(model);
			
			model.addObject("mes","Email exist");	
			model.addObject("alert", "danger");
		}
		return model;
	}
	
	public void getSideBar(ModelAndView model) {
		model.setViewName("layout");
		model.addObject("sb","sidebar");
		
		model.addObject("categories", categoryService.getAll());
		model.addObject("authors", authorService.getAll());
		model.addObject("pcs", publishCompanyService.getAll());
	}
	
	public void getRegistration(ModelAndView model){
		getSideBar(model);	
		
		model.addObject("views","register");
		model.addObject("title","Đăng ký");
			
		model.addObject("users",new users());
	}
	
	public void getLogin(ModelAndView model){		
		getSideBar(model);
		
		model.addObject("views","login");
		model.addObject("title","Đăng nhập");		
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
}
