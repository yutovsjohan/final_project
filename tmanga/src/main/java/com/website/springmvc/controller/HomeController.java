package com.website.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.libs.GetModel;

@Controller
@RequestMapping(value="/controller")
public class HomeController {
	
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = {"/", "trang-chu", "/index"}, method = RequestMethod.GET)
	public ModelAndView getHomePage(){		
		ModelAndView model = new ModelAndView();
		getModel.getHome(model);						
		return model;
	}	
	
	@RequestMapping(value = {"/gioi-thieu", "/introduce"}, method = RequestMethod.GET)
	public ModelAndView getIntroducePage(){		
		ModelAndView model = new ModelAndView();
		getModel.getIntroduce(model);						
		return model;
	}	
	
	@RequestMapping(value = {"/lien-he", "/contact"}, method = RequestMethod.GET)
	public ModelAndView getContactPage(){		
		ModelAndView model = new ModelAndView();
		getModel.getContact(model);						
		return model;
	}	
	
	@RequestMapping(value = "/trackOrder", method = RequestMethod.GET)
	public ModelAndView getTrackOrderPage(){		
		ModelAndView model = new ModelAndView();
		getModel.getTrackOrder(model);						
		return model;
	}
}
