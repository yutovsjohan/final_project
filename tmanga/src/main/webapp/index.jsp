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
	<!--header-->
<header id="header">
	<div class="header_top" style="background-color: #15224f;"><!--header_top-->
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<div class="contactinfo">
						<ul class="nav nav-pills">
							<li >
								<a href="#" style="color:red">
								<li class="fa fa-phone" style="color:red">0283 9608 712	</li>					
								</a>
							 </li>
							
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
					 <div class="dropdown">
					    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Danh Mục Sản Phẩm
					    <span class="caret"></span></button>
					    <ul class="dropdown-menu">
					      <li><a href="#">Pokemon Đặc Biệt</a></li>
					      <li><a href="#">Conan Thám Tử Lừng Danh</a></li>
					      <li><a href="#">JavaScript</a></li>
					    </ul>
					  </div>
				</div>
				<div class="col-sm-5 col-lg-5 col-xs-12 col-md-5">
				<ul class="nav navbar-nav navbar-right">
					<form class="form-group" role="search" method="get" action="#">
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
	<div class="row">
		<div class="col-sm-1 col-md-1"></div>
		<div class="col-sm-4 col-lg-4 col-xs-12 col-md-4"><a href="#" style="color:blue">Trang Chủ </a> / <a href="#" style="color:blue">Pokemon Đặc Biệt</a></div>
	</div>
<hr>
<!-- content -->
<section>
		<div class="row" >
			<div class="col-sm-4 col-lg-4 col-xs-12 col-md-4">				
					<img src="<c:url value="/images/products/pokemon-tap-16.png"/>" title="" alt="" style="width: 100%; height: 300px ;width:250px">				
			</div>
			<div class="col-sm-7 col-lg-7 col-md-7">
				
				<div class="row">
					<h2>Pokemon Đặc Biệt</h2>
					<h4 >Tình Trạng : <b style="color:green">Còn Hàng</b></h4>
					<hr>
					<h4>Tác giả :<a href="#">Fujiko.F.Fujio</a></h4>
					<h4>Gía Bìa : 20000 <u>VNĐ</u> </h4>
					<h4>Kích Thước : 144 x 28 </h4>
					<h4>Số Trang : 99</h4>
					<h4>Phát hành :28-05-2015</h4>
					
					<div class="row">
						<div class="col-sm-9 col-lg-9 col-md-9">
							<span style="color:orange; font-weight:bold; font-size:20px">Thông Tin Khuyến Mãi</span>
							<hr style="border: 1px solid orange; margin-top:0px">
							<ul style="padding:1px;">						
								<li class="glyphicon glyphicon-ok" style="padding:1px;">Giao hàng trong 24h tại khu vực TP.HCM</li>
								<li class="glyphicon glyphicon-ok" style="padding:1px;">Đóng gói 3 lớp cực kỳ cẩn thận cho đơn hàng sách ở xa</li>
								<li class="glyphicon glyphicon-ok" style="padding:1px;">Miễn phí đổi trả</li>
						</div>					
					</div>
					
					<div class="row">
						<div class="col-sm-2 col-lg-2 col-md-2">
							<p>Số Lượng</p>
							<select name="quantity" id="quantity" style="width:50px">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
							</select>						 
						</div>			
								
						<div class="col-sm-2 col-lg-2 col-md-2">
							<button type="button" class="btn btn-info" >Đặt Hàng</button>
						</div>
						
						<div class="col-sm-2 col-lg-2 col-md-2">
							<button type="button" class="btn btn-info" style="background-color:#9400d3;border-color:#9400d3">Yêu Thích</button>
						</div>
												
					</div>	
						
				</div>
								
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-1 col-lg-1 col-md-1"></div>
			<div class="col-sm-10 col-lg-10 col-md-10">
				<span style="color:orange; font-weight:bold; font-size:20px">Thông Tin Khuyến Mãi</span>
				<hr style="border: 1px solid orange; margin-top:0px">
			</div>
		</div>
		
		<div class="row">
			<div>
						<%int dem=0; %>
						<% for(int j = 1; j <= 4; j++) {  dem++; %>
						<div class="col-lg-3  col-sm-3 col-xs-3">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<a href="#" title="">
											<img src="<c:url value="/images/products/pokemon-tap-16.png" />" alt="" style="width:100px; height:150px; margin-top:25px;" />
											<h5 style="height:50px; ">Pokemon Đặc Biệt tập 16</h5>
											<h5 style="color:black">Phát hành: 26-08-2015</h5>

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
		</div>
				
		<div class="row">
			<div class="col-sm-1 col-lg-1 col-md-1"></div>
			<div class="col-sm-10 col-lg-10 col-md-10">
				<span style="color:orange; font-weight:bold; font-size:20px"> Giới thiệu </span>
				<hr style="border: 1px solid orange; margin-top:0px">
			</div>			
			
		</div>
		<div class="row">
			<div class="col-sm-1 col-lg-1 col-md-1"></div>
			<div class="col-sm-10 col-lg-10 col-md-10">
				<p>Conan Bộ Đặc Biệt (thường gọi là Thám tử lừng danh Conan - Bộ đặc biệt) là tuyển tập truyện tranh Conan ngắn vẽ bởi trợ lý của Gosho Aoyama, được xuất bản tại Shogakukan dưới tên Ladybug Comics. Những mẩu truyện trong bộ đặc biệt này không hề liên quan đến mạch truyện, cốt truyện chính của truyện tranh conan. Ngoài chính truyện, chúng ta cũng có thể tìm đọc những cuốn truyện này trong lúc chờ phát hành những tập mới. Tuy không phải do tác giả vẽ và lên mạch truyện, nhưng đây được xem như phiên bản 2 của truyện conan (do trợ lý của tác giả vẽ nhé). </p>
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