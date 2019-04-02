<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- menu -->
	<div class="panel panel-primary">
		<div class="panel-heading">Danh mục sản phẩm</div>
		<div class="panel-body">
			<c:set var = "number" scope = "session" value = "0"/>
			<c:forEach var="category" items="${categories}">
				<c:set var = "number" scope = "session" value = "${number + 1 }" />
				<c:if test="${category.status == 1 }">
					<c:if test="${number != 1}">
						<hr>
					</c:if>						
					<a href="category/${category.unsignedName }">
						<div id="menu_doc" dataHref="#">
							${category.name }
						</div>						
					</a>
				</c:if>
			</c:forEach>
		</div>
	</div>
		
	<div class="panel panel-primary">
		<div class="panel-heading">Tác giả</div>
		<div class="panel-body">
			<c:set var = "number" scope = "session" value = "0"/>
			<c:forEach var="author" items="${authors}">
				<c:set var = "number" scope = "session" value = "${number + 1 }" />
				<c:if test="${author.status == 1 }">
					<c:if test="${number != 1}">
						<hr>
					</c:if>	
					<a href="author/${author.unsignedName }">
						<div id="menu_doc" dataHref="#">
							${author.name }	
						</div>
					</a>
				</c:if>
			</c:forEach>				
		</div>
	</div>
						
	<div class="panel panel-primary">
		<div class="panel-heading">Nhà xuất bản</div>
		<div class="panel-body">
			<c:set var = "number" scope = "session" value = "0"/>
			<c:forEach var="pc" items="${pcs}" >
				<c:set var = "number" scope = "session" value = "${number + 1 }" />
				<c:if test="${pc.status == 1 }">
					<c:if test="${number != 1}">
						<hr>
					</c:if>	
					<a href="publishing-company/${pc.unsignedName }">
						<div id="menu_doc" dataHref="#">
							${pc.name }
						</div>
					</a>
				</c:if>
			</c:forEach>
		</div>
	</div>		
<!-- /menu -->