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
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.entities.Category;
@Controller
@RequestMapping(value = "/controller")
public class CategoryAdminController {
	
	@Autowired
	CategoryService cateService;
	
	@RequestMapping(value = "/cateAdmin", method = RequestMethod.GET)
	public ModelAndView getCateAdminPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/CategoryAdmin");
		model.addObject("categories", cateService.getAll());
		return model;
	}
}
