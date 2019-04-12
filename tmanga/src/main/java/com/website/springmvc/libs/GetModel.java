package com.website.springmvc.libs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.AuthorService;
import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.BillDetailService;
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.NewsService;
import com.website.springmvc.Services.PublishCompanyService;
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
		
	public void getSideBar(ModelAndView model) {
		model.setViewName("layout");
		model.addObject("sb","sidebar");
		
		model.addObject("categories", categoryService.getAll());
		model.addObject("authors", authorService.getAll());
		model.addObject("pcs", publishCompanyService.getAll());
	}
	
	public void getHome(ModelAndView model) {
		getSideBar(model);
		
		model.addObject("views","index");
		model.addObject("title","T-Manga");
		
		model.addObject("news", newsService.getNewsForBanner());		
		model.addObject("topSelling", comicService.getComicForTopSelling());
		model.addObject("newComic", comicService.getNewComicInHomePage());
		model.addObject("otherComic", comicService.getOtherComicInHomePage());
	}			
	
	public void getRegistration(ModelAndView model){
		getSideBar(model);	
		
		model.addObject("views","register");
		model.addObject("title","Ä�Äƒng kÃ½");
			
		model.addObject("users",new Users());
	}
	
	public void getLogin(ModelAndView model){		
		getSideBar(model);
		
		model.addObject("views","login");
		model.addObject("title","Ä�Äƒng nháº­p");		
	}
	
	public void getCart(ModelAndView model){
				
		model.setViewName("layout");
		model.addObject("sb","");
		model.addObject("views","cart");
		model.addObject("title","Giá»� hÃ ng");
	}
	
	public void getContact(ModelAndView model){		
		getSideBar(model);
		
		model.addObject("views","contact");
		model.addObject("title","LiÃªn há»‡");		
	}
	
	public void getIntroduce(ModelAndView model){		
		getSideBar(model);
		
		model.addObject("views","introduce");
		model.addObject("title","Giá»›i thiá»‡u");		
	}
	
	public void getTrackOrder(ModelAndView model){		
		getSideBar(model);
		
		model.addObject("views","trackOrder");
		model.addObject("title","Theo dÃµi Ä‘Æ¡n hÃ ng");		
	}
	
	public void getSideBarCustomer(ModelAndView model) {
		model.setViewName("layout");
		model.addObject("sb","customer/sidebarCustomer");
	}
	
	public void getCustomerEditInfo(ModelAndView model) {
		getSideBarCustomer(model);
				
		model.addObject("views","customer/customerEditInformation");
		model.addObject("title","TÃ i khoáº£n cá»§a tÃ´i");
	}
	
	public void getFavoriteList(ModelAndView model) {
		getSideBarCustomer(model);
				
		model.addObject("views","customer/favoriteList");
		model.addObject("title","Danh sÃ¡ch sáº£n pháº©m yÃªu thÃ­ch");
	}
	
	public void getOrderHistory(ModelAndView model) {
		getSideBarCustomer(model);
				
		model.addObject("views","customer/orderHistory");
		model.addObject("title","Ä�Æ¡n hÃ ng Ä‘Ã£ Ä‘áº·t");
	}
}
