package com.website.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.BilldetailService;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.users;
import com.website.springmvc.libs.GetModel;

@Controller
@RequestMapping(value="/controller")
public class CustomerController {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private BilldetailService billdetailService; 
	
	@Autowired
	private BillService billService; 
	
	@Autowired
	private ComicService comicService;
	
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = "/customer/edit", method = RequestMethod.GET)
	public ModelAndView getCustomerEditInfo(HttpSession session) {
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") != null) {
			getModel.getCustomerEditInfo(model);
		}
		else {
			getModel.getHome(model);			
		}
		return model;
	}	
	
	@RequestMapping(value = "/customer/favoriteList", method = RequestMethod.GET)
	public ModelAndView getFavoriteList(HttpSession session) {
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") != null) {
			getModel.getFavoriteList(model);			
		}
		else {
			getModel.getHome(model);
		}
		return model;
	}
	
	@RequestMapping(value = "/customer/orderHistory", method = RequestMethod.GET)
	public ModelAndView getOrderHistory(HttpSession session) {
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") != null) {
			getModel.getOrderHistory(model);
			
			long id = ((users) session.getAttribute("account")).getId();
			model.addObject("bills", billService.getBillOfUser(id));
			
		}
		else {
			getModel.getHome(model);
		}
		return model;
	}
}