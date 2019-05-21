<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<hr>
<div class="w3-container">
	<h2 style="color:red"><spring:message code="admin.sidebar.report" /></h2>
	<br>
	
	<div class="row">
		<div class="col-sm-3">
			<div class="form-group">
				<label for="dateStart"><spring:message code="admin.home.dateStart" /></label>
				<input type="date" class="form-control" name="dateStart" required="required" id="dateStart" value="2019-05-01" />
			</div>
		</div>
		<div class="col-sm-3">
			<div class="form-group">
				<label for="dateEnd"><spring:message code="admin.home.dateEnd" /></label>
				<input type="date" class="form-control" name="dateEnd" required="required" id="dateEnd" value="2019-05-01" />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-4">
			<div class="form-group">
				<select class="form-control" id="pt">
					<option value="1"><spring:message code="admin.report.bydate" /></option>
					<option value="2"><spring:message code="admin.report.bymonth" /></option>
					<option value="3"><spring:message code="admin.report.byyear" /></option>
				</select>
			</div>
		</div>
		<div class="col-sm-2">
			<button class="btn btn-success" id="report" dataUrl="report"><spring:message code="admin.home.createchart" /></button>
		</div>
	</div>
	
	<hr>
	<p id="total"></p>
	<div style="color:red">
		<center><h3 id="title-chart"></h3></center>		
	</div>
	
	<div style="width: 1000px;" id="chart">
		<canvas id="myChart" width="400" height="400"></canvas>		
	</div>
	
</div>