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

import com.website.springmvc.entities.Comic;
import com.website.springmvc.entities.Author;
import com.website.springmvc.entities.Category;
import com.website.springmvc.entities.PublishCompany;

import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.Services.AuthorService;
import com.website.springmvc.Services.PublishCompanyService;


@Controller
@RequestMapping(value = "/controller")
public class ProductManagementController {
	
}
