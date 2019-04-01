<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Đăng nhập</h2>
<hr>   
<br>
<div class="row">
	<div class="col-sm-10">
		<div class="panel panel-primary">
			<div class="panel-heading">Thông tin đăng nhập</div>
			
			<div class="panel-body">
				<form action="http://localhost:1207/QLbansach/public/dang-nhap" method="post">
				<input type="hidden" name="_token" value="Y1O1F3RtL7O24rLqVCbuLyAizm9YJXTcyZZqdj2R">
				  <div class="form-group">
				    <label for="email">Email *</label>
				    <input type="email" class="form-control" name="email" >
				  </div>
				  <div class="form-group">
				    <label for="password">Mật khẩu *</label>
				    <input type="password" class="form-control" name="password" >
				  </div>

				  <button type="submit" class="btn btn-info" style="background-color: #428bca">Đăng nhập</button>
				  <a class="btn btn-warning forget-password">Quên mật khẩu</a>
				  <br><br>
				</form>
			</div>
		</div>
	</div>
</div>

<br><br>

<div class="row form-forget-password" hidden>
	<div class="col-sm-10">
		<div class="panel panel-primary">
			<div class="panel-heading">Tìm tài khoản của bạn</div>
			
			<div class="panel-body">
				<form action="http://localhost:1207/QLbansach/public/quen-mat-khau-customer" method="post">
				<input type="hidden" name="_token" value="Y1O1F3RtL7O24rLqVCbuLyAizm9YJXTcyZZqdj2R">
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



