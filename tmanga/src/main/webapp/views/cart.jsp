<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	
<!-- content -->
<div class="container">
	<h1 style="font-weight: bold">Giỏ hàng</h1>
	<c:if test="${mes != '' }">
		<div class="alert alert-${alert }" role="alert">
			${mes }
		 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		</div>
		<br><br>
	</c:if> 	
	<div id="mycart">
		<c:choose>			
			<c:when test="${sessionScope.cart != null}">				
				<div class="shopping-cart">			 
				  <div class="column-labels">
				    <label class="product-image">Hình</label>
				    <label class="product-details">Sản phẩm</label>
				    <label class="product-price">Giá (VNĐ)</label>
				    <label class="product-quantity">Số lượng</label>
				    <label class="product-removal">Xóa</label>
				    <label class="product-line-price">Tổng (VNĐ)</label>
				  </div>
				 
				 <c:forEach var="item" items="${sessionScope.cart.getList() }">
					  <div class="product">
					    <div class="product-image">
					      <a href="${pageContext.request.contextPath}/controller/detail?c=${item.comic.unsignedName }" title="${item.comic.name }">
					      	<img src="<c:url value="/images/products/${item.comic.image }"/>" alt="${item.comic.name }" >
					      </a>
					    </div>
					    <div class="product-details">
					      <a href="${pageContext.request.contextPath}/controller/detail?c=${item.comic.unsignedName }" title="${item.comic.name }">
					      	<div class="product-title">${item.comic.name }</div>
					      </a>
					      <p class="product-description"></p>
					    </div>
					    <div class="product-price">
					    	<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${item.comic.sale }" /> 				    
					    </div>
					    <div class="product-quantity">
					      <input type="number" value="${item.amount }" min="1" max="${item.comic.amount }" dataId=${item.comic.id } id="price">
					    </div>
					    <div class="product-removal">
					      <button class="remove-product" dataId=${item.comic.id } >
					        Xóa
					      </button>
					    </div>
					    <div class="product-line-price">
					    	<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${item.comic.sale * item.amount }" /> 	
					    </div>
					  </div>
				  </c:forEach>			 
				 
				  <div class="totals">
				    <div class="totals-item">
				      <label>Cộng tiền hàng</label>
				      <div class="totals-value" id="cart-subtotal"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${sessionScope.cart.total() }" /> </div>
				    </div>			    
				    <div class="totals-item">
				      <label>Phí vận chuyển</label>
				      <div class="totals-value" id="cart-shipping">15.000</div>
				    </div>
				    <hr style="border: 1px solid orange; width: 350px; float: right">
				    <div class="totals-item totals-item-total" style="font-size:20px">
				      <label>Thành tiền</label>
				      <div class="totals-value" id="cart-total" style="font-size: 20px; color: red; font-weight: bold;"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${sessionScope.cart.total() + 15000 }" /></div>
				    </div>
				  </div>
				 	<a href="${pageContext.request.contextPath}/controller/deletecart" id="deletecart"><button class="btn btn-danger" >Xóa giỏ hàng</button></a>	
				</div>
				<br><br>
				
				<c:if test="${sessionScope.account == null}">		
					<p class="alert alert-danger">Bạn cần phải <a href="${pageContext.request.contextPath}/controller/login">đăng nhập</a> để đặt hàng hoặc <a href="${pageContext.request.contextPath}/controller/signup">đăng ký</a> tài khoản</p>
				</c:if> 
				
				<c:if test="${sessionScope.account != null}">
					<c:if test="${sessionScope.account.active == 2}">				
						<p style="color:red">Chưa xác nhận tài khoản</p>
						<button class="btn btn-info" id="send-email-confirm-account" dataId="${sessionScope.account.id}">Bấm vào đây để gửi mail xác nhận tài khoản</button>				
					</c:if>
					
					<c:if test="${sessionScope.account.active != 2}">
						<a href="${pageContext.request.contextPath}/controller/shipping" class="btn btn-info" style="float:right">Tiến hành đặt hàng</a>
					</c:if>
				</c:if>
			</c:when>
			<c:otherwise>
				<div style="background-color:white">
					<center>
						<img src="<c:url value="/images/cart-empty.png" />" class="img-responsive" />
						<p> Giỏ hàng của bạn hiện chưa có sản phẩm nào.</p>
						<p><a class="btn btn-info" href="${pageContext.request.contextPath}/controller/index">Tiếp tục mua sắm</a></p>
						<br>
					</center>
				</div>
			</c:otherwise>
		</c:choose>		
	</div>
</div>

<!-- /content -->