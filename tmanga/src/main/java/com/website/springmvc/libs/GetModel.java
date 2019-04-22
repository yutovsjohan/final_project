package com.website.springmvc.libs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.AddressService;
import com.website.springmvc.Services.AuthorService;
import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.BillDetailService;
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.Services.CityService;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.DistrictService;
import com.website.springmvc.Services.FavoriteListService;
import com.website.springmvc.Services.NewsService;
import com.website.springmvc.Services.OrderStatusService;
import com.website.springmvc.Services.PublishCompanyService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.Address;
import com.website.springmvc.entities.City;
import com.website.springmvc.entities.Comic;
import com.website.springmvc.entities.District;
import com.website.springmvc.entities.FavoriteList;
import com.website.springmvc.entities.News;
import com.website.springmvc.entities.Users;

@Service
public class GetModel {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private PublishCompanyService publishCompanyService;

	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ComicService comicService;	
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private BillDetailService billDetailService;
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private FavoriteListService favoriteListService;
		
	public void getSideBar(ModelAndView model) {
		model.setViewName("layout");
		model.addObject("sb","sidebar");
		
		model.addObject("categories", categoryService.getAll());
		model.addObject("authors", authorService.getAll());
		model.addObject("pcs", publishCompanyService.getAll());
	}
	
	public void getHome(ModelAndView model, HttpSession session) {
		getSideBar(model);
		
		model.addObject("views","index");
		model.addObject("title","T-Manga");
		
		model.addObject("news", newsService.getNewsForBanner());		
		model.addObject("topSelling", comicService.getComicForTopSelling());
		model.addObject("newComic", comicService.getNewComicInHomePage());
		model.addObject("otherComic", comicService.getOtherComicInHomePage());
		
		if(session.getAttribute("account") != null) {
			Users u = (Users) session.getAttribute("account");
			List<FavoriteList> list = favoriteListService.getListByUser(u.getId(), 0, 4);
			
			model.addObject("favoritelist", list);
		}
	}			
	
	public void getRegistration(ModelAndView model){
		getSideBar(model);	
		
		model.addObject("views","register");
		model.addObject("title","Đăng ký");
			
		model.addObject("users",new Users());
	}
	
	public void getLogin(ModelAndView model){		
		getSideBar(model);
		
		model.addObject("views","login");
		model.addObject("title","Đăng nhập");		
	}
	
	public void getForgetPassword(ModelAndView model, String email){
		getSideBar(model);

		model.addObject("views", "changePassword");
		model.addObject("title", "Đặt lại mật khẩu mới");
		model.addObject("email", email);
	}
	
	public void getProductList(ModelAndView model, String action, String key, String name, 
								int page, int sort) {
		getSideBar(model);
		
		String title = "";
		long id = 1;		
		
		List<Comic> comics;
		
		if(action.equalsIgnoreCase("search")) {
			comics = comicService.getListComic(key, 0, 0, sort);
						
			model.addObject("title", "Tìm kiếm " + key);			
			
			model.addObject("key", "search");			
			model.addObject("k",key);
		}
		else if(action.equalsIgnoreCase("nc")) {
			comics = comicService.getNewComic(0, 48);
			
			model.addObject("title", "Truyện tranh mới");
			model.addObject("key", "newcomic");
		}
		else {			
			if(key.equalsIgnoreCase("category")) {
				title = categoryService.get(name).getName();
				id = categoryService.get(name).getId();
			}	
			else if(key.equalsIgnoreCase("author")) {
				title = authorService.get(name).getName();
				id = authorService.get(name).getId();
			}
			else if(key.equalsIgnoreCase("publishing-company")) {
				title = publishCompanyService.get(name).getName();
				id = publishCompanyService.get(name).getId();
			}
			
			comics = comicService.getListComic(key, id, 0, 0, sort);
			
			model.addObject("title",title);			
			
			model.addObject("key", key);
			model.addObject("un", name);
		}	
		
		int totalPage = 0;
		int totalComic = 0;		
		
		totalComic = comics.size();
		totalPage = totalComic / 12;
		
		if(totalComic % 12 != 0){
			totalPage++;
		}		
				
		if(action.equalsIgnoreCase("search")) {
			comics = comicService.getListComic(key, 12*(page-1), 12, sort);
		}
		else if(action.equalsIgnoreCase("nc")) {
			comics = comicService.getNewComic(12*(page-1), 12);
		}
		else {
			comics = comicService.getListComic(key, id, 12*(page-1), 12, sort);
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
						
		model.addObject("totalpage", totalPage);
		model.addObject("pageselected", page);
		model.addObject("totalcomic", totalComic);
		
		model.addObject("comiclist", comics);
		model.addObject("sort", sort);		
		model.addObject("views","productList");	
	}
	
	public void getProductDetail(ModelAndView model, String name, HttpSession session){		
		Long idAuthor = comicService.get(name).getAuthor().getId();
		Long idCategory = comicService.get(name).getCategory().getId();
		Long idPC = comicService.get(name).getPublishcompany().getId();
		
		getSideBar(model);
		
		model.addObject("views","productDetail");
		model.addObject("title", comicService.get(name).getName());
		model.addObject("author",authorService.get(idAuthor));
		model.addObject("category",categoryService.get(idCategory));
		model.addObject("publishcompany",publishCompanyService.get(idPC));
		model.addObject("comic", comicService.get(name));
		model.addObject("listComicForAuthor", comicService.getListForAuthor(idAuthor));		
		
		if(session.getAttribute("account") != null) {
			Users u = (Users) session.getAttribute("account");
			List<FavoriteList> list = favoriteListService.getListByUser(u.getId(), 0, 4);
			
			model.addObject("favoritelist", list);
		}
	}
	
	public void getCart(ModelAndView model){
				
		model.setViewName("layout");
		model.addObject("sb","");
		model.addObject("views","cart");
		model.addObject("title","Giỏ hàng");
	}
	
	public void getShippingPage(ModelAndView model, Users u){
		
		model.setViewName("layout");
		model.addObject("sb","");
		model.addObject("views","shipping");
		model.addObject("title","Thông tin giao hàng");
		model.addObject("addressList", addressService.getListAddressByUser(u.getId(),0,0));
	}
		
	public void getPaymentPage(ModelAndView model, HttpSession session, Long idAddress){		
		model.setViewName("layout");
		model.addObject("sb","");
		model.addObject("views","payment");
		model.addObject("title","Thanh toán và đặt mua");
		
		Address selectedAddress = addressService.get(idAddress);
		Users u = (Users) session.getAttribute("account");
		
		if((long) selectedAddress.getIdUser().getId() == (long) u.getId()) {
			if(selectedAddress.getChoose() == 0) {				
				Address defaultAddress = addressService.getDefaultAddressByUser(u.getId());
				defaultAddress.setChoose((byte) 0);
				addressService.update(defaultAddress);
				
				selectedAddress.setChoose((byte) 1);
				addressService.update(selectedAddress);
			}
		}
		else {
			selectedAddress = addressService.getDefaultAddressByUser(u.getId());
		}
		
		model.addObject("address", selectedAddress);
		
		Calendar cal = Calendar.getInstance();
             
	    cal.add(Calendar.DAY_OF_MONTH, 2);
	    Date dateAfter = cal.getTime();
	    model.addObject("deliveryDate", dateAfter);
	}
	
	public void getContact(ModelAndView model){		
		getSideBar(model);
		
		model.addObject("views","contact");
		model.addObject("title","Liên hệ");		
	}
	
	public void getIntroduce(ModelAndView model){		
		getSideBar(model);
		
		model.addObject("views","introduce");
		model.addObject("title","Giới thiệu");		
	}
	
	public void getNews(ModelAndView model, int page){		
		getSideBar(model);
		
		model.addObject("views","newsList");
		model.addObject("title","Tin tức");		
		
		List<News> news = newsService.getAll();
		
		int totalPage = 0;
		int totalNews = 0;		
		
		totalNews = news.size();
		totalPage = totalNews / 10;
		
		if(totalNews % 10 != 0){
			totalPage++;
		}
		
		news = newsService.getListNews(10*(page-1), 10);
		
		model.addObject("news", news);
		model.addObject("totalpage", totalPage);
		model.addObject("pageselected", page);
		model.addObject("totalnews", totalNews);
	}
	
	public void getNewsDetail(ModelAndView model, News news){		
		getSideBar(model);
		
		model.addObject("views","newsDetail");
		model.addObject("title",news.getTitle());
		model.addObject("news",news);
	}
	
	public void getTrackOrder(ModelAndView model){		
		getSideBar(model);
		
		model.addObject("views","trackOrder");
		model.addObject("title","Theo dõi đơn hàng");		
	}
	
	public void getSideBarCustomer(ModelAndView model) {
		model.setViewName("layout");
		model.addObject("sb","customer/sidebarCustomer");
	}
	
	public void getCustomerEditInfo(ModelAndView model) {
		getSideBarCustomer(model);
				
		model.addObject("views","customer/Information");
		model.addObject("title","Tài khoản của tôi");
	}
	
	public void getFavoriteList(ModelAndView model) {
		getSideBarCustomer(model);
				
		model.addObject("views","customer/favoriteList");
		model.addObject("title","Danh sách yêu thích");
	}
	
	public void getOrderHistory(ModelAndView model) {
		getSideBarCustomer(model);
				
		model.addObject("views","customer/orderHistory");
		model.addObject("title","Đơn hàng đã đặt");
	}
	
	public void getOrderDetail(ModelAndView model, Long idBill) {
		getSideBarCustomer(model);
		
		model.addObject("bill", billService.get(idBill));
		model.addObject("billDetail", billDetailService.getBillDetailByIdBill(idBill));
		model.addObject("orderStatus", orderStatusService.getOrderStatusByIdBill(idBill));
				
		model.addObject("views","customer/orderDetail");
		model.addObject("title","Chi tiết đơn hàng");
	}
	
	public void getAddressBook(ModelAndView model, HttpSession session, int page) {
		getSideBarCustomer(model);
				
		model.addObject("views","customer/addressBook");
		model.addObject("title","Sổ địa chỉ (Address Book)");
		
		if(session.getAttribute("account") != null) {
			Users u = (Users) session.getAttribute("account");
			List<Address> address = addressService.getListAddressByUser(u.getId(), 0, 0);
			
			int totalPage = 0;
			int totalAddress = 0;		
			
			totalAddress = address.size();
			totalPage = totalAddress / 10;
			
			if(totalAddress % 10 != 0){
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
					
			address = addressService.getListAddressByUser(u.getId(), 10*(page-1), 10);
			
			model.addObject("totalpage", totalPage);
			model.addObject("pageselected", page);
			model.addObject("totalcomic", totalAddress);
							
			model.addObject("address", address);
		}
	}
	
	public void getCreateAddress(ModelAndView model, HttpSession session, String mode, Long idAddress) {
		getSideBarCustomer(model);
				
		model.addObject("views","customer/addressDetail");
		model.addObject("Address", new Address());
		model.addObject("title","Thêm địa chỉ mới");
		
		if(mode.equalsIgnoreCase("edit")) {
			model.addObject("title","Cập nhật địa chỉ");
			model.addObject("Address", addressService.get(idAddress));
			model.addObject("district", districtService.getDistrictByCity(addressService.get(idAddress).getCity().getId()));
		}
		
		if(session.getAttribute("account") != null) {
			model.addObject("city", cityService.getAllSortByName());			
		}
	}
}
