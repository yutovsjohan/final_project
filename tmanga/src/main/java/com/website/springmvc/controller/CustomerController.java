package com.website.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.BillDetailService;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.Bill;
import com.website.springmvc.entities.Users;
import com.website.springmvc.libs.GetModel;

@Controller
@RequestMapping(value="/controller")
public class CustomerController {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private BillDetailService billDetailService; 
	
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

	@RequestMapping(value = "/customer/edit", method = RequestMethod.POST)
	public String getEditInfo() {
		String str = "";
		return str;
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
	public ModelAndView getOrderHistory(HttpSession session, @RequestParam(name = "p", defaultValue = "1") int page) {
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") != null) {
			getModel.getOrderHistory(model);
			
			int id = ((Users) session.getAttribute("account")).getId();
			List<Bill> bills = billService.getBillByUser(id, 0, 0);
			
			int totalPage = 0;
			int totalBill = 0;		
			
			totalBill = bills.size();
			totalPage = totalBill / 12;
			
			if(totalBill % 12 != 0){
				totalPage++;
			}
			
			bills = billService.getBillByUser(id, 10*(page-1), 10);
			
			model.addObject("bills", bills);
			model.addObject("totalpage", totalPage);
			model.addObject("pageselected", page);
			model.addObject("totalbill", totalBill);
		}
		else {
			getModel.getHome(model);
		}
		return model;
	}
}
