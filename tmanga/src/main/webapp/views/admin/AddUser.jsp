<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<hr>
<div class="w3-container">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  		<a class="navbar-brand">Thêm nhân viên</a>
	</nav>
</div>

<div class="w3-container">	 
	<c:if test="${mes != '' }">
		<div class="alert alert-${alert }" role="alert">
			${mes }
		 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		</div>
	</c:if> 
	
	<form:form action="user-signup" method="post" modelAttribute="Users">
		<div class="form-group">
			<label for="email">Email</label>
			<form:input path="email" type="email" class="form-control" name="email" required="required" id="email" placeholder="nhập email"/>
			<p id="note" style="color:red" hidden>Email đã có người sử dụng</p>
		</div>
		<div class="form-group">
			<label for="name">Họ tên</label>
			<form:input path="name" type="text" class="form-control" name="name" required="required" placeholder="nhập họ tên"/>
		</div>
		<div class="form-group">
			<label for="phone">Điện thoại</label>
			<form:input path="phone" type="text" class="form-control" name="phone" id="phone" required="required" maxlength="10" placeholder="nhập điện thoại"/>
		</div>
		<div class="form-group">
			<label for="password">Nhập mật khẩu</label>	<i id="showpw" class="fa fa-eye" aria-hidden="true" title="Hiển thị password" style="font-size: 20px"></i>
			<form:input path="password" class="form-control password" type="password" id="password" name="password" placeholder="nhập password" required="required" />			
		</div>
		<div class="form-group">
			<label for="password">Nhập lại mật khẩu</label>
			<input type="password" class="form-control password" name="repassword" id="repassword" required="required" placeholder="nhập lại mật khẩu">
		</div>	
		<div class="form-group">
			<label for="role">Chức vụ</label>
			<form:select path="role.id" name="role" class="form-control">
				<option value="3">Delivery</option>
				<option value="1">Admin</option>
			</form:select>
		</div>	
		<input type="submit" class="btn btn-success" id="submit" value="Lưu">
	</form:form>
	<br>	
</div>
