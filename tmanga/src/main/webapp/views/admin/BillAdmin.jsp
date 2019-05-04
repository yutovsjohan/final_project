<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<hr>
<div class="w3-container">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  		<a class="navbar-brand">Danh sách đơn hàng</a>
	
		<div class="col-sm-5">
			<form class="navbar-form" role="search" method="get" action="userAdmin">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Tìm đơn hàng" name="q" id="search">
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit" style="height:34px"><i class="glyphicon glyphicon-search"></i></button>
					</div>
				</div>
			</form>
		</div>	
	</nav>
	
	<nav class="navbar navbar-expand-lg navbar-light bg-light">	
		<a class="navbar-brand">
			<div class="navbar-form">				
			    <select class="form-control" id="sort">
				  	<option value="0">--- Sắp xếp theo ---</option>
				  	<option value="1">Mã đơn hàng tăng dần</option>
					<option value="2">Mã đơn hàng giảm dần</option>
					<option value="7">Ngày đặt hàng tăng dần</option>
					<option value="8">Ngày đặt hàng giảm dần</option>
					<option value="9">Ngày giao hàng tăng dần</option>
					<option value="10">Ngày giao hàng giảm dần</option> 
					<option value="11">Tên người giao từ A-Z</option>
					<option value="12">Tên người giao từ Z-A</option>
			  	</select>
			</div>  
		</a>
	</nav>
</div>

<div class="w3-container">
	<table class="table table-striped" id="myTable">
		<thead>
			<tr>
		        <th>Mã đơn hàng</th>
		        <th>Ngày đặt hàng</th>
		        <th>Tình trạng</th>		        	        		        
		        <th>Ngày giao dự kiến</th>
		        <th>Người giao</th>
		        <th></th>
	      	</tr>
	    </thead>
	    
	    <tbody id="table-list">
			<c:forEach var="bill" items="${bills}">
				<tr>
					<td><a href="billdetail?id=${bill.id }" title="Xem chi tiết đơn hàng">${bill.id }</a> <c:if test="${bill.view == 0 }"><span style="background: red; color: white; border-radius: 10px; padding: 5px; margin: 5px; font-weight: 800;">Mới</span> </c:if></td>
					<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${bill.orderDate }" /></td>
					<td>${bill.status }</td>
					<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${bill.deliveryDate }" /></td>
					<td>
						<c:if test="${bill.active != -1 }">
							<div class="select-delivery" dataId="${bill.id }">
								<c:choose>
									<c:when test="${bill.delivery != null }">
										${bill.delivery }
									</c:when>
									<c:otherwise>
										<button class="btn btn-info" title="Chọn người giao hàng">Chọn</button>
									</c:otherwise>
								</c:choose>	
							</div>											
						</c:if>		
						<select name="delivery" class="delivery" dataId="${bill.id }" hidden>
							<option value="0">-- Chọn người giao --</option>
							<c:forEach var="delivery" items="${delivery }">
								<option value="${delivery.id }" <c:if test="${delivery.name == bill.delivery }">selected</c:if> >${delivery.name }</option>
							</c:forEach>
						</select>					
					</td>
					<td><a href="billdetail?id=${bill.id }" title="Xem chi tiết đơn hàng"><i class="fa fa-2x fa-info-circle" aria-hidden="true"></i></a></td>
				</tr>
			</c:forEach>
		</tbody> 	
	</table>
</div>

<center>
	<ul class="pagination">
		<c:if test="${totalpage != 1 && totalpage != 0 }">
			<c:if test="${1 != pageselected }">
				<li><a href="bill?p=1" rel="next" style="border-radius:20px"> << </a></li>
				<li><a href="bill?p=${pageselected - 1}" rel="next"> < </a></li>
			</c:if>			

			<c:forEach var = "i" begin="${start }" end="${end}">
				<c:choose>
					<c:when test="${i == pageselected }">
						<li class="active"><span style="border-radius:20px">${i }</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="bill?p=${i}">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
				
			<c:if test="${totalpage != pageselected }">
				<li><a href="bill?p=${pageselected + 1}" rel="next"> > </a></li>
				<li><a href="bill?p=${totalpage}" rel="next" style="border-radius:20px"> >> </a></li>
			</c:if>	
			
		</c:if>
	</ul>
</center>