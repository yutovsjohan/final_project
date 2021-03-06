package com.website.springmvc.libs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.AddressService;
import com.website.springmvc.Services.AuthorService;
import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.BillDetailService;
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.Services.CityService;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.ContactService;
import com.website.springmvc.Services.DistrictService;
import com.website.springmvc.Services.FavoriteListService;
import com.website.springmvc.Services.NewsService;
import com.website.springmvc.Services.OrderStatusService;
import com.website.springmvc.Services.PublishCompanyService;
import com.website.springmvc.Services.RoleService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.entities.Address;
import com.website.springmvc.entities.Bill;
import com.website.springmvc.entities.Category;
import com.website.springmvc.entities.Comic;
import com.website.springmvc.entities.Contact;
import com.website.springmvc.entities.FavoriteList;
import com.website.springmvc.entities.News;
import com.website.springmvc.entities.Role;
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
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private RoleService roleService;
	
	// Phương thức này được gọi mỗi lần có Submit.
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == MyUploadForm.class) {

			// Đăng ký để chuyển đổi giữa các đối tượng multipart thành byte[]
			dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
	}
		
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
			
			model.addObject("title", "Truyện mới nhập");
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
		Comic comic = comicService.get(name, "customer");
		Long idAuthor = comic.getAuthor().getId();
						
		getSideBar(model);
		
		model.addObject("views","productDetail");
		model.addObject("title", comic.getName());
		model.addObject("comic", comic);
		model.addObject("listComicForAuthor", comicService.getListForAuthor(idAuthor));		
		
		//model.addObject("author",authorService.get(idAuthor));
		//model.addObject("category",categoryService.get(idCategory));
		//model.addObject("publishcompany",publishCompanyService.get(idPC));
		//Long idCategory = comicService.get(name).getCategory().getId();
		//Long idPC = comicService.get(name).getPublishcompany().getId();
	
		if(session.getAttribute("account") != null) {
			
//			if(u.getRole().getId() == (long)2) {
//				List<FavoriteList> list;
//				if(favoriteListService.getByUsersAndComic(u.getId(), comic.getId()) == null) {
//					 list = favoriteListService.getListByUser(u.getId(), 0, 36);
//					
//					if(list.size() == 36) {
//						list.remove(list.lastIndexOf(list));
//					}
//					FavoriteList f = new FavoriteList();
//					f.setComic(comic);
//					f.setUser(u);
//					favoriteListService.add(f);
//				}			
				
			Users u = (Users) session.getAttribute("account");
			if(u.getRole().getId() == (long)2) {
				List<FavoriteList> list = favoriteListService.getListByUser(u.getId(), 0, 4);
				if(list.size() == 0) {
					list = null;
				}
				model.addObject("favoritelist", list);
			}
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
		
		Address selectedAddress;
		Users u = (Users) session.getAttribute("account");
		
		if(idAddress == (long) 0) {
			selectedAddress = addressService.getDefaultAddressByUser(u.getId());
		}
		else {
			selectedAddress = addressService.get(idAddress);
		}
				
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
		
		news = newsService.getListNews(10*(page-1), 10, 1, "");
		
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
	
	public void getCustomerEditInfo(ModelAndView model, String href, HttpSession session) {
		if(href.equalsIgnoreCase("edit-user")) {
			getLayoutAdmin(model, session);
			
			model.addObject("views","../customer/Information");
			model.addObject("title","Chỉnh sửa thông tin cá nhân");
			if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
				model.addObject("title","Edit profile");
			}
		}
		else {
			getSideBarCustomer(model);
					
			model.addObject("views","customer/Information");
			model.addObject("title","Tài khoản của tôi");
		}
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
	
	public void getLayoutAdmin(ModelAndView model, HttpSession session) {
		model.setViewName("admin/layoutAdmin");
		long countNewOrder = 0, countNewContact = 0;
		countNewOrder = billService.getCountNewBill();
		countNewContact = contactService.getCountNewContact();
		model.addObject("countNewOrder", countNewOrder);
		model.addObject("countNewContact", countNewContact);
		
		if(session.getAttribute("lang") == null || ((String) session.getAttribute("lang")).equalsIgnoreCase("") ) {
			session.setAttribute("lang", "vi");
		}
	}
	
	public void getListBillPage(ModelAndView model, int page, Long idBill, HttpSession session) {
		getLayoutAdmin(model, session);
		
		List<Bill> bills;
		if(idBill != (long)0 && billService.get(idBill) != null ) {
			bills = new ArrayList<Bill>(1);
		}
		else {			
			bills = billService.getAll();
		}
				
		int totalPage = 0;
		int totalBill = 0;		
		
		totalBill = bills.size();
		totalPage = totalBill / 10;
		
		if(totalBill % 10 != 0){
			totalPage++;
		}		
		
		if(idBill != (long)0 && billService.get(idBill) != null) {
			bills.add(billService.get(idBill));
		}
		else {
			bills = billService.getAll(10*(page-1), 10);
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
								
		model.addObject("bills", bills);
		model.addObject("totalpage", totalPage);
		model.addObject("pageselected", page);
		model.addObject("totalbill", totalBill);
		
		model.addObject("delivery", usersService.getListDelivery());
		model.addObject("views","BillAdmin");
		model.addObject("title","Danh sách đơn hàng");
		if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
			model.addObject("title","List of Bills");
		}
	}
	
	public void getBillDetailPage(ModelAndView model, Long idBill, HttpSession session) {
		if(idBill == (long) 0 || billService.get(idBill) == null) {
			
		}
		else {
			Bill bill = billService.get(idBill);
			bill.setView((byte) 1);
			billService.update(bill);
			
			getLayoutAdmin(model, session);
			model.addObject("bill", bill);
			model.addObject("billDetail", billDetailService.getBillDetailByIdBill(idBill));
			model.addObject("orderStatus", orderStatusService.getOrderStatusByIdBill(idBill));
			model.addObject("views","BillDetailAdmin");
			model.addObject("title","Chi tiết đơn hàng #" + idBill.toString());
			if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
				model.addObject("title","Bill Detail #" + idBill.toString());
			}
			model.addObject("delivery", usersService.getListDelivery());
		}
	}
	
	public void getUserAdmin(ModelAndView model, int page, String key, String mes, String alert, String action, HttpSession session) {
		getLayoutAdmin(model, session);
		model.addObject("views","UserAdmin");
		model.addObject("title","Danh sách User");
		if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
			model.addObject("title","List of Users");
		}
		
		List<Users> users = null;
		if(action.equalsIgnoreCase("staff")) {
			users = usersService.getListStaff(0,0,key);
		}
		else if(action.equalsIgnoreCase("customer")) {
			users = usersService.getListCustomer(0,0,key);
		} 
		
		int totalPage = 0;
		int totalUsers = 0;		
		
		totalUsers = users.size();
		totalPage = totalUsers / 10;
		
		if(totalUsers % 10 != 0){
			totalPage++;
		}		
		
		if(action.equalsIgnoreCase("staff")) {
			users = usersService.getListStaff(10*(page-1), 10,key);
		}
		else if(action.equalsIgnoreCase("customer")) {
			users = usersService.getListCustomer(10*(page-1), 10,key);
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
		model.addObject("totalUsers", totalUsers);
		
		model.addObject("mes", mes);
		model.addObject("alert", alert);
		
		model.addObject("action", action);
		model.addObject("users", users);
	}
	
	public void getAddUser(ModelAndView model, String mes, String alert, HttpSession session) {
		getLayoutAdmin(model, session);
		model.addObject("views","AddUser");
		model.addObject("title","Thêm nhân viên");
		if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
			model.addObject("title","Add new staff");
		}
		model.addObject("mes", mes);
		model.addObject("alert", alert);
		model.addObject("Users", new Users());
	}
			
	public void getNewsAdmin(ModelAndView model, int page, String mes, String alert, String key, HttpSession session) {
		getLayoutAdmin(model, session);
		model.addObject("views","NewsAdmin");
		model.addObject("title","Danh sách tin tức");
		if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
			model.addObject("title","List of News");
		}
		model.addObject("mes", mes);
		model.addObject("alert", alert);
		
		List<News> news = newsService.getListNews(0, 0, 0, key);
		
		int totalPage = 0;
		int totalNews = 0;		
		
		totalNews = news.size();
		totalPage = totalNews / 10;
		
		if(totalNews % 10 != 0){
			totalPage++;
		}		
				
		news = newsService.getListNews(10*(page-1), 10, 0, key);
					
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
		model.addObject("totalbill", totalNews);
		
		model.addObject("news", news);
	}
	
	public void getNewsDetailAdmin(ModelAndView model, String mes, String alert, Long idNews, String mode, HttpSession session) {
		getLayoutAdmin(model, session);
		model.addObject("views","NewsDetailAdmin");
		model.addObject("mes", mes);
		model.addObject("alert", alert);
		model.addObject("mode", mode);
		if(mode.equalsIgnoreCase("add")) {
			model.addObject("title","Thêm tin tức");
			if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
				model.addObject("title","List of News");
			}
			model.addObject("News", new News());
		}
		else if(mode.equalsIgnoreCase("edit")) {
			if(idNews == (long) 0 || newsService.get(idNews) == null) {
				getNewsAdmin(model, 1, "", "", "", session);
			}
			else {
				if(newsService.get(idNews) != null) {
					model.addObject("News", newsService.get(idNews));
					model.addObject("title","Chi tiết tin tức");
					if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
						model.addObject("title","News Detail");
					}
				}
				else {
					getNewsAdmin(model, 1, "", "", "", session);
				}
			}
		}
		MyUploadForm myUploadForm = new MyUploadForm();
	    model.addObject("myUploadForm", myUploadForm);
	}
	
	public boolean checkAdmin(HttpSession session) {
		if(session.getAttribute("account") != null) {
			Users u = usersService.get(((Users) session.getAttribute("account")).getId());			
			if(u.getRole().getId() == (long)2 || u.getActive() == 0) {
				return false;
			}		
			return true;
		}
		return false;		
	}
	
	public void getProductListAdmin(ModelAndView model, String q, Long id, String key, int page, String mes, String alert, HttpSession session) {
		getLayoutAdmin(model, session);
		model.addObject("views","ProductManList");
		model.addObject("title","Danh sách truyện");
		if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
			model.addObject("title","List of Comics");
		}
		
		List<Comic> comics;
		
		if(q.equalsIgnoreCase("category")) {
			comics = comicService.getListComicByCategory(id, 0, 0, key);
		}
		else if(q.equalsIgnoreCase("author")) {
			comics = comicService.getListComicByAuthor(id, 0, 0, key);
		}
		else if(q.equalsIgnoreCase("publishcompany")) {
			comics = comicService.getListComicByPublishCompany(id, 0, 0, key);
		}
		else {
			comics = comicService.getAllComic(key, 0, 0);
		}

		int totalPage = 0;
		int totalComic = 0;		

		totalComic = comics.size();
		totalPage = totalComic / 10;

		if(totalComic % 10 != 0){
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

		if(q.equalsIgnoreCase("category")) {
			comics = comicService.getListComicByCategory(id, 10*(page-1), 10, key);
		}
		else if(q.equalsIgnoreCase("author")) {
			comics = comicService.getListComicByAuthor(id, 10*(page-1), 10, key);
		}
		else if(q.equalsIgnoreCase("publishcompany")) {
			comics = comicService.getListComicByPublishCompany(id, 10*(page-1), 10, key);
		}
		else {
			comics = comicService.getAllComic(key, 10*(page-1), 10);
		}

		model.addObject("comics", comics);
		model.addObject("totalpage", totalPage);
		model.addObject("pageselected", page);
		
		String href = "";
		if(!q.equalsIgnoreCase("")) {
			href += "&q=" + q + "&id=" + id;
		}
		if(!key.equalsIgnoreCase("")) {
			href += "&key=" + key;
		}
		model.addObject("href", href);
		
		List<Category> cate = categoryService.getListName();
		model.addObject("cateList", cate);
		
		model.addObject("mes", mes);
		model.addObject("alert", alert);
	}
	
	public void getProductDetailAdmin(ModelAndView model, String mes, String alert, Long idComic, String mode, HttpSession session) {
		getLayoutAdmin(model, session);		
		model.addObject("views","ProductMan");
		model.addObject("mes", mes);
		model.addObject("alert", alert);
		model.addObject("mode", mode);
		if(mode.equalsIgnoreCase("add")) {
			Users u = ((Users)session.getAttribute("account"));			
			if( u.getRole().getId() == (long)1) {
				model.addObject("title","Thêm truyện mới");	
				if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
					model.addObject("title","Add new comic");
				}
			}
			else if( u.getRole().getId() == (long)1) {
				getHome(model, session);
			}			
			else {
				getProductListAdmin(model, "", (long) 0, "", 1, "", "", session);
			}
//			model.addObject("comic", new Comic());
		}
		else {
			if(idComic == (long) 0 || comicService.get(idComic) == null) {
				getHome(model, session);
			}
			else {
				if(comicService.get(idComic) != null) {
					model.addObject("comic", comicService.get(idComic));
					model.addObject("title","Chi tiết truyện");
					if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
						model.addObject("title","Comic Detail");
					}
				}
				else {
					getHome(model, session);
				}
			}
		}
		model.addObject("categories", categoryService.getListName());
		model.addObject("PCs", publishCompanyService.getAll());
		model.addObject("authorschoice", authorService.getAll());
		
		MyUploadForm myUploadForm = new MyUploadForm();
	    model.addObject("myUploadForm", myUploadForm);
	}
	
	public void getRolePage(ModelAndView model, int page, String key, String mes, String alert, HttpSession session) {
		getLayoutAdmin(model, session);
		model.addObject("views","Role");
		model.addObject("title","Danh sách chức vụ");
		if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
			model.addObject("title","List of Roles");
		}
		
		model.addObject("mes", mes);
		model.addObject("alert", alert);
		
		List<Role> roles = roleService.getAll();
		
		int totalPage = 0;
		int totalRole = 0;		
		
		totalRole = roles.size();
		totalPage = totalRole / 10;
		
		if(totalRole % 10 != 0){
			totalPage++;
		}		
				
		roles = roleService.getListRole(10*(page-1), 10, key);
					
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
		model.addObject("totalRole", totalRole);
		
		model.addObject("roles", roles);
		List<Object[]> list = usersService.getCountUserByRole();
		model.addObject("countRole", list);
	}
	
	public void getContactAdminPage(ModelAndView model, int page, String key, HttpSession session) {
		getLayoutAdmin(model, session);
		model.addObject("views","ContactAdmin");
		model.addObject("title","Danh sách các tin nhắn");
		if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
			model.addObject("title","List of Messages");
		}
		
		List<Contact> contacts = contactService.getAll(0, 0, key);
		
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

		contacts = contactService.getAll(10*(page-1), 10, key);

		model.addObject("authors", contacts);
		model.addObject("totalpage", totalPage);
		model.addObject("pageselected", page);
		model.addObject("contacts", contacts);
	}
	
	public void getContactDetail(ModelAndView model, Long idContact, HttpSession session) {
		getLayoutAdmin(model, session);
		model.addObject("views","ContactDetail");
		model.addObject("title","Chi tiết tin nhắn");
		
		if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
			model.addObject("title","Message Detail");
		}
		
		if(contactService.get(idContact) != null) {
			Contact contact = contactService.get(idContact);
			contact.setView((byte) 1);
			contactService.update(contact);
			model.addObject("contact", contact);
		}
		else {
			getContactAdminPage(model, 1, "", session);
		}
	}
}
