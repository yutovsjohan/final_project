<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	
<!-- content -->
<div class="container">
	<h1 style="font-weight: bold">Giỏ hàng</h1>
	<c:if test="${mes != null }">
		<div class="alert alert-${alert }" role="alert">
			${mes }
		 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		</div>
		<br>
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
					<c:choose>
						<c:when test="${sessionScope.account != null}">		
							<form action="${pageContext.request.contextPath}/controller/order" method="post">			
								<div class="panel panel-primary">
						            <div class="panel-heading">
						                <h3 class="panel-title">Thông tin khách hàng</h3>
						            </div>
						            <div class="panel-body">
						                <table class="table table-hover">                    
						                    <tr>
						                        <th>Họ tên</th>
						                        <td><input type="text" class="form-control" name="name" value="${sessionScope.account.name }" readonly placeholder=""></td>
						                    </tr>
						                    <tr>
						                        <th>Email</th>
						                        <td><input type="email" class="form-control" name="email" value="${sessionScope.account.email }" readonly placeholder=""></td>
						                    </tr>
						                    <tr>
						                        <th>Điện thoại</th>
						                        <td><input type="text" class="form-control" name="phone" value="${sessionScope.account.phone }" readonly placeholder=""></td>
						                    </tr>
						                    <tr>
						                        <th>Địa chỉ</th>
						                        <td><input type="text" class="form-control" name="address" value="${sessionScope.account.address }" readonly placeholder=""></p></td>
						                    </tr>
						                    <tr>
						                        <th>Lời nhắn</th>
						                        <td><textarea rows="4" cols="50" class="form-control" name="note" placeholder="Nhập lời nhắn" id="note"></textarea></td>
						                    </tr>	
						                    <tr>
						                    	<th></th>
						                    	<td><a href="${pageContext.request.contextPath}/controller/customer/edit"><button type="button" class="btn btn-info">Chỉnh sửa thông tin cá nhân</button></a></td>
						                    </tr>			                    
						                </table>
						            </div>
						        </div>
						        
					            <button id="order" type="button" class="btn btn-success" style="float:right; display:none"  >Đặt hàng</button>
				            </form>
				            <button id="r_order" type="button" class="btn btn-info" style="float:left" >Tiến hành đặt hàng</button></a>
						</c:when>
						<c:otherwise>
							<p class="alert alert-danger">Bạn cần phải <a href="${pageContext.request.contextPath}/controller/login">đăng nhập</a> để đặt hàng hoặc <a href="${pageContext.request.contextPath}/controller/signup">đăng ký</a> tài khoản</p> 
						</c:otherwise>
					</c:choose>
				
				
			</c:when>
			<c:otherwise>
				<p class="glyphicon glyphicon-ok"> <span style="font-family:'Roboto', sans-serif">Giỏ hàng của bạn hiện chưa có sản phẩm nào, hãy <a href="${pageContext.request.contextPath}/controller/index">quay lại để mua hàng.</a> </span> </p>
			</c:otherwise>
		</c:choose>		
	</div>
</div>

<!-- /content -->