<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<hr>
<div class="w3-container">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  		<a class="navbar-brand">${title }</a>
	</nav>
</div>

<div class="w3-container">	 
	<c:if test="${mes != '' }">
		<div class="alert alert-${alert }" role="alert">
			${mes }
		 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		</div>
	</c:if> 
	
	<form:form action="newsDetail" method="post" modelAttribute="News" enctype="multipart/form-data;boundary=----WebKitFormBoundaryyrV7KO0BoCBuDbTL">
		<input hidden="hidden" value=${mode } name="mode" />
		<input hidden="hidden" value=${News.id } name="id"/>
		<div class="form-group">
			<label for="title">Tiêu đề</label>
			<form:input path="title" value="${News.title }" type="text" class="form-control" name="title" placeholder="nhập tiêu đề tin tức"/>
		</div>
		<div class="form-group">			
			<label for="image">Hình</label><br>		
			<c:if test="${mode == 'edit' }">
				<img src="<c:url value="/images/news/${News.image }" />" alt="${News.title}" width=200px height=150px />
			</c:if>	
			<input type="file" name="file"/>
		</div>
		<div class="form-group">
			<label for="summary">Nội dung tóm tắt</label>
			<form:textarea path="summary" class="form-control" name="summary" rows="4" placeholder="nhập nội dung tóm tắt" />
		</div>
		<div class="form-group ckeditor">
			<label for="content">Nội dung</label>
			<form:textarea path="content" class="ckeditor" name="content" rows="20" />			
		</div>		
		<button type="submit" class="btn btn-success">Lưu</button>
	</form:form>
	<br>	
</div>
