<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${(sessionScope.account != null)}">
	<div class="col-md-9 ">	
		<h2 style="color: chocolate">Sổ địa chỉ </h2>
		<br>
		
		<c:if test="${mes != '' }">
			<div class="alert alert-${alert }" role="alert">
				${mes }
			 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			</div>
		</c:if> 
		<br>
		
		<a class="add-address" href="${pageContext.request.contextPath}/controller/customer/address?mode=add">
			<i class="fa fa-plus fa-2x"></i>
			<span> Thêm địa chỉ mới</span>
		</a>
		
		<c:if test="${address != null }">
			<c:forEach items="${address }" var="add">
				<div class="panel panel-default addressBook" dataId=${add.id }>
				    <div class="panel-body">
				        <p>${add.name} 
				        	<c:if test="${add.choose == 1 }"> <span class="defaultAddress"><i class="fa fa-check-circle"></i> Địa chỉ mặc định</span> </c:if>
				        	<c:if test="${add.choose == 0 }"><a dataId="${add.id }" class="btn btn-danger removeAddress" style="float:right">Xóa</a></c:if>
				        	<a class="btn btn-info" href="${pageContext.request.contextPath}/controller/customer/address?mode=edit&id=${add.id}" style="float:right; margin-right:25px">Chỉnh sửa</a>				        	 
				        </p> 
				        <p>Địa chỉ: ${add.address }, ${add.district.name }, ${add.city.name }</p>
				        <p>Điện thoại:  ${add.phone }</p>	
				    </div>
				</div>
			</c:forEach>
		</c:if>		
		
	</div>
</c:if>