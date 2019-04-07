<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${(sessionScope.account != null)}">
	<div class="col-md-9 ">
		<h2 style="color: chocolate">Thông tin cá nhân</h2>
		<table class="table table-bordered">
			<tr>
				<td>Họ Tên</td>
				<td>${sessionScope.account.name }</td>
			</tr>
			<tr>
				<td>Email</td>
				<td>${sessionScope.account.email }</td>
			</tr>
			<tr>
				<td>Điện thoại</td>
				<td>${sessionScope.account.phone }</td>
			</tr>
			<tr>
				<td>Địa chỉ</td>
				<td>${sessionScope.account.address }</td>
			</tr>			
		</table>
	</div>
</c:if>