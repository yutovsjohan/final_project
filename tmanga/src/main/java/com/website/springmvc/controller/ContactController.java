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
	public ModelAndView getContactAdmin(@RequestParam(name = "p", defaultValue = "1") int page) {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/layoutAdmin");
		model.addObject("views","ContactAdmin");
		model.addObject("title","Danh sách các tin nhắn");
		
		model.addObject("allMess", contactService.getAllMessage());
		model.addObject("unView", contactService.getUnReadMessage());
		model.addObject("userNum",userService.getUserNum());
		
		List<Contact> contacts = contactService.getAll(0, 0);
		
		int totalPage = 0;
		int totalContact = 0;		

		totalContact = contacts.size();
		totalPage = totalContact / 10;

		if(totalContact % 10 != 0){
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

		contacts = contactService.getAll(10*(page-1), 10);

		model.addObject("authors", contacts);
		model.addObject("totalpage", totalPage);
		model.addObject("pageselected", page);
		model.addObject("contacts", contacts);
		return model;
	}
	
	@RequestMapping(value = "/contactDetail", method = RequestMethod.GET)
	public ModelAndView getContactDetail(@RequestParam(name = "id", defaultValue = "0") Long idContact) {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/layoutAdmin");
		model.addObject("views","ContactDetail");
		model.addObject("title","Chi tiết tin nhắn");
		
		if(contactService.get(idContact) != null) {
			Contact contact = contactService.get(idContact);
			contact.setView((byte) 1);
			contactService.update(contact);
			model.addObject("contact", contact);
		}
		
		return model;
	}
	
	@RequestMapping(value = "/answer", method = RequestMethod.POST)
	public String AnswerMessage (@RequestParam(name = "id", defaultValue = "0") Long idContact, Model model,
								@RequestParam(name = "mes", defaultValue = "") String mes,
								@RequestParam(name = "alert", defaultValue = "") String alert,
								@RequestParam(name = "answer", defaultValue = "") String answer,
								@RequestParam(name = "email", defaultValue = "") String email) {
		String str = "redirect:contactDetail?id=" + idContact;
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
		return str;
	}
}
