<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${(users != null)}">
	<div class="col-md-9 ">
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
		
		<%-- <c:if test="${bill.active == 0}">			
	    	<p style="color:red;font-size: 18px; text-align: center; ">Quý khách vui lòng kiểm tra mail và xác nhận đơn đặt hàng</p>
		</c:if> --%>
		
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
		    </table>
		</div>
		
		<br>		
		
		<c:if test="${bill.active != 2 && bill.active != -1 }">
			<button class="btn btn-warning cancelOrder"  style="float: left; margin-bottom:20px"><i class="fa fa-trash-o fa-2x" aria-hidden="true"  title="Hủy đơn hàng" > Hủy đơn hàng</i></button>			
		</c:if>
		
		<%-- <c:if test="${bill.active == 0 }">		  		
		    <div class="guimail">
		      <button class="btn btn-info" id="send-email-confirm-bill" style="float: right;" ><i class="fa fa-2x fa-envelope-o" aria-hidden="true"></i> Gửi lại mail xác nhận đặt hàng</button>
		    </div>
		    <br><br><br>
	    </c:if>  --%> 
	    
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
	    
	    <c:if test="${href != 'trackOrder' }">
		    <br>
		    <a href="${pageContext.request.contextPath}/controller/customer/orderHistory" class="btn btn-info">Quay lại</a>
	    </c:if>
	    <div id="href" hidden>${href }</div>
	    <div id="idBill" hidden>${bill.id }</div>
	    <div id="email" hidden>${users.email }</div>
	     
	</div>
	
</c:if>