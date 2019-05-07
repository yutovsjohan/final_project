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
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body style="background-color:#f0f0fa;">
<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
<body class="w3-light-grey">

<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
  <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  <span class="w3-bar-item w3-right">T-Manga</span>
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:270px;" id="mySidebar"><br>
  <div class="w3-container w3-row">    
    <div class="w3-col w3-bar">
      <a href="edit-user" title="<spring:message code='admin.sidebar.title.edituser' />" ><span> <spring:message code="admin.sidebar.label" /> <strong>${sessionScope.account.name }</strong></span></a><br>
      <a href="edit-user" class="w3-bar-item w3-button" title="<spring:message code='admin.sidebar.title.edituser' />" > <i class="fa fa-user"></i></a>
      <a href="change-language?lang=en" title="<spring:message code='admin.sidebar.title.language-en' />" ><img src="https://o7planning.org/templates/o7planning/resources/images/languages/en.png" height="25" style="float:right; margin-left: 10px"></a>
      <a href="change-language?lang=vi" title="<spring:message code='admin.sidebar.title.language-vi' />" ><img src="https://o7planning.org/templates/o7planning/resources/images/languages/vi.png" height="25" style="float:right"></a>      
      <a href="logout" class="w3-bar-item w3-button" title="<spring:message code='admin.sidebar.title.logout' />"><i class="fa fa-sign-out"></i></a>
      <!-- <a href="#" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i></a> -->
      <!-- <a href="#" class="w3-bar-item w3-button"><i class="fa fa-cog"></i></a> -->
    </div>
  </div>
  <hr>
  <div class="w3-container">
    <!-- <h5>Menu</h5> -->
  </div>
  <div class="w3-bar-block">
    <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="<spring:message code='admin.sidebar.closemenu' />"><i class="fa fa-remove fa-fw"></i> <spring:message code="admin.sidebar.closemenu" /></a>
    <a href="adminHome" class="w3-bar-item w3-button w3-padding <c:if test="${views == 'adminHome' }"> w3-blue </c:if> "><i class="fa fa-tachometer fa-fw"></i>  <spring:message code="admin.sidebar.dashboard" /> </a>
    <a href="report" class="w3-bar-item w3-button w3-padding <c:if test="${views == 'Report' }"> w3-blue </c:if> "><i class="fa fa-bar-chart fa-fw"></i>  <spring:message code="admin.sidebar.report" /> </a>
    <a href="bill" class="w3-bar-item w3-button w3-padding <c:if test="${views == 'BillAdmin' }"> w3-blue </c:if> "><i class="fa fa-diamond fa-fw"></i>  <spring:message code="admin.sidebar.bill" /> <c:if test="${countNewOrder != 0 }"><span style="background: red; color: white; border-radius: 10px; padding: 5px; margin: 5px; font-weight: 800;">${countNewOrder }</span> </c:if> </a>
    <a href="contactAdmin" class="w3-bar-item w3-button w3-padding <c:if test="${views == 'ContactAdmin' }"> w3-blue </c:if> "><i class="fa fa-commenting-o fa-fw"></i>  <spring:message code="admin.sidebar.contact" /> <c:if test="${countNewContact != 0 }"><span style="background: green; color: white; border-radius: 10px; padding: 5px; margin: 5px; font-weight: 800;">${countNewContact }</span> </c:if></a>
    <a href="userAdmin" class="w3-bar-item w3-button w3-padding <c:if test="${views == 'UserAdmin' }"> w3-blue </c:if> "><i class="fa fa-users fa-fw"></i>  <spring:message code="admin.sidebar.user" /> </a>    
    <a href="comicList" class="w3-bar-item w3-button w3-padding <c:if test="${views == 'ProductManList' }"> w3-blue </c:if> "><i class="fa fa-book fa-fw"></i>  <spring:message code="admin.sidebar.product" /> </a>
    <a href="cateAdmin" class="w3-bar-item w3-button w3-padding <c:if test="${views == 'CategoryAdmin' }"> w3-blue </c:if> "><i class="fa fa-folder-open fa-fw"></i>  <spring:message code="admin.sidebar.category" /> </a>    
    <a href="authorAdmin" class="w3-bar-item w3-button w3-padding <c:if test="${views == 'AuthorAdmin' }"> w3-blue </c:if> "><i class="fa fa-male fa-fw"></i>  <spring:message code="admin.sidebar.author" /> </a>
    <a href="pubcomAdmin" class="w3-bar-item w3-button w3-padding <c:if test="${views == 'PublishingAdmin' }"> w3-blue </c:if> "><i class="fa fa-bank fa-fw"></i>  <spring:message code="admin.sidebar.publishcompany" /> </a>
    <a href="newsAdmin" class="w3-bar-item w3-button w3-padding <c:if test="${views == 'NewsAdmin' }"> w3-blue </c:if> "><i class="fa fa-newspaper-o fa-fw"></i>  <spring:message code="admin.sidebar.news" /> </a>
    <c:if test="${sessionScope.account.role.id == 1 }">
    	<a href="role" class="w3-bar-item w3-button w3-padding <c:if test="${views == 'role' }"> w3-blue </c:if> "><i class="fa fa-cog fa-fw"></i>  <spring:message code="admin.sidebar.role" /> </a>
    </c:if>
    <!-- <a href="logout" class="w3-bar-item w3-button w3-padding"><i class="fa fa-reply fa-fw"></i> Đăng xuất </a><br><br> -->
  </div>
</nav>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<div class="w3-main" style="margin-left: 275px; margin-top: 43px;">
	<!-- !PAGE CONTENT! -->
	<c:if test="${views != ''}">
		<c:import url="${views }.jsp" />
	</c:if>
	<!-- End page content -->
	
	<!-- Footer -->
	<div class="footer-bottom" style="text-align:center; color:white; padding:7px">
		© 2019, T-Manga, All rights reserved | Design by tmanga
	</div>
	<!-- /Footer -->
</div>

<div id="lang" data="${sessionScope.lang }"></div>
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
	
	<script src="<c:url value="/resources/js/jquery.js" />" ></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
	<script src="<c:url value="/resources/js/jquery.scrollUp.min.js" />" ></script>
	<script src="<c:url value="/resources/js/price-range.js" />" ></script>
    <script src="<c:url value="/resources/js/jquery.prettyPhoto.js" />" ></script>
    <script src="<c:url value="/resources/js/main.js" />" ></script>
	<script src="<c:url value="/resources/ckeditor/ckeditor.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.elevatezoom.js" />" ></script>
	<script src="<c:url value="/resources/js/style.js" />" ></script>	
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
	
</body>
</html>