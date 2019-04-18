<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-9 ">
		
	<c:if test="${mes != '' }">
		<div class="alert alert-${alert }" role="alert">
			${mes }
		 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		</div>
	</c:if> 
	<br>
	
	<div class="box">
		<form action="${pageContext.request.contextPath}/controller/change-password" method="post">
			<h1>Đặt lại mật khẩu mới</h1>
			<input type="password" name="password" id="password" placeholder="nhập password" required="required">
			<input type="password" name="repassword" id="repassword" placeholder="nhập lại password" required="required">
			<input type="text" name="passcode" id="passcode" placeholder="nhập passcode" required="required">
			<input type="submit" name="" id="submit" value="Cập nhật">
		</form>
	</div>
</div>