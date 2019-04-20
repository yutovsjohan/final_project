package com.website.springmvc.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.libs.GetModel;

@Controller
@RequestMapping(value="/controller")
public class HomeController {
	
	@Autowired
	GetModel getModel;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private BillService billService;
		
	@RequestMapping(value = {"/", "trang-chu", "/index"}, method = RequestMethod.GET)
	public ModelAndView getHomePage(HttpSession session){		
		ModelAndView model = new ModelAndView();
		getModel.getHome(model, session);
		session.setAttribute("url", "index");
		return model;
	}	
	
	@RequestMapping(value = {"/gioi-thieu", "/introduce"}, method = RequestMethod.GET)
	public ModelAndView getIntroducePage(HttpSession session){		
		ModelAndView model = new ModelAndView();
		getModel.getIntroduce(model);	
		session.setAttribute("url", "introduce");
		return model;
	}	
	
	@RequestMapping(value = "/trackOrder", method = RequestMethod.GET)
	public ModelAndView getTrackOrderPage(@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "danger") String alert,
										@RequestParam(name = "email", defaultValue = "") String email,
										@RequestParam(name = "idBill", defaultValue = "0") Long idBill,
										HttpSession session){		
		ModelAndView model = new ModelAndView();
		getModel.getTrackOrder(model);
		
		String str = "trackOrder";
		
		session.setAttribute("url", str);
		
		if(!email.equalsIgnoreCase("") && idBill != 0) {
			if(checkEmail(email) != (long) 0) {
				Long idUser = checkEmail(email);
				if(billService.getBillByIdBillAndUser(idBill, idUser) != null) {
					getModel.getOrderDetail(model, idBill);
					getModel.getSideBar(model);
					
					model.addObject("users", usersService.get(idUser));	
					model.addObject("href", "trackOrder");
				}
				else {
					mes = "Email và mã đơn hàng không hợp lệ";
				}
			}
			else {
				mes = "Email không tồn tại";
			}
		}
		
		model.addObject("mes", mes);
		model.addObject("alert", alert);
		model.addObject("email", email);
		model.addObject("idbill", idBill);
		return model;
	}
	
	private Long checkEmail(String email) {
		if(usersService.get(email) == null)
			return (long) 0;
		return usersService.get(email).getId();
	}
	
	
}
