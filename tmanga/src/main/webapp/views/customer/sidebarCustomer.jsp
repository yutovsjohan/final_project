<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!-- menu -->
<c:if test="${(sessionScope.account != null)}">
	<div class="col-md-3 ">
		<div class="panel panel-primary">
			<div class="panel-heading">
				Tài khoản của tôi
			</div>
			<div class="panel-body">
				
				<a href="edit">
					<div id="menu_doc" <c:if test="${views == 'customer/customerEditInformation' }">class="actived"</c:if> >					
						Thông tin tài khoản
					</div>
				</a>
				<!-- <hr>
				<a href="#">
					<div id="menu_doc">
						Thông báo của tôi
					</div>
				</a> -->
				<hr>
				<a href="favoriteList">
					<div id="menu_doc" <c:if test="${views == 'customer/favoriteList' }">class="actived"</c:if> >					
						Sản phẩm yêu thích
					</div>
				</a>
				<hr>
				<a href="orderHistory">
					<div id="menu_doc" <c:if test="${views == 'customer/orderHistory' }">class="actived"</c:if> >					
						Đơn hàng đã đặt
					</div>
				</a>
				<!--<hr>
				<a href="#">				
					<div id="menu_doc">
						Sản phẩm đã mua
					</div>
				</a> -->
				<hr>
				<a href="../logout">
					<div id="menu_doc" dataHref="logout">
						Đăng xuất
					</div>
				</a>
			</div>
		</div>			
	</div>
</c:if>
<!-- /menu -->