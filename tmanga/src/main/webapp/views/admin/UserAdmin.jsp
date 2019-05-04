<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<hr>
<div class="w3-container">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  		<a class="navbar-brand">Danh sách nhân viên</a>
	
		<div class="col-sm-4">
			<form class="navbar-form" role="search" method="get" action="userAdmin">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Tìm nhân viên" name="q" id="search">
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit" style="height:34px"><i class="glyphicon glyphicon-search"></i></button>
					</div>
				</div>
			</form>
		</div>
	
	</nav>
	
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="navbar-brand">
			<a href="add-user" class="btn btn-success" style="margin:8px">Thêm nhân viên mới</a>
		</div>
							
		<a class="navbar-brand">
			<div class="navbar-form">				
			    <select class="form-control" id="sort">
				  	<option value="0">--- Sắp xếp theo ---</option> 
					<option value="15">Tên từ A-Z</option>
					<option value="16">Tên từ Z-A</option>
					<option value="3">Email từ A-Z</option>
					<option value="4">Email từ Z-A</option>
					<option value="17">Sắp xếp theo chức vụ</option>
			  	</select>
			</div>  
		</a>
	</nav>
</div>

<c:if test="${mes != '' }">
	<div class="alert alert-${alert }" role="alert">
		${mes }
	 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	</div>
</c:if> 
			
<hr>
<div class="w3-container">
	<table class="table table-striped" id="myTable">
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
		<tbody id="table-list">
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>${user.phone}</td>
					<td>${user.role.name }</td>
					<c:choose>					
						<c:when test="${user.id == sessionScope.account.id}">
							<td colspan="2">
								<a href="edit-user" class="btn btn-default">Chỉnh sửa thông tin cá nhân</a>
							</td>
						</c:when>
						<c:otherwise>
							<td>
								<button class="btn btn-info setup-pw" dataId="${user.id }">Đặt lại mật khẩu</button>
							</td>
							<td>
								<div class="ban-acc" dataId="${user.id}" dataActive="${user.active }">
									<c:if test="${user.active == 0 }">
										<button class="btn btn-warning">Mở tài khoản</button>
									</c:if>
									<c:if test="${user.active == 1 }">
										<button class="btn btn-danger">Cấm tài khoản</button>
									</c:if>
								</div>
							</td>
						</c:otherwise>
					</c:choose>					
				</tr>
			</c:forEach>
		</tbody>

	</table>
</div>

<center>
	<ul class="pagination">
		<c:if test="${totalpage != 1 && totalpage != 0 }">
			
			<c:if test="${key == '' }">
				<c:set var = "tempurl" scope = "session" value = ""/>
			</c:if>
			
			<c:if test="${key != '' }">
				<c:set var = "tempurl" scope = "session" value = "&q=${key }"/>
			</c:if>
						
			<c:if test="${1 != pageselected }">
				<li><a href="userAdmin?p=1${tempurl }" rel="next" style="border-radius: 20px"> << </a></li>
				<li><a href="userAdmin?p=${pageselected - 1}${tempurl }" rel="next">	< </a></li>
			</c:if>

			<c:forEach var="i" begin="${start }" end="${end}">
				<c:choose>
					<c:when test="${i == pageselected }">
						<li class="active"><span style="border-radius: 20px">${i }</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="userAdmin?p=${i}${tempurl }">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${totalpage != pageselected }">
				<li><a href="userAdmin?p=${pageselected + 1}${tempurl }" rel="next"> > </a></li>
				<li><a href="userAdmin?p=${totalpage}${tempurl }" rel="next"	style="border-radius: 20px"> >> </a></li>
			</c:if>

		</c:if>
	</ul>
</center>
