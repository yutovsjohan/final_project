<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<hr>
<div class="w3-container">
	<div class="row" style="padding:15px">
		<div class="col-sm-3" style="font-size: 20px; color:red" >
			${title }
		</div>
		<div class="col-sm-4">				
			<form role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Tìm tác giả" name="q" id="search">				
					<div style="margin-top:-34px; float:right">
						<button class="btn btn-default" type="submit" style="height:34px"><i class="glyphicon glyphicon-search"></i></button>
					</div>
				</div>
			</form>
		</div>
		
		<div class="col-sm-4">
			<form class="" role="search">
				<div class="form-group">
					<select class="form-control" id="sort">
					  	<option value="0">--- Sắp xếp theo ---</option> 
						<option value="1">STT tăng dần</option>
						<option value="2">STT giảm dần</option>
						<option value="3">Tên danh mục A-Z</option>
						<option value="4">Tên danh mục Z-A</option>
						<option value="5">Số lượng truyện tăng dần</option>
						<option value="6">Số lượng truyện giảm dần</option>
				  	</select>
				</div>
			</form>
		</div>
	</div>
	
	<c:if test="${sessionScope.account.role.id == 1 }">
		<div class="row" style="padding:15px">
			<div class="col-sm-3" style="float:left; font-size: 20px">
				<button class="btn btn-success author" dataMode="add" style="margin:8px">Thêm tác giả</button>
			</div>
		</div>
	</c:if>	
</div>

<div class="w3-container">
	<table class="table table-striped" id="myTable">
		<thead>
			<tr>
				<th>STT</th>
				<th>Tên tác giả</th>
				<c:if test="${sessionScope.account.role.id == 1 }">
					<th>Trạng thái</th>
				</c:if>
				<c:if test="${sessionScope.account.role.id != 1 }">
					<th>Hiển thị trên trang web</th>
				</c:if>
				<th>Số lượng truyện</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="table-list">
			<c:forEach items="${authors}" var="author">
				<tr class="authorList" dataId="${author.id }">
					<td>${author.id}</td>
					<td class="name" dataId="${author.id }">${author.name}</td>
					<td>
						<c:choose>
							<c:when test="${author.status == 0 && sessionScope.account.role.id == 1}">
								<span class="showHideAuthor" dataId=${author.id } action="0"
									style="font-size: 20px;"><i class="fa fa-eye-slash"
									title="Bấm để hiện lên trên trang web"></i></span>
							</c:when>
							<c:when test="${author.status == 1 && sessionScope.account.role.id == 1}">
								<span class="showHideAuthor" dataId=${author.id } action="1"
									style="font-size: 20px;"><i class="fa fa-eye"
									title="Bấm để ẩn trên trang web"></i></span>
							</c:when>
							<c:when test="${author.status == 0 && sessionScope.account.role.id != 1}">
								<span style="color: red; font-size: 20px;"><i class="fa fa-times-circle" title="Không hiển thị trên trang web"></i></span>
							</c:when>
							<c:when test="${author.status == 1 && sessionScope.account.role.id != 1}">
								<span style="color:green; font-size: 20px;"><i class="fa fa-check-circle" title="Hiển thị trên trang web"></i></span>
							</c:when>
						</c:choose>
					</td>
					<td>${author.comics.size() }</td>
					<td>
						<a href="comicList?q=author&id=${author.id }" class="btn btn-warning" title="Xem danh sách truyện thuộc tác giả ${author.name }">Xem</a>
						<c:if test="${sessionScope.account.role.id == 1 }">
							<button class="btn btn-info author" dataId=${author.id } dataMode="edit">Sửa</button>
							<c:if test="${author.comics.size() == 0 }">
								<button class="btn btn-danger removeAuthor" dataId="${author.id }">Xóa</button>
							</c:if>
						</c:if>
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
				<li><a href="authorAdmin?p=1${tempurl }" rel="next" style="border-radius: 20px"> << </a></li>
				<li><a href="authorAdmin?p=${pageselected - 1}${tempurl }" rel="next">	< </a></li>
			</c:if>

			<c:forEach var="i" begin="${start }" end="${end}">
				<c:choose>
					<c:when test="${i == pageselected }">
						<li class="active"><span style="border-radius: 20px">${i }</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="authorAdmin?p=${i}${tempurl }">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${totalpage != pageselected }">
				<li><a href="authorAdmin?p=${pageselected + 1}${tempurl }" rel="next"> > </a></li>
				<li><a href="authorAdmin?p=${totalpage}${tempurl }" rel="next"	style="border-radius: 20px"> >> </a></li>
			</c:if>

		</c:if>
	</ul>
</center>