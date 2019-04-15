<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${(sessionScope.account != null)}">
	<div class="col-md-9 ">
		<c:if test="${!empty sessionScope.account.comics}">
			<c:forEach var="comic" items="${sessionScope.account.comics }">
				<div class="col-lg-3  col-sm-6 col-xs-6" >
					<div class="product-image-wrapper">
						<div class="single-products">
							<div class="productinfo text-center">
								<a href="${pageContext.request.contextPath}/controller/detail?c=${comic.unsignedName }" title="${comic.name }">
									<img src="<c:url value="/images/products/${comic.image }" />" alt="${comic.name }" style="width:150px; height:200px; margin-top:25px;" />
									<h5 style="height:50px; ">${comic.name }</h5>
									<h5 style="background-color: green; color:white">Phát hành: <fmt:formatDate pattern = "dd-MM-yyyy" value = "${comic.publishDate }" /></h5>								
									<h5><span style="margin-right: 15px; font-size: 18px;  color: red;"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${comic.sale}" /> <u>đ</u></span></h5>				
								</a>
								<c:choose>
									<c:when test="${comic.amount == 0 }">
										<a href="${pageContext.request.contextPath}/controller/#" class="btn btn-danger" title="Báo tôi khi có hàng" style="background-color: crimson; border-color: crimson"><i class="fa fa-bullhorn" aria-hidden="true" ></i></a>
									</c:when>
									<c:otherwise>
										<button dataId="${comic.id }" dataName="${comic.name }" class="btn btn-info them-vao-gio-hang" title="Thêm vào giỏ hàng" style="background-color: #337ab7; border-color: #337ab7"><i class="fa fa-shopping-cart" aria-hidden="true"></i></button>
									</c:otherwise>
								</c:choose>
								
								<c:set var = "flag" scope = "session" value = "true"/>
								
								<c:choose>
									<c:when test="${flag == true }">
										<a class="btn btn-warning favoritelist" data="1" dataId=${comic.id }><i class="fa fa-heart" title="Hủy yêu thích" aria-hidden="true" ></i></a>
									</c:when>
									<c:otherwise>
										<a class="btn btn-warning favoritelist" data="0" dataId=${comic.id }><i class="fa fa-heart-o" title="Thêm vào danh sách yêu thích" aria-hidden="true" ></i></a>
									</c:otherwise>
								</c:choose>										
										
								<a href="${pageContext.request.contextPath}/controller/detail?c=${comic.unsignedName }" class="btn btn-info" title="Xem chi tiết"><i class="fa fa-search" aria-hidden="true"></i></a>						
							</div>
						</div>
					</div>
				</div>	
			</c:forEach>
			<input id="route" value="${pageContext.request.contextPath}/controller/favoritelist" hidden/>
		</c:if>
		
		<c:if test="${empty sessionScope.account.comics}">
			<h3>Hiện tại bạn chưa có sản phẩm yêu thích</h3>
		</c:if>
	</div>
</c:if>