<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="col-md-9 ">
	<div class="row">
		<div class="col-md-5 col-sm-6">
			<!-- Large modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg" style="background-color: rgb(240, 240, 250);">
				<img id="hinh" src="<c:url value="/images/products/${comic.image }"/>"  alt="${comic.name }" style="width:300px; height:360px; margin-top:70px; margin-bottom:50px"/>
			</button>
			
			<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content" style="width:550px; height:600px;">
						<img src="<c:url value="/images/products/${comic.image }"/>" alt="${comic.name }" style="width:550px; height:600px;"/>
					</div>
				</div>
			</div>
			
			<!-- nút like và share -->
			<center>
				<div class="fb-like" data-href="" data-layout="button_count" data-action="like" data-size="large" data-show-faces="true" data-share="true"></div>
			</center>
			<!--!!! nút like và share -->
		</div>
		
		<div class="col-md-7 col-sm-6">
			
				<input type="text" value="${comic.id }" name="idComic" id="idComic" hidden />
				<h2 style="color: red" id="nameComic">${comic.name }</h2>
				<br>
				<p>Tình trạng: 				
					<c:choose>
						<c:when test="${comic.amount == 0 }">
							<span style="font-weight:bold; color:red">HẾT HÀNG</span>
						</c:when>
						<c:otherwise>
							<span style="font-weight:bold; color:green">Còn hàng</span>
						</c:otherwise>
					</c:choose>				
				</p>
	           
				<table class="table table-hover">
					<tr>
						<td>Tác giả</td>
						<td><a href="${pageContext.request.contextPath}/controller/product?q=author&un=${comic.author.unsignedName }">${comic.author.name }</a></td>
					</tr>
					<tr>
						<td>Tại T-Manga</td>
						<td>
							<span style="font-weight:bold; color:red"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${comic.sale}" /> <u>đ</u></span>
						</td>
					</tr>
					<tr>
						<td>Nhà xuất bản</td>
						<td><a href="${pageContext.request.contextPath}/controller/product?q=publishing-company&un=${comic.publishcompany.unsignedName }">${comic.publishcompany.name }</a></td>
					</tr>
					<tr>
						<td>Ngày phát hành</td>
						<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${comic.publishDate }" /></td>
					</tr>
					<tr>
						<td>Trọng lượng</td>
						<td>${comic.weight } gam</td>
					</tr>
					<tr>
						<td>Kích thước</td>
						<td>${comic.size }</td>
					</tr>
					<tr>
						<td>Loại bìa</td>
						<td>${comic.bookCover }</td>
					</tr>
				</table>
	           
				<hr style="border:1px solid orange">
	
				<p><span class="glyphicon glyphicon-ok"></span> &nbsp;&nbsp; Giao hàng trong 24h tại khu vực TPHCM</p>
				<p><span class="glyphicon glyphicon-ok"></span> &nbsp;&nbsp; Bọc bìa Plastic Miễn Phí cho THÊM sách bìa mềm phù hợp với Chính sách và Quy cách bọc</p>
				<p><span class="glyphicon glyphicon-ok"></span> &nbsp;&nbsp; Đóng gói 3 lớp cực kỳ cẩn thận cho đơn hàng sách ở xa. (Ngoài khu vực TPHCM)</p>
	            <hr style="border:1px solid orange">
	               
				<div>
					<c:choose>
						<c:when test="${comic.amount == 0}">
							<center><span style="font-weight:bold; color:red; font-size:25px" >HẾT HÀNG</span></center>
						</c:when>
						<c:otherwise>
							<div>                 
								<span style="float: left; margin-right: 50px; padding-top: 7px;">Số lượng</span>
								<div class="input-group" style="width: 100px;">
									<input type="number" class="form-control" name="sl" id="sl" value=1 min=1  >
								</div>
							</div>
							
							<br>
							<button type="submit" class="btn btn-lg btn-info addtocart" title="Thêm vào giỏ hàng" style="background-color: #337ab7; border-color: #337ab7"><i class="fa fa-shopping-cart" aria-hidden="true"> Thêm vào giỏ hàng</i></button>	
						</c:otherwise>
					</c:choose>
					
	            </div>
	           
		</div> 
	
	</div>
	
	<h3 style="font-weight:bold; color:orange">Giới thiệu</h3>
	<hr style="border:1px solid orange; margin-top:0px">
	<h3 style="color:red">${comic.name }</h3>
	${comic.content }
	<br><br>
	
	<div class="row">
		<div class="col-sm-10">
			<span style="color:orange; font-weight:bold; font-size:20px">Truyện cùng tác giả</span>
		</div>
		<div class="col-sm-2" style="margin-top:8px; font-style:italic">
			<a href="${pageContext.request.contextPath}/controller/product?q=author&un=${comic.author.unsignedName }">XEM THÊM >></a>
		</div>		
	</div>
	
	<hr style="border: 1px solid orange; margin-top:0px">
	
	<c:forEach var="cm" items="${listComicForAuthor}">
		<div class="col-lg-3  col-sm-6 col-xs-6">
			<div class="product-image-wrapper">
				<div class="single-products">
					<div class="productinfo text-center">
						<a href="${pageContext.request.contextPath}/controller/detail?c=${cm.unsignedName }" class="productList" dataId="${cm.id }" title="${cm.name }">
							<img src="<c:url value="/images/products/${cm.image }"/>" alt="${cm.name }" style="width:150px; height:200px; margin-top:25px;" />
							<h5 style="height:50px; ">${cm.name }</h5>
							<h5 style="background-color: green; color:white">Phát hành: <fmt:formatDate pattern = "dd-MM-yyyy" value = "${cm.publishDate }" /></h5>
							<h5>
								<span style="margin-right: 15px; font-size: 18px;  color: red;">
									<c:choose>
										<c:when test="${cm.amount == 0 }">
											HẾT HÀNG
										</c:when>
										<c:otherwise>
											<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${cm.sale}" /> <u>đ</u>
										</c:otherwise>
									</c:choose>
									
								</span>
							</h5>
						</a>
						
						<c:choose>
							<c:when test="${cm.amount == 0 }">
								<%-- <a href="${pageContext.request.contextPath}/controller/#" class="btn btn-danger" title="Báo tôi khi có hàng" style="background-color: crimson; border-color: crimson"><i class="fa fa-bullhorn" aria-hidden="true" ></i></a> --%>
							</c:when>
							<c:otherwise>
								<button dataId="${cm.id }" dataName="${cm.name }" class="btn btn-info them-vao-gio-hang" title="Thêm vào giỏ hàng" style="background-color: #337ab7; border-color: #337ab7"><i class="fa fa-shopping-cart" aria-hidden="true"></i></button>
							</c:otherwise>
						</c:choose>
						
						<%-- <c:set var = "flag" scope = "session" value = "false"/>
						<c:if test="${sessionScope.account.email != null}">
							<c:if test="${favoritelist != null}">
								<c:forEach var="favoritelist" items="${favoritelist }">
									<c:if test="${favoritelist.comic.id == cm.id }">
										<c:set var = "flag" scope = "session" value = "true" />		
									</c:if>									
								</c:forEach>
							</c:if>
							
							<c:choose>
								<c:when test="${flag == true }">
									<a class="btn btn-warning favoritelist" data="1" dataId=${cm.id }><i class="fa fa-heart" title="Hủy yêu thích" aria-hidden="true" ></i></a>
								</c:when>
								<c:otherwise>
									<a class="btn btn-warning favoritelist" data="0" dataId=${cm.id }><i class="fa fa-heart-o" title="Thêm vào danh sách yêu thích" aria-hidden="true" ></i></a>
								</c:otherwise>
							</c:choose>							
						</c:if> --%>
							
						<a href="${pageContext.request.contextPath}/controller/detail?c=${cm.unsignedName }" class="btn btn-info" title="Xem chi tiết"><i class="fa fa-search" aria-hidden="true"></i></a></div>
				</div>
			</div>
		</div>
	</c:forEach>
	<br><br>
	
	<c:if test="${favoritelist.size() != null && favoritelist.size() != 0 && sessionScope.account.email != null}">
		<div class="row">
			<div class="col-sm-10">
				<span style="color:orange; font-weight:bold; font-size:20px">Truyện đã xem</span>
			</div>
			<div class="col-sm-2" style="margin-top:8px; font-style:italic">
				<a href="${pageContext.request.contextPath}/controller/customer/favoriteList">XEM THÊM >></a>
			</div>		
		</div>
		
		<hr style="border: 1px solid orange; margin-top:0px">
		
		<div class="row">
			<c:forEach var="favoritelist" items="${favoritelist}">
				<div class="col-lg-3  col-sm-6 col-xs-6">
					<div class="product-image-wrapper">
						<div class="single-products">
							<div class="productinfo text-center">
								<a href="${pageContext.request.contextPath}/controller/detail?c=${favoritelist.comic.unsignedName }" class="productList" dataId="${favoritelist.comic.id }" title="${favoritelist.comic.name }">
									<img src="<c:url value="/images/products/${favoritelist.comic.image }"/>" alt="${favoritelist.comic.name }" style="width:150px; height:200px; margin-top:25px;" />
									<h5 style="height:50px; ">${favoritelist.comic.name }</h5>
									<h5 style="background-color: green; color:white">Phát hành: <fmt:formatDate pattern = "dd-MM-yyyy" value = "${favoritelist.comic.publishDate }" /></h5>
									<h5>
										<span style="margin-right: 15px; font-size: 18px;  color: red;">
											<c:choose>
												<c:when test="${favoritelist.comic.amount == 0 }">
													HẾT HÀNG
												</c:when>
												<c:otherwise>
													<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${favoritelist.comic.sale}" /> <u>đ</u>
												</c:otherwise>
											</c:choose>
											
										</span>
									</h5>
								</a>
									
								<c:choose>
									<c:when test="${favoritelist.comic.amount == 0 }">
										<%-- <a href="${pageContext.request.contextPath}/controller/#" class="btn btn-danger" title="Báo tôi khi có hàng" style="background-color: crimson; border-color: crimson"><i class="fa fa-bullhorn" aria-hidden="true" ></i></a> --%>
									</c:when>
									<c:otherwise>
										<button dataId="${favoritelist.comic.id }" dataName="${favoritelist.comic.name }" class="btn btn-info them-vao-gio-hang" title="Thêm vào giỏ hàng" style="background-color: #337ab7; border-color: #337ab7"><i class="fa fa-shopping-cart" aria-hidden="true"></i></button>
									</c:otherwise>
								</c:choose>								
								<a href="${pageContext.request.contextPath}/controller/detail?c=${favoritelist.comic.unsignedName }" class="btn btn-info" title="Xem chi tiết"><i class="fa fa-search" aria-hidden="true"></i></a></div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	
	<input id="route" value="${pageContext.request.contextPath}/controller/favoritelist" hidden/>
	<div class="fb-comments" data-href="http://localhost:8080/tmanga/controller/detail?c=${comic.unsignedName }" data-numposts="5"></div>
</div>
