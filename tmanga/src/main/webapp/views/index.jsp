<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	
<div class="col-md-9 ">
	<!-- silder -->
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1" class=""></li>
			<li data-target="#myCarousel" data-slide-to="2" class=""></li>

		</ol>
		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<c:set var = "number" scope = "session" value = "0"/>
			<c:forEach var="banner" items="${news}">
				<c:set var = "number" scope = "session" value = "${number + 1 }" />
				<c:choose>
				<c:when test="${number == 1}">
					<div class="item active" style="padding-left: 0px;">
				</c:when>
				<c:otherwise>
					<div class="item" style="padding-left: 0px;">
				</c:otherwise>
				</c:choose>
		 				<a href="news/${banner.title }">
		 					<img src="<c:url value="/images/news/${banner.image }" />" title="${banner.title }" alt="${banner.title }" style="width: 100%; height: 500px">
		 				</a>
		 			</div>
 			</c:forEach>	
		</div>
		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
			<span class="sr-only">Previous</span>
		</a>
		<a class="right carousel-control" href="#myCarousel" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<br>
	<!-- /silder -->
	
<!--top bán chạy-->
	<div class="row">
		<div class="col-sm-12">
			<span style="color:orange; font-weight:bold; font-size:20px">TOP BÁN CHẠY</span>
		</div>
	</div>
	<hr style="border: 1px solid orange; margin-top:0px">

	<div id="recommended-item-carousel" class="carousel slide " data-ride="carousel">
		<div class="carousel-inner">
			<c:set var = "number" scope = "session" value = "-1"/>
			<c:forEach var="ts" items="${topSelling}">
				<c:set var = "number" scope = "session" value = "${number + 1 }" />
				<c:choose>
					<c:when test="${number == 0 }">
						<div class="item active">
					</c:when>
					<c:when test="${number % 4 == 0 }">
						<div class="item ">
					</c:when>
				</c:choose>
					
					<div class="col-sm-3 col-xs-6 ">
						<div class="product-image-wrapper">
							<div class="single-products">
								<div class="productinfo text-center">
									<a href="detail?c=${ts.unsignedName }">
										<div class="btn btn-primary" style="position: absolute; left: 5px;">${number + 1 }</div>
										<img src="<c:url value="/images/products/${ts.image }" />" title="${ts.name }" alt="${ts.image }" style="width:150px; height:200px; margin-top:25px;" />
										<h5 style="height:50px; ">${ts.name }</h5>
										<h5 style="background-color: green; color:white">Phát hành: <fmt:formatDate pattern = "dd-MM-yyyy" value = "${ts.publishDate }" /></h5>
										<h5 style="font-size: 18px; color: red;"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${ts.price}" /> <u>đ</u></h5>
									</a>
								
									<c:choose>
										<c:when test="${ts.amount == 0 }">
											<a href="#" class="btn btn-danger" title="Báo tôi khi có hàng" style="background-color: crimson; border-color: crimson"><i class="fa fa-bullhorn" aria-hidden="true" ></i></a>
										</c:when>
										<c:otherwise>
											<button dataId="${ts.id }" dataName="${ts.name }" class="btn btn-info them-vao-gio-hang" title="Thêm vào giỏ hàng" style="background-color: #337ab7; border-color: #337ab7"><i class="fa fa-shopping-cart" aria-hidden="true"></i></button>
										</c:otherwise>
									</c:choose>						
									
									<c:if test="${a }">
									<c:choose>
										<c:when test="${sessionScope.account.email != null}">
											<a href="#" class="btn btn-warning" title="Hủy yêu thích"><i class="fa fa-heart" aria-hidden="true" ></i></a>
										</c:when>
										<c:otherwise>
											<a href="#" class="btn btn-warning" title="Thêm vào danh sách yêu thích"><i class="fa fa-heart-o" aria-hidden="true" ></i></a>
										</c:otherwise>
									</c:choose>
									</c:if>
									
									<a href="detail?c=${ts.unsignedName }" class="btn btn-info" title="Xem chi tiết"><i class="fa fa-search" aria-hidden="true"></i></a>
	
								</div>
							</div>
						</div>
					</div>
				
				<c:if test="${number % 4 == 3 }">
					</div>
				</c:if>
			</c:forEach>
		</div>
		
		<a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev" >
			<i class="fa fa-angle-left"></i>
		</a>
		<a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
			<i class="fa fa-angle-right"></i>
		</a>
	</div>
<!--/top bán chạy-->

<!--truyện tranh mới-->

	<div>
		<div>
			<span style="color:orange; font-weight:bold; font-size:20px">TRUYỆN TRANH MỚI </span>
			<span><a href="#" style="float:right; margin-left: 10px; margin-top: 7px;">XEM THÊM >></a></span>
			<hr style="border: 1px solid orange; margin-top:0px">
		</div>
		<div>
			<c:forEach var="nc" items="${newComic }">
				<div class="col-lg-3  col-sm-6 col-xs-6">
					<div class="product-image-wrapper">
						<div class="single-products">
							<div class="productinfo text-center">
								<a href="detail?c=${nc.unsignedName }">
									<img src="<c:url value="/images/products/${nc.image }" />" title="${nc.name }" alt="${nc.image }" style="width:150px; height:200px; margin-top:25px;" />
									<h5 style="height:50px; ">${nc.name }</h5>
									<h5 style="background-color: green; color:white">Phát hành: <fmt:formatDate pattern = "dd-MM-yyyy" value = "${nc.publishDate }" /></h5>
									<h5 style="font-size: 18px; color: red;"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${nc.price}" /> <u>đ</u></h5>
								</a>
								
								<c:choose>
									<c:when test="${nc.amount == 0 }">
										<a href="#" class="btn btn-danger" title="Báo tôi khi có hàng" style="background-color: crimson; border-color: crimson"><i class="fa fa-bullhorn" aria-hidden="true" ></i></a>
									</c:when>
									<c:otherwise>
										<button dataId="${nc.id }" dataName="${nc.name }" class="btn btn-info them-vao-gio-hang" title="Thêm vào giỏ hàng" style="background-color: #337ab7; border-color: #337ab7"><i class="fa fa-shopping-cart" aria-hidden="true"></i></button>
									</c:otherwise>
								</c:choose>						
								
								<a href="detail?c=${nc.unsignedName }" class="btn btn-info" title="Xem chi tiết"><i class="fa fa-search" aria-hidden="true"></i></a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	<!--/truyện tranh mới-->
	
	<!--Truyện tranh khác-->
	<div>
		<div>
			<span style="color:orange; font-weight:bold; font-size:20px">TRUYỆN TRANH KHÁC </span>
			<span><a href="#" style="float:right; margin-left: 10px; margin-top: 7px;">XEM THÊM >></a></span>
			<hr style="border: 1px solid orange; margin-top:0px">
		</div>
		<div>
			<c:forEach var="oc" items="${otherComic }">
				<div class="col-lg-3  col-sm-6 col-xs-6">
					<div class="product-image-wrapper">
						<div class="single-products">
							<div class="productinfo text-center">
								<a href="detail?c=${oc.unsignedName }">
									<img src="<c:url value="/images/products/${oc.image }" />" title="${oc.name }" alt="${oc.image }" style="width:150px; height:200px; margin-top:25px;" />
									<h5 style="height:50px; ">${oc.name }</h5>
									<h5 style="background-color: green; color:white">Phát hành: <fmt:formatDate pattern = "dd-MM-yyyy" value = "${oc.publishDate }" /></h5>
									<h5 style="font-size: 18px; color: red;"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${oc.price}" /> <u>đ</u></h5>
								</a>
								
								<c:choose>
									<c:when test="${oc.amount == 0 }">
										<a href="#" class="btn btn-danger" title="Báo tôi khi có hàng" style="background-color: crimson; border-color: crimson"><i class="fa fa-bullhorn" aria-hidden="true" ></i></a>
									</c:when>
									<c:otherwise>
										<button dataId="${oc.id }" dataName="${oc.name }" class="btn btn-info them-vao-gio-hang" title="Thêm vào giỏ hàng" style="background-color: #337ab7; border-color: #337ab7"><i class="fa fa-shopping-cart" aria-hidden="true"></i></button>
									</c:otherwise>
								</c:choose>								
								
								<a href="detail?c=${oc.unsignedName }" class="btn btn-info" title="Xem chi tiết"><i class="fa fa-search" aria-hidden="true"></i></a>
	
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	<!--/Truyện tranh khác-->
</div>
