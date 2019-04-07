package com.website.springmvc.libs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.AuthorService;
import com.website.springmvc.Services.BilldetailService;
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.NewsService;
import com.website.springmvc.Services.PublishCompanyService;
import com.website.springmvc.entities.users;

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
		model.addObject("hreftemp","");
		
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
		model.addObject("title","Đăng ký");
			
		model.addObject("users",new users());
	}
	
	public void getLogin(ModelAndView model){		
		getSideBar(model);
		
		model.addObject("views","login");
		model.addObject("title","Đăng nhập");		
	}
	
	public void getCart(ModelAndView model){
				
		model.setViewName("layout");
		model.addObject("sb","");
		model.addObject("views","cart");
		model.addObject("title","Giỏ hàng");
	}
	
	public void getSideBarUser(ModelAndView model) {
		model.setViewName("layout");
		model.addObject("sb","user/sidebarUser");
		model.addObject("hreftemp","../");
	}
	
	public void getUserHome(ModelAndView model) {
		getSideBarUser(model);
				
		model.addObject("views","user/userHome");
		model.addObject("title","Tài khoản của bạn");
	}
	
	public void getUserInfo(ModelAndView model) {
		getSideBarUser(model);
				
		model.addObject("views","user/userInfo");
		model.addObject("title","Chỉnh sửa thông tin cá nhân");
	}
	
	public void getFavoriteList(ModelAndView model) {
		getSideBarUser(model);
				
		model.addObject("views","user/favoriteList");
		model.addObject("title","Danh sách sản phẩm yêu thích");
	}
	
	public void getOrderHistory(ModelAndView model) {
		getSideBarUser(model);
				
		model.addObject("views","user/orderHistory");
		model.addObject("title","Đơn hàng đã đặt");
	}
}
