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
	<div id="text">
		<c:forEach var="cm" items="${comic }">
			<p>${cm.name }</p>
		</c:forEach>
	</div>
	
	<div class="pagination-container">
		<nav>
			<ul class="pagination">
				<li class="disabled"><span><</span></li>
				<li class="active"><span>1</span></li>
				<li><a href="http://localhost:1207/QLbansach/public/danh-muc/pokemon-dac-biet?sort=1&amp;page=2">2</a></li>
				<li><a href="http://localhost:1207/QLbansach/public/danh-muc/pokemon-dac-biet?sort=1&amp;page=3">3</a></li>
				<li><a href="http://localhost:1207/QLbansach/public/danh-muc/pokemon-dac-biet?sort=1&amp;page=4">4</a></li>
				<li><a href="http://localhost:1207/QLbansach/public/danh-muc/pokemon-dac-biet?sort=1&amp;page=5">5</a></li>
				<li><a href="http://localhost:1207/QLbansach/public/danh-muc/pokemon-dac-biet?sort=1&amp;page=2" rel="next">></a></li>
			</ul>
		</nav>
	</div>	
	<script src="<c:url value="/resources/js/jquery.js" />" ></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
	<script src="<c:url value="/resources/js/jquery.scrollUp.min.js" />" ></script>
	<script src="<c:url value="/resources/js/price-range.js" />" ></script>
    <script src="<c:url value="/resources/js/jquery.prettyPhoto.js" />" ></script>
    <script src="<c:url value="/resources/js/main.js" />" ></script>
	
	<script type="text/javascript">
		$(document).ready(function(){			
			for(var i = 0 ; i<10;i++){
			    $("pagination").append('<li><a href="#">'+(i+1)+'</a></li> ');
			}			
		})
	</script>
</body>
</html>