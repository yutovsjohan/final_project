<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-md-9 ">
	<h2 style="color: blue;font-weight: bold">Tra cứu tình trạng đơn hàng</h2>
	<hr style="border: 1px solid blue">
	
	<c:if test="${mes != null }">
		<div class="alert alert-${alert }" role="alert">
			${mes }
		 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		</div>
	</c:if> 
	
	<form class="form-horizontal" method="get" action="${pageContext.request.contextPath}/controller/#">
	  <div class="form-group">
	    <label for="email" class="col-sm-2 control-label">Địa chỉ email</label>
	    <div class="col-sm-10">
	      <input type="email" class="form-control" name="email">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="madh" class="col-sm-2 control-label">Mã đơn hàng</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="madh" >
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-default">Xác nhận</button>
	    </div>
	  </div>
	</form>
</div>