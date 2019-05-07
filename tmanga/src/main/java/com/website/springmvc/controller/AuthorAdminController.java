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
import com.website.springmvc.entities.Author;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.RemoveAccent;
import com.website.springmvc.Services.AuthorService;;

@Controller
@RequestMapping(value = "/controller")
public class AuthorAdminController {
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = "/authorAdmin", method = RequestMethod.GET)
	public ModelAndView getAuthorAdmin(@RequestParam(name = "mes", defaultValue = "") String mes,
									@RequestParam(name = "alert", defaultValue = "") String alert,
									@RequestParam(name = "p", defaultValue = "1") int page,
									@RequestParam(name = "q", defaultValue = "") String key, HttpSession session) {
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			session.setAttribute("url", "authorAdmin?p=" + page + "&q=" + key);
			getModel.getLayoutAdmin(model, session);
			model.addObject("views","AuthorAdmin");
			model.addObject("title","Danh sách tác giả");
			if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
				model.addObject("title","List of Authors");
			}
			
			List<Author> authors = authorService.getListAuthors(key, 0, 0);
	
			int totalPage = 0;
			int totalAuthors = 0;		
	
			totalAuthors = authors.size();
			totalPage = totalAuthors / 10;
	
			if(totalAuthors % 10 != 0){
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
	
			authors = authorService.getListAuthors(key, 10*(page-1), 10);
	
			model.addObject("authors", authors);
			model.addObject("totalpage", totalPage);
			model.addObject("pageselected", page);
			model.addObject("key", key);
			
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}
	
	@RequestMapping(value = "/author", method = RequestMethod.POST)
	public String saveAuthor(HttpSession session, Model model,
							@RequestParam("name") String name,
							@RequestParam(name = "mode", defaultValue = "add") String mode,
							@RequestParam(name = "id", defaultValue = "0") Long idAuthor){
		String str = "redirect:authorAdmin";
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			if(mode.equalsIgnoreCase("add")) {
				Author author = new Author();
				author.setName(name);
				author.setUnsignedName(RemoveAccent.changeTitle(name));
				authorService.add(author);
				model.addAttribute("mes", "Thêm thành công");
				model.addAttribute("alert", "success");
			}
			else if(mode.equalsIgnoreCase("edit")) {
				if(authorService.get(idAuthor) == null) {
					model.addAttribute("mes", "ID không tồn tại");
					model.addAttribute("alert", "danger");
				}
				else{
					Author author = authorService.get(idAuthor);
					author.setName(name);
					author.setUnsignedName(RemoveAccent.changeTitle(name));
					authorService.update(author);
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

	@RequestMapping(value = "/showHideAuthor", method = RequestMethod.POST)
	public String showHide(HttpSession session, Model model,								
							@RequestParam(name = "id", defaultValue = "0") Long idAuthor) {
		String str = "redirect:authorAdmin";
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			if(authorService.get(idAuthor) != null) {
				Author author = authorService.get(idAuthor);
				if(author.getStatus() == 0){
					author.setStatus((byte) 1);
				}
				else if(author.getStatus() == 1){
					author.setStatus((byte) 0);
				}
				authorService.update(author);
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

	@RequestMapping(value = "/removeAuthor", method = RequestMethod.POST)
	public void removeAddress(@RequestParam(name = "id") Long idAuthor, HttpServletResponse response,
								HttpSession session) {
		String str = "success";
		boolean f = getModel.checkAdmin(session);
		
		
		if(f && authorService.get(idAuthor) != null) {
			authorService.delete(idAuthor);
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
