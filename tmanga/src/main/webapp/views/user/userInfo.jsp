<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${(sessionScope.account != null)}">
	<div class="col-md-9 ">
		<h2 style="color: chocolate">Chỉnh sửa thông tin cá nhân</h2>
		<hr>
		
		<c:if test="${mes != null }">
			<div class="alert alert-${alert }" role="alert">
				${mes }
			 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			</div>
		</c:if> 
		
		<form action="#" method="post">		
		  <div class="form-group">
		    <label for="email">Email</label>
		    <input type="email" class="form-control" value="${sessionScope.account.email}" name="email" disabled="">
		  </div>  
		  <div class="form-group">
		    <label for="name">Họ tên</label>
		    <input type="text" class="form-control" value="${sessionScope.account.name }" name="name" >
		  </div>
		  <div class="form-group">
		    <label for="address">Địa chỉ</label>
		    <input type="text" class="form-control" value="${sessionScope.account.address }" name="address">
		  </div>
		  <div class="form-group">
		    <label for="phone">Điện thoại</label>
		    <input type="text" class="form-control" value="${sessionScope.account.phone }" name="phone" >
		  </div>
		  <div class="form-group">
		  	<input type="checkbox" name="changePassword" id="changePassword">
		    <label for="changePassword"> Đổi mật khẩu</label>
		    <input type="password" class="form-control password" name="password" disabled="">
		  </div>
		  <div class="form-group">
		    <label for="password">Nhập lại mật khẩu</label>
		    <input type="password" class="form-control password" name="repassword" disabled="">
		  </div>
		  
		  <button type="submit" class="btn btn-success">Lưu</button>
		</form>
	</div>
</c:if>