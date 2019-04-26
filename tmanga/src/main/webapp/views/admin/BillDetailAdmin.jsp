<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
	<div class="col-sm-11" style="margin:20px">	
		<h4 style="text-align: center;">Mã đơn hàng: ${bill.id }</h4>
		<h4 style="text-align: center;">Ngày đặt hàng: <span style="color:green; font-weight: bold; font-size: 18px;"><fmt:formatDate pattern = "dd-MM-yyyy" value = "${bill.orderDate}" /></span></h4>
		<h4 style="text-align: center;">
			Tình trạng đơn hàng: 
			<span style="color:green; font-weight: bold; font-size: 20px;">
				${bill.status }
			</span>
		</h4>
		<br>
		
		<c:if test="${mes != '' }">
			<div class="alert alert-${alert }" role="alert">
				${mes }
			 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			</div>
		</c:if> 
		
		<div class="table-responsive">
		    <table class="table table-hover table-bordered">
		      <tr>
		        <th>Ngày</th>
		        <th>Tình trạng đơn hàng</th>		        
		      </tr>
		      <c:forEach var="os" items="${orderStatus}">
		        <tr>
		          <td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${os.created_at}" /></td>
		          <td>${os.content }</td>
		     	</tr>
		      </c:forEach>
		      
		      <c:if test="${bill.active != 2 }">
			      <tr>
			      	<td></td>
			      	<td>
			      		<c:choose>
			      			<c:when test="${bill.active == 1 }">
			      				<a href="add-order-status?id=${bill.id }" class="btn btn-warning" title="Thêm tình trạng mới">Đang vận chuyển</a>
			      			</c:when>
			      			<c:when test="${bill.active == 0 }">
			      				<a href="add-order-status?id=${bill.id }" class="btn btn-success" title="Thêm tình trạng mới">Giao hàng thành công</a>
			      			</c:when>
			      		</c:choose>
			      	</td>
			      </tr>
		      </c:if>
		    </table>
		</div>
		
		<div class="table-responsive">
		  <table class="table table-bordered">
		    <thead>
		      <tr>
		        <th>Thông tin người nhận</th>
		        <th>Thông tin khác</th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		        <td>
		          <p>Tên : ${bill.address.name}</p>
		          <p>Địa chỉ : ${bill.address.address }, ${bill.address.district.name }, ${bill.address.city.name }</p>
		          <p>Điện thoại : ${bill.address.phone }</p>
		        </td>
		        <td>
		          <p>Ngày giao hàng dự kiến: <fmt:formatDate pattern = "dd-MM-yyyy" value = "${bill.deliveryDate}" /></p>
		          <p>${bill.note }</p>
		          <p>Người giao: ${bill.delivery }</p>	          
		        </td>
		      </tr>
		    </tbody>  
		  </table>
		</div>
		
		<br>
		<table class="table table-hover table-bordered">
		  <thead>
		      <tr>
		          <th style="text-align: center;">Sản phẩm</th>
		          <th style="text-align: center;">Số lượng</th>
		          <th style="text-align: center;">Giá (VNĐ)</th>
		          <th style="text-align: center;">Tạm tính</th>
		      </tr>
		  </thead>
		
		  <c:forEach var="billdetail" items="${billDetail }">
		  <tbody>
		      <tr>
		          <td class="cart_description">
		          	<a href="${pageContext.request.contextPath}/controller/detail?c=${billdetail.comic.unsignedName }">
		          		<img src="<c:url value="/images/products/${billdetail.comic.image }" />" alt="${billdetail.comic.name}" style="width:110px; height:140px;" />
		          	</a>
		          	<a href="${pageContext.request.contextPath}/controller/detail?c=${billdetail.comic.unsignedName }">${billdetail.comic.name}</a>
		          </td>
		          <td class="cart_quantity" style="vertical-align:middle; text-align:center" >
		          	${billdetail.amount}
		          </td>
		          <td class="cart_price" style="vertical-align:middle; text-align:center" > <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${billdetail.comic.sale}" /> <u>đ</u></td> 
		          <td style="vertical-align:middle; text-align:center" >
		          	<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${billdetail.amount * billdetail.comic.sale}" /> <u>đ</u>
		          </td>
		      </tr>
		  </tbody>
		  </c:forEach>
		
		  <tbody>
		    <tr style="font-weight:bold; font-size:20px; ">
		      <td colspan="3" style="text-align: right;">
		      	<p>Tổng tạm tính:</p>
		        <p>Chi phí vận chuyển:</p>
		        <p>Thành tiền:</p>
		      </td>
		      <td colspan="2" style="text-align:left;">
		      	<p style="color: orange"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${bill.total - 15000}" /> <u>đ</u></p>
		      	<p style="color: green">15.000 <u>đ</u></p>
		      	<p style="color: red"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${bill.total}" /> VNĐ </p>
		      </td>
		    </tr>
		  </tbody>
		</table>
		  		
	</div>	
</div>