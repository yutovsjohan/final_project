package com.website.springmvc.libs;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class RemoveAccent {
	public static String removeAccent(String s) {
	  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
	  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	  return pattern.matcher(temp).replaceAll("").replace('đ','d').replace('Đ','D');
	}
		
	public static String changeTitle(String s) {
		s = removeAccent(s);		
		Pattern pattern = Pattern.compile("[\\W_]");
		s = pattern.matcher(s).replaceAll(" ");
		
		s = s.trim();
		while(s.contains("  ")) {
			s =	s.replaceAll("  ", " ");
		}
		
		s = s.replaceAll(" ", "-");
		s = s.toLowerCase();
		return s;
	}
}
