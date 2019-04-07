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
				Tài khoản của bạn
			</div>
			<div class="panel-body">
				<a href="home">
				<c:choose>
					<c:when test="${title == 'Tài khoản của bạn' }"><div id="menu_doc" class="actived"> </c:when>
					<c:otherwise><div id="menu_doc"  > </c:otherwise>
				</c:choose>				
						Thông tin của tôi
					</div>
				</a>
				<hr>
				<a href="information">
				<c:choose>
					<c:when test="${title == 'Chỉnh sửa thông tin cá nhân' }"><div id="menu_doc" class="actived"> </c:when>
					<c:otherwise><div id="menu_doc"  > </c:otherwise>
				</c:choose>					
						Chỉnh sửa thông tin cá nhân
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
				<c:choose>
					<c:when test="${title == 'Danh sách sản phẩm yêu thích' }"><div id="menu_doc" class="actived"> </c:when>
					<c:otherwise><div id="menu_doc"  > </c:otherwise>
				</c:choose>
						Sản phẩm yêu thích
					</div>
				</a>
				<hr>
				<a href="orderHistory">
				<c:choose>
					<c:when test="${title == 'Đơn hàng đã đặt' }"><div id="menu_doc" class="actived"> </c:when>
					<c:otherwise><div id="menu_doc"  > </c:otherwise>
				</c:choose>
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