<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<hr>
<div class="w3-container">	
	<div class="row" style="padding:15px">
		<div class="col-sm-3" style="font-size: 20px; color:red">
			${title }
		</div>
		<div class="col-sm-4">
			<form role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Tìm truyện" name="key" id="search">				
					<div style="margin-top:-34px; float:right">
						<button class="btn btn-default" type="submit" style="height:34px"><i class="glyphicon glyphicon-search"></i></button>
					</div>
				</div>
			</form>
		</div>
		
		<div class="col-sm-4">
			<div class="form-group">
				<select class="form-control" id="sort">
				  	<option value="0">--- Sắp xếp theo ---</option>						
					<option value="3">Tên truyện từ A-Z</option>
					<option value="4">Tên truyện từ Z-A</option>
					<option value="5">Số lượng tăng dần</option>
					<option value="6">Số lượng giảm dần</option>
					<option value="11">Danh mục từ A-Z</option>
					<option value="12">Danh mục từ Z-A</option>
					<option value="18">Giá tăng dần</option>
					<option value="19">Giá giảm dần</option>
			  	</select>
			</div>
		</div>
	</div>
	
	<c:if test="${sessionScope.account.role.id == 1 }">	
		<div class="row" style="padding:15px">
			<div class="col-sm-3" style="float:left; font-size: 20px">
				<a class="btn btn-success" href="comic">Thêm truyện</a>
			</div>
		</div>
	</c:if>
	
	<c:if test="${mes != '' }">
		<div class="alert alert-${alert }" role="alert">
			${mes }
		 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		</div>
	</c:if> 
	
	<%-- <div class="row" style="padding:15px; background-color: cyan; border-radius: 10px; text-align: center;" id="change-category" hidden>
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
		
		<div class="col-sm-2">
			<label for="price">Đổi giá tiền</label>
		</div>
		<div class="col-sm-2">
			<div class="form-group">
				<input type="number" id="price" min=1 class="form-control" />				
			</div>
		</div>
		<div class="col-sm-2">
			<div class="form-group">
				<button class="btn btn-success" id="change-price">Xác nhận</button>
			</div>
		</div>
	</div> --%>
</div>

<div class="w3-container">
	<table class="table" id="myTable">
		<thead>						
			<tr>
				<c:if test="${sessionScope.account.role.id == 1 }">
					<th>
						<c:if test="${comics.size() != 0 }">
							<input class="checkbox" type="checkbox" class="form-check-input" title="Chọn tất cả" id="select-all" dataCount="0">
						</c:if>
					</th>
				</c:if>
				<!-- <th id="sort-by-name" dataSort="1" title="Bấm vào để sắp xếp theo tên truyện">Tên truyện <i class="fa fa-arrows-v" aria-hidden="true" style="float:left; margin-right:10px; font-size:20px"></i></th> -->
				<th>Tên truyện</th>
				<th>Hình</th>
				<th>Số lượng</th>
				<th>Danh mục</th>
				<th>Giá (VNĐ)</th>
				<c:if test="${sessionScope.account.role.id == 1 }">
					<th>Trạng thái</th>
				</c:if>
				<c:if test="${sessionScope.account.role.id != 1 }">
					<th>Hiển thị trên trang web</th>
				</c:if>
				<th></th>
			</tr>	
			
			<c:if test="${sessionScope.account.role.id == 1 }">
				<tr id="change-comic" data="true" hidden style="padding:15px; background-color: orange; border-radius: 10px; text-align: center;">
					<td id="count-comic-selected" colspan="3" style="color:white; font-size:18px; font-weight:bold"></td>
					<td>
						<div class="form-group">
							<input type="number" id="amount" min=0 max=100 class="form-control" />				
						</div>
					</td>
					<td>
						<div class="form-group">				
							<select class="form-control" id="select-option-change-category">
							  	<option value="0">--- Chọn danh mục ---</option>	
							  	<c:forEach var="cate" items="${cateList }">					
									<option value="${cate[0] }">${cate[1] }</option>
								</c:forEach>
						  	</select>
						</div>
					</td>
					<td>
						<div class="form-group">
							<input type="number" id="price" min=1 class="form-control" />				
						</div>
					</td>
					<td></td>
					<td>
						<div class="form-group">
							<button class="btn btn-success" id="confirm-change-comic">Xác nhận</button>
						</div>
					</td>
				</tr>
			</c:if>		
		</thead>
		<tbody id="table-list">
			<c:forEach items="${comics}" var="product">
				<tr dataId=${product.id } dataAction="0">
					<c:if test="${sessionScope.account.role.id == 1 }">
						<td>
							<div class="form-check">
								<input type="checkbox" class="form-check-input checkbox" dataId=${product.id }>						    
							</div>
						</td>
					</c:if>
					<td>${product.name}</td>
					<td><img src="<c:url value="/images/products/${product.image }" />" alt="${product.name }" title="${product.name }" style="width:70px; height:100px;" /></td>
					<td class="amount">${product.amount }</td>
					<td class="categoryName">${product.category.name }</td>
					<td><span class="price"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.sale}" /></span> </td>
					<td>
						<c:choose>
							<c:when test="${product.status == 0 && sessionScope.account.role.id == 1}">
								<span class="showHideComic" dataId=${product.id } action="0"
									style="font-size: 20px;"><i class="fa fa-eye-slash"
									title="Bấm để hiện lên trên trang web"></i></span>
							</c:when>
							<c:when test="${product.status == 1 && sessionScope.account.role.id == 1}">
								<span class="showHideComic" dataId=${product.id } action="1"
									style="font-size: 20px;"><i class="fa fa-eye"
									title="Bấm để ẩn trên trang web"></i></span>
							</c:when>
							<c:when test="${product.status == 0 && sessionScope.account.role.id != 1}">
								<span style="color: red; font-size: 20px;"><i class="fa fa-times-circle" title="Không hiển thị trên trang web"></i></span>
							</c:when>
							<c:when test="${product.status == 1 && sessionScope.account.role.id != 1}">
								<span style="color:green; font-size: 20px;"><i class="fa fa-check-circle" title="Hiển thị trên trang web"></i></span>
							</c:when>
						</c:choose>
					</td>
					<td>
						<c:if test="${sessionScope.account.role.id == 1 }">
							<a href="comic?id=${product.id }&mode=edit" class="btn btn-info edit-comic">Sửa</a>
						</c:if> 
						<c:if test="${sessionScope.account.role.id != 1 }">
							<a href="comic?id=${product.id }&mode=view" class="btn btn-info">Xem</a>
						</c:if>
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