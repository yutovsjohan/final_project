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
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/font-awesome.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/prettyPhoto.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/price-range.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/animate.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/responsive.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/product.css" />"
	rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body style="background-color: #f0f0fa;">
	<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Raleway", sans-serif
}
</style>
<body class="w3-light-grey">

	<!-- Top container -->
	<div class="w3-bar w3-top w3-black w3-large" style="z-index: 4">
		<button
			class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey"
			onclick="w3_open();">
			<i class="fa fa-bars"></i>  Menu
		</button>
		<span class="w3-bar-item w3-right">T-Manga</span>
	</div>

	<!-- Sidebar/menu -->
	<nav class="w3-sidebar w3-collapse w3-white w3-animate-left"
		style="z-index:3;width:300px;" id="mySidebar">
	<br>
	<div class="w3-container w3-row">
		<div class="w3-col s4">
			<img src="/w3images/avatar2.png" class="w3-circle w3-margin-right"
				style="width: 46px">
		</div>
		<div class="w3-col s8 w3-bar">
			<span>Welcome, <strong>Admin</strong></span><br> <a href="#"
				class="w3-bar-item w3-button"><i class="fa fa-envelope"></i></a> <a
				href="#" class="w3-bar-item w3-button"><i class="fa fa-user"></i></a>
			<a href="#" class="w3-bar-item w3-button"><i class="fa fa-cog"></i></a>
		</div>
	</div>
	<hr>
	<div class="w3-container">
		<h5>Dashboard</h5>
	</div>
	<div class="w3-bar-block">
		<a href="#"
			class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black"
			onclick="w3_close()" title="close menu"><i
			class="fa fa-remove fa-fw"></i>  Close Menu</a> <a href="adminHome"
			class="w3-bar-item w3-button w3-padding w3-blue"><i
			class="fa fa-users fa-fw"></i>  Overview</a> <a href="#"
			class="w3-bar-item w3-button w3-padding"><i
			class="fa fa-eye fa-fw"></i>  Outcome Statistic</a> <a href="userAdmin"
			class="w3-bar-item w3-button w3-padding"><i
			class="fa fa-users fa-fw"></i>  User Management</a> <a href="cateAdmin"
			class="w3-bar-item w3-button w3-padding"><i
			class="fa fa-bullseye fa-fw"></i>  Category Management</a> <a href="#"
			class="w3-bar-item w3-button w3-padding"><i
			class="fa fa-diamond fa-fw"></i>  Orders</a> <a href="contactAdmin"
			class="w3-bar-item w3-button w3-padding"><i
			class="fa fa-bell fa-fw"></i>  Contact Management</a> <a
			href="authorAdmin" class="w3-bar-item w3-button w3-padding"><i
			class="fa fa-bank fa-fw"></i>  Author Management</a> <a
			href="pubcomAdmin" class="w3-bar-item w3-button w3-padding"><i
			class="fa fa-history fa-fw"></i>  Publishing Company</a> <a href="#"
			class="w3-bar-item w3-button w3-padding"><i
			class="fa fa-cog fa-fw"></i>  Product Management</a> <a href="login"
			class="w3-bar-item w3-button w3-padding"><i
			class="fa fa-reply fa-fw"></i>  Logout</a><br>
		<br>
	</div>
	</nav>


	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px; margin-top: 43px;">

		<!-- Header -->
		<header class="w3-container" style="padding-top:22px">
		<h5>
			<b><i class="fa fa-dashboard"></i> My Dashboard</b>
		</h5>
		</header>

		<div class="w3-row-padding w3-margin-bottom">
			<div class="w3-quarter">
				<div class="w3-container w3-red w3-padding-16">
					<div class="w3-left">
						<i class="fa fa-comment w3-xxxlarge"></i>
					</div>
					<div class="w3-right">
						<h3 modelAttribute="allMess">${allMess}</h3>
					</div>
					<div class="w3-clear"></div>
					<h4>Messages</h4>
				</div>
			</div>
			<div class="w3-quarter">
				<div class="w3-container w3-blue w3-padding-16">
					<div class="w3-left">
						<i class="fa fa-eye w3-xxxlarge"></i>
					</div>
					<div class="w3-right">
						<h3 modelAttribue="unView">${unView}</h3>
					</div>
					<div class="w3-clear"></div>
					<h4>Views</h4>
				</div>
			</div>
			<div class="w3-quarter">
				<div class="w3-container w3-teal w3-padding-16">
					<div class="w3-left">
						<i class="fa fa-share-alt w3-xxxlarge"></i>
					</div>
					<div class="w3-right">
						<h3>23</h3>
					</div>
					<div class="w3-clear"></div>
					<h4>Shares</h4>
				</div>
			</div>
			<div class="w3-quarter">
				<div class="w3-container w3-orange w3-text-white w3-padding-16">
					<div class="w3-left">
						<i class="fa fa-users w3-xxxlarge"></i>
					</div>
					<div class="w3-right">
						<h3 modelAttribute="userNum">${userNum}</h3>
					</div>
					<div class="w3-clear"></div>
					<h4>Users</h4>
				</div>
			</div>
		</div>
		<hr>
		<div class="w3-container">
			<div class="col-md-3 col-lg-3 col-xs-3">
				<h1>All User</h1>
			</div>

		</div>
		<hr>
		<div class="row">
			<div class="col-md-1 col-lg-1 col-xs-1">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Sort By <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Sender</a></li>
					<li><a href="#">Title</a></li>
					<li><a href="#">Content</a></li>
					<li><a href="#">View</a></li>
					<li><a href="#">Status</a></li>
				</ul>
			</div>

			<div class="col-md-2 col-lg-2 col-xs-2">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Per Page <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<input class="form-control" id="myInput" type="text"
						placeholder="Search..">
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
				</ul>
			</div>
			<div class="col-md-2 col-lg-2 col-xs-2">
				<h6>Contact Search</h6>
			</div>
			<div class="col-md-2 col-lg-2 col-xs-2">
				<input class="form-control" id="myInput" type="text"
					placeholder="Search..">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-lg-1 col-md-1 col-xs-1"></div>
			<div class="col-lg-10 col-md-10 col-xs-10">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Sender</th>
							<th>Title</th>
							<th>Content</th>
							<th>View</th>
							<th>Status</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${contacts}" var="contact">
							<tr>
								<td>${contact.sender}</td>
								<td>${contact.title}</td>
								<td>${contact.content}</td>
								<td>${contact.view}</td>
								<td>${contact.status}</td>
								<td><button class="btn btn-info">Update</button></td>
								<td><button class="btn btn-danger">Delete</button></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-5 col-md-5 col-xs-5"></div>
			<div class="col-lg-7 col-md-7 col-xs-7">
				<ul class="pagination">
					<li class="previous"><a href="#">Previous</a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li class="next"><a href="#">Next</a></li>
				</ul>
			</div>
		</div>
	</div>
	<hr>
	<br>
	<div class="w3-container w3-dark-grey w3-padding-32">
		<div class="w3-row">
			<div class="col-lg-3 col-md-3">
				<!-- widgets column left -->
				<ul class="list-unstyled clear-margins">
					<!-- widgets -->
					<li class="widget-container widget_nav_menu">
						<!-- widgets list -->
						<h1 class="title-widget">Hỗ trợ khách hàng</h1>
						<ul>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Hướng dẫn đặt hàng</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Chính sách đổi trả</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Phương thức vận chuyển</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Phương thức thanh toán</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<div class="col-lg-3 col-md-3">
				<!-- widgets column left -->
				<ul class="list-unstyled clear-margins">
					<!-- widgets -->
					<li class="widget-container widget_nav_menu">
						<!-- widgets list -->
						<h1 class="title-widget">T-Manga</h1>
						<ul>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Giới thiệu</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Chính sách bồi hoàn</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Điều khoản sử dụng</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Chính sách bảo mật</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- widgets column left end -->

			<div class="col-lg-3 col-md-3">
				<!-- widgets column left -->
				<ul class="list-unstyled clear-margins">
					<!-- widgets -->
					<li class="widget-container widget_nav_menu">
						<!-- widgets list -->
						<h1 class="title-widget">Tài khoản của bạn</h1>
						<ul>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Xem giỏ hàng</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Thông tin cá nhân</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Sách yêu thích</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Lịch sử giao dịch</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- widgets column left end -->

			<div class="col-lg-3 col-md-3">
				<!-- widgets column center -->
				<ul class="list-unstyled clear-margins">
					<!-- widgets -->
					<li class="widget-container widget_recent_news">
						<!-- widgets list -->
						<h1 class="title-widget">Liên hệ cho chúng tôi</h1>
						<div class="footerp">
							<h2 class="title-median">Cửa hàng sách T-Manga</h2>
							<p>
								<b>Địa chỉ : </b>96 Phạm Đình Hổ P2 Q6 TPHCM
							</p>
							<p>
								<b>Gmail :</b> <a href="#">tmangavn@gmail.com</a>
							</p>
							<p>
								<b>Điện thoại </b> <b style="color: #ffc106;">(8AM to 10PM):</b>
								<br> 0283 9608 712 , 0126 737 5656
							</p>
						</div>
						<div class="social-icons">
							<ul class="nomargin">
								<a href="https://www.facebook.com/TMangavn/"><i
									class="fa fa-facebook-square fa-3x social-fb" id="social"></i></a>
								<a href="https://twitter.com/tmangavn"><i
									class="fa fa-twitter-square fa-3x social-tw" id="social"></i></a>
								<a href="https://plus.google.com/u/0/107972177251069959921"><i
									class="fa fa-google-plus-square fa-3x social-gp" id="social"></i></a>
							</ul>
						</div>
			</div>
		</div>

		<!-- Footer -->
		<footer>
		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
						<div class="copyright">© 2019, T-Manga, All rights reserved</div>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
						<div class="design">
							Design by tmanga</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		</footer>
		<div id="modal-da-them-vao-gio-hang" class="modal fade" role="dialog">
			<div class="modal-dialog modal-sm">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-body">
						<p>
							Đã thêm <b></b> vào giỏ hàng.
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!-- End page content -->
	</div>

	<script>
		// Get the Sidebar
		var mySidebar = document.getElementById("mySidebar");

		// Get the DIV with overlay effect
		var overlayBg = document.getElementById("myOverlay");

		// Toggle between showing and hiding the sidebar, and add overlay effect
		function w3_open() {
			if (mySidebar.style.display === 'block') {
				mySidebar.style.display = 'none';
				overlayBg.style.display = "none";
			} else {
				mySidebar.style.display = 'block';
				overlayBg.style.display = "block";
			}
		}

		// Close the sidebar with the close button
		function w3_close() {
			mySidebar.style.display = "none";
			overlayBg.style.display = "none";
		}
	</script>

</body>
</html>

<!-- /content -->

<!--footer-->
<hr>
<footer class="footer1">
<div class="container">
	<div class="row">
		<!-- row -->
		<!-- widgets column left end -->



		</li>
		</ul>
	</div>
</div>
</div>

</footer>




<!--footer-->



<script src="<c:url value="/resources/js/jquery.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.scrollUp.min.js" />"></script>
<script src="<c:url value="/resources/js/price-range.js" />"></script>
<script src="<c:url value="/resources/js/jquery.prettyPhoto.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>

</body>

