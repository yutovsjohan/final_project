<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<hr>
<div class="w3-container">
	<h1>Danh sách nhân viên</h1>
	<button class="btn btn-success" type="button">Thêm nhân viên mới</button>
</div>

<hr>
<div class="row">
	<div class="col-lg-1 col-md-1 col-xs-1"></div>
	<div class="col-lg-11 col-md-11 col-xs-11">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Họ tên</th>
					<th>Email</th>
					<th>Điện thoại</th>
					<th>Chức vụ</th>					
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.name}</td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
						<td>${user.role.name }</td>
						<td><button class="btn btn-info">Đặt lại mật khẩu</button></td>
						<td><button class="btn btn-danger">Cấm tài khoản</button></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
</div>
