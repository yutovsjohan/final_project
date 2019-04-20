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
import com.website.springmvc.entities.Author;
import com.website.springmvc.Services.AuthorService;;

@Controller
@RequestMapping(value = "/controller")
public class AuthorAdminController {
	
	@Autowired
	AuthorService authorService;
	
	@RequestMapping(value = "/authorAdmin", method = RequestMethod.GET)
	public ModelAndView getAuthorAdmin() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/AuthorAdmin");
		model.addObject("authors", authorService.getAll());
		return model;
	}
}
