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

import com.website.springmvc.entities.Bill;
import com.website.springmvc.entities.BillDetail;

import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.BillDetailService;

@Controller
@RequestMapping(value = "/controller")
public class CustomerOrderController {
	
}
