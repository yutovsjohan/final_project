<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="col-md-9 ">
	<!-- <h2 style="color:blue">Đăng ký</h2>
	<hr style="border:2px solid blue">   -->
	 
	<c:if test="${mes != '' }">
		<div class="alert alert-${alert }" role="alert">
			${mes }
		 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		</div>
	</c:if> 
	<br>
	     
    <div class="box">
		<form:form action="${pageContext.request.contextPath}/controller/signup" method="post" modelAttribute="users" id="contactform"> 
			<h1>Thông tin đăng ký</h1>
			<form:input path="name" id="name" name="name" placeholder="nhập họ tên" required="required" type="text"/> 
			<form:input path="email" id="email" name="email" placeholder="nhập email" required="required" type="email"/>
			<form:input path="password" type="password" id="password" name="password" placeholder="nhập password" required="required" style="display:inline"/>
			<i id="showpw" class="fa fa-eye" aria-hidden="true" title="Hiện thị password" style="font-size:20px; position: absolute; margin-left: 10px; margin-top: 35px;"></i>
			 
			<input type="password" id="repassword" name="repassword" required="required" placeholder="nhập lại password">
	       	<form:input path="phone" id="phone" name="phone" placeholder="nhập số điện thoại" required="required" type="text" maxlength="10"/>
	       	
	       	 
	       	<input class="btn btn-success" id="submit" name="submit" id="submit" value="Đăng ký" type="submit"> 	 
 		</form:form>
	</div>      
	<br>	
</div>
