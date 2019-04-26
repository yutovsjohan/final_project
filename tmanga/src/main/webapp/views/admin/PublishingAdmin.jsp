<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<hr>
<h1>Danh sách nhà xuất bản</h1>
<button class="btn btn-success publishing" dataMode="add">Thêm
	nhà xuất bản</button>
<hr>

<div class="row">
	<div class="col-lg-1 col-md-1 col-xs-1"></div>
	<div class="col-lg-10 col-md-10 col-xs-10">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Tên nhà xuất bản</th>
					<th>Trạng thái</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${PubComs}" var="PubCom">
					<tr class="PCList" dataId="${PubCom.id }">
						<td>${PubCom.id}</td>
						<td class="name" dataId="${PubCom.id }">${PubCom.name}</td>
						<td><c:choose>
								<c:when test="${PubCom.status == 0 }">
									<span class="showHidePC" dataId=${PubCom.id } action="0"
										style="font-size: 20px;"><i class="fa fa-eye-slash"
										title="Bấm để hiện lên trên trang web"></i></span>
								</c:when>
								<c:otherwise>
									<span class="showHidePC" dataId=${PubCom.id } action="1"
										style="font-size: 20px;"><i class="fa fa-eye"
										title="Bấm để ẩn trên trang web"></i></span>
								</c:otherwise>
							</c:choose></td>
						<td><button class="btn btn-info publishing"
								dataId=${PubCom.id } dataMode="edit">Sửa</button></td>
						<td><c:if test="${PubCom.comics.size() == 0 }">
								<button class="btn btn-danger removePC" dataId="${PubCom.id }">Xóa</button>
							</c:if></td>
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
				<li><a href="pubcomAdmin?p=1" rel="next"
					style="border-radius: 20px"> << </a></li>
				<li><a href="pubcomAdmin?p=${pageselected - 1}" rel="next">
						< </a></li>
			</c:if>

			<c:forEach var="i" begin="${start }" end="${end}">
				<c:choose>
					<c:when test="${i == pageselected }">
						<li class="active"><span style="border-radius: 20px">${i }</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="pubcomAdmin?p=${i}">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${totalpage != pageselected }">
				<li><a href="pubcomAdmin?p=${pageselected + 1}" rel="next">
						> </a></li>
				<li><a href="pubcomAdmin?p=${totalpage}" rel="next"
					style="border-radius: 20px"> >> </a></li>
			</c:if>

		</c:if>
	</ul>
</center>

