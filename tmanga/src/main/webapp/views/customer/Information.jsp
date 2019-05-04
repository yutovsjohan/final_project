<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="w3-container">
	<c:if test="${(sessionScope.account != null)}">
		<div class="col-md-9 ">
			<h2 style="color: chocolate">Thông tin tài khoản</h2>
			<hr>
			
			<c:if test="${mes != '' }">
				<div class="alert alert-${alert }" role="alert">
					${mes }
				 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
			</c:if> 
			
			<form action="${pageContext.request.contextPath}/controller/customer/edit" method="post">		
			  <div class="form-group">
			    <label for="email">Email</label>
			    <input type="email" class="form-control" value="${sessionScope.account.email}" name="email" disabled="">
			  </div>  
			  <div class="form-group">
			    <label for="name">Họ tên</label>
			    <input type="text" class="form-control" value="${sessionScope.account.name }" name="name" required="required">
			  </div>
			  <div class="form-group">
			    <label for="phone">Điện thoại</label>
			    <input type="text" class="form-control" value="${sessionScope.account.phone }" name="phone" id="phone" required="required" maxlength="10">
			  </div>
			  <div class="form-group">
			  	<input type="checkbox" name="changePassword" id="changePassword">
			    <label for="changePassword"> Đổi mật khẩu</label> <i id="showpw" class="fa fa-eye" aria-hidden="true" title="Hiển thị password" style="font-size:20px"></i>
			    <input type="password" class="form-control password" name="password" id="password" disabled="" required="required">
			  </div>
			  <div class="form-group">
			    <label for="password">Nhập lại mật khẩu</label>
			    <input type="password" class="form-control password" name="repassword" id="repassword" disabled="" required="required">
			  </div>
			  <input name="href" value="${href }" hidden />
			  <button type="submit" class="btn btn-success" id="save">Lưu</button>
			</form>
		</div>
	</c:if>
</div>
<hr>