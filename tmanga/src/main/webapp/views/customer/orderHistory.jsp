<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${(sessionScope.account != null)}">
	<div class="col-md-9 ">
		<h2 style="color: chocolate">Đơn hàng đã đặt </h2>
		<hr>
		<c:if test="${mes != null }">
			<div class="alert alert-${alert }" role="alert">
				${mes }
			 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			</div>
		</c:if> 
		<c:choose>
			<c:when test="${bills == null }">
				<p>Hiện tại bạn chưa có đơn hàng. <a href="index">Quay về trang web</a> để mua hàng.</p>
			</c:when>
			<c:otherwise>
				<table class="table table-hover table-bordered">
				  <thead>
				      <tr>
				          <th>Mã đơn hàng</th>
				          <th>Ngày mua</th>
				          <th style="width: 200px">Địa chỉ </th>
				          <th>Ngày giao</th>
				          <th>Tình trạng</th>
				          <th>#</th>
				      </tr>
				  </thead>
				  <c:forEach var="bill" items="${bills}">
				  	<c:if test="${bill.idUser.id == sessionScope.account.id }">
					  <tbody>
					      <tr>
					         <td>${bill.id }</td>
					         <td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${bill.orderDate}" /></td>
					         <td>${sessionScope.account.address }</td>
					         <td>
					         	<c:choose>
					         		<c:when test="${bill.active == 1 }">${bill.deliveryDate }</c:when>
					         		<c:otherwise></c:otherwise>
					         	</c:choose>
					         </td>
					         <td>${bill.status }</td>
					         <td>
					         	<a href="#"><i class="fa fa-info-circle fa-2x" aria-hidden="true" title="Xem chi tiết đơn hàng"></i></a>				
					         </td>
					      </tr>
					  </tbody>
					</c:if>
				  </c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</c:if>