<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	
<!-- content -->
<div class="container">
	<h1 style="font-weight: bold">Giỏ hàng</h1>
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
					      <a href="detail?c=${item.comic.unsignedName }" title="${item.comic.name }">
					      	<img src="<c:url value="/images/products/${item.comic.image }"/>" alt="${item.comic.name }" >
					      </a>
					    </div>
					    <div class="product-details">
					      <a href="detail?c=${item.comic.unsignedName }" title="${item.comic.name }">
					      	<div class="product-title">${item.comic.name }</div>
					      </a>
					      <p class="product-description"></p>
					    </div>
					    <div class="product-price">
					    	<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${item.comic.sale }" /> 				    
					    </div>
					    <div class="product-quantity">
					      <input type="number" value="${item.amount }" min="1" max="${item.comic.amount }" dataId=${item.comic.id } >
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
				 	<button class="checkout">Cập nhật lại</button>
				 	<a href="deletecart"><button class="btn btn-danger" >Xóa giỏ hàng</button></a>	
				</div>
			</c:when>
			<c:otherwise>
				<p>Giỏ hàng trống. <a href="index"> Quay lại để mua </a>.</p>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<!-- /content -->