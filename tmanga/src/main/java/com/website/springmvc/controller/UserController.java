package com.website.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.AuthorService;
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.NewsService;
import com.website.springmvc.Services.PublishCompanyService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.role;
import com.website.springmvc.entities.users;
import com.website.springmvc.libs.TripleDES;

@Controller
@RequestMapping(value="/controller")
public class UserController {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private PublishCompanyService publishCompanyService;

	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ComicService comicService;	
	
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
	
	@RequestMapping(value = {"/login" , "/dang-nhap"}, method = RequestMethod.GET)
	public ModelAndView getLoginPage(){
		ModelAndView model = new ModelAndView();
		getLogin(model);
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
			getLogin(model);
		} 
		else {
			session.setAttribute("account", usersService.get(check));
			getHome(model);
			
		}
		return model;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		return "redirect:index";
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
	
	public void getHome(ModelAndView model) {
		getSideBar(model);
		
		model.addObject("views","index");
		model.addObject("title","T-Manga");
		
		model.addObject("news", newsService.getNewsForBanner());		
		model.addObject("topSelling", comicService.getComicForTopSelling());
		model.addObject("newComic", comicService.getNewComicInHomePage());
		model.addObject("otherComic", comicService.getOtherComicInHomePage());
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
