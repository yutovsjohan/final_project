package com.website.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.ContactService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.config.MyConstants;
import com.website.springmvc.entities.Contact;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.SendEmail;

@Controller
@RequestMapping(value="/controller")
public class ContactController {
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	GetModel getModel;
	
	@RequestMapping(value = {"/lien-he", "/contact"}, method = RequestMethod.GET)
	public ModelAndView getContactPage(@RequestParam(name = "mes", defaultValue = "") String mes,
									@RequestParam(name = "alert", defaultValue = "") String alert,
									HttpSession session){		
		ModelAndView model = new ModelAndView();
		model.addObject("mes", mes);
		model.addObject("alert", alert);
		getModel.getContact(model);		
		
		String str = "contact";
		if(!mes.equalsIgnoreCase("")) {
			str += "?mes=" + mes + "&alert=" + alert;
		}
		session.setAttribute("url", str);
		return model;
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String getContact(@RequestParam("email") String email, 
							@RequestParam("title") String title,
							@RequestParam("content") String content, Model model){		
		Contact contact = new Contact();
		contact.setSender(email);
		contact.setContent(content);
		contact.setTitle(title);
		contactService.add(contact);
		model.addAttribute("mes","Gửi thành công");
		model.addAttribute("alert","success");
		return "redirect:/controller/contact";
	}
	
	@RequestMapping(value = "/contactAdmin", method = RequestMethod.GET)
	public ModelAndView getContactAdmin(@RequestParam(name = "p", defaultValue = "1") int page, HttpSession session,
										@RequestParam(name = "q", defaultValue = "") String key) {
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			session.setAttribute("url", "contactAdmin?p=" + page + "&q=" + key);
			getModel.getContactAdminPage(model, page, key, session);
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}
	
	@RequestMapping(value = "/contactDetail", method = RequestMethod.GET)
	public ModelAndView getContactDetail(@RequestParam(name = "id", defaultValue = "0") Long idContact, HttpSession session) {
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		if(f) {						
			if(idContact == (long) 0 || contactService.get(idContact) == null) {
				getModel.getContactAdminPage(model, 1, "", session);
			}
			else {
				session.setAttribute("url", "contactDetail?id=" + idContact);
				getModel.getContactDetail(model, idContact, session);
			}
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}
	
	@RequestMapping(value = "/answer", method = RequestMethod.POST)
	public String AnswerMessage (@RequestParam(name = "id", defaultValue = "0") Long idContact, Model model,
								@RequestParam(name = "mes", defaultValue = "") String mes,
								@RequestParam(name = "alert", defaultValue = "") String alert,
								@RequestParam(name = "answer", defaultValue = "") String answer,
								@RequestParam(name = "email", defaultValue = "") String email, HttpSession session) {
		String str = "redirect:contactDetail?id=" + idContact;
		boolean f = getModel.checkAdmin(session);
		if(f) {
			if(contactService.get(idContact) != null) {
				Contact contact = contactService.get(idContact);
				contact.setStatus((byte) 1);
				contactService.update(contact);
				model.addAttribute("mes","Gửi thành công");
				model.addAttribute("alert","success");
				
				SendEmail.sendGrid(MyConstants.EMAIL, email, "Trả lời thắc mắc" , answer, true);
			}
			else {
				model.addAttribute("mes","Gửi thất bại");
				model.addAttribute("alert","fail");
			}
		}
		else {
			str = "redirect:index";
		}
		return str;
	}
}
