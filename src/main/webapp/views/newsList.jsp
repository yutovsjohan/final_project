<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-md-9 ">
	<c:forEach var="news" items="${news }">
		 <div class="row">
	        <div class="col-sm-3">
	            <a href="${pageContext.request.contextPath}/controller/news-detail?un=${news.unsignedTitle}"><img src="<c:url value="/images/news/${news.image }" />" alt="${news.title }" width=200px height=150px /></a>
	        </div>
	        <div class="col-sm-9">
	            <a href="${pageContext.request.contextPath}/controller/news-detail?un=${news.unsignedTitle}"><b>${news.title }</b></a>
	            <p><fmt:formatDate pattern = "dd-MM-yyyy" value = "${news.created_at }" /> | T-Manga</p>
	            <p>${news.summary }</p>
	        </div>
	    </div>
	    <hr>
	</c:forEach>
	
	<center>
		<ul class="pagination">
			<c:if test="${totalpage != 1 && totalpage != 0 }">
				<c:if test="${1 != pageselected }">
					<li><a href="${pageContext.request.contextPath}/controller/news?p=1" rel="next" style="border-radius:20px"> << </a></li>
					<li><a href="${pageContext.request.contextPath}/controller/news?p=${pageselected - 1}" rel="next"> < </a></li>
				</c:if>			
	
				<c:forEach var = "i" begin="1" end="${totalpage }">
					<c:choose>
						<c:when test="${i == pageselected }">
							<li class="active"><span style="border-radius:20px">${i }</span></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath}/controller/news?p=${i}">${i }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
					
				<c:if test="${totalpage != pageselected }">
					<li><a href="${pageContext.request.contextPath}/controller/${href }news?p=${pageselected + 1}" rel="next"> > </a></li>
					<li><a href="${pageContext.request.contextPath}/controller/${href }news?p=${totalpage}" rel="next" style="border-radius:20px"> >> </a></li>
				</c:if>	
				
			</c:if>
		</ul>
	</center>
	
	
</div>