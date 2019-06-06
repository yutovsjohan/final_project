package com.website.springmvc.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.entities.Comic;
import com.website.springmvc.entities.News;
import com.website.springmvc.entities.Category;
import com.website.springmvc.entities.PublishCompany;
import com.website.springmvc.libs.GetModel;
import com.website.springmvc.libs.RemoveAccent;
import com.website.springmvc.Services.ComicService;
import com.website.springmvc.Services.CategoryService;
import com.website.springmvc.Services.AuthorService;
import com.website.springmvc.Services.PublishCompanyService;


@Controller
@RequestMapping(value = "/controller")
public class ProductManagementController {
	@Autowired
	GetModel getModel;
	
	@Autowired
	ComicService comicService;
	
	@Autowired
	private CategoryService categoryService;
		
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private PublishCompanyService publishCompanyService;
	
	@RequestMapping(value = "/comicList", method = RequestMethod.GET)
	public ModelAndView getComicList(@RequestParam(name = "mes", defaultValue = "") String mes,
									@RequestParam(name = "alert", defaultValue = "") String alert,
									@RequestParam(name = "p", defaultValue = "1") int page,
									@RequestParam(name = "q", defaultValue = "") String q,
									@RequestParam(name = "id", defaultValue = "0") Long id,
									@RequestParam(name = "key", defaultValue = "") String key,
									HttpSession session) {
		//key: search word key, q: author, category, publish company
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			if(!q.equalsIgnoreCase("") && id == (long)0 ) {
				q = "";
			}
			String str = "comicList?p=" + page + "&key=" + key;
			if(id != (long)0) {
				str += "&q=" + q + "&id=" + id;
			}
			session.setAttribute("url", str);
			getModel.getProductListAdmin(model, q, id, key, page, mes, alert, session);		
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}
	
	@RequestMapping(value = "/comic", method = RequestMethod.GET)
	public ModelAndView getComicDetailAdmin(@RequestParam(name = "id", defaultValue = "0") Long idComic,
										@RequestParam(name = "mode", defaultValue = "add") String mode,
										@RequestParam(name = "mes", defaultValue = "") String mes,
										@RequestParam(name = "alert", defaultValue = "") String alert,
										HttpSession session){		
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			session.setAttribute("url", "comic?mode=" + mode);
			if(idComic != (long)0) {
				session.setAttribute("url", "comic?mode=" + mode + "&id=" + idComic);
			}
			getModel.getProductDetailAdmin(model, mes, alert, idComic, mode, session);
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}	
	
	@RequestMapping(value = "/comic", method = RequestMethod.POST)
	public String saveComic(@RequestParam(name = "mes", defaultValue = "") String mes,
							@RequestParam(name = "alert", defaultValue = "") String alert,
							@RequestParam(name = "mode", defaultValue = "add") String mode,
							@RequestParam(name = "file", defaultValue = "") CommonsMultipartFile image,
							@RequestParam(name = "publishDate", defaultValue = "") String publishDate,
							@RequestParam(name = "name", defaultValue = "") String name,
							@RequestParam(name = "category", defaultValue = "0") Long idCategory,
							@RequestParam(name = "author", defaultValue = "0") Long idAuthor,
							@RequestParam(name = "publishCompany", defaultValue = "0") Long idPublishCompany,
							@RequestParam(name = "price", defaultValue = "0") int price,
							@RequestParam(name = "amount", defaultValue = "0") int amount,
							@RequestParam(name = "size", defaultValue = "") String size,
							@RequestParam(name = "weight", defaultValue = "250") int weight,
							@RequestParam(name = "bookCover", defaultValue = "") String bookCover,
							@RequestParam(name = "name", defaultValue = "") String content,
							@RequestParam(name = "id", defaultValue = "0") Long idComic,
							HttpSession session, Model model, HttpServletRequest request) throws IOException, ServletException{
		String str = "redirect:comicList";
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			Comic comic = new Comic();
			if(mode.equalsIgnoreCase("edit")) {
				if(comicService.get(idComic) != null) {
					comic = comicService.get(idComic);
				}
			}
			
			if(image.getSize() != (long)0) {
				// Thư mục gốc upload file.
				String uploadRootPath = request.getServletContext().getRealPath("images/products");
				System.out.println("uploadRootPath=" + uploadRootPath);
	
				File uploadRootDir = new File(uploadRootPath);
				
				// Tạo thư mục gốc upload nếu nó không tồn tại.
				if (!uploadRootDir.exists()) {
					uploadRootDir.mkdirs();
				}
				CommonsMultipartFile fileData = image;
				
				List<File> uploadedFiles = new ArrayList<File>();
				
				// Tên file gốc tại Client.
				String nameImg = fileData.getOriginalFilename();
				System.out.println("Client File Name = " + name);
				comic.setImage(nameImg);
	
				if (name != null && name.length() > 0) {
					try {
						// Tạo file tại Server.
						File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + nameImg);
	
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
		    
			
			if(name != null && !name.equalsIgnoreCase("") && !name.equalsIgnoreCase(comic.getName())) {
				comic.setName(name);
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
				Calendar cal = Calendar.getInstance();
				java.util.Date date = cal.getTime();
				String temp = dateFormat.format(date);
				String title = RemoveAccent.changeTitle(comic.getName()) + "-t" + temp ;
				comic.setUnsignedName(RemoveAccent.changeTitle(title));
			}
			if(!publishDate.equalsIgnoreCase("")) {
				comic.setPublishDate(Date.valueOf(publishDate));
			}
			
			if(idCategory != (long)0 || categoryService.get(idCategory) != null) {
				comic.setCategory(categoryService.get(idCategory));
			}
			if(idAuthor != (long)0 || authorService.get(idAuthor) != null) {
				comic.setAuthor(authorService.get(idAuthor));
			}
			if(idPublishCompany != (long)0 || publishCompanyService.get(idPublishCompany) != null) {
				comic.setPublishcompany(publishCompanyService.get(idPublishCompany));
			}
			comic.setAmount(amount);
			comic.setBookCover(bookCover);
			comic.setContent(content);
			comic.setPrice(price);
			comic.setWeight(weight);
			comic.setStatus((byte)0);
			comic.setSize(size);
			comic.setSale(price);
			
			if(mode.equalsIgnoreCase("add")) {
				comicService.add(comic);
				model.addAttribute("mes", "Thêm thành công");
			}
			else if(mode.equalsIgnoreCase("edit")) {
				comicService.update(comic);
				model.addAttribute("mes", "Cập nhật thành công");
			}
			model.addAttribute("alert", "success");			
		}
		else {
			str = "redirect:index";
		}
		return str;
	}
	
	@RequestMapping(value = "/change-category", method = RequestMethod.POST)
	public void changeCategory(@RequestParam(name = "idComic", defaultValue = "0") Long idComic, HttpServletResponse response,
								@RequestParam(name = "idCategory", defaultValue = "0") Long idCategory,
								HttpSession session) {
		String str = "success";
		boolean f = getModel.checkAdmin(session);
		
		if(f && idComic != (long)0 && idCategory != (long)0 ) {
			Comic comic = comicService.get(idComic);
			comic.setCategory(categoryService.get(idCategory));
			comicService.update(comic);
		}
		else {
			str = "fail";
		}
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/change-price", method = RequestMethod.POST)
	public void changePrice(@RequestParam(name = "idComic", defaultValue = "0") Long idComic, HttpServletResponse response,
								@RequestParam(name = "price", defaultValue = "0") int price,
								HttpSession session) {
		String str = "success";
		boolean f = getModel.checkAdmin(session);
		
		if(f && idComic != (long)0 && price != 0 ) {
			Comic comic = comicService.get(idComic);
			comic.setSale(price);
			comicService.update(comic);
		}
		else {
			str = "fail";
		}
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/change-amount", method = RequestMethod.POST)
	public void changeAmount(@RequestParam(name = "idComic", defaultValue = "0") Long idComic, HttpServletResponse response,
								@RequestParam(name = "amount", defaultValue = "0") int amount,
								HttpSession session) {
		String str = "success";
		boolean f = getModel.checkAdmin(session);
		
		if(f && idComic != (long)0 ) {
			Comic comic = comicService.get(idComic);
			comic.setAmount(amount);
			comicService.update(comic);
		}
		else {
			str = "fail";
		}
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/showHideComic", method = RequestMethod.POST)
	public String showHide(HttpSession session, Model model,								
							@RequestParam(name = "id", defaultValue = "0") Long idComic) {
		String str = "redirect:comicList";
		boolean f = getModel.checkAdmin(session);
		
		if(f) {
			if(comicService.get(idComic) != null) {
				Comic comic = comicService.get(idComic);
				if(comic.getStatus() == 0){
					comic.setStatus((byte) 1);
				}
				else if(comic.getStatus() == 1){
					comic.setStatus((byte) 0);
				}
				comicService.update(comic);
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
	
	/*@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test() {
		Comic comic;
		for(long i=1; i<= 440; i++) {
			comic = comicService.get(i);
			comic.setPrice(comic.getSale());
			comicService.update(comic);
		}
	}*/
}
