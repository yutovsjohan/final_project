package com.website.springmvc.libs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Part;

import org.springframework.stereotype.Service;

@Service
public class Upload {
	public String getFileName(Part filepart) {
		String filename="";
		String header=filepart.getHeader("Content-Disposition");
//		System.out.println("header: "+header);
		int beginIndex=header.lastIndexOf("=");
		filename=header.substring(beginIndex+1);
		
		
		Pattern p = Pattern.compile("\"([^\"]*)\"");
		Matcher m = p.matcher(filename);
		while(m.find()) {
			filename=m.group(1);
		}
//		System.out.println("filename: "+filename);
		
//		Dành cho IE
//		beginIndex=header.lastIndexOf("\\");
//		filename=filename.substring(beginIndex+1);
//		System.out.println("filename: "+filename);
		
		return filename;
	}
	
	public void uploadFile(String uploadRootPath, Part file) {
		InputStream fis;
		try {
			fis = file.getInputStream();
			byte[] data = new byte[fis.available()];
			fis.read(data);
			
			FileOutputStream out = new FileOutputStream(new File(uploadRootPath + "\\" + getFileName(file)));
			out.write(data);			
			
			out.close();
		} catch (IOException e) {
			System.out.println("Thất bại");
		}
		System.out.println("Thành công");
	}
}
