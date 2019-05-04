<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<hr>
<div class="w3-container">	
	<div class="row" style="padding:15px">
		<div class="col-sm-3" style="float:left; font-size: 20px">
			Danh sách truyện
		</div>
		<div class="col-sm-5" style="float:right">
			<form class="" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Tìm truyện" name="q" id="search">				
					<div style="margin-top:-34px; float:right">
						<button class="btn btn-default" type="submit" style="height:34px"><i class="glyphicon glyphicon-search"></i></button>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<div class="row" style="padding:15px">
		<div class="col-sm-3" style="float:left; font-size: 20px">
			<a class="btn btn-success">Thêm truyện</a>
		</div>
		<div class="col-sm-3" style="float:right">
			<form class="" role="search">
				<div class="form-group">
					<select class="form-control" id="sort">
					  	<option value="0">--- Sắp xếp theo ---</option>						
						<option value="3">Tên danh mục A-Z</option>
						<option value="4">Tên danh mục Z-A</option>
						<option value="5">Số lượng tăng dần</option>
						<option value="6">Số lượng giảm dần</option>
						<option value="11">Danh mục từ A-Z</option>
						<option value="12">Danh mục từ Z-A</option>
				  	</select>
				</div>
			</form>
		</div>
	</div>
	
	<div class="row" style="padding:15px; background-color: cyan; border-radius: 10px; text-align: center;" id="change-category" hidden>
		<div class="col-sm-2">
			<label for="select-option-change-category">Đổi danh mục</label>
		</div>
		<div class="col-sm-3">
			<div class="form-group">				
				<select class="form-control" id="select-option-change-category">
				  	<option value="0">--- Chọn danh mục ---</option>	
				  	<c:forEach var="cate" items="${cateList }">					
						<option value="${cate[0] }">${cate[1] }</option>
					</c:forEach>
			  	</select>
			</div>
		</div>
	</div>
</div>

<div class="w3-container">
	<table class="table" id="myTable">
		<thead>
			<tr>
				<th><input class="checkbox" type="checkbox" class="form-check-input" title="Chọn tất cả" id="select-all" dataCount="0"></th>
				<th>Tên truyện</th>
				<th>Hình</th>
				<th>Số lượng</th>
				<th>Danh mục</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="table-list">
			<c:forEach items="${comics}" var="product">
				<tr dataId=${product.id } dataAction="0">
					<td>
						<div class="form-check">
							<input type="checkbox" class="form-check-input checkbox" dataId=${product.id }>						    
						</div>
					</td>
					<td>${product.name}</td>
					<td><img src="<c:url value="/images/products/${product.image }" />" alt="${comic.name }" style="width:70px; height:100px;" /></td>
					<td>${product.amount }</td>
					<td class="categoryName">${product.category.name }</td>
					<td>
						<%-- <button class="btn btn-danger removeCategory" dataId="${category.id }" title="Xóa danh mục ${category.name }">Xóa</button> --%>						
					</td>					
				</tr>						
			</c:forEach>
		</tbody>
	</table>
</div>

<center>
	<ul class="pagination">
		<c:if test="${totalpage != 1 && totalpage != 0 }">
			<c:if test="${href == '' }">
				<c:set var = "tempurl" scope = "session" value = ""/>
			</c:if>
			
			<c:if test="${href != '' }">
				<c:set var = "tempurl" scope = "session" value = "${href }"/>
			</c:if>
			
			<c:if test="${1 != pageselected }">
				<li><a href="comicList?p=1${tempurl }" rel="next"
					style="border-radius: 20px"> << </a></li>
				<li><a href="comicList?p=${pageselected - 1}${tempurl }" rel="next"> <
				</a></li>
			</c:if>

			<c:forEach var="i" begin="${start }" end="${end}">
				<c:choose>
					<c:when test="${i == pageselected }">
						<li class="active"><span style="border-radius: 20px">${i }</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="comicList?p=${i}${tempurl }">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${totalpage != pageselected }">
				<li><a href="comicList?p=${pageselected + 1}${tempurl }" rel="next"> >
				</a></li>
				<li><a href="comicList?p=${totalpage}${tempurl }" rel="next"
					style="border-radius: 20px"> >> </a></li>
			</c:if>

		</c:if>
	</ul>
</center>