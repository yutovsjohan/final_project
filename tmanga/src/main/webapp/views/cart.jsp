<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	
<!-- content -->
<div class="container">
	<h1 style="font-weight: bold">Giỏ hàng</h1>
	<c:choose>
		<c:when test="${sessionScope.cart != null}">
			<div class="shopping-cart">
	 
			  <div class="column-labels">
			    <label class="product-image">Image</label>
			    <label class="product-details">Product</label>
			    <label class="product-price">Price</label>
			    <label class="product-quantity">Quantity</label>
			    <label class="product-removal">Remove</label>
			    <label class="product-line-price">Total</label>
			  </div>
			  
			 <c:forEach var="item" items="${sessionScope.cart.getList() }">
				  <div class="product">
				    <div class="product-image">
				      <a href="detail?c=${item.comic.unsignedName }" title="${item.comic.name }">
				      	<img src="<c:url value="/images/products/${item.comic.image }"/>" alt="${item.comic.name }" style="width:150px; height:200px"  >
				      </a>
				    </div>
				    <div class="product-details">
				      <a href="detail?c=${item.comic.unsignedName }" title="${item.comic.name }">
				      	<div class="product-title">${item.comic.name }</div>
				      </a>
				      <p class="product-description"></p>
				    </div>
				    <div class="product-price">
				    	<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${item.comic.sale }" /> <u>đ</u>				    
				    </div>
				    <div class="product-quantity">
				      <input type="number" value="${item.amount }" min="1">
				    </div>
				    <div class="product-removal">
				      <button class="remove-product">
				        Remove
				      </button>
				    </div>
				    <div class="product-line-price">
				    	<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${item.comic.sale * item.amount }" /> <u>đ</u>	
				    </div>
				  </div>
			  </c:forEach>			 
			 
			  <div class="totals">
			    <div class="totals-item">
			      <label>Subtotal</label>
			      <div class="totals-value" id="cart-subtotal"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${sessionScope.cart.total() }" /> <u>đ</u></div>
			    </div>
			    <div class="totals-item">
			      <label>Tax (5%)</label>
			      <div class="totals-value" id="cart-tax">3.60</div>
			    </div>
			    <div class="totals-item">
			      <label>Shipping</label>
			      <div class="totals-value" id="cart-shipping">15.00</div>
			    </div>
			    <div class="totals-item totals-item-total">
			      <label>Grand Total</label>
			      <div class="totals-value" id="cart-total">90.57</div>
			    </div>
			  </div>
			       
			 	<button class="checkout">Checkout</button>	
			</div>
		</c:when>
		<c:otherwise>
			<p>Giỏ hàng trống. <a href="index"> Quay lại để mua </a>.</p>
		</c:otherwise>
	</c:choose>
</div>

<!-- /content -->