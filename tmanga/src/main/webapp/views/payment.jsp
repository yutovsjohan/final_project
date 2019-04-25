<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	
<div class="container">
	<c:if test="${sessionScope.cart != null && sessionScope.account != null}">		
	
	<form action="${pageContext.request.contextPath}/controller/order" method="post">
		<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="panel panel-primary">
		            <div class="panel-heading">
		                <h3 class="panel-title">Đơn hàng</h3>
		            </div>
		            <div class="panel-body">
		            	
		            	<table class="table table-border">
		            		<a href="${pageContext.request.contextPath}/controller/cart" class="btn btn-warning" style="float:right">Sửa</a>
		            		<c:forEach var="item" items="${sessionScope.cart.getList() }">
			            		<tr>
			            			<td>${item.amount } x <a href="${pageContext.request.contextPath}/controller/detail?c=${item.comic.unsignedName }" title="${item.comic.name }">${item.comic.name }</a></td>	
			            			<td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${item.comic.sale }" /> <u>đ</u></td>					            		
			            		</tr>	
			            	</c:forEach>
			            	           
			            	<tr>
			            		<td>Phí vận chuyển</td>
			            		<td>15.000 <u>đ</u></td>
			            	</tr> 		
			            	<tr>
			            		<td style="font-size:20px"><b>Thành tiền</b></td>
			            		<td style="width:150px"><span style="font-size: 20px; color: red; font-weight: bold;"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${sessionScope.cart.total() + 15000 }" /> <u>đ</u></span> </td>
			            	</tr>
		            	</table>   	
		            </div>
				</div>
			</div>
				
			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="panel panel-primary">
		            <div class="panel-heading">
		                <h3 class="panel-title">Địa chỉ giao hàng</h3>
		            </div>
		            <div class="panel-body">
		            	<a href="${pageContext.request.contextPath}/controller/customer/address?mode=edit&id=${address.id}" class="btn btn-warning" style="float:right">Sửa</a>
		               	<p><b>${address.name }</b></p>
		                <p>${address.address }, ${address.district.name }, ${address.city.name }</p>
						<p>Điện thoại: ${address.phone }</p>    
		            </div>
		        </div>
			</div>
			
			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="panel panel-primary">
		            <div class="panel-heading">
		                <h3 class="panel-title">Thanh toán và giao hàng</h3>
		            </div>
		            <div class="panel-body">
		               	<p><span class="glyphicon glyphicon-ok" style="color:green"></span> &nbsp;&nbsp; Dự kiến giao hàng: <fmt:formatDate pattern = "dd-MM-yyyy" value = "${deliveryDate }" /></p>
		               			               	
		               	<div class="form-group">
						    <label for="pttt"><span class="glyphicon glyphicon-ok" style="color:green"></span>&nbsp;&nbsp; Thanh toán</label>
						    <select name="pttt" class="form-control" required="required" id="pttt">
						    	<option value="-1">--- Chọn hình thức thanh toán ---</option>
						    	<option value="0">Thanh toán tiền mặt khi nhận hàng</option>
						    	<option value="1">Thanh toán bằng thẻ quốc tế Visa</option>
						    	<option value="2">Thanh toán bằng thẻ ATM</option>
						    </select>						    	
						    <p style="color:red" id="note">* Bạn cần phải chọn hình thức thanh toán trước khi đặt mua</p>
						  </div>   
		            </div>
		        </div>
			</div>
		</div>
		
		<button class="btn btn-success" style="float:right" disabled="disabled" id="order">Đặt mua</button>
		</form>
	</c:if>
</div>