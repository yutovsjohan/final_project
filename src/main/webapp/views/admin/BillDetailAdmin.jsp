<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
	<div class="col-sm-11" style="margin:20px">	
		<h4 style="text-align: center;">Mã đơn hàng: ${bill.id }</h4>
		<h4 style="text-align: center;">Ngày đặt hàng: <span style="color:green; font-weight: bold; font-size: 18px;"><fmt:formatDate pattern = "dd-MM-yyyy" value = "${bill.orderDate}" /></span></h4>
		<h4 style="text-align: center;">
			Tình trạng đơn hàng: 
			<span style="color:green; font-weight: bold; font-size: 20px;">
				${bill.status }
			</span>
		</h4>
		<br>
		
		<c:if test="${mes != '' }">
			<div class="alert alert-${alert }" role="alert">
				${mes }
			 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			</div>
		</c:if> 
		
		<div class="table-responsive">
		    <table class="table table-hover table-bordered">
		      <tr style="background-color: aquamarine;">
		        <th>Ngày</th>
		        <th>Tình trạng đơn hàng</th>		        
		      </tr>
		      <c:forEach var="os" items="${orderStatus}">
		        <tr>
		          <td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${os.created_at}" /></td>
		          <td>${os.content }</td>
		     	</tr>
		      </c:forEach>
		      
		      <c:if test="${sessionScope.account.role.id == 1 }">
			      <c:if test="${bill.active != -1 }">
				      <c:if test="${bill.active != 2 }">
					      <tr>
					      	<td></td>
					      	<td>
					      		<c:choose>
					      			<c:when test="${bill.active == 1 }">
					      				<button dataId=${bill.id } class="btn btn-warning change-status" title="Thêm tình trạng mới">Đang vận chuyển</button>
					      			</c:when>
					      			<c:when test="${bill.active == 0 }">
					      				<button dataId=${bill.id } class="btn btn-success change-status" title="Thêm tình trạng mới">Giao hàng thành công</button>
					      			</c:when>			      			
					      		</c:choose>
					      	</td>
					      </tr>
				      </c:if>
				      
				      <c:if test="${bill.active != 1 }">
				      	<tr>
				      		<td></td>
				      		<td>		      	
			   			 		<button class="btn btn-danger return-status" dataId=${bill.id } title="Quay lại tình trạng trước đó">Quay lại tình trạng trước</button>
			   			 	</td>
			   			</tr>
			   		  </c:if>
			   	   </c:if>
			   </c:if>
		    </table>
		</div>
		
		<div class="table-responsive">
		  <table class="table table-bordered">
		    <thead>
		      <tr style="background-color: cyan;">
		        <th>Thông tin người nhận</th>
		        <th>Thông tin khác</th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		        <td>
		          <p>Tên : ${bill.address.name}</p>
		          <p>Địa chỉ : ${bill.address.address }, ${bill.address.district.name }, ${bill.address.city.name }</p>
		          <p>Điện thoại : ${bill.address.phone }</p>
		        </td>
		        <td>
		          <p <c:if test="${sessionScope.account.role.id == 1 }"> title="Bấm vào để thay đổi ngày giao hàng" </c:if> >
					Ngày giao hàng dự kiến:
					<c:if test="${sessionScope.account.role.id == 1 }">
						<span class="delivery-date" dataId="${bill.id }">
							<fmt:formatDate pattern = "dd-MM-yyyy" value = "${bill.deliveryDate }" />
						</span>
						<div class="form-delivery-date" hidden dataId="${bill.id }">
							<input type="date" value='<fmt:formatDate pattern = "yyyy-MM-dd" value = "${bill.deliveryDate }" />' class="form-control select-delivery-date" dataId="${bill.id }">
							<a class="btn btn-success confirm-delivery-date" dataId="${bill.id }">Xác nhận</a>
							<a class="btn btn-danger cancel-delivery-date" dataId="${bill.id }">Hủy bỏ</a>				
						</div>
					</c:if>
					
					<c:if test="${sessionScope.account.role.id != 1 }">
						<fmt:formatDate pattern = "dd-MM-yyyy" value = "${bill.deliveryDate }" />
					</c:if>
		          
		          <p>${bill.note }</p>
		          <p <c:if test="${sessionScope.account.role.id == 1 }"> title="Bấm vào để đổi người giao" </c:if> >
		          	Người giao:
		          	<c:if test="${sessionScope.account.role.id == 1 }">
				        <c:if test="${bill.active != -1}"> 
				          	<span class="select-delivery" dataId="${bill.id }">
				          		<c:choose>
									<c:when test="${bill.delivery != null }">
										${bill.delivery }
									</c:when>
									<c:otherwise>
										<button class="btn btn-info" title="Chọn người giao hàng">Chọn</button>
									</c:otherwise>
								</c:choose>	
				          	</span>
				          	<select name="delivery" class="form-control delivery" dataId="${bill.id }" hidden>
								<option value="0">-- Chọn người giao --</option>
								<c:forEach var="delivery" items="${delivery }">
									<option value="${delivery.id }" <c:if test="${delivery.name == bill.delivery }">selected</c:if> >${delivery.name }</option>
								</c:forEach>
							</select>
						</c:if>
					</c:if>
					
					<c:if test="${sessionScope.account.role.id != 1 }">
						${bill.delivery }
					</c:if>
		          </p>	          
		        </td>
		      </tr>
		    </tbody>  
		  </table>
		</div>
		
		<br>
		<table class="table table-hover table-bordered">
		  <thead>
		      <tr style="background-color: orange;">
		          <th style="text-align: center;">Sản phẩm</th>
		          <th style="text-align: center;">Số lượng</th>
		          <th style="text-align: center;">Giá (VNĐ)</th>
		          <th style="text-align: center;">Tạm tính</th>
		      </tr>
		  </thead>
		
		  <c:forEach var="billdetail" items="${billDetail }">
		  <tbody>
		      <tr>
		          <td class="cart_description">
		          	<a href="${pageContext.request.contextPath}/controller/detail?c=${billdetail.comic.unsignedName }">
		          		<img src="<c:url value="/images/products/${billdetail.comic.image }" />" alt="${billdetail.comic.name}" style="width:110px; height:140px;" />
		          	</a>
		          	<a href="${pageContext.request.contextPath}/controller/detail?c=${billdetail.comic.unsignedName }">${billdetail.comic.name}</a>
		          </td>
		          <td class="cart_quantity" style="vertical-align:middle; text-align:center" >
		          	${billdetail.amount}
		          </td>
		          <td class="cart_price" style="vertical-align:middle; text-align:center" > <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${billdetail.comic.sale}" /> <u>đ</u></td> 
		          <td style="vertical-align:middle; text-align:center" >
		          	<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${billdetail.amount * billdetail.comic.sale}" /> <u>đ</u>
		          </td>
		      </tr>
		  </tbody>
		  </c:forEach>
		
		  <tbody>
		    <tr style="font-weight:bold; font-size:20px; ">
		      <td colspan="3" style="text-align: right;">
		      	<p>Tổng tạm tính:</p>
		        <p>Chi phí vận chuyển:</p>
		        <p>Thành tiền:</p>
		      </td>
		      <td colspan="2" style="text-align:left;">
		      	<p style="color: orange"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${bill.total - 15000}" /> <u>đ</u></p>
		      	<p style="color: green">15.000 <u>đ</u></p>
		      	<p style="color: red"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${bill.total}" /> VNĐ </p>
		      </td>
		    </tr>
		  </tbody>
		</table>
		
		<a href="bill" class="btn btn-info">Quay lại</a>
		  		
	</div>	
</div>