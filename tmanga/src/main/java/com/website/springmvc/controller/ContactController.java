package com.website.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.ContactService;
import com.website.springmvc.entities.Contact;
import com.website.springmvc.libs.GetModel;

@Controller
@RequestMapping(value="/controller")
public class ContactController {
	@Autowired
	private ContactService contactService;
	
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
}
