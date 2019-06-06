<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-md-9 ">
	<h2 style="color:red">${news.title }</h2>
	<p><fmt:formatDate pattern = "dd-MM-yyyy" value = "${news.created_at }" /> | T-Manga</p>
	<hr>
	${news.content }
		
	<div class="fb-comments" data-href="http://localhost:8080/tmanga/controller/news-detail?un=${news.unsignedTitle}" data-numposts="5"></div>
</div>