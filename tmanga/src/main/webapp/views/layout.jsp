<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${title }</title>

<link rel="shortcut icon" href="<c:url value="/images/logo.png" />" />
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/prettyPhoto.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/price-range.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/responsive.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/cart.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/register.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">

</head>
<body style="background-color:#f0f0fa;">

<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = 'https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.11';
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

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
						<a href="${pageContext.request.contextPath}/controller/index"><img src="<c:url value="/images/logo.png" />" style="width:40px; height:40px;" alt="" /><span style="font-size:24px; font-weight:bold;"> T-Manga</span></a>
					</div>						
				</div>
				<div class="col-sm-8">
					<div class="shop-menu pull-right">
						<ul class="nav navbar-nav">
							<c:choose>
								<c:when test="${sessionScope.account.email != null}">
									<li><a href="${pageContext.request.contextPath}/controller/customer/edit"><i class="fa fa-user" ></i> Xin chào ${sessionScope.account.name }</a></li>
									<li><a href="${pageContext.request.contextPath}/controller/logout"><i class="fa fa-sign-out" ></i> Đăng xuất</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath}/controller/signup"><i class="fa fa-user" ></i> Đăng ký</a></li>
									<li><a href="${pageContext.request.contextPath}/controller/login"><i class="fa fa-lock" ></i> Đăng nhập</a></li>
								</c:otherwise>
							</c:choose>
							
							<c:choose>
								<c:when test="${sessionScope.cart != null}">
									<li><a href="${pageContext.request.contextPath}/controller/cart" id="gio-hang"><i class="fa fa-shopping-cart"></i>Giỏ hàng <span class="itemcount">${sessionScope.cart.quantity() }</span></a></li>
									<span id="itemcount" hidden> ${sessionScope.cart.quantity() }</span>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath}/controller/cart" id="gio-hang"><i class="fa fa-shopping-cart"></i>Giỏ hàng <span class="itemcount">0</span></a></li>
									<span id="itemcount" hidden>0</span>
								</c:otherwise>
							</c:choose>
							
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
					<li><a href="${pageContext.request.contextPath}/controller/index" <c:if test="${views == 'index' }">class="actived"</c:if> >Trang chủ</a></li>							
					<li><a href="${pageContext.request.contextPath}/controller/#" <c:if test="${views == 'news' }">class="actived"</c:if>>Tin tức</a></li>
					<li><a href="${pageContext.request.contextPath}/controller/introduce" <c:if test="${views == 'introduce' }">class="actived"</c:if> >Giới thiệu</a></li>
					<li><a href="${pageContext.request.contextPath}/controller/contact" <c:if test="${views == 'contact' }">class="actived"</c:if> >Liên hệ</a></li>
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Hỗ trợ khách hàng <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/controller/trackOrder" <c:if test="${views == 'trackOrder' }">class="actived"</c:if> >Theo dõi đơn hàng</a></li>
							<li><a href="${pageContext.request.contextPath}/controller/#">Hướng dẫn đặt hàng</a></li>
							<li><a href="${pageContext.request.contextPath}/controller/#">Phương thức vận chuyển</a></li>
                            <li><a href="${pageContext.request.contextPath}/controller/#">Phương thức thanh toán</a></li>
                            <li><a href="${pageContext.request.contextPath}/controller/#">Chính sách đổi trả</a></li>
						</ul>
					</li>
				</ul>
				</div>
				<div class="col-sm-5 col-lg-5 col-xs-12 col-md-5">
				<ul class="nav navbar-nav navbar-right">
					<form class="navbar-form" role="search" method="get" action="${pageContext.request.contextPath}/controller/product">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Tìm truyện" name="q">
							<input hidden name="a" value="search"/>
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
			<c:if test="${sb != '' }">
				<c:import url="${sb }.jsp" />
			</c:if>			
			
			<c:if test="${views != ''}">
				<c:import url="${views }.jsp" />
			</c:if>
			
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
					<p></p>
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
    <script src="<c:url value="/resources/js/jquery.elevatezoom.js" />" ></script>
    <script src="<c:url value="/resources/js/cart.js" />" ></script>
    <script src="<c:url value="/resources/js/style.js" />" ></script>

</body>
</html>