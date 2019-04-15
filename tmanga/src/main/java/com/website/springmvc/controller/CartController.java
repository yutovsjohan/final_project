package com.website.springmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.website.springmvc.Services.CartDetailService;
import com.website.springmvc.Services.CartService;
import com.website.springmvc.Services.BillDetailService;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.OrderStatusService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.config.MyConstants;
import com.website.springmvc.entities.Bill;
import com.website.springmvc.entities.BillDetail;
import com.website.springmvc.entities.Cart;
import com.website.springmvc.entities.CartDetail;
import com.website.springmvc.entities.Comic;
import com.website.springmvc.entities.OrderStatus;
import com.website.springmvc.entities.Users;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.GioHang;
import com.website.springmvc.libs.Item;

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
	BillDetailService billDetailService;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	OrderStatusService orderStatusService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CartDetailService cartDetailService;
	
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
									@RequestParam(name = "mes", defaultValue = "") String mes,
									HttpSession session){
		ModelAndView model = new ModelAndView();		
		if(!mes.equalsIgnoreCase("")) {
			model.addObject("mes",mes);
			model.addObject("alert",alert);
		}
		getModel.getCart(model);
		if(session.getAttribute("account") != null) {	
			Long idUser = ((Users) session.getAttribute("account")).getId();
			if(checkCart(idUser) != 0) {
				List<CartDetail> listCartDetail = cartDetailService.getDetailByIdCart(checkCart(idUser));
				
				if(listCartDetail.size() != 0) {
					Item item;
					GioHang gioHang = new GioHang();
					HashMap<Long, Item> hashCart = new HashMap<>();
					
					for (int i = 0; i < listCartDetail.size(); i++) {
						item = new Item();
						item.setComic(listCartDetail.get(i).getComic());
						item.setAmount(listCartDetail.get(i).getAmount());
						hashCart.put(listCartDetail.get(i).getComic().getId(), item);
					}
					gioHang.setCart(hashCart);
					session.removeAttribute("cart");
					session.setAttribute("cart", gioHang);
				}
			}
		}
			
		return model;	
	}	
	
	@RequestMapping(value = "/addtocart", method = RequestMethod.GET)
	public void getAddToCart(@RequestParam("id") Long idComic, 
							@RequestParam(name = "amount", defaultValue = "1") int amount,
							@RequestParam(name = "action", defaultValue = "add") String action,
							HttpSession session, HttpServletResponse response) {
		String str = "";
		
		if(amount < 0) {
            str = "Số lượng không thể âm";
		}
		else {
			Comic comic = comicService.get(idComic);
			GioHang gioHang = new GioHang();
			int amountInCart = 0;
			boolean f = false;
			
			if(session.getAttribute("cart") != null) {
				ArrayList<Item> items =  (ArrayList<Item>) ((GioHang) session.getAttribute("cart")).getList();
				HashMap<Long, Item> hashCart = new HashMap<>();
				for (int i = 0; i < items.size(); i++) {
					hashCart.put(items.get(i).getComic().getId(), items.get(i));
					if(items.get(i).getComic().getId() == idComic) {
						f = true;
					}
				}
				gioHang.setCart(hashCart);
				if(f) {
					amountInCart = gioHang.getItemForId(idComic).getAmount();
				}
			}					
			
			if(comic.getAmount() == 0) {
				gioHang.delete(idComic);
                str = "Truyện này tạm hết hàng";
			}
			else {	
				if(action.equalsIgnoreCase("add")) {
					if( amount + amountInCart > comic.getAmount()) {
                        str = comic.getName() + " cần số lượng tối đa được phép mua là " + comic.getAmount();
					}
					else if(amount != 0){
						gioHang.add(idComic, comic, amount);
						session.setAttribute("cart", gioHang);	
						str = "added";						
					}
				}
				
				else {
					if(amount == 0) {
						gioHang.delete(idComic);
						str = "removed";
					}
					else if( amount > comic.getAmount() || amount < 0) {
                        str = comic.getName() + " cần số lượng tối đa được phép mua là " + comic.getAmount();
					}
					else {
						amount -= gioHang.getItemForId(idComic).getAmount();
						gioHang.add(idComic, comic, amount);
						str = "updated";
					}
					
					if(gioHang.quantity() == 0) {
						if(session.getAttribute("cart") != null && session.getAttribute("account") != null){
							Long idUser = ((Users) session.getAttribute("account")).getId();
							Cart cart = cartService.getCartByUser(idUser);
							CartDetail cartDetail = cartDetailService.getDetailByCartAndProduct(cart.getId(), idComic);
							
							cartDetailService.delete(cartDetail.getId());
						}
						session.removeAttribute("cart");
					}
					else {
						session.setAttribute("cart", gioHang);						
					}										
				}	
				
				if(session.getAttribute("cart") != null && session.getAttribute("account") != null){
					Cart cart = new Cart();
					Long idUser = ((Users) session.getAttribute("account")).getId();
					if(cartService.getCartByUser(idUser) != null) {
						cart = cartService.getCartByUser(idUser);
					}
					else { 
						cart.setIdUser(((Users) session.getAttribute("account")));
						cartService.add(cart);
					}											
					
					CartDetail cartDetail = new CartDetail();
					
					ArrayList<Item> items =  (ArrayList<Item>) ((GioHang) session.getAttribute("cart")).getList();
					
					List<CartDetail> listCartDetail = cartDetailService.getDetailByIdCart(cart.getId());
					for(int i = 0; i < listCartDetail.size(); i++) {
						cartDetailService.delete(listCartDetail.get(i).getId());
					}
					
					for (int i = 0; i < items.size(); i++) {	
//						if(cartDetailService.getDetailByCartAndProduct(cart.getId(), idComic) != null) {
//							cartDetail = cartDetailService.getDetailByCartAndProduct(cart.getId(), idComic);
//							cartDetail.setAmount((byte)items.get(i).getAmount());
//							cartDetailService.update(cartDetail);
//						}
//						else {
//							cartDetail.setCart(cart);
//							cartDetail.setComic(items.get(i).getComic());
//							cartDetail.setAmount((byte)items.get(i).getAmount());
//							cartDetailService.add(cartDetail);
//						}	
						
						cartDetail.setCart(cart);
						cartDetail.setComic(items.get(i).getComic());
						cartDetail.setAmount((byte)items.get(i).getAmount());
						cartDetailService.add(cartDetail);
						
//						if(cartDetailService.getDetailByCartAndProduct(cart.getId(), idComic) != null) {
//							cartDetailService.update(cartDetail);
//						}
//						else {
//							cartDetailService.add(cartDetail);
//						}
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
	public void getRemoveItem(@RequestParam("id") Long idComic, HttpSession session, HttpServletResponse response){
		String str = "";
		if(session.getAttribute("cart") != null) {
			if(session.getAttribute("cart") != null && session.getAttribute("account") != null){
				Long idUser = ((Users) session.getAttribute("account")).getId();
				Cart cart = cartService.getCartByUser(idUser);
				CartDetail cartDetail = cartDetailService.getDetailByCartAndProduct(cart.getId(), idComic);
				cartDetailService.delete(cartDetail.getId());
			}
			
			GioHang gioHang = (GioHang) session.getAttribute("cart");
			gioHang.delete(idComic);
			if(gioHang.quantity() == 0) {
				session.removeAttribute("cart");
			}
			else {
				session.setAttribute("cart", gioHang);
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
		if(session.getAttribute("cart") != null && session.getAttribute("account") != null){
			Long idUser = ((Users) session.getAttribute("account")).getId();
			Cart cart = cartService.getCartByUser(idUser);
			List<CartDetail> listCartDetail = cartDetailService.getDetailByIdCart(cart.getId());
			for(int i = 0; i < listCartDetail.size(); i++) {
				cartDetailService.delete(listCartDetail.get(i).getId());
			}
		}
		session.removeAttribute("cart");
		return "redirect:/controller/cart";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String getOrder(Model model, HttpSession session,
						@RequestParam(name = "note", defaultValue = "") String note){
		boolean f = true;
		
		if(session.getAttribute("cart") != null && session.getAttribute("account") != null) {
			ArrayList<Item> items =  (ArrayList<Item>) ((GioHang) session.getAttribute("cart")).getList();
			
			for (int i = 0; i < items.size(); i++) {
				if(items.get(i).getComic().getAmount() < items.get(i).getAmount()) {
					f = false;
					break;
				}
			}
			
			if(f) {
				Bill bill = new Bill();
				bill.setIdUser( (Users) session.getAttribute("account"));
				bill.setTotal(((GioHang) session.getAttribute("cart")).total() + 15000 );
                bill.setStatus("Chưa xác nhận đơn hàng");
				bill.setDelivery(usersService.get(2));
				billService.add(bill);
										
				
				BillDetail Billdetail = new BillDetail();
				Comic comic = new Comic();
				
				for (int i = 0; i < items.size(); i++) {
					comic = items.get(i).getComic();
					
					Billdetail.setBill(bill);				
					Billdetail.setComic(comic);
					Billdetail.setAmount((byte) items.get(i).getAmount());
					Billdetail.setPrice(items.get(i).getComic().getPrice());
					billDetailService.add(Billdetail);
					
					comic.setQuantitySold(comic.getQuantitySold() + items.get(i).getAmount());
					comic.setAmount(comic.getAmount() - items.get(i).getAmount());
					comicService.update(comic);
				}
				
				OrderStatus orderstatus = new OrderStatus();
                orderstatus.setContent("Đặt hàng thành công");
				orderstatus.setNote("kh");
				orderstatus.setBill(bill);
				orderStatusService.add(orderstatus);
				
				f = true;
				
				Long idUser = ((Users) session.getAttribute("account")).getId();
				Cart cart = cartService.getCartByUser(idUser);
				List<CartDetail> listCartDetail = cartDetailService.getDetailByIdCart(cart.getId());
				for(int i = 0; i < listCartDetail.size(); i++) {
					cartDetailService.delete(listCartDetail.get(i).getId());
				}
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
	
	private long checkCart(Long iduser) {
		long i = 0;
		if(cartService.getCartByUser(iduser) != null ) {
			i = cartService.getCartByUser(iduser).getId();
		}
		return i;
	}

}
