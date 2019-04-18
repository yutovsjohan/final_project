<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${(sessionScope.account != null)}">
	<div class="col-md-9 ">	
		<h2 style="color: chocolate">
			<c:if test="${mode == 'add' }">Thêm địa chỉ mới</c:if>
			<c:if test="${mode == 'edit' }">Chỉnh sửa địa chỉ</c:if>
		</h2>
		<br>
		
		<c:if test="${mes != '' }">
			<div class="alert alert-${alert }" role="alert">
				${mes }
			 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			</div>
		</c:if> 
		<br>
		
		<form:form action="${pageContext.request.contextPath}/controller/customer/address" method="post" modelAttribute="Address">		
		  <form:input path="id" type="hidden"/>
		  <c:if test="${mode == 'edit' }"><input hidden name="mode" value="edit" /></c:if>
		  <div class="form-group">
		    <label for="name">Họ tên</label>		    
		    <form:input path="name" type="text" class="form-control" value="" name="name" required="required" placeholder="Nhập họ tên" id="name"/>
		  </div>
		  <div class="form-group">
		    <label for="phone">Điện thoại</label>
		    <form:input path="phone" type="text" class="form-control" value="" name="phone" required="required" placeholder="Nhập số điện thoại" id="phone" maxlength="10"/>
		  </div>
		  <div class="form-group">
		    <label for="city">Tỉnh / Thành phố</label>
		    <form:select path="city.id" name="city" class="form-control" required="required" id="city">
		    	<option value="0" <c:if test="${mode == 'add' }">selected</c:if> >Chọn Tỉnh / Thành phố</option>
		    	<c:forEach var="c" items="${city }">
		    		<option value="${c.id }" <c:if test="${mode == 'edit' && Address.city.id == c.id}">selected</c:if> >${c.name }</option>
		    	</c:forEach>
		    </form:select>
		  </div>
		  <div class="form-group">
		    <label for="district">Quận / Huyện</label>
		    <form:select path="district.id" name="district" class="form-control" required="required" id="district">
		    	<option value="0" <c:if test="${mode == 'add' }">selected</c:if> >Chọn Quận / Huyện</option>
		    	<c:if test="${mode == 'edit' }">
			    	<c:forEach var="d" items="${district }">
			    		<option value="${d.id }" <c:if test="${mode == 'edit' && Address.district.id == d.id}">selected</c:if> >${d.name }</option>
			    	</c:forEach>
		    	</c:if>
		    </form:select>
		  </div>
		  <div class="form-group">
		    <label for="address">Địa chỉ</label>
		    <form:textarea path="address" class="form-control" rows="10" value="" name="address" placeholder="Nhập địa chỉ" required="required" id="address"/>
		  </div>
		  <c:if test="${mode == 'edit' && Address.choose == 0 }">
			  <div class="form-group">
			  	<form:input path="choose" type="number" value="0" name="choose" id="choose" hidden="hidden" min="0" max="1"/>
			    <input type="checkbox" class="form-group" name="chooseDefault" value="0" id="chooseDefault" />
			    <label for="chooseDefault"> Sử dụng địa chỉ này làm mặc định.</label>
			  </div>
		  </c:if>
		  
		  <c:if test="${mode == 'edit' && Address.choose == 1 }">
		   <form:input path="choose" type="hidden"/>
		   </c:if>
		  
		  <button type="submit" class="btn btn-success" id="save">Lưu</button>
		</form:form>
	</div>
</c:if>