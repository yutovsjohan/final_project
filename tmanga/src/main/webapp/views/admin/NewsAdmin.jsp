<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<hr>
<div class="w3-container">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  		<a class="navbar-brand">Danh sách tin tức</a>
	
	<div class="col-sm-4">
		<form class="navbar-form" role="search" method="get" action="newsAdmin">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Tìm tin tức" name="q" id="search">
				
				<div class="input-group-btn">
					<button class="btn btn-default" type="submit" style="height:34px"><i class="glyphicon glyphicon-search"></i></button>
				</div>
			</div>
		</form>
	</div>
	
	<a href="newsDetail?mode=add" class="btn btn-success" style="margin:8px">Thêm tin tức</a>
	</nav>
</div>
<hr>

<c:if test="${mes != '' }">
	<div class="alert alert-${alert }" role="alert">
		${mes }
	 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	</div>
	<hr>
</c:if> 

<div class="w3-container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Hình</th>
				<th>Tin tức</th>
				<th>Trạng thái</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="table-list">
			<c:forEach items="${news}" var="news">
				<tr class="newsList" dataId="${news.id }">
					<td><a href="newsDetail?id=${news.id }&mode=edit"><img src="<c:url value="/images/news/${news.image }" />" alt="${news.title }" width=200px height=150px /></a></td>
					<td>
						<a href="newsDetail?id=${news.id }&mode=edit"><b>${news.title }</b></a>
			            <p><fmt:formatDate pattern = "dd-MM-yyyy" value = "${news.created_at }" /> | T-Manga</p>
			            <p>${news.summary }</p>
					</td>
					<td><c:choose>
							<c:when test="${news.status == 0 }">
								<span class="showHideNews" dataId=${news.id } action="0"
									style="font-size: 20px;"><i class="fa fa-eye-slash"
									title="Bấm để hiện lên trên trang web"></i></span>
							</c:when>
							<c:otherwise>
								<span class="showHideNews" dataId=${news.id } action="1"
									style="font-size: 20px;"><i class="fa fa-eye"
									title="Bấm để ẩn trên trang web"></i></span>
							</c:otherwise>
						</c:choose></td>
					<td>
						<a href="newsDetail?id=${news.id }&mode=edit" class="btn btn-info" >Sửa</a> 
						<br><br>
						<button class="btn btn-danger removeNews" dataId="${news.id }">Xóa</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<center>
	<ul class="pagination">
		<c:if test="${totalpage != 1 && totalpage != 0 }">
			
			<c:if test="${key == '' }">
				<c:set var = "tempurl" scope = "session" value = ""/>
			</c:if>
			
			<c:if test="${key != '' }">
				<c:set var = "tempurl" scope = "session" value = "&q=${key }"/>
			</c:if>
						
			<c:if test="${1 != pageselected }">
				<li><a href="newsAdmin?p=1${tempurl }" rel="next" style="border-radius: 20px"> << </a></li>
				<li><a href="newsAdmin?p=${pageselected - 1}${tempurl }" rel="next">	< </a></li>
			</c:if>

			<c:forEach var="i" begin="${start }" end="${end}">
				<c:choose>
					<c:when test="${i == pageselected }">
						<li class="active"><span style="border-radius: 20px">${i }</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="newsAdmin?p=${i}${tempurl }">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${totalpage != pageselected }">
				<li><a href="newsAdmin?p=${pageselected + 1}${tempurl }" rel="next"> > </a></li>
				<li><a href="newsAdmin?p=${totalpage}${tempurl }" rel="next"	style="border-radius: 20px"> >> </a></li>
			</c:if>

		</c:if>
	</ul>
</center>