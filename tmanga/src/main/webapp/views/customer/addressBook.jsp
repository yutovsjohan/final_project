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
				        	<c:if test="${add.bill.size() == 0 }">
			        			<a dataId="${add.id }" class="btn btn-danger removeAddress" style="float:right">Xóa</a>
			        		</c:if>
				        	<c:if test="${add.choose == 0 }">
				        		<a href="${pageContext.request.contextPath}/controller/customer/defaultAddress?id=${add.id}" class="btn btn-warning" style="float:right; margin-right:15px" title="Đặt làm địa chỉ mặc định">Mặc định</a>
				        	</c:if>
				        	<a class="btn btn-info" href="${pageContext.request.contextPath}/controller/customer/address?mode=edit&id=${add.id}" style="float:right; margin-right:15px">Chỉnh sửa</a>				        	 
				        </p> 
				        <p>Địa chỉ: ${add.address }, ${add.district.name }, ${add.city.name }</p>
				        <p>Điện thoại:  ${add.phone }</p>	
				    </div>
				</div>
			</c:forEach>
			
			<center>
			<ul class="pagination">
				<c:if test="${totalpage != 1 && totalpage != 0 }">
					<c:if test="${1 != pageselected }">
						<li><a href="${pageContext.request.contextPath}/controller/${href }&p=1&s=${sort}" rel="next" style="border-radius:20px"> << </a></li>
						<li><a href="${pageContext.request.contextPath}/controller/${href }&p=${pageselected - 1}&s=${sort}" rel="next"> < </a></li>
					</c:if>			
		
					<c:forEach var = "i" begin="${start }" end="${end}">
						<c:choose>
							<c:when test="${i == pageselected }">
								<li class="active"><span style="border-radius:20px">${i }</span></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath}/controller/${href }&p=${i}&s=${sort}">${i }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
						
					<c:if test="${totalpage != pageselected }">
						<li><a href="${pageContext.request.contextPath}/controller/${href }&p=${pageselected + 1}&s=${sort}" rel="next"> > </a></li>
						<li><a href="${pageContext.request.contextPath}/controller/${href }&p=${totalpage}&s=${sort}" rel="next" style="border-radius:20px"> >> </a></li>
					</c:if>	
					
				</c:if>
			</ul>
		</center>
		</c:if>		
		
	</div>
</c:if>