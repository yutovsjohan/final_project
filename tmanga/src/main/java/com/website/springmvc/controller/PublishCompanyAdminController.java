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

import com.website.springmvc.Services.PublishCompanyService;;
@Controller
@RequestMapping(value = "/controller")
public class PublishCompanyAdminController {
	
	@Autowired
	private PublishCompanyService pubcomService;
	@RequestMapping(value = "/pubcomAdmin", method = RequestMethod.GET)
	public ModelAndView getPublishCompanyAdmin() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/PublishingAdmin");
		model.addObject("PubComs", pubcomService.getAll());
		return model;
	}
}
