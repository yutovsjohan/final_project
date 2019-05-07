<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<hr>
<div class="w3-container">
	<div class="row" style="padding:15px">
		<div class="col-sm-4" style="font-size: 20px; color:red">
			${title }
		</div>
		<div class="col-sm-4">
			<form role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Nhập email của người gửi" name="q" id="search">
					<div style="margin-top:-34px; float:right">
						<button class="btn btn-default" type="submit" style="height:34px"><i class="glyphicon glyphicon-search"></i></button>
					</div>
				</div>
			</form>
		</div>
		<div class="col-sm-4">
			<select class="form-control" id="sort">
			  	<option value="0">--- Sắp xếp theo ---</option> 
				<option value="7">Ngày gửi tăng dần</option>
				<option value="8">Ngày gửi giảm dần</option>
				<option value="13">Tên email từ A-Z</option>
				<option value="14">Tên email từ Z-A</option>
		  	</select>
		</div>
	</div>
</div>

<div class="w3-container">
	<table class="table table-striped" id="myTable">
		<thead>
			<tr>
				<th></th>
				<th>Ngày</th>
				<th>Email</th>
				<th>Tiêu đề</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="table-list">
			<c:forEach items="${contacts}" var="contact">
				<tr>
					<c:choose>
						<c:when test="${contact.view == 0 }">
							<td style="color: green; font-style: italic;"><u>Mới</u></td>
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
