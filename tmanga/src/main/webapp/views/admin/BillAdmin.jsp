<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<div class="w3-container">
	<h1>Danh sách đơn hàng</h1>
</div>
<hr>

<table class="table table-hover">
	<thead>
		<tr>
	        <th>Mã đơn hàng</th>
	        <th>Ngày đặt hàng</th>
	        <th>Tình trạng</th>		        	        		        
	        <th>Ngày giao dự kiến</th>
	        <th>Người giao</th>
	        <th>#</th>
      	</tr>
    </thead>
    
    <tbody>
		<c:forEach var="bill" items="${bills}">
			<tr>
				<td>${bill.id }</td>
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


<center>
	<ul class="pagination">
		<c:if test="${totalpage != 1 && totalpage != 0 }">
			<c:if test="${1 != pageselected }">
				<li><a href="${pageContext.request.contextPath}/controller/bill?p=1" rel="next" style="border-radius:20px"> << </a></li>
				<li><a href="${pageContext.request.contextPath}/controller/bill?p=${pageselected - 1}" rel="next"> < </a></li>
			</c:if>			

			<c:forEach var = "i" begin="1" end="${totalpage }">
				<c:choose>
					<c:when test="${i == pageselected }">
						<li class="active"><span style="border-radius:20px">${i }</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/controller/bill?p=${i}">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
				
			<c:if test="${totalpage != pageselected }">
				<li><a href="${pageContext.request.contextPath}/controller/bill?p=${pageselected + 1}" rel="next"> > </a></li>
				<li><a href="${pageContext.request.contextPath}/controller/bill?p=${totalpage}" rel="next" style="border-radius:20px"> >> </a></li>
			</c:if>	
			
		</c:if>
	</ul>
</center>