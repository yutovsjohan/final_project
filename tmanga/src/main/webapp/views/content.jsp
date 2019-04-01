<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	

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
			<% 
				int dem = 0;
				for(int i = 1; i <= 3; i++) { 
			%>
			<div class="item <% if(i == 1) { %> active <% } %>">
				<% for(int j = 1; j <= 4; j++) {  dem++; %>
				<div class="col-sm-3 col-xs-6 ">
					<div class="product-image-wrapper">
						<div class="single-products">
							<div class="productinfo text-center">
								<a href="#" title="Pokemon Đặc Biệt tập 16">
									<div class="btn btn-primary" style="position: absolute; left: 5px;"><%= dem %></div>
									<img src="<c:url value="/images/products/pokemon-tap-16.png" />" alt="Pokemon Đặc Biệt tập 16" style="width:150px; height:200px; margin-top:25px;" />												
									<h5 style="height:50px; ">Pokemon Đặc Biệt tập 16</h5>
									<h5 style="background-color: green; color:white">Phát hành: 26-08-2015</h5>

									<h5>
										<span style="margin-right: 15px; font-size: 18px;  color: red;">20,000 <u>đ</u></span>
										<span style="text-decoration: line-through; color: black; font-size: 12px;">22,000 <u>đ</u></span>
									</h5>
								</a>

								<button dataId="4" dataName="Pokemon Đặc Biệt tập 16" class="btn btn-info them-vao-gio-hang" title="Thêm vào giỏ hàng" style="background-color: #337ab7; border-color: #337ab7" ><i class="fa fa-shopping-cart" aria-hidden="true"></i></button>	

								<a href="#" class="btn btn-info" title="Xem chi tiết" ><i class="fa fa-search" aria-hidden="true"></i></a>

							</div>
						</div>
					</div>
				</div>
				<% } %>		
			</div>
			<% } %>	
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
			<% for(int j = 1; j <= 4; j++) {  dem++; %>
			<div class="col-lg-3  col-sm-6 col-xs-6">
				<div class="product-image-wrapper">
					<div class="single-products">
						<div class="productinfo text-center">
							<a href="#" title="">
								<img src="<c:url value="/images/products/pokemon-tap-16.png" />" alt="" style="width:150px; height:200px; margin-top:25px;" />
								<h5 style="height:50px; ">Pokemon Đặc Biệt tập 16</h5>
								<h5 style="background-color: green; color:white">Phát hành: 26-08-2015</h5>

								<h5 style="font-size: 18px; color: red;">20,000 <u>đ</u></h5>
							</a>

							<button dataId="430" dataName="" class="btn btn-info them-vao-gio-hang" title="Thêm vào giỏ hàng" style="background-color: #337ab7; border-color: #337ab7"><i class="fa fa-shopping-cart" aria-hidden="true"></i></button>						
							
							<a href="#" class="btn btn-info" title="Xem chi tiết"><i class="fa fa-search" aria-hidden="true"></i></a>

						</div>
					</div>
				</div>
			</div>
			<% } %>
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
			<% for(int j = 1; j <= 4; j++) {  dem++; %>
			<div class="col-lg-3  col-sm-6 col-xs-6">
				<div class="product-image-wrapper">
					<div class="single-products">
						<div class="productinfo text-center">
							<a href="#" title="">
								<img src="<c:url value="/images/products/pokemon-tap-16.png" />" alt="" style="width:150px; height:200px; margin-top:25px;" />
								<h5 style="height:50px; ">Pokemon Đặc Biệt tập 16</h5>
								<h5 style="background-color: green; color:white">Phát hành: 26-08-2015</h5>

								<h5 style="font-size: 18px; color: red;">20,000 <u>đ</u></h5>
							</a>
							<a href="#" class="btn btn-danger" title="Báo tôi khi có hàng" style="background-color: crimson; border-color: crimson"><i class="fa fa-bullhorn" aria-hidden="true" ></i></a>
							
							<a href="#" class="btn btn-info" title="Xem chi tiết"><i class="fa fa-search" aria-hidden="true"></i></a>

						</div>
					</div>
				</div>
			</div>
			<% } %>
		</div>
	<!--/Truyện tranh khác-->
