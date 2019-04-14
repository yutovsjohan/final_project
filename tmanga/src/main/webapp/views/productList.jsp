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
	<form method="get" action="#">	
		<button type="submit" class="btn btn-info" style="float: right; margin-left: 10px;">Sắp xếp</button>
		  <select class="form-control" style="float: right; width: 150px; margin-left: 10px;" name="sort"> 
			<option value="1" selected >Ngày phát hành</option>
			<option value="2" >Giá tăng dần</option>
			<option value="3" >Giá giảm dần</option>
			<option value="4" >Bán chạy</option>
		  </select>      
	 </form>
	 
	 <div class="clearfix"></div>
	<hr style="border: 1px solid orange; margin-top:0px">
	<div class="row">
		<c:set var = "number" scope = "session" value = "0"/>
		<c:forEach var="comic" items="${comiclist}">
			<c:set var = "number" scope = "session" value = "${number + 1 }" />
			<div class="col-lg-3  col-sm-6 col-xs-6 listcomic" data="${number }" <c:if test="${number > 12 }">hidden</c:if> >
				<div class="product-image-wrapper">
					<div class="single-products">
						<div class="productinfo text-center">
							<a href="detail?c=${comic.unsignedName }" title="${comic.name }">
								<img src="<c:url value="/images/products/${comic.image }" />" alt="${comic.name }" style="width:150px; height:200px; margin-top:25px;" />
								<h5 style="height:50px; ">${comic.name }</h5>
								<h5 style="background-color: green; color:white">Phát hành: <fmt:formatDate pattern = "dd-MM-yyyy" value = "${comic.publishDate }" /></h5>								
								<h5><span style="margin-right: 15px; font-size: 18px;  color: red;"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${comic.sale}" /> <u>đ</u></span></h5>				
							</a>
							<c:choose>
								<c:when test="${comic.amount == 0 }">
									<a href="#" class="btn btn-danger" title="Báo tôi khi có hàng" style="background-color: crimson; border-color: crimson"><i class="fa fa-bullhorn" aria-hidden="true" ></i></a>
								</c:when>
								<c:otherwise>
									<button dataId="${comic.id }" dataName="${comic.name }" class="btn btn-info them-vao-gio-hang" title="Thêm vào giỏ hàng" style="background-color: #337ab7; border-color: #337ab7"><i class="fa fa-shopping-cart" aria-hidden="true"></i></button>
								</c:otherwise>
							</c:choose>
									
							<a href="detail?c=${comic.unsignedName }" class="btn btn-info" title="Xem chi tiết"><i class="fa fa-search" aria-hidden="true"></i></a>						
						</div>
					</div>
				</div>
			</div>	
		</c:forEach>			
	</div>
	
	<ul class="pagination">
		<c:if test="${totalpage != 1 && totalpage != 0 }">
			<c:if test="${1 != pageselected }">
				<li data="-1" id="_pre"><a rel="next"> << </a></li>
				<li data="-2" id="pre"><a rel="next"> < </a></li>
			</c:if>			

			<c:forEach var = "i" begin="1" end="${totalpage }">
				<c:choose>
					<c:when test="${i == pageselected }">
						<li data="${i }" class="active"><span>${i }</span></li>
					</c:when>
					<c:otherwise>
						<li data="${i }"><a>${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
				
			<c:if test="${totalpage != pageselected }">
				<li data="-3" id="next"><a rel="next"> > </a></li>
				<li data="-4" id="_next"><a rel="next"> >> </a></li>
			</c:if>	
			
		</c:if>
	</ul>
	
	<input hidden value="${pageselected }" id="page" />
	<input hidden value="${totalpage }" id="totalpage" />
	<input hidden value="${href }" id="href" />
</div>
	