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
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.entities.Author;
import com.website.springmvc.entities.Category;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.RemoveAccent;
@Controller
@RequestMapping(value = "/controller")
public class CategoryAdminController {
	
	@Autowired
	CategoryService cateService;
	
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = "/cateAdmin", method = RequestMethod.GET)
	public ModelAndView getAuthorAdmin(@RequestParam(name = "mes", defaultValue = "") String mes,
									@RequestParam(name = "alert", defaultValue = "") String alert,
									@RequestParam(name = "p", defaultValue = "1") int page,
									@RequestParam(name = "q", defaultValue = "") String key, HttpSession session) {
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			session.setAttribute("url", "cateAdmin?p=" + page + "&q=" + key);
			getModel.getLayoutAdmin(model, session);
			model.addObject("views","CategoryAdmin");
			model.addObject("title","Danh sách danh mục");
			if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
				model.addObject("title","List of Categories");
			}
			
			List<Category> categories = cateService.getListCategories(key, 0, 0);
	
			int totalPage = 0;
			int totalCate = 0;		
	
			totalCate = categories.size();
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
	
			categories = cateService.getListCategories(key, 10*(page-1), 10);
	
			model.addObject("categories", categories);
			model.addObject("totalpage", totalPage);
			model.addObject("pageselected", page);
			model.addObject("key", key);
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String saveAuthor(HttpSession session, Model model,
							@RequestParam("name") String name,
							@RequestParam(name = "mode", defaultValue = "add") String mode,
							@RequestParam(name = "id", defaultValue = "0") Long idCate){
		String str = "redirect:cateAdmin";
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			if(mode.equalsIgnoreCase("add")) {
				Category category = new Category();
				category.setName(name);
				category.setUnsignedName(RemoveAccent.changeTitle(name));
				cateService.add(category);
				model.addAttribute("mes", "Thêm thành công");
				model.addAttribute("alert", "success");
			}
			else if(mode.equalsIgnoreCase("edit")) {
				if(cateService.get(idCate) == null) {
					model.addAttribute("mes", "ID không tồn tại");
					model.addAttribute("alert", "danger");
				}
				else{
					Category category = cateService.get(idCate);
					category.setName(name);
					category.setUnsignedName(RemoveAccent.changeTitle(name));
					cateService.update(category);
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

	@RequestMapping(value = "/showHideCate", method = RequestMethod.POST)
	public String showHide(HttpSession session, Model model,								
							@RequestParam(name = "id", defaultValue = "0") Long idCate) {
		String str = "redirect:cateAdmin";
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			if(cateService.get(idCate) != null) {
				Category category = cateService.get(idCate);
				if(category.getStatus() == 0){
					category.setStatus((byte) 1);
				}
				else if(category.getStatus() == 1){
					category.setStatus((byte) 0);
				}
				cateService.update(category);
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

	@RequestMapping(value = "/removeCate", method = RequestMethod.POST)
	public void removeAddress(@RequestParam(name = "id") Long idCate, HttpServletResponse response, HttpSession session) {
		String str = "success";
		boolean f = getModel.checkAdmin(session);
		
		if(f && cateService.get(idCate) != null) {
			cateService.delete(idCate);
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
