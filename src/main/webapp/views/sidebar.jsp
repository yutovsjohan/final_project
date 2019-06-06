<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- menu -->
<div class="col-md-3">
	<div class="panel panel-primary">
		<div class="panel-heading">Danh mục sản phẩm</div>
		<div class="panel-body">
			<c:set var = "number" scope = "session" value = "0"/>
			<c:forEach var="category" items="${categories}">
				<c:set var = "number" scope = "session" value = "${number + 1 }" />
				<c:if test="${category.status == 1 }">
					<c:if test="${number != 1}">
						<hr style="border: 1px solid #eee;">
					</c:if>						
					<a href="${pageContext.request.contextPath}/controller/product?a=pl&q=category&un=${category.unsignedName }">
						<c:choose>
							<c:when test="${title == category.name}">
								<div id="menu_doc" class="actived">		
							</c:when>
							<c:otherwise>
								<div id="menu_doc">	
							</c:otherwise>
						</c:choose>
						
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
						<hr style="border: 1px solid #eee;">
					</c:if>	
					<a href="${pageContext.request.contextPath}/controller/product?a=pl&q=author&un=${author.unsignedName }">
						<c:choose>
							<c:when test="${title == author.name}">
								<div id="menu_doc" class="actived">		
							</c:when>
							<c:otherwise>
								<div id="menu_doc">	
							</c:otherwise>
						</c:choose>
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
						<hr style="border: 1px solid #eee;">
					</c:if>	
					<a href="${pageContext.request.contextPath}/controller/product?a=pl&q=publishing-company&un=${pc.unsignedName }">
						<c:choose>
							<c:when test="${title == pc.name}">
								<div id="menu_doc" class="actived">		
							</c:when>
							<c:otherwise>
								<div id="menu_doc">	
							</c:otherwise>
						</c:choose>
							${pc.name }
						</div>
					</a>
				</c:if>
			</c:forEach>
		</div>
	</div>
	
	<c:if test="${key == 'search' }">
		<div class="panel panel-primary">
			<div class="panel-heading">Giá</div>
			<div class="panel-body">
				<a href="${pageContext.request.contextPath}/controller/${href}&s=5">
					<div id="menu_doc" <c:if test="${sort == 5 }"> class="actived" </c:if> >	
						< 20.000 <u>đ</u>
					</div>
				</a>
				
				<hr style="border: 1px solid #eee;">
				<a href="${pageContext.request.contextPath}/controller/${href}&s=6">
					<div id="menu_doc" <c:if test="${sort == 6 }"> class="actived" </c:if> >	
						Từ 20.000 <u>đ</u> - 30.000 <u>đ</u>
					</div>
				</a>
				
				<hr style="border: 1px solid #eee;">
				<a href="${pageContext.request.contextPath}/controller/${href}&s=7">
					<div id="menu_doc" <c:if test="${sort == 7 }"> class="actived" </c:if> >	
						Từ 30.000 <u>đ</u> - 40.000 <u>đ</u>
					</div>
				</a>
				
				<hr style="border: 1px solid #eee;">
				<a href="${pageContext.request.contextPath}/controller/${href}&s=8">
					<div id="menu_doc" <c:if test="${sort == 8 }"> class="actived" </c:if> >	
						Từ 40.000 <u>đ</u> - 50.000 <u>đ</u>
					</div>
				</a>
				
				<hr style="border: 1px solid #eee;">
				<a href="${pageContext.request.contextPath}/controller/${href}&s=9">
					<div id="menu_doc" <c:if test="${sort == 9 }"> class="actived" </c:if> >	
						> 50.000 <u>đ</u>
					</div>
				</a>
			</div>
		</div>	
	</c:if>
</div>
<!-- /menu -->