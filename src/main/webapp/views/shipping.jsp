<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	
<div class="container">
	<h3>Chọn địa chỉ giao hàng có sẵn bên dưới:</h3>
	<c:choose>
		<c:when test="${addressList.size() == 0 }">
			<a class="add-address" href="${pageContext.request.contextPath}/controller/customer/address?mode=add">
				<i class="fa fa-plus fa-2x"></i>
				<span> Thêm địa chỉ giao hàng</span>
			</a>
		</c:when>
		<c:otherwise>
			<div class="row">
				<c:forEach items="${addressList }" var="add">
					<div class="col-lg-6 col-md-6 col-sm-6">
						<div class="panel panel-default addressBook <c:if test='${add.choose == 1 }'>address-active</c:if>">
						    <div class="panel-body">
						        <p><b>${add.name}</b> 
						        	<c:if test="${add.choose == 1 }"> <span class="defaultAddress" style="float:right"><i class="fa fa-check-circle"></i> Địa chỉ mặc định</span> </c:if>
						        </p> 
						        <p>Địa chỉ: ${add.address }, ${add.district.name }, ${add.city.name }</p>
						        <p>Điện thoại:  ${add.phone }</p>
						        <a class="btn btn-info btnShipping <c:if test='${add.choose == 0 }'>address-unactive</c:if>" href="${pageContext.request.contextPath}/controller/payment?id=${add.id}" >Giao đến địa chỉ này</a>
						        <a class="btn btn-default" href="${pageContext.request.contextPath}/controller/customer/address?mode=edit&id=${add.id}" >Chỉnh sửa</a>
						    </div>
						</div>
					</div>
				</c:forEach>
			</div>
			<p>Bạn muốn giao hàng đến địa chỉ khác? <a href="${pageContext.request.contextPath}/controller/customer/address?mode=add">Thêm địa chỉ giao hàng mới</a></p>
			<a class="btn btn-success" href="${pageContext.request.contextPath}/controller/payment" style="float:right">Tiếp tục đặt hàng</a>
		</c:otherwise>
	</c:choose>
</div>