package com.website.springmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.AddressService;
import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.DistrictService;
import com.website.springmvc.Services.FavoriteListService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.config.MyConstants;
import com.website.springmvc.entities.Address;
import com.website.springmvc.entities.Bill;
import com.website.springmvc.entities.Comic;
import com.website.springmvc.entities.District;
import com.website.springmvc.entities.FavoriteList;
import com.website.springmvc.entities.Users;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.TripleDES;

@Controller
@RequestMapping(value="/controller")
public class CustomerController {
	@Autowired
	private UsersService userService;
	
	@Autowired
	private BillService billService; 
		
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private FavoriteListService favoriteListService;
	
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
			session.setAttribute("url", "customer/edit");
		}
		else {
			getModel.getHome(model, session);			
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
				u.setPassword(TripleDES.Encrypt(password, MyConstants.DES_KEY));
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
			List<FavoriteList> list = favoriteListService.getListByUser(u.getId(), 0, 0);
									
			int totalPage = 0;
			int totalComic = 0;		
			
			totalComic = list.size();
			totalPage = totalComic / 12;
			
			if(totalComic % 12 != 0){
				totalPage++;
			}	
			list = favoriteListService.getListByUser(u.getId(), 12*(page-1), 12);
									
			model.addObject("favoritelist", list);
			model.addObject("totalpage", totalPage);
			model.addObject("pageselected", page);
			model.addObject("totalcomic", totalComic);
			
			session.setAttribute("url", "customer/favoriteList?p=" + page);
		}
		else {
			getModel.getHome(model, session);
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
			
			session.setAttribute("url", "customer/orderHistory?p=" + page);
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}
	
	@RequestMapping(value = "/customer/orderDetail", method = RequestMethod.GET)
	public ModelAndView getOrderDetail(HttpSession session, @RequestParam(name = "id", defaultValue = "0") Long idBill) {
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") != null && billService.getBillByIdBillAndUser(idBill, ((Users) session.getAttribute("account")).getId()) != null) {
			getModel.getOrderDetail(model, idBill);
			
			model.addObject("users", session.getAttribute("account"));
			
			if(idBill != (long)0) {
				session.setAttribute("url", "customer/orderDetail?id=" + idBill);
			}
			else {
				session.setAttribute("url", "customer/orderHistory");
			}
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}	
	
	@RequestMapping(value = "/customer/addressBook", method = RequestMethod.GET)
	public ModelAndView getAddressBook(HttpSession session, 
										@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "") String alert) {
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") != null) {
			getModel.getAddressBook(model, session);
			model.addObject("mes", mes);
			model.addObject("alert", alert);
			
			session.setAttribute("url", "customer/addressBook");
		}
		else {
			getModel.getHome(model, session);			
		}
		return model;
	}
	
	@RequestMapping(value = "/customer/address", method = RequestMethod.GET)
	public ModelAndView getCreateAddress(HttpSession session, 
										@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "") String alert,
										@RequestParam(name = "mode", defaultValue = "add") String mode,
										@RequestParam(name = "id", defaultValue = "0") Long idAddress) {
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") != null) {
			getModel.getCreateAddress(model, session, mode, idAddress);
			model.addObject("mes", mes);
			model.addObject("alert", alert);
			model.addObject("mode", mode);			
			
			String str = "customer/address?mode=" + mode;
			
			if(mode.equalsIgnoreCase("edit")) {
				str += "&id=" + idAddress;
			}
			session.setAttribute("url", str);
		}
		else {
			getModel.getHome(model, session);			
		}
		return model;
	}
	
	@RequestMapping(value = "/customer/address", method = RequestMethod.POST)
	public String getSaveAddress(HttpSession session, Model model,
								@ModelAttribute("Address") Address address,
								@RequestParam(name = "mode", defaultValue = "add") String mode) {
		String str = "redirect:addressBook";
		if(address.getDistrict().getId() == (long)0 || address.getCity().getId() == (long)0) {
			str = "redirect:add-address";
			model.addAttribute("mes", "Thêm thất bại");
			model.addAttribute("alert", "danger");
		}
		else {
			Users u = (Users) session.getAttribute("account");
			address.setIdUser(u);
			
			if(addressService.getDefaultAddressByUser(u.getId()) == null) {
				address.setChoose((byte) 1);
			}
			else if(address.getChoose() == 1){
				Address add = addressService.getDefaultAddressByUser(u.getId());
				add.setChoose((byte) 0);
				addressService.update(add);
			}
			
			if(mode.equalsIgnoreCase("add")) {
				addressService.add(address);
				model.addAttribute("mes", "Thêm thành công");
			}
			else if(mode.equalsIgnoreCase("edit")) {
				addressService.update(address);
				model.addAttribute("mes", "Sửa thành công");
			}			
			
			model.addAttribute("alert", "success");
		}
		return str;
	}
	
	@RequestMapping(value = "/customer/getDistrict", method = RequestMethod.GET)
	public void getDistrictByCity(@RequestParam(name = "city") Long idCity, HttpServletResponse response) {
		String str = "<option value=\"0\" selected>Chọn Quận / Huyện</option>";
		
		if(idCity != (long) 0) {
			List<District> district = districtService.getDistrictByCity(idCity);
			for (int i = 0; i < district.size(); i++) {
				str += "<option value=\"" + district.get(i).getId() + "\">" + district.get(i).getName() + "</option>";
			}
		}
		
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/customer/removeAddress", method = RequestMethod.POST)
	public void getRemoveAddress(@RequestParam(name = "id") Long idAddress, HttpServletResponse response) {
		String str = "success";
		
		if(addressService.get(idAddress) != null) {
			addressService.delete(idAddress);
		}
		else {
			str = "fail";
		}
		
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
