<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<hr>
<div class="w3-container">
	<h2 style="color:red">Thống kê doanh thu</h2>
	<br>
	
	<div class="row">
		<div class="col-sm-3">
			<div class="form-group">
				<label for="dateStart">Ngày bắt đầu</label>
				<input type="date" class="form-control" name="dateStart" required="required" id="dateStart" value="2019-05-01" />
			</div>
		</div>
		<div class="col-sm-3">
			<div class="form-group">
				<label for="dateEnd">Ngày kết thúc</label>
				<input type="date" class="form-control" name="dateEnd" required="required" id="dateEnd" value="2019-05-01" />
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
			<button class="btn btn-success" id="report" dataUrl="report">Tạo biểu đồ</button>
		</div>
	</div>
	
	<hr>
	<div style="color:red">
		<center><h3 id="title-chart"></h3></center>
	</div>
	
	<div style="width: 1000px;" id="chart">
		<canvas id="myChart" width="400" height="400"></canvas>		
	</div>
	
</div>