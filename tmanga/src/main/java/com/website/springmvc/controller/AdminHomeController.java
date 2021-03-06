package com.website.springmvc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.Services.BillService;
import com.website.springmvc.Services.ContactService;
import com.website.springmvc.Services.UsersService;
import com.website.springmvc.libs.GetModel;

@Controller
@RequestMapping(value = "/controller")
public class AdminHomeController {

	@Autowired
	ContactService contactService;
	
	@Autowired
	UsersService userService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	GetModel getModel;
	
	@Autowired
	ResourceBundleMessageSource messageSource;
				
	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	public ModelAndView getAdminHome(HttpSession session) {
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
				
		if(f) {
			getModel.getLayoutAdmin(model, session);
			model.setViewName("admin/layoutAdmin");
			model.addObject("views", "adminHome");
			model.addObject("title", "T-Manga | Quản trị");
			if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
				model.addObject("title", "T-Manga | Administrator");
			}
			
			
//			model.addObject("allMess", contactService.getAllMessage());
//			model.addObject("userNum",userService.getUserNum());
			
			model.addObject("contactUnView", contactService.getUnReadMessage());
			model.addObject("billUnView", billService.getBillUnView());
			model.addObject("countCustomer", userService.getCountCustomer());
			model.addObject("countStaff", userService.getCountStaff());
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -7);
			Date date = cal.getTime();

			String[] listString;
			String str = "", label = "", value = "", temp = "";
			int day = 0, month = 0, year = 0;
	        for(int i = 1; i <= 7; i++) { 
	        	cal.add(Calendar.DAY_OF_MONTH, 1);
	        	date = cal.getTime();
	        	temp = dateFormat.format(date);
	        	listString = temp.split("-");
	        	day = Integer.parseInt(listString[2]);
	        	month = Integer.parseInt(listString[1]);
	        	year = Integer.parseInt(listString[0]);
	        	label += day + "/" + month + ",";
	        	value += billService.countBillByDate(day, month, year).toString() + ",";	        	
	        }
	        str = label + ";" + value;
			model.addObject("countBillDaily",str);
			
			cal = Calendar.getInstance();
			date = cal.getTime();
			temp = dateFormat.format(date);
			
			model.addObject("dateEnd", temp);
			
			listString = temp.split("-");
			model.addObject("dateE",  listString[2] + "/" + listString[1] + "/" + listString[0]);
			
			
			cal.add(Calendar.DAY_OF_MONTH, -6);
			date = cal.getTime();
			temp = dateFormat.format(date);
			
			model.addObject("dateStart", temp);
			
			listString = temp.split("-"); 
			model.addObject("dateS",  listString[2] + "/" + listString[1] + "/" + listString[0]);
						
			session.setAttribute("url", "adminHome");			
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}
	
	@RequestMapping(value = "/change-language", method = RequestMethod.GET)
	public String changeLanguage(HttpSession session, @RequestParam(name = "lang", defaultValue = "vi") String lang) {
		String str = "";
		if(session.getAttribute("url") == null || ((String) session.getAttribute("url")).equalsIgnoreCase("") ) {
			session.setAttribute("url","adminHome");
		}
		if(lang.equalsIgnoreCase("en")) {
			messageSource.setBasenames("messages_en");
			session.setAttribute("lang", "en");
		}
		else if(lang.equalsIgnoreCase("vi")) {
			messageSource.setBasenames("messages_vn");
			session.setAttribute("lang", "vi");
		}
		str = "redirect:" + session.getAttribute("url");
		
		return str;
	}
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public ModelAndView getReport(HttpSession session) {
		ModelAndView model = new ModelAndView();
		boolean f = getModel.checkAdmin(session);
				
		if(f) {
			session.setAttribute("url", "report");
			getModel.getLayoutAdmin(model, session);
			model.addObject("title", "Thống kê doanh thu");
			model.addObject("views", "Report");
			if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
				model.addObject("title", "Revenue Statistic");
			}
		}
		else {
			getModel.getHome(model, session);
		}
		return model;
	}
		
	@RequestMapping(value = "/create-report", method = RequestMethod.POST)
	public void createReport(HttpServletResponse response, @RequestParam(name = "dateStart", defaultValue = "") String dateStart,
							@RequestParam(name = "dateEnd", defaultValue = "") String dateEnd,
							@RequestParam(name = "pt", defaultValue = "1") int pt, HttpSession session) throws ParseException {
		String str = "";
		if(pt == 1) {
			str = reportByDay(dateStart, dateEnd, pt);
		}
		else if(pt == 2) {
			str = reportByMonth(dateStart, dateEnd, session);
		}
		else if(pt == 3) {
			str = reportByYear(dateStart, dateEnd, session);
		}
		else if(pt == 4) {
			str = reportByDay(dateStart, dateEnd, pt);
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
	
	public String reportByDay(String dateStart, String dateEnd, int pt) {
		String str = "", label = "", value = "";		
				
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(java.sql.Date.valueOf(dateStart));		
		Date dateS = calStart.getTime();
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(java.sql.Date.valueOf(dateEnd));		
		Date dateE = calEnd.getTime();
		String strTemp = "";
		String[] listString;
		int day, month, year;
		long total = 0;
		
		if(dateS.compareTo(dateE) <= 0){
			for(Date temp = dateS;  ; ) {
				if(temp.after(dateE))
					break;
	        	
				strTemp = dateFormat.format(temp);
	        	listString = strTemp.split("/");
	        	        	        	
	        	day = Integer.parseInt(listString[2]);
	        	month = Integer.parseInt(listString[1]);
	        	year = Integer.parseInt(listString[0]);
	        	
	        	label += day + "/" + month + ",";
	        	if(billService.getReportByDate(day, month, year) != null) {		
	        		if(pt == 1) {
	        			value += billService.getReportByDate(day, month, year).toString() + ",";
	        			total += billService.getReportByDate(day, month, year);
	        		}
	        		else if(pt == 4) {
	        			value += billService.countBillByDate(day, month, year).toString() + ",";
	        		}
	        	}
	        	else {
	        		value += "0,";
	        	}
	        	
	        	calStart.add(Calendar.DAY_OF_MONTH, 1);
				temp = calStart.getTime();
			}
			
			str = label + ";" + value + ";" + total;
		}
		else {
			str = "fail";
		}
		
		return str;
	}
	
	public String reportByMonth(String dateStart, String dateEnd, HttpSession session) {
		String str = "", label = "", value = "";		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(java.sql.Date.valueOf(dateStart));		
		Date dateS = calStart.getTime();
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(java.sql.Date.valueOf(dateEnd));		
		Date dateE = calEnd.getTime();
		String strTemp = "";
		String[] listString;
		int month, year;
		long total = 0;
		
		if(dateS.compareTo(dateE) <= 0){
			for(Date temp = dateS;  ; ) {
				if(temp.after(dateE))
					break;
	        	
				strTemp = dateFormat.format(temp);
	        	listString = strTemp.split("/");
	        	
	        	month = Integer.parseInt(listString[1]);
	        	year = Integer.parseInt(listString[0]);
	        	
	        	
	        	if(((String) session.getAttribute("lang")).equalsIgnoreCase("vi")) {
	        		label += "Tháng " + month + "/" + year + ",";
				}
				if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
					label += "Month " + month + "/" + year + ",";
				}
				
	        	if(billService.getReportByMonth(month, year) != null) {		
	        		value += billService.getReportByMonth(month, year).toString() + ",";
	        		total += billService.getReportByMonth(month, year);
	        	}
	        	else {
	        		value += "0,";
	        	}
	        	
	        	calStart.add(Calendar.MONTH, 1);
				temp = calStart.getTime();
			}
			
			str = label + ";" + value + ";" + total;
		}
		else {
			str = "fail";
		}
		
		return str;				
	}
	
	public String reportByYear(String dateStart, String dateEnd, HttpSession session) {
		String str = "", label = "", value = "";
		String[] dateTemp = dateStart.split("-");
		int yearStart = Integer.parseInt(dateTemp[0]);
		
		dateTemp = dateEnd.split("-");
		int yearEnd = Integer.parseInt(dateTemp[0]);
		long total = 0;
		
		for(int i = yearStart; i <= yearEnd; i++) {
			if(((String) session.getAttribute("lang")).equalsIgnoreCase("vi")) {
				label += "Năm " + i + ",";
			}
			if(((String) session.getAttribute("lang")).equalsIgnoreCase("en")) {
				label += "Year " + i + ",";
			}
			if(billService.getReportByYear(i) != null) {				
				value += billService.getReportByYear(i).toString() + ",";
				total += billService.getReportByYear(i);
			}
			else {
				value += "0,";
			}
		}
		str = label + ";" + value + ";" + total;
		
		return str;
	}
	
//	public String reportByDayEx() {
//		String[] dateTemp = dateStart.split("-");
//		int dayStart = Integer.parseInt(dateTemp[2]);
//		
//		dateTemp = dateEnd.split("-");
//		int dayEnd = Integer.parseInt(dateTemp[2]);
//		int monthEnd = Integer.parseInt(dateTemp[1]);
//		
//		dateTemp[2] = String.valueOf(Integer.parseInt(dateTemp[2]) + 1) ;
//		dateEnd = String.join("-", dateTemp);
//		
//		List<Object[]> listResult = billService.getListByDate(java.sql.Date.valueOf(dateStart) , java.sql.Date.valueOf(dateEnd));
//		dateTemp = dateStart.split("-");
//		
//		String str = "", label = "", value = "";
//		int d = 0, m = 0, y = 0, temp = dayStart - 1, count = 1;
//		for (Object[] aRow : listResult) {
//			d = (int) aRow[1];
//			m = (int) aRow[2];
//			y = (int) aRow[3];
//			
//			if(temp != d) {
//				if(temp != d-1) {						
//					for(int i = temp + 1; i < d; i++) {
//						label += i + "-" + m + ",";
//						value += "0,";
//					}				
//				}
//			
//				label += d + "-" + m;
//				value += billService.getReportByDate(d,m,y).toString();
//				if(count != listResult.size()) {
//					value += ",";
//					label += ",";
//				}
//			}
//			temp = d;
//			count++;
//					
//		}
//		
//		if(temp != dayEnd) {						
//			for(int i = temp + 1; i < dayEnd; i++) {
//				label += "," + i + "-" + monthEnd + ",";
//				value += ",0,";
//			}				
//			label += dayEnd + "-" + monthEnd;
//			value += "0";
//		}
//		
//		str = label + "/" + value;
//		return str;
//	}
}
