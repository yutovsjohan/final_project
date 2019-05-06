<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-md-9 ">
	<c:choose>
		<c:when test="${key == 'search' }">
			<span style="color:orange; font-weight:bold; font-size:20px; float: left" >Kết quả tìm kiếm cho '${k }': ${totalcomic } kết quả </span>
		</c:when>
		<c:otherwise>
			<span style="color:orange; font-weight:bold; font-size:20px; float: left" >${title }</span>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${key != 'newcomic' }">
		<form method="get" action="product">
			<c:choose>
				<c:when test="${key == 'search' }">
					<input hidden value="search" name="a">
					<input hidden value="${k }" name="q">
				</c:when>			
				<c:otherwise>
					<input hidden value="pl" name="a">
					<input hidden value="${key }" name="q">
					<input hidden value="${un }" name="un">
				</c:otherwise>
			</c:choose>	
			
			<!-- <button type="submit" class="btn btn-info" style="float: right; margin-left: 10px;">Sắp xếp</button> -->
			  <select onchange="this.form.submit()" class="form-control" style="float: right; width: 200px; margin-left: 10px;" name="s"> 
				<option value="1" <c:if test="${sort == 1 }">selected</c:if> >Ngày phát hành</option>
				<option value="2" <c:if test="${sort == 2 }">selected</c:if> >Sản phẩm bán chạy</option>
				<option value="3" <c:if test="${sort == 3 }">selected</c:if> >Tên sản phẩm A-Z</option>
				<option value="4" <c:if test="${sort == 4 }">selected</c:if> >Tên sản phẩm Z-A</option>
				<option value="10" <c:if test="${sort == 10 }">selected</c:if> >Giá tăng dần</option>
				<option value="11" <c:if test="${sort == 11 }">selected</c:if> >Giá giảm dần</option>
			  </select>      
		 </form>
	</c:if>
	 
	 <div class="clearfix"></div>
	<hr style="border: 1px solid orange; margin-top:0px">
	
	<c:if test="${key == 'search' && sort >= 5 && sort <= 9}">
		<p class="tcdc"><i class="fa fa-info-circle" aria-hidden="true" style="color: orange;"></i> Tiêu chí đang chọn:
		<c:choose>			
			<c:when test="${sort == 5 }">
				 <span>Giá thấp hơn 20.000 <u>đ</u></span>
			</c:when>
			<c:when test="${sort == 6 }">
				<span>Giá: 20.000 <u>đ</u> - 30.000 <u>đ</u></span>
			</c:when>
			<c:when test="${sort == 7 }">
				<span>Giá: 30.000 <u>đ</u> - 40.000 <u>đ</u></span>
			</c:when>
			<c:when test="${sort == 8 }">
				<span>Giá: 40.000 <u>đ</u> - 50.000 <u>đ</u></span>
			</c:when>
			<c:when test="${sort == 9 }">
				 <span>Giá lớn hơn 50.000 <u>đ</u></span>
			</c:when>
		</c:choose>
		</p>
	</c:if>
			
	<div class="row">
		<c:forEach var="comic" items="${comiclist}">
			<div class="col-lg-3  col-sm-6 col-xs-6" >
				<div class="product-image-wrapper">
					<div class="single-products">
						<div class="productinfo text-center">
							<a href="${pageContext.request.contextPath}/controller/detail?c=${comic.unsignedName }" class="productList" dataId="${comic.id }" title="${comic.name }">
								<img src="<c:url value="/images/products/${comic.image }" />" alt="${comic.name }" style="width:150px; height:200px; margin-top:25px;" />
								<h5 style="height:50px; ">${comic.name }</h5>
								<h5 style="background-color: green; color:white">Phát hành: <fmt:formatDate pattern = "dd-MM-yyyy" value = "${comic.publishDate }" /></h5>								
								<h5><span style="margin-right: 15px; font-size: 18px;  color: red;">
									<c:choose>
										<c:when test="${comic.amount == 0 }">
											HẾT HÀNG
										</c:when>
										<c:otherwise>
											<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${comic.sale}" /> <u>đ</u>
										</c:otherwise>
									</c:choose>
									
								</span></h5>				
							</a>
							<c:choose>
								<c:when test="${comic.amount == 0 }">
									<%-- <a href="${pageContext.request.contextPath}/controller/#" class="btn btn-danger" title="Báo tôi khi có hàng" style="background-color: crimson; border-color: crimson"><i class="fa fa-bullhorn" aria-hidden="true" ></i></a> --%>
								</c:when>
								<c:otherwise>
									<button dataId="${comic.id }" dataName="${comic.name }" class="btn btn-info them-vao-gio-hang" title="Thêm vào giỏ hàng" style="background-color: #337ab7; border-color: #337ab7"><i class="fa fa-shopping-cart" aria-hidden="true"></i></button>
								</c:otherwise>
							</c:choose>
										
							<a href="${pageContext.request.contextPath}/controller/detail?c=${comic.unsignedName }" class="btn btn-info" title="Xem chi tiết"><i class="fa fa-search" aria-hidden="true"></i></a>						
						</div>
					</div>
				</div>
			</div>	
		</c:forEach>			
	</div>
	
	<center>
		<ul class="pagination">
			<c:if test="${totalpage != 1 && totalpage != 0 }">
				<c:if test="${1 != pageselected }">
					<li><a href="${pageContext.request.contextPath}/controller/${href }&p=1&s=${sort}" rel="next" style="border-radius:20px"> << </a></li>
					<li><a href="${pageContext.request.contextPath}/controller/${href }&p=${pageselected - 1}&s=${sort}" rel="next"> < </a></li>
				</c:if>			
	
				<c:forEach var = "i" begin="${start }" end="${end}">
					<c:choose>
						<c:when test="${i == pageselected }">
							<li class="active"><span style="border-radius:20px">${i }</span></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath}/controller/${href }&p=${i}&s=${sort}">${i }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
					
				<c:if test="${totalpage != pageselected }">
					<li><a href="${pageContext.request.contextPath}/controller/${href }&p=${pageselected + 1}&s=${sort}" rel="next"> > </a></li>
					<li><a href="${pageContext.request.contextPath}/controller/${href }&p=${totalpage}&s=${sort}" rel="next" style="border-radius:20px"> >> </a></li>
				</c:if>	
				
			</c:if>
		</ul>
	</center>
	
	<input id="route" value="${pageContext.request.contextPath}/controller/favoritelist" hidden/>
</div>
	