<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>T-Manga</title>

<link rel="shortcut icon" href="<c:url value="/images/logo.png" />" />
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/prettyPhoto.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/price-range.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/responsive.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

</head>
<body>
<header id="header"><!--header-->
	<div class="header_top" style="background-color: #15224f;"><!--header_top-->
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<div class="contactinfo">
						<ul class="nav nav-pills">
							<li ><a href="#" style="color:white"><i class="fa fa-phone"></i> 0283 9608 712</a></li>
							<li ><a href="#" style="color:white"><i class="fa fa-envelope"></i> tmangavn@gmail.com</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="social-icons pull-right">
						<ul class="nav navbar-nav">
							<li><a href="https://www.facebook.com/TMangavn/" style="color:white"><i class="fa fa-facebook"></i></a></li>
							<li ><a href="https://twitter.com/tmangavn" style="color:white"><i class="fa fa-twitter"></i></a></li>
							<li><a href="https://plus.google.com/u/0/107972177251069959921"><i class="fa fa-google-plus" style="color:white"></i></a></li>
							
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div><!--/header_top-->
	
	<div class="header-middle"><!--header-middle-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<div class="logo pull-left">
						<a href="index.jsp"><img src="<c:url value="/images/logo.png" />" style="width:40px; height:40px;" alt="" /><span style="font-size:24px; font-weight:bold;"> T-Manga</span></a>
					</div>						
				</div>
				<div class="col-sm-8">
					<div class="shop-menu pull-right">
						<ul class="nav navbar-nav">
							<li><a href="#"><i class="fa fa-user" ></i> Đăng ký</a></li>
							<li><a href="#"><i class="fa fa-lock" ></i> Đăng nhập</a></li>
							<li><a href="#" id="gio-hang"><i class="fa fa-shopping-cart"></i>Giỏ hàng trống</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div><!--/header-middle-->



	<div class="container"><!--header-bottom-->
		<nav class="navbar navbar-default" style="font-size:15px; background-color:white; border-color:white" >
			<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Đóng / mở menu</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				</button>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<div class="col-sm-7 col-lg-7 col-xs-12 col-md-7">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp"  >Trang chủ</a></li>							
					<li><a href="#" >Tin tức</a></li>
					<li><a href="#" >Giới thiệu</a></li>
					<li><a href="#" >Liên hệ</a></li>
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Hỗ trợ khách hàng <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#" >Theo dõi đơn hàng</a></li>
							<li><a href="#">Hướng dẫn đặt hàng</a></li>
							<li><a href="#">Phương thức vận chuyển</a></li>
                               <li><a href="#">Phương thức thanh toán</a></li>
                               <li><a href="#">Chính sách đổi trả</a></li>
						</ul>
					</li>
				</ul>
				</div>
				<div class="col-sm-5 col-lg-5 col-xs-12 col-md-5">
				<ul class="nav navbar-nav navbar-right">
					<form class="navbar-form" role="search" method="get" action="#">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Tìm truyện" name="search">
							<div class="input-group-btn">
								<button class="btn btn-default" type="submit" style="height:34px"><i class="glyphicon glyphicon-search"></i></button>
							</div>
						</div>
					</form>
				</ul>
				</div>
			</div><!--/.nav-collapse -->
			</div><!--/.container-fluid -->
		</nav>
	</div><!--/header-bottom-->

</header><!--/header-->	
	
<!-- content -->
<section>
	<div class="container">
		<div class="row">
			<!-- menu -->
			<div class="col-md-3">
				<div class="panel panel-primary">
					<div class="panel-heading">Danh mục sản phẩm</div>
					<div class="panel-body">
						<c:forEach items="${categories}" var="category">						
							<a href="#">
								<div id="menu_doc" dataHref="#">
									${category.name }
								</div>
								<hr>						
							</a>
						</c:forEach>
					</div>
				</div>
				
				<div class="panel panel-primary">
					<div class="panel-heading">Tác giả</div>
					<div class="panel-body">
						<% for(int i = 1; i <= 4; i++) { %>
						<a href="#">
							<div id="menu_doc" dataHref="#">
								Fujiko.F.Fujio 
							</div>
							<% if(i != 4) { %>
							<hr>	
							<% } %>
						</a>
						<% } %>
					</div>
				</div>
								
				<div class="panel panel-primary">
					<div class="panel-heading">Nhà xuất bản</div>
					<div class="panel-body">
						<% for(int i = 1; i <= 4; i++) { %>
						<a href="#">
							<div id="menu_doc" dataHref="#">
								Kim Đồng 
							</div>
							<% if(i != 4) { %>
							<hr>	
							<% } %>
						</a>
						<% } %>
					</div>
				</div>		
							
			</div>
			<!-- /menu -->
			
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
						<div class="item active" style="padding-left: 0px;">
							<a href="#">
								<img src="<c:url value="/images/news/pokemon-movie-20-i-choose-you.png" />" title="" alt="" style="width: 100%; height: 500px">
							</a>
						</div>
						<div class="item" style="padding-left: 0px;">
							<a href="#">
								<img src="<c:url value="/images/news/pokemon-movie-20-i-choose-you.png" />" title="" alt="" style="width: 100%; height: 500px">
							</a>
						</div>
						<div class="item" style="padding-left: 0px;">
							<a href="#">
								<img src="<c:url value="/images/news/pokemon-movie-20-i-choose-you.png" />" title="" alt="" style="width: 100%; height: 500px">
							</a>
						</div>
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
			
		</div>
	</div>
</section>
<!-- /content -->
	
<!--footer-->
<hr>
<footer class="footer1">
	<div class="container">
		<div class="row"><!-- row -->
        	<div class="col-lg-3 col-md-3"><!-- widgets column left -->
                <ul class="list-unstyled clear-margins"><!-- widgets -->
            		<li class="widget-container widget_nav_menu"><!-- widgets list -->
                    	<h1 class="title-widget">Hỗ trợ khách hàng</h1>
                      	<ul>
                           	<li><a  href="#"><i class="fa fa-angle-double-right"></i> Hướng dẫn đặt hàng</a></li>
                            <li><a  href="#"><i class="fa fa-angle-double-right"></i> Chính sách đổi trả</a></li>
                            <li><a  href="#"><i class="fa fa-angle-double-right"></i> Phương thức vận chuyển</a></li>
                            <li><a  href="#"><i class="fa fa-angle-double-right"></i> Phương thức thanh toán</a></li>
                       	</ul>
                    </li>
                </ul>
			</div><!-- widgets column left end -->
                
            <div class="col-lg-3 col-md-3"><!-- widgets column left -->
            	<ul class="list-unstyled clear-margins"><!-- widgets -->
                	<li class="widget-container widget_nav_menu"><!-- widgets list -->
                    	<h1 class="title-widget">T-Manga</h1>
                       	<ul>
							<li><a  href="#"><i class="fa fa-angle-double-right"></i>  Giới thiệu</a></li>
                               <li><a  href="#"><i class="fa fa-angle-double-right"></i>  Chính sách bồi hoàn</a></li>
                               <li><a  href="#"><i class="fa fa-angle-double-right"></i>  Điều khoản sử dụng</a></li>
							<li><a  href="#"><i class="fa fa-angle-double-right"></i>  Chính sách bảo mật</a></li>
						</ul>
                    </li>
				</ul>
            </div><!-- widgets column left end -->
                
            <div class="col-lg-3 col-md-3"><!-- widgets column left -->
            	<ul class="list-unstyled clear-margins"><!-- widgets -->
                	<li class="widget-container widget_nav_menu"><!-- widgets list -->
                    	<h1 class="title-widget">Tài khoản của bạn</h1>
                       	<ul>
			 				<li><a href="#"><i class="fa fa-angle-double-right"></i> Xem giỏ hàng</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i> Thông tin cá nhân</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i> Sách yêu thích</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i> Lịch sử giao dịch</a></li>
						</ul>
                    </li>
				</ul>
           	</div><!-- widgets column left end -->
                
            <div class="col-lg-3 col-md-3"><!-- widgets column center -->
            	<ul class="list-unstyled clear-margins"><!-- widgets -->
                 	<li class="widget-container widget_recent_news"><!-- widgets list -->
                    	<h1 class="title-widget">Liên hệ cho chúng tôi </h1>
                       	<div class="footerp"> 
                           	<h2 class="title-median">Cửa hàng sách T-Manga</h2>
							<p><b>Địa chỉ : </b>96 Phạm Đình Hổ P2 Q6 TPHCM </p>
                               <p><b>Gmail :</b> <a href="#">tmangavn@gmail.com</a></p>
                               <p><b>Điện thoại </b>
							<b style="color:#ffc106;">(8AM to 10PM):</b> <br>  0283 9608 712 , 0126 737 5656  </p>
   						</div>
						<div class="social-icons">
                           	<ul class="nomargin">
                           		<a href="https://www.facebook.com/TMangavn/"><i class="fa fa-facebook-square fa-3x social-fb" id="social"></i></a>
            					<a href="https://twitter.com/tmangavn"><i class="fa fa-twitter-square fa-3x social-tw" id="social"></i></a>
            					<a href="https://plus.google.com/u/0/107972177251069959921"><i class="fa fa-google-plus-square fa-3x social-gp" id="social"></i></a>
							</ul>
                       	</div>
                    </li>
				</ul>
        	</div>
		</div>
	</div>
	
</footer>

<div class="footer-bottom">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<div class="copyright">© 2019, T-Manga, All rights reserved</div>
			</div>

			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<div class="design">Design by tmanga</a></div>
			</div>
		</div>
	</div>
</div>


<!--footer-->
	
	<div id="modal-da-them-vao-gio-hang" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">
			<!-- Modal content-->
			<div class="modal-content">	      
				<div class="modal-body">
					<p>Đã thêm <b></b> vào giỏ hàng.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<script src="<c:url value="/resources/js/jquery.js" />" ></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
	<script src="<c:url value="/resources/js/jquery.scrollUp.min.js" />" ></script>
	<script src="<c:url value="/resources/js/price-range.js" />" ></script>
    <script src="<c:url value="/resources/js/jquery.prettyPhoto.js" />" ></script>
    <script src="<c:url value="/resources/js/main.js" />" ></script>

</body>
</html>