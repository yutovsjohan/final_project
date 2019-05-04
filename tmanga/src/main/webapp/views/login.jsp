<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-9 ">
	<!-- <h2 style="color:blue">Đăng nhập</h2>
	<hr style="border:2px solid blue">   -->
	
	<c:if test="${mes != '' }">
		<div class="alert alert-${alert }" role="alert">
			${mes }
		 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		</div>
	</c:if> 
	<br>
		
	<div class="box">
	<form action="${pageContext.request.contextPath}/controller/login" method="post">
		<h1>Thông tin đăng nhập</h1>
		<input type="email" name="email" id="email" placeholder="nhập email" required="required">		
		<input type="password" name="password" placeholder="nhập password" required="required" id="password" style="display:inline;">
		<i id="showpw" class="fa fa-eye" aria-hidden="true" title="Hiển thị password" style="font-size:20px; position: absolute; margin-left: 10px; margin-top: 35px;"></i>
		<input type="submit" name="" id="submit" value="Đăng nhập">
	</form>
	<br>
	<a class="forgetps forget-password" style="display:inline">Quên mật khẩu</a>
	</div>
	
	
	<!-- <div class="row" hidden>
		<div class="col-sm-10">
			<div class="panel panel-primary">
				<div class="panel-heading">Thông tin đăng nhập</div>
				
				<div class="panel-body">
					<form action="login" method="post">
					<input type="hidden" name="" value="">
					  <div class="form-group">
					    <label for="email">Email *</label>
					    <input type="email" class="form-control" name="email" placeholder="nhập email" >
					  </div>
					  <div class="form-group">
					    <label for="password">Mật khẩu *</label>
					    <input type="password" class="form-control" name="password" placeholder="nhập password">
					  </div>
	
					  <button type="submit" class="btn btn-info" style="background-color: #428bca">Đăng nhập</button>
					  <a class="btn btn-warning forget-password">Quên mật khẩu</a>
					  <br><br>
					</form>
				</div>
			</div>
		</div>
	</div> -->
	
	<br><br>
	
	<div class="row form-forget-password" hidden>
		<div class="col-sm-10">
			<div class="panel panel-primary">
				<div class="panel-heading">Tìm tài khoản của bạn</div>
				
				<div class="panel-body">
					<form action="${pageContext.request.contextPath}/controller/forgetpassword" method="post">
					<input type="hidden" name="" value="">
					  <div class="form-group">
					    <label for="email">	
							Vui lòng nhập email để tìm kiếm tài khoản.</label>
					    <input type="email" class="form-control" name="email" placeholder="Nhập email">
					  </div>				  
					  <div class="guimail">
					  <button class="btn btn-info forget-password">Tìm kiếm</button>				  
					  </div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


