package com.website.springmvc.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
										@RequestParam(name = "p", defaultValue = "1") int page, HttpSession session,
										@RequestParam(name = "q", defaultValue = "0") Long idBill){
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			String str = "bill?p=" + page;
			if(idBill != (long)0) {
				str += "q=" + idBill;
			}
			session.setAttribute("url", str );
			getModel.getListBillPage(model, page, idBill, session);
			model.addObject("mes", mes);
			model.addObject("alert", alert);
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}
	
	@RequestMapping(value = "/billdetail" , method = RequestMethod.GET)
	public ModelAndView getBillDetailPage(@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "") String alert,
										@RequestParam(name = "id", defaultValue = "0") Long idBill, HttpSession session){
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			session.setAttribute("url", "billdetail?id=" + idBill);
			getModel.getBillDetailPage(model, idBill, session);
			model.addObject("mes", mes);
			model.addObject("alert", alert);
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}
	
	@RequestMapping(value = "/add-order-status" , method = RequestMethod.GET)
	public String addOrderStatus(@RequestParam(name = "id", defaultValue = "0") Long idBill, Model model, HttpSession session){
		String str = "redirect:billdetail?id=" + idBill;
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
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
		}
		else {
			str = "redirect:index";
		}
		
		return str;
	}	
	
	@RequestMapping(value = "/come-back-order-status" , method = RequestMethod.GET)
	public String comeBackStatus(@RequestParam(name = "id", defaultValue = "0") Long idBill, Model model, HttpSession session){
		String str = "redirect:billdetail?id=" + idBill;
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			if(idBill == (long) 0 || billService.get(idBill) == null) {
				str = "redirect:index";
			}
			else {			
				boolean flag = true;
				Bill bill = billService.get(idBill);				
								
				if(bill.getActive() == 0) {					
					bill.setActive(1);
					bill.setStatus("Đặt hàng thành công");
					billService.update(bill);
				}
				else if(bill.getActive() == 2) {					
					bill.setActive(0);
					bill.setStatus("Đang vận chuyển");
					billService.update(bill);
				}
				else {
					flag = false;
					model.addAttribute("mes", "Quay lại trạng thái trước thất bại");
					model.addAttribute("alert", "danger");
				}
				
				if(flag) {
					OrderStatus os = orderStatusService.getNewestOrderStatusByIdBill(idBill);
					model.addAttribute("mes", "Quay lại trạng thái trước thành công");
					model.addAttribute("alert", "success");
					orderStatusService.delete(os);
				}
			}
		}
		else {
			str = "redirect:index";
		}
		
		return str;
	}
	
	@RequestMapping(value = "/select-delivery" , method = RequestMethod.POST)
	public void selectDelivery(@RequestParam(name = "idBill", defaultValue = "0") Long idBill, HttpServletResponse response,
								@RequestParam(name = "idUser", defaultValue = "0") Long idUser, HttpSession session){
		String str = "fail";
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
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
		}		
			
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/select-delivery-date" , method = RequestMethod.POST)
	public void selectDeliveryDate(@RequestParam(name = "idBill", defaultValue = "0") Long idBill, HttpServletResponse response,
								@RequestParam(name = "date", defaultValue = "") String strDate, HttpSession session){
		String str = "fail";
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			if(idBill == (long) 0 || strDate.equalsIgnoreCase("") || billService.get(idBill) == null) {
				str = "fail";
			}
			else {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.setTime(java.sql.Date.valueOf(strDate));				
				java.util.Date date = cal.getTime();
				
				Bill bill = billService.get(idBill);
				
				String temp = dateFormat.format(bill.getOrderDate());
				Date orderDate = Date.valueOf(temp);
				
				temp = dateFormat.format(date);
				Date deliveryDate = Date.valueOf(temp);
									
				if(orderDate.compareTo(deliveryDate) <= 0) {
					bill.setDeliveryDate(date);
					billService.update(bill);
					dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					str = dateFormat.format(date);
				}
				else {					
					str = "fail";
				}
			}
		}		
			
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
