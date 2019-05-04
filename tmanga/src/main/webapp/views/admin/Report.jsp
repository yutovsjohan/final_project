<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="w3-container">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  		<a class="navbar-brand">Thống kê doanh thu</a>
	</nav>
	
	<div class="alert alert-warning " role="alert">
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    <span aria-hidden="true">&times;</span>
	  </button>
	  <strong>Lưu ý</strong> <br>
	  <p> - Thống kê theo từng ngày, hai mốc thời gian phải cùng tháng, cùng năm</p>
	  <p> - Thống kê theo từng tháng, hai mốc thời gian phải cùng năm</p>	  
	</div>
	
	<div class="row">
		<div class="col-sm-3">
			<div class="form-group">
				<label for="dateStart">Từ ngày</label>
				<input type="date" class="form-control" name="dateStart" required="required" id="dateStart" value="2019-04-01" />
			</div>
		</div>
		<div class="col-sm-3">
			<div class="form-group">
				<label for="dateEnd">Đến ngày</label>
				<input type="date" class="form-control" name="dateEnd" required="required" id="dateEnd" value="2019-04-01" />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-4">
			<div class="form-group">
				<select class="form-control" id="pt">
					<option value="1">Thống kê theo từng ngày</option>
					<option value="2">Thống kê theo từng tháng</option>
					<option value="3">Thống kê theo từng năm</option>
				</select>
			</div>
		</div>
		<div class="col-sm-2">
			<button class="btn btn-success" id="report" >Tạo biểu đồ</button>
		</div>
	</div>
				
	<div style="width: 1000px;" id="chart">
		<canvas id="myChart" width="400" height="400"></canvas>
	</div>
</div>