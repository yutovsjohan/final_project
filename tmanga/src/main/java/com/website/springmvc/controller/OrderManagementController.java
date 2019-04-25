package com.website.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.libs.GetModel;

@Controller
@RequestMapping(value="/controller")
public class OrderManagementController {
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = "/bill" , method = RequestMethod.GET)
	public ModelAndView getListBillPage(@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "") String alert,
										@RequestParam(name = "page", defaultValue = "1") int page){
		ModelAndView model = new ModelAndView();
		getModel.getListBillPage(model, page);
		model.addObject("mes", mes);
		model.addObject("alert", alert);
		return model;
	}
	
	@RequestMapping(value = "/billdetail" , method = RequestMethod.GET)
	public ModelAndView getBillDetailPage(@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "") String alert,
										@RequestParam(name = "id", defaultValue = "0") Long idBill){
		ModelAndView model = new ModelAndView();
		getModel.getBillDetailPage(model, idBill);
		model.addObject("mes", mes);
		model.addObject("alert", alert);
		return model;
	}
}
