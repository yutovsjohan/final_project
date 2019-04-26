<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${mes != null }">
	<div class="alert alert-${alert }" role="alert">
		${mes } <a href="#" class="close" data-dismiss="alert"
			aria-label="close">&times;</a>
	</div>
</c:if>
<br>

<table class="table table-bordered table-hover">
	<tr>
		<th>Ngày gửi</th>
		<td><fmt:formatDate pattern="dd-MM-yyyy"
				value="${contact.created_at }" /></td>
	</tr>
	<tr>
		<th>Người gửi</th>
		<td>${contact.sender }</td>
	</tr>
	<tr>
		<th>Tiêu đề</th>
		<td>${contact.title }</td>
	</tr>
	<tr>
		<th>Nội dung</th>
		<td>${contact.content }</td>
	</tr>
</table>
<br>


<form method="POST" action="answer">
	<h2 style="color: red">Gửi mail trả lời khách hàng</h2>
	<textarea class="ckeditor" name="answer" rows="7"
		placeholder="Nhập nội dung tin nhắn" required="required"></textarea>
	<input hidden name="id" value="${contact.id }" />
	<input hidden name="email" value="${contact.sender }"/>
	<button type="submit" class="btn btn-success guimail">Gửi</button>
</form>

<%-- <c:choose>
	<c:when test="${contact.status == 0 }">
		<form method="POST" action="answer">
			<h2 style="color: red">Gửi mail trả lời khách hàng</h2>
			<textarea class="ckeditor" name="answer" rows="7"
				placeholder="Nhập nội dung tin nhắn" required="required"></textarea>
			<input hidden name="id" value="${contact.id }" />
			<input hidden name="email" value="${contact.sender }"/>
			<button type="submit" class="btn btn-success guimail">Gửi</button>
		</form>
	</c:when>
	<c:otherwise>
		<h2 style="color: red">
			<span class="glyphicon glyphicon-ok"></span> &nbsp;&nbsp; Đã gửi mail
			trả lời
		</h2>
	</c:otherwise>
</c:choose> --%>

