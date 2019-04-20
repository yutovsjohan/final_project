package com.website.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.ContactService;
import com.website.springmvc.Services.UsersService;

@Controller
@RequestMapping(value = "/controller")
public class AdminHomeController {

	@Autowired
	ContactService contactService;
	
	@Autowired
	UsersService userService;
	
	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	public ModelAndView getAdminHome() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/adminHome");
		model.addObject("allMess", contactService.getAllMessage());
		model.addObject("unView", contactService.getUnReadMessage());
		model.addObject("userNum",userService.getUserNum());
		return model;
	}
}
