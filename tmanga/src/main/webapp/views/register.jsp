<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2 style="color:blue">Đăng ký</h2>
<hr style="border:2px solid blue">   
<c:if test="${mes != null }">
	<div class="alert alert-${alert }" role="alert">
		${mes }
	 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	</div>
</c:if> 
<br>

<div class="row">
	<div class="col-sm-10">
		<div class="panel panel-primary">
			<div class="panel-heading">Thông tin đăng ký</div>
			
			<div class="panel-body"> 
				<form:form action="./signup" method="post" modelAttribute="users">
					<div class="form-group">
				    <label for="email">Email *</label>
				    <form:input path="email" type="email" class="form-control" name="email" placeholder="nhập email" required="required" />
				  </div>
				  <div class="form-group">
				    <label for="password">Mật khẩu *</label>
				    <form:input path="password" type="password" class="form-control" name="password" placeholder="nhập password" required="required"/>
				  </div>
				  <div class="form-group">
				    <label for="repassword">Nhập lại mật khẩu *</label>
				    <input class="form-control" type="password" name="repassword" placeholder="nhập lại password" required="required"/>
				  </div>
				  <div class="form-group">
				    <label for="name">Họ tên *</label>
				    <form:input path="name" type="text" class="form-control" name="name" placeholder="nhập tên" required="required"/>
				  </div>
				  <div class="form-group">
				    <label for="address">Địa chỉ *</label>
				    <form:input path="address" type="text" class="form-control" name="address" placeholder="nhập địa chỉ" required="required"/>
				  </div>				  
				  <div class="form-group">
				    <label for="phone">Điện thoại *</label>
				    <form:input path="phone" type="text" class="form-control" name="phone" placeholder="nhập điện thoại" required="required"/>
				  </div>
				  <p style="font-weight: bold;color:red">(*) bắt buộc</p>				  
				  <button type="submit" class="btn btn-primary" style="background-color: #428bca">Đăng ký</button> 	 
				</form:form>
			</div>
		</div>
	</div>
</div>
