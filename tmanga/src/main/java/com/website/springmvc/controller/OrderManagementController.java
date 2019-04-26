package com.website.springmvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.OrderStatusService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.Bill;
import com.website.springmvc.entities.OrderStatus;
import com.website.springmvc.entities.Users;
import com.website.springmvc.libs.GetModel;

@Controller
@RequestMapping(value="/controller")
public class OrderManagementController {
	@Autowired
	GetModel getModel;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@RequestMapping(value = "/bill" , method = RequestMethod.GET)
	public ModelAndView getListBillPage(@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "") String alert,
										@RequestParam(name = "p", defaultValue = "1") int page){
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
	
	@RequestMapping(value = "/add-order-status" , method = RequestMethod.GET)
	public String addOrderStatus(@RequestParam(name = "id", defaultValue = "0") Long idBill, Model model){
		String str = "redirect:billdetail?id=" + idBill;
		
		if(idBill == (long) 0 || billService.get(idBill) == null) {
			str = "redirect:index";
		}
		else {			
			Bill bill = billService.get(idBill);
			OrderStatus os = new OrderStatus();
			model.addAttribute("mes", "Thêm tình trạng mới thành công");
			model.addAttribute("alert", "success");
			
			if(bill.getActive() == 1) {
				os.setBill(bill);
				os.setContent("Đang vận chuyển");
				orderStatusService.add(os);
				
				bill.setActive(0);
				bill.setStatus("Đang vận chuyển");
				billService.update(bill);
			}
			else if(bill.getActive() == 0) {
				os.setBill(bill);
				os.setContent("Giao hàng thành công");
				orderStatusService.add(os);
				
				bill.setActive(2);
				bill.setStatus("Giao hàng thành công");
				billService.update(bill);
			}
			else {
				model.addAttribute("mes", "Thêm thất bại");
				model.addAttribute("alert", "danger");
			}
		}
		
		return str;
	}
	
	@RequestMapping(value = "/select-delivery" , method = RequestMethod.POST)
	public void selectDelivery(@RequestParam(name = "idBill", defaultValue = "0") Long idBill, HttpServletResponse response,
								@RequestParam(name = "idUser", defaultValue = "0") Long idUser){
		String str = "fail";
		if(idBill == (long) 0 || idUser == (long) 0 || billService.get(idBill) == null || usersService.get(idUser) == null) {
			str = "fail";
		}
		else {
			Bill bill = billService.get(idBill);
			Users user = usersService.get(idUser);
			bill.setDelivery(user.getName());
			billService.update(bill);
			str = user.getName();
		}
		
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
