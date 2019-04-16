package com.website.springmvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.BillDetailService;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.OrderStatusService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.Bill;
import com.website.springmvc.entities.BillDetail;
import com.website.springmvc.entities.Comic;
import com.website.springmvc.entities.Users;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.TripleDES;

@Controller
@RequestMapping(value="/controller")
public class CustomerController {
	@Autowired
	private UsersService userService;
	
	@Autowired
	private BillDetailService billDetailService; 
	
	@Autowired
	private BillService billService; 
	
	@Autowired
	private ComicService comicService;
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = "/customer/edit", method = RequestMethod.GET)
	public ModelAndView getCustomerEditInfo(HttpSession session, 
										@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "") String alert) {
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") != null) {
			getModel.getCustomerEditInfo(model);
			model.addObject("mes", mes);
			model.addObject("alert", alert);
		}
		else {
			getModel.getHome(model);			
		}
		return model;
	}	

	@RequestMapping(value = "/customer/edit", method = RequestMethod.POST)
	public String getEditInfo(@RequestParam(name = "name") String name,
							@RequestParam(name = "address") String address,
							@RequestParam(name = "phone") String phone,
							@RequestParam(name = "password", defaultValue = "") String password,
							HttpSession session, Model model) {
		String str = "";
		if(session.getAttribute("account") != null) {
			Users u = userService.get(((Users) session.getAttribute("account")).getId());
			u.setName(name);
			u.setAddress(address);
			u.setPhone(phone);
			
			if(!password.equalsIgnoreCase("")) {
				u.setPassword(TripleDES.Encrypt(u.getPassword(), "123"));
			}
			
			userService.update(u);
			session.setAttribute("account", u);
			
			model.addAttribute("mes", "Sửa thành công");
			model.addAttribute("alert", "success");
			str = "redirect:edit";
		}
		else {
			str = "redirect:index";
		}
		return str;
	}
	
	@RequestMapping(value = "/customer/favoriteList", method = RequestMethod.GET)
	public ModelAndView getFavoriteList(HttpSession session, @RequestParam(name = "p", defaultValue = "1") int page) {
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") != null) {
			getModel.getFavoriteList(model);		
			Users u = userService.get(((Users) session.getAttribute("account")).getId());
			List<Object[]> listResult = userService.getList(u.getId(), 0, 0);
									
			int totalPage = 0;
			int totalComic = 0;		
			
			totalComic = listResult.size();
			totalPage = totalComic / 12;
			
			if(totalComic % 12 != 0){
				totalPage++;
			}	
			listResult = userService.getList(u.getId(), 12*(page-1), 12);
			
			List<Comic> comics = new ArrayList<Comic>(totalComic);
			int k=0;
			for (Object[] aRow : listResult) {
				comics.add(k, (Comic) aRow[1]);
				k++;
			}
						
			model.addObject("comiclist", comics);
			model.addObject("totalpage", totalPage);
			model.addObject("pageselected", page);
			model.addObject("totalcomic", totalComic);
		}
		else {
			getModel.getHome(model);
		}
		return model;
	}
	
	@RequestMapping(value = "/customer/orderHistory", method = RequestMethod.GET)
	public ModelAndView getOrderHistory(HttpSession session, @RequestParam(name = "p", defaultValue = "1") int page) {
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") != null) {
			getModel.getOrderHistory(model);
			
			Long id = ((Users) session.getAttribute("account")).getId();
			List<Bill> bills = billService.getBillByUser(id, 0, 0);
			
			int totalPage = 0;
			int totalBill = 0;		
			
			totalBill = bills.size();
			totalPage = totalBill / 12;
			
			if(totalBill % 12 != 0){
				totalPage++;
			}
			
			bills = billService.getBillByUser(id, 10*(page-1), 10);
			
			model.addObject("bills", bills);
			model.addObject("totalpage", totalPage);
			model.addObject("pageselected", page);
			model.addObject("totalbill", totalBill);
		}
		else {
			getModel.getHome(model);
		}
		return model;
	}
	
	@RequestMapping(value = "/customer/orderDetail", method = RequestMethod.GET)
	public ModelAndView getOrderDetail(HttpSession session, @RequestParam(name = "id", defaultValue = "0") Long idBill) {
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") != null && billService.getBillByIdBillAndUser(idBill, ((Users) session.getAttribute("account")).getId()) != null) {
			getModel.getOrderDetail(model);
			model.addObject("bill", billService.get(idBill));
			model.addObject("billDetail", billDetailService.getBillDetailByIdBill(idBill));
			model.addObject("orderStatus", orderStatusService.getOrderStatusByIdBill(idBill));
			model.addObject("users", session.getAttribute("account"));
		}
		else {
			getModel.getHome(model);
		}
		return model;
	}	
}
