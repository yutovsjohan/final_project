package com.website.springmvc.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.CartDetailService;
import com.website.springmvc.Services.CartService;
import com.website.springmvc.Services.AddressService;
import com.website.springmvc.Services.BillDetailService;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.OrderStatusService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.Address;
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
import com.website.springmvc.libs.SendEmail;

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
	
	@Autowired
	AddressService addressService;
		
	@RequestMapping(value = { "/cart", "/gio-hang" }, method = RequestMethod.GET)
	public ModelAndView getCartPage(@RequestParam(name = "alert", defaultValue = "") String alert, 
									@RequestParam(name = "mes", defaultValue = "") String mes,
									HttpSession session){
		ModelAndView model = new ModelAndView();		
		
		model.addObject("mes",mes);
		model.addObject("alert",alert);
		
		getModel.getCart(model);
		if(session.getAttribute("account") != null) {	
			Long idUser = ((Users) session.getAttribute("account")).getId();
			if(checkCart(idUser) != 0) {
				if(countCartDetailByCart(checkCart(idUser)) != null) {
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
		}
		session.setAttribute("url", "cart");
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
					if((long) items.get(i).getComic().getId() == (long) idComic) {
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
						//amount -= gioHang.getItemForId(idComic).getAmount();
						gioHang.setAmount(idComic, comic, amount);
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
						cartDetail.setCart(cart);
						cartDetail.setComic(items.get(i).getComic());
						cartDetail.setAmount((byte)items.get(i).getAmount());
						cartDetailService.add(cartDetail);						
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
	
	@RequestMapping(value = "/shipping", method = RequestMethod.GET)
	public ModelAndView getShippingPage(HttpSession session,
									@RequestParam(name = "mes", defaultValue = "") String mes,
									@RequestParam(name = "alert", defaultValue = "") String alert){
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") == null) {
			getModel.getHome(model, session);
		}
		else {
			getModel.getShippingPage(model, (Users)session.getAttribute("account"));
			session.setAttribute("url", "shipping");
		}
		return model;
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public ModelAndView getPaymentPage(HttpSession session,
									@RequestParam(name = "mes", defaultValue = "") String mes,
									@RequestParam(name = "alert", defaultValue = "") String alert,
									@RequestParam(name = "id", defaultValue = "0") Long idAddress){
		ModelAndView model = new ModelAndView();
		if(session.getAttribute("account") == null || idAddress == (long) 0) {
			getModel.getHome(model, session);
		}
		else {
			getModel.getPaymentPage(model, idAddress, session);
			session.setAttribute("url", "payment?id=" + idAddress);
		}
		return model;
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String getOrder(Model model, HttpSession session){
		boolean f = true;
		
		if(session.getAttribute("cart") != null && session.getAttribute("account") != null) {
			ArrayList<Item> items =  (ArrayList<Item>) ((GioHang) session.getAttribute("cart")).getList();
			
			for (int i = 0; i < items.size(); i++) {
				if(items.get(i).getComic().getAmount() < items.get(i).getAmount()) {
					f = false;
					break;
				}
			}
			Users u = (Users) session.getAttribute("account");
			Address defaultAddress = addressService.getDefaultAddressByUser(u.getId());
			if(defaultAddress == null) {
				f = false;
			}
			else if((long) defaultAddress.getIdUser().getId() != (long) u.getId()) {
				f = false;
			}
			
			if(f) {
				Bill bill = new Bill();
				bill.setAddress(defaultAddress);
				bill.setTotal(((GioHang) session.getAttribute("cart")).total() + 15000 );
                bill.setStatus("Chưa xác nhận đơn hàng");
				bill.setDelivery(usersService.get((long) 2));
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
								
				Long idUser = ((Users) session.getAttribute("account")).getId();
				Cart cart = cartService.getCartByUser(idUser);
				List<CartDetail> listCartDetail = cartDetailService.getDetailByIdCart(cart.getId());
				for(int i = 0; i < listCartDetail.size(); i++) {
					cartDetailService.delete(listCartDetail.get(i).getId());
				}
				session.removeAttribute("cart");	
				
				sendEmail(u, bill);				
			}			
		}
		else {
			f = false;
		}
		
		
		if(f) {
			model.addAttribute("alert","success");
            model.addAttribute("mes","Đặt hàng thành công. Quý khách vui lòng kiểm tra email để xác nhận đơn hàng");
		}
		else {
			model.addAttribute("alert","danger");
            model.addAttribute("mes","Đặt hàng thất bại");
		}		
		
		return "redirect:/controller/cart";
	}
	
	@RequestMapping(value = "/confirm-order", method = RequestMethod.GET)
	public String getConfirmOrder(Model model, HttpSession session,
								@RequestParam(name = "id", defaultValue = "0") Long idBill,
								@RequestParam(name = "email", defaultValue = "") String email){
		String str = "redirect:trackOrder";
		Bill bill = billService.get(idBill);
		if(bill != null) {
			if(bill.getActive() == 0) {
				Calendar cal = Calendar.getInstance();
	            
			    cal.add(Calendar.DAY_OF_MONTH, 2);
			    Date dateAfter = cal.getTime();
			    
			    bill.setDeliveryDate(dateAfter);
				bill.setActive(1);
				bill.setStatus("Đơn hàng đã được xác nhận");
				billService.update(bill);
				
				OrderStatus orderstatus = new OrderStatus();
	            orderstatus.setContent("Đơn hàng đã được xác nhận");
				orderstatus.setNote("kh");
				orderstatus.setBill(bill);
				orderStatusService.add(orderstatus);
				
				model.addAttribute("alert","success");
	            model.addAttribute("mes","Xác nhận đơn hàng thành công. Quý khách có thể kiểm tra tình hình đơn hàng của mình tại đây.");
	            model.addAttribute("idBill", idBill);
	            model.addAttribute("email", email);
			}
			else {
				model.addAttribute("alert","warning");
	            model.addAttribute("mes","Đơn hàng đã được xác nhận");
			}			
		}
		else {
			model.addAttribute("alert","danger");
            model.addAttribute("mes","Đơn hàng không tồn tại");
		}
		return str;
	}	
	
	@RequestMapping(value = "/send-email-confirm-bill", method = RequestMethod.POST)
	public String getSendMailConfirmBill(Model model, HttpSession session,
								@RequestParam(name = "idBill", defaultValue = "0") Long idBill,
								@RequestParam(name = "email", defaultValue = "") String email){
		String str = "redirect:trackOrder";
		Users u = usersService.get(email);
		if(u != null) {
			if(billService.getBillByIdBillAndUser(idBill, u.getId()) != null) {
				str = "redirect:trackOrder?email=" + email + "&idBill=" + idBill;
				Bill bill = billService.get(idBill);
				if(sendEmail(u, bill)) {
					model.addAttribute("alert","success");
		            model.addAttribute("mes","Gửi mail thành công");
				}
				else {
					model.addAttribute("alert","danger");
		            model.addAttribute("mes","Gửi mail không thành công");
				}
			}
			else {				
				model.addAttribute("alert","danger");
	            model.addAttribute("mes","Đơn hàng không hợp lệ");
			}
		}
		else {
			model.addAttribute("alert","danger");
            model.addAttribute("mes","Email không tồn tại");
		}
		return str;
	}
	
	@RequestMapping(value = "/cancel-order", method = RequestMethod.POST)
	public String getCancelOrder(Model model, HttpSession session,
								@RequestParam(name = "idBill", defaultValue = "0") Long idBill,
								@RequestParam(name = "email", defaultValue = "") String email) {
		String str = "";
		Users u = usersService.get(email);
		if(u != null) {
			if(billService.getBillByIdBillAndUser(idBill, u.getId()) != null) {
				str = "redirect:trackOrder?email=" + email + "&idBill=" + idBill;
				Bill bill = billService.get(idBill);
				bill.setActive(-1);			
				bill.setStatus("Hủy bỏ đơn hàng");
				billService.update(bill);
				
				OrderStatus orderstatus = new OrderStatus();
	            orderstatus.setContent("Hủy bỏ đơn hàng");
				orderstatus.setNote("kh");
				orderstatus.setBill(bill);
				orderStatusService.add(orderstatus);
				
				List<BillDetail> items = billDetailService.getBillDetailByIdBill(idBill);
				Comic comic;
				
				for (int i = 0; i < items.size(); i++) {
					comic = items.get(i).getComic();
										
					comic.setQuantitySold(comic.getQuantitySold() - items.get(i).getAmount());
					comic.setAmount(comic.getAmount() + items.get(i).getAmount());
					comicService.update(comic);
				}
				
				model.addAttribute("alert","success");
	            model.addAttribute("mes","Hủy đơn hàng thành công");
			}
			else {				
				model.addAttribute("alert","danger");
	            model.addAttribute("mes","Đơn hàng không hợp lệ");
			}
		}
		else {
			model.addAttribute("alert","danger");
            model.addAttribute("mes","Email không tồn tại");
		}
		return str;
	}
	
	private boolean sendEmail(Users u, Bill bill) {
		String content = "<p><b>Cảm ơn quý khách " + u.getName() + " đã đặt hàng tại T-Manga.vn,</b></p>"; 
		content += "<p>T-Manga.vn rất vui thông báo đơn hàng #" + bill.getId() + " của quý khách đã được tiếp nhận và đang trong quá trình xử lý.</p>";
		content += "<p>Quý khách có thể bấm vào <b>Xem chi tiết đơn hàng</b> để kiểm tra lại thông tin đơn hàng của mình</p>";
		content += "<p><a href='http://localhost:8080/tmanga/controller/trackOrder?idBill="+ bill.getId() + "&email=" + u.getEmail() + "'>Chi tiết đơn hàng</a></p>";
		content += "<p>Xin quý khách vui lòng kiểm tra kỹ lại hóa đơn của mình, sau đó bấm vào nút <b>Xác nhận đặt hàng</b></p>";
		content += "<p><a href='http://localhost:8080/tmanga/controller/confirm-order?id="+ bill.getId() + "&email=" + u.getEmail() + "'>Xác nhận đặt hàng</a></p>";
		content += "<p>Một lần nữa T-Manga.vn cảm ơn quý khách.</p>";
		content += "<p>Kính chúc quý khách có một ngày tốt lành.</p>";
		content += "<p><a href='http://localhost:8080/tmanga/controller/'><b>T-Manga.vn</b></a></p>";
				
		return SendEmail.sendGrid("yutovsjohan@gmail.com", u.getEmail(), "Xác nhận đơn hàng #" + bill.getId(), content, true);
	}
		
	private long checkCart(Long idUser) {
		long i = 0;
		if(cartService.getCartByUser(idUser) != null ) {
			i = cartService.getCartByUser(idUser).getId();
		}
		return i;
	}
	
	private Long countCartDetailByCart(Long idCart) {
		if(cartDetailService.countCartDetailByCart(idCart) != null) {
			return cartDetailService.countCartDetailByCart(idCart);
		}
		return null;
	}

}
