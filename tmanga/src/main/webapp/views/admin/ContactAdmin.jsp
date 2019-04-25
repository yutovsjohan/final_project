<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="w3-container">
	<h1>Danh sách các tin nhắn</h1>
</div>

<hr>
<div class="row">
	<div class="col-lg-1 col-md-1 col-xs-1"></div>
	<div class="col-lg-10 col-md-10 col-xs-10">
		<table class="table table-striped">
			<thead>
				<tr>
					<th></th>
					<th>Ngày</th>
					<th>Người gửi</th>
					<th>Tiêu đề</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contacts}" var="contact">
					<tr>
						<c:choose>
							<c:when test="${contact.view == 0 }">
								<td style="color: green; font-style: italic;"><u>Mới</u></td>
							</c:when>
							<c:when test="${contact.status == 0 }">
								<td><i class="fa fa-times-circle" aria-hidden="true" style="color: red"></i> Chưa trả lời</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>	
						</c:choose>
						
						<td><fmt:formatDate pattern="dd-MM-yyyy"
								value="${contact.created_at }" /></td>
						<td>${contact.sender}</td>
						<td>${contact.title}</td>
						<td style="color:blue">
							<a href="contactDetail?id=${contact.id }" title="Xem chi tiết">
								<i class="fa fa-2x fa-info-circle" aria-hidden="true"></i>
							</a>							
						</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
</div>

<center>
	<ul class="pagination">
		<c:if test="${totalpage != 1 && totalpage != 0 }">
			<c:if test="${1 != pageselected }">
				<li><a href="authorAdmin?p=1" rel="next"
					style="border-radius: 20px"> << </a></li>
				<li><a href="authorAdmin?p=${pageselected - 1}" rel="next">
						< </a></li>
			</c:if>

			<c:forEach var="i" begin="${start }" end="${end}">
				<c:choose>
					<c:when test="${i == pageselected }">
						<li class="active"><span style="border-radius: 20px">${i }</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="authorAdmin?p=${i}">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${totalpage != pageselected }">
				<li><a href="authorAdmin?p=${pageselected + 1}" rel="next">
						> </a></li>
				<li><a href="authorAdmin?p=${totalpage}" rel="next"
					style="border-radius: 20px"> >> </a></li>
			</c:if>

		</c:if>
	</ul>
</center>
