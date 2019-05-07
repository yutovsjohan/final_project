<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<hr>
<div class="w3-container">
	<div class="row" style="padding:15px">
		<div class="col-sm-3" style="font-size: 20px; color:red">
			${title }
		</div>
		<div class="col-sm-4">
			<form role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Tìm chức vụ" name="q" id="search">				
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
					<option value="1">STT tăng dần</option>
					<option value="2">STT giảm dần</option>
					<option value="3">Tên chức vụ từ A-Z</option>
					<option value="4">Tên chức vụ từ Z-A</option>
			  	</select>
			</div>
		</div>
	</div>
	
	<div class="row" style="padding:15px">
		<div class="col-sm-3" style="float:left; font-size: 20px">
			<button class="btn btn-success role" dataMode="add" style="margin:8px">Thêm chức vụ</button>
		</div>
	</div>
</div>

<div class="w3-container">
	<table class="table table-striped" id="myTable">
		<thead>
			<tr>
				<th>STT</th>
				<th>Tên chức vụ</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="table-list">
			<c:forEach items="${roles}" var="role">
				<tr class="roleList" dataId="${role.id }">
					<td>${role.id}</td>
					<td class="name" dataId="${role.id }">${role.name}</td>
					<td>
						<button class="btn btn-info role" dataId=${role.id }
							dataMode="edit">Sửa</button>
						
						<c:forEach var="list" items="${countRole }">
							<c:if test="${list[0] == role.id && list[1] == 0}">
								<button class="btn btn-danger removeRole" dataId="${role.id }">Xóa</button>
							</c:if>
						</c:forEach>
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
				<li><a href="role?p=1${tempurl }" rel="next" style="border-radius: 20px"> << </a></li>
				<li><a href="role?p=${pageselected - 1}${tempurl }" rel="next">	< </a></li>
			</c:if>

			<c:forEach var="i" begin="${start }" end="${end}">
				<c:choose>
					<c:when test="${i == pageselected }">
						<li class="active"><span style="border-radius: 20px">${i }</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="role?p=${i}${tempurl }">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${totalpage != pageselected }">
				<li><a href="role?p=${pageselected + 1}${tempurl }" rel="next"> > </a></li>
				<li><a href="role?p=${totalpage}${tempurl }" rel="next"	style="border-radius: 20px"> >> </a></li>
			</c:if>

		</c:if>
	</ul>
</center>