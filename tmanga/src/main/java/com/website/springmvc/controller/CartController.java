package com.website.springmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.BilldetailService;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.OrderstatusService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.config.MyConstants;
import com.website.springmvc.entities.bill;
import com.website.springmvc.entities.billdetail;
import com.website.springmvc.entities.comic;
import com.website.springmvc.entities.orderstatus;
import com.website.springmvc.entities.users;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.cart;
import com.website.springmvc.libs.item;

@Controller
@RequestMapping(value="/controller")
public class CartController {
	@Autowired
	GetModel getModel;
	
	@Autowired
	ComicService comicService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	BilldetailService billDetailService;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	OrderstatusService orderStatusService;
	
	/*@Autowired
    public JavaMailSender emailSender;
 
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String sendHtmlEmail() {
 
    	MimeMessage message = emailSender.createMimeMessage();
    	 
        boolean multipart = true;
         
        MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, multipart, "utf-8");
			String htmlMsg = "<h3>Im testing send a HTML email</h3>"
	                +"<img src='http://www.apache.org/images/asf_logo_wide.gif'>";
	         
	        message.setContent(htmlMsg, "text/html");
	         
	        helper.setTo(MyConstants.FRIEND_EMAIL);
	         
	        helper.setSubject("Test send HTML email");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
     
        this.emailSender.send(message);
 
        return "Email Sent!";
    } */
	
	@RequestMapping(value = { "/cart", "/gio-hang" }, method = RequestMethod.GET)
	public ModelAndView getCartPage(@RequestParam(name = "alert", defaultValue = "") String alert, 
									@RequestParam(name = "mes", defaultValue = "") String mes){
		ModelAndView model = new ModelAndView();		
		if(!mes.equalsIgnoreCase("")) {
			model.addObject("mes",mes);
			model.addObject("alert",alert);
		}
		getModel.getCart(model);		
		return model;	
	}	
	
	@RequestMapping(value = "/addtocart", method = RequestMethod.GET)
	public void getAddToCart(@RequestParam("id") int idComic, 
							@RequestParam(name = "amount", defaultValue = "1") int amount,
							@RequestParam(name = "action", defaultValue = "add") String action,
							HttpSession session, HttpServletResponse response) {
		String str = "";
		
		if(amount < 0) {
			str = "Số lượng không thể âm";
		}
		else {
			comic comic = comicService.get(idComic);
			cart cart = new cart();
			int amountInCart = 0;
			boolean f = false;
			
			if(session.getAttribute("cart") != null) {
				ArrayList<item> items =  (ArrayList<item>) ((cart) session.getAttribute("cart")).getList();
				HashMap<Integer, item> hashcart = new HashMap<>();
				for (int i = 0; i < items.size(); i++) {
					hashcart.put(items.get(i).getComic().getId(), items.get(i));
					if(items.get(i).getComic().getId() == idComic) {
						f = true;
					}
				}
				cart.setCart(hashcart);
				if(f) {
					amountInCart = cart.getItemForId(idComic).getAmount();
				}
			}					
			
			if(comic.getAmount() == 0) {
				cart.delete(idComic);
				str = "Truyện này tạm hết hàng";
			}
			else {	
				if(action.equalsIgnoreCase("add")) {
					if( amount + amountInCart > comic.getAmount()) {
						str = comic.getName() + " cần số lượng tối đa được phép mua là " + comic.getAmount();
					}
					else if(amount != 0){
						cart.add(idComic, comic, amount);
						session.setAttribute("cart", cart);	
						str = "added";
					}
				}
				
				else {
					if(amount == 0) {
						cart.delete(idComic);
						str = "removed";
					}
					else if( amount > comic.getAmount() || amount < 0) {
						str = comic.getName() + " cần số lượng tối đa được phép mua là " + comic.getAmount();
					}
					else {
						amount -= cart.getItemForId(idComic).getAmount();
						cart.add(idComic, comic, amount);
						str = "updated";
					}
					
					if(cart.quantity() == 0) {
						session.removeAttribute("cart");
					}
					else {
						session.setAttribute("cart", cart);
					}
				}						
			}				
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
	
	@RequestMapping(value = "/removeitem", method = RequestMethod.GET)
	public void getRemoveItem(@RequestParam("id") int idComic, HttpSession session, HttpServletResponse response){
		String str = "";
		if(session.getAttribute("cart") != null) {
			cart cart = (cart) session.getAttribute("cart");
			cart.delete(idComic);
			if(cart.quantity() == 0) {
				session.removeAttribute("cart");
			}
			else {
				session.setAttribute("cart", cart);
			}
			str = "removed";
		}
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/deletecart", method = RequestMethod.GET)
	public String getDeteleCart(HttpSession session){
		session.removeAttribute("cart");
		return "redirect:/controller/cart";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String getOrder(Model model, HttpSession session,
						@RequestParam(name = "note", defaultValue = "") String note){
		boolean f = true;
		
		if(session.getAttribute("cart") != null && session.getAttribute("account") != null) {
			ArrayList<item> items =  (ArrayList<item>) ((cart) session.getAttribute("cart")).getList();
			
			for (int i = 0; i < items.size(); i++) {
				if(items.get(i).getComic().getAmount() < items.get(i).getAmount()) {
					f = false;
					break;
				}
			}
			
			if(f) {
				bill bill = new bill();
				bill.setIdUser( (users) session.getAttribute("account"));
				bill.setTotal(((cart) session.getAttribute("cart")).total() + 15000 );
				bill.setStatus("Chưa xác nhận đơn hàng");
				bill.setDelivery(usersService.get(2));
				billService.add(bill);
										
				
				billdetail billdetail = new billdetail();
				comic comic = new comic();
				
				for (int i = 0; i < items.size(); i++) {
					comic = items.get(i).getComic();
					
					billdetail.setBill(bill);				
					billdetail.setComic(comic);
					billdetail.setAmount((byte) items.get(i).getAmount());
					billdetail.setPrice(items.get(i).getComic().getPrice());
					billDetailService.add(billdetail);
					
					comic.setQuantitySold(comic.getQuantitySold() + items.get(i).getAmount());
					comic.setAmount(comic.getAmount() - items.get(i).getAmount());
					comicService.update(comic);
				}
				
				orderstatus orderstatus = new orderstatus();
				orderstatus.setContent("Đặt hàng thành công");
				orderstatus.setNote("kh");
				orderstatus.setBill(bill);
				orderStatusService.add(orderstatus);
				
				f = true;
				session.removeAttribute("cart");				
			}			
		}
		else {
			f = false;
		}
		
		
		if(f) {
			model.addAttribute("alert","success");
			model.addAttribute("mes","Đặt hàng thành công");
		}
		else {
			model.addAttribute("alert","danger");
			model.addAttribute("mes","Đặt hàng thất bại");
		}		
		
		return "redirect:/controller/cart";
	}

}
