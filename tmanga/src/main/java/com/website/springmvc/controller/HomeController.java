package com.website.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.BillDetailService;
import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.OrderStatusService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.Bill;
import com.website.springmvc.entities.BillDetail;
import com.website.springmvc.entities.Users;
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
	
	@Autowired
	private BillDetailService billDetailService;
	
	@Autowired
	private OrderStatusService orderStatusService;
	
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
	
	@RequestMapping(value = "/trackOrder", method = RequestMethod.GET)
	public ModelAndView getTrackOrderPage(@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "danger") String alert,
										@RequestParam(name = "email", defaultValue = "") String email,
										@RequestParam(name = "idBill", defaultValue = "0") Long idBill){		
		ModelAndView model = new ModelAndView();
		getModel.getTrackOrder(model);
		
		if(!email.equalsIgnoreCase("") && idBill != 0) {
			if(checkEmail(email) != (long) 0) {
				Long idUser = checkEmail(email);
				if(billService.getBillByIdBillAndUser(idBill, idUser) != null) {
					getModel.getOrderDetail(model);
					getModel.getSideBar(model);
					model.addObject("bill", billService.get(idBill));
					model.addObject("billDetail", billDetailService.getBillDetailByIdBill(idBill));
					model.addObject("orderStatus", orderStatusService.getOrderStatusByIdBill(idBill));
					model.addObject("users", usersService.get(idUser));					
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
		List<Users> listUsers = usersService.getAll();
		Users u = new Users();
		for (int i = 0; i < listUsers.size(); i++) {
			u = listUsers.get(i);
			if(u.getEmail().equalsIgnoreCase(email)) {
				return u.getId();
			}
		}
		return (long) 0;
	}
	
	
}
