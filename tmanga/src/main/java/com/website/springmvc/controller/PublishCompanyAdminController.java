package com.website.springmvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.PublishCompanyService;
import com.website.springmvc.entities.PublishCompany;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.RemoveAccent;;
@Controller
@RequestMapping(value = "/controller")
public class PublishCompanyAdminController {
	
	@Autowired
	private PublishCompanyService pubcomService;
	
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = "/pubcomAdmin", method = RequestMethod.GET)
	public ModelAndView getAuthorAdmin(@RequestParam(name = "mes", defaultValue = "") String mes,
									@RequestParam(name = "alert", defaultValue = "") String alert,
									@RequestParam(name = "p", defaultValue = "1") int page,
									@RequestParam(name = "q", defaultValue = "") String key, HttpSession session) {
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			session.setAttribute("url", "pubcomAdmin?p=" + page + "&q=" + key);
			getModel.getLayoutAdmin(model, session);
			model.addObject("views","PublishingAdmin");
			model.addObject("title","Danh sách nhà xuất bản");
			if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
				model.addObject("title","List of Publishing Companies");
			}
			
			List<PublishCompany> pubComs = pubcomService.getListPublishCompany(key, 0, 0);
	
			int totalPage = 0;
			int totalCate = 0;		
	
			totalCate = pubComs.size();
			totalPage = totalCate / 10;
	
			if(totalCate % 10 != 0){
				totalPage++;
			}		
	
			int start = 1, end = 7;
	
			if(totalPage > 7){		
				if(page - 3 > 0){	
					if(page + 3 >= totalPage){	
						start =  totalPage - 6;
						end = totalPage;
					}	
					else{	
						start = page - 3;
						end = page + 3;
					}	
				}	
			}
			else {
				end = totalPage;
			}
	
			model.addObject("start", start);
			model.addObject("end", end);
	
			pubComs = pubcomService.getListPublishCompany(key, 10*(page-1), 10);
	
			model.addObject("PubComs", pubComs);
			model.addObject("totalpage", totalPage);
			model.addObject("pageselected", page);
			model.addObject("key", key);
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}
	
	@RequestMapping(value = "/publishCompany", method = RequestMethod.POST)
	public String saveAuthor(HttpSession session, Model model,
							@RequestParam("name") String name,
							@RequestParam(name = "mode", defaultValue = "add") String mode,
							@RequestParam(name = "id", defaultValue = "0") Long idPC){
		String str = "redirect:pubcomAdmin";
		boolean f = getModel.checkAdmin(session);
		
		if(f) {			
			if(mode.equalsIgnoreCase("add")) {
				PublishCompany publishCompany = new PublishCompany();
				publishCompany.setName(name);
				publishCompany.setUnsignedName(RemoveAccent.changeTitle(name));
				pubcomService.add(publishCompany);
				model.addAttribute("mes", "Thêm thành công");
				model.addAttribute("alert", "success");
			}
			else if(mode.equalsIgnoreCase("edit")) {
				if(pubcomService.get(idPC) == null) {
					model.addAttribute("mes", "ID không tồn tại");
					model.addAttribute("alert", "danger");
				}
				else{
					PublishCompany publishCompany = pubcomService.get(idPC);
					publishCompany.setName(name);
					publishCompany.setUnsignedName(RemoveAccent.changeTitle(name));
					pubcomService.update(publishCompany);
					model.addAttribute("mes", "Sửa thành công");
					model.addAttribute("alert", "success");
				}
			}
		}
		else {
			str = "redirect:index";
		}
		return str;
	}

	@RequestMapping(value = "/showHidePublishing", method = RequestMethod.POST)
	public String showHide(HttpSession session, Model model,								
							@RequestParam(name = "id", defaultValue = "0") Long idPC) {
		String str = "redirect:pubcomAdmin";
		boolean f = getModel.checkAdmin(session);
		
		if(f) {	
			if(pubcomService.get(idPC) != null) {
				PublishCompany publishCompany = pubcomService.get(idPC);
				if(publishCompany.getStatus() == 0){
					publishCompany.setStatus((byte) 1);
				}
				else if(publishCompany.getStatus() == 1){
					publishCompany.setStatus((byte) 0);
				}
				pubcomService.update(publishCompany);
				model.addAttribute("mes", "Cập nhật thành công");
				model.addAttribute("alert", "success");
			}
			else{
				model.addAttribute("mes", "ID không tồn tại");
				model.addAttribute("alert", "danger");
			}
		}
		else {
			str = "redirect:index";
		}
		return str;
	}

	@RequestMapping(value = "/removePublishing", method = RequestMethod.POST)
	public void removeAddress(@RequestParam(name = "id") Long idPC, HttpServletResponse response, HttpSession session) {
		String str = "success";
		boolean f = getModel.checkAdmin(session);
		if(f && pubcomService.get(idPC) != null) {
			pubcomService.delete(idPC);
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
