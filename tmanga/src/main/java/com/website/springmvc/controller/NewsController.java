package com.website.springmvc.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.NewsService;
import com.website.springmvc.entities.News;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.MyUploadForm;
import com.website.springmvc.libs.RemoveAccent;

@Controller
@RequestMapping(value="/controller")
public class NewsController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	GetModel getModel;
		
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value = {"news", "tin-tuc"}, method = RequestMethod.GET)
	public ModelAndView getNewsPage(@RequestParam(name = "p", defaultValue = "1") int page,
									HttpSession session){		
		ModelAndView model = new ModelAndView();
		getModel.getNews(model, page);
		
		session.setAttribute("url", "news");
		return model;
	}	
	
	@RequestMapping(value = "news-detail", method = RequestMethod.GET)
	public ModelAndView getNewsDetailPage(@RequestParam(name = "un", defaultValue = "") String un,
											HttpSession session){
		ModelAndView model = new ModelAndView();
		News news = newsService.getNewsById(un);
		getModel.getNewsDetail(model, news);
		
		session.setAttribute("url", "news-detail?un=" + un);
		return model;
	}	
	
	@RequestMapping(value = "newsAdmin", method = RequestMethod.GET)
	public ModelAndView getNewsAdminPage(@RequestParam(name = "p", defaultValue = "1") int page,
										@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "") String alert,
										HttpSession session){		
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			getModel.getNewsAdmin(model, page, mes, alert);
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}	
	
	@RequestMapping(value = "newsDetail", method = RequestMethod.GET)
	public ModelAndView getNewsDetailAdmin(@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "") String alert,
										HttpSession session, @RequestParam(name = "id", defaultValue = "0") Long idNews,
										@RequestParam(name = "mode", defaultValue = "add") String mode){		
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			getModel.getNewsDetailAdmin(model, mes, alert, idNews, mode, session);
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}	
	
	@RequestMapping(value = "newsDetail", method = RequestMethod.POST)
	public String saveNews(@RequestParam(name = "mes", defaultValue = "") String mes,
						@RequestParam(name = "alert", defaultValue = "") String alert,
						@RequestParam(name = "mode", defaultValue = "add") String mode,
//						@RequestParam(name = "title", defaultValue = "") String title,
//						@RequestParam(name = "content", defaultValue = "") String content,
//						@RequestParam(name = "summary", defaultValue = "") String summary,
//						@RequestParam(name = "id", defaultValue = "0") Long idNews,
						@RequestParam(name = "file", defaultValue = "") CommonsMultipartFile image,
						@ModelAttribute("News") News news,
						HttpSession session, Model model, HttpServletRequest request) throws IOException, ServletException{	
		String str = "redirect:newsAdmin";
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
//			News news = new News();
//			if(mode.equalsIgnoreCase("edit")) {
//				if(newsService.get(idNews) != null) {
//					news = newsService.get(idNews);
//				}				
//				else {
//					model.addAttribute("mes", "Cập nhật không thành công");
//					model.addAttribute("alert", "danger");
//					return "redirect:newsAdmin";
//				}
//			}
						
//			String description = myUploadForm.getDescription();
//			System.out.println("Description: " + description);
			
			if(image.getSize() != (long)0) {
				// Thư mục gốc upload file.
				String uploadRootPath = request.getServletContext().getRealPath("images/news");
				System.out.println("uploadRootPath=" + uploadRootPath);
	
				File uploadRootDir = new File(uploadRootPath);
				
				// Tạo thư mục gốc upload nếu nó không tồn tại.
				if (!uploadRootDir.exists()) {
					uploadRootDir.mkdirs();
				}
				CommonsMultipartFile fileData = image;
				
				List<File> uploadedFiles = new ArrayList<File>();
				
				// Tên file gốc tại Client.
				String name = fileData.getOriginalFilename();
				System.out.println("Client File Name = " + name);
				news.setImage(name);
	
				if (name != null && name.length() > 0) {
					try {
						// Tạo file tại Server.
						File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
	
						// Luồng ghi dữ liệu vào file trên Server.
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(fileData.getBytes());
						stream.close();
						//
						uploadedFiles.add(serverFile);
						System.out.println("Write file: " + serverFile);
					} catch (Exception e) {
						System.out.println("Error Write file: " + name);
					}
				}
			}
		       
//			news.setTitle(title);
//			news.setContent(content);
//			news.setSummary(summary);
			
			if(news.getTitle() != null) {
				news.setUnsignedTitle(RemoveAccent.changeTitle(news.getTitle()));
			}
			
			if(mode.equalsIgnoreCase("add")) {
				newsService.add(news);
				model.addAttribute("mes", "Thêm thành công");
			}
			else if(mode.equalsIgnoreCase("edit")) {
				newsService.update(news);
				model.addAttribute("mes", "Cập nhật thành công");
			}
			model.addAttribute("alert", "success");			
		}
		else {
			str = "redirect:index";
		}
		return str;
	}
	
	@RequestMapping(value = "/showHideNews", method = RequestMethod.POST)
	public String showHide(HttpSession session, Model model,								
							@RequestParam(name = "id", defaultValue = "0") Long idNews) {
		String str = "redirect:authorAdmin";
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			if(newsService.get(idNews) != null) {
				News news = newsService.get(idNews);
				if(news.getStatus() == 0){
					news.setStatus((byte) 1);
				}
				else if(news.getStatus() == 1){
					news.setStatus((byte) 0);
				}
				newsService.update(news);
				model.addAttribute("mes", "Cập nhật thành công");
				model.addAttribute("alert", "success");
			}
			else{
				model.addAttribute("mes", "ID không tồn tại");
				model.addAttribute("alert", "danger");
			}
		}
		else {
			str = "redirect:index";
		}
		return str;
	}
	
	@RequestMapping(value = "/removeNews", method = RequestMethod.POST)
	public void removeAddress(@RequestParam(name = "id") Long idNews, HttpServletResponse response,
								HttpSession session) {
		String str = "success";
		boolean f = getModel.checkAdmin(session);
		
		
		if(f && newsService.get(idNews) != null) {
			newsService.delete(idNews);
		}
		else {
			str = "fail";
		}
		

		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
