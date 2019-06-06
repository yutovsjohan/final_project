<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<hr>
<div class="w3-container">
	<%-- <nav class="navbar navbar-expand-lg navbar-light bg-light">
  		<a class="navbar-brand">${title }</a>
	</nav> --%>
	<h2 style="color:red">${comic.name }</h2>
</div>

<div class="w3-container">	 
	<c:if test="${sessionScope.account.role.id == 1 }">
		<c:if test="${mes != '' }">
			<div class="alert alert-${alert }" role="alert">
				${mes }
			 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			</div>
		</c:if> 
		
		<form action="comic" method="post" enctype="multipart/form-data">
			<input hidden="hidden" value=${mode } name="mode" id="mode" />
			<input hidden="hidden" value="${views }" name="views" id="views" />
			<input type="hidden" name="id" value="${comic.id}" />
			
			<div class="form-group">
				<label for="name" class="control-label">Tên truyện</label>
				<input type="text" id="name" name="name" class="form-control" required="required" value="${comic.name}" />
			</div>
			
			<div class="form-group">
				<label for="category" class="control-label">Danh mục</label> 			
				<select class="form-control" id="category" name="category">
					<c:forEach items ="${categories}" var="category" >
						<option value="${category[0]}" <c:if test="${comic.category.id == category[0] }"> selected </c:if> >${category[1]}</option>
					</c:forEach>
				</select> 
			</div>
			<div class="form-group">
				<label for="author" class="control-label">Tác giả</label>
				<select class="form-control" id="author" name="author"> 
					<c:forEach items ="${authorschoice}" var="author" >
						<option value="${author.id}" <c:if test="${comic.author.id == author.id }"> selected </c:if> >${author.name}</option>
					</c:forEach>
				</select>
			</div> 
			<div class="form-group">
				<label for="publishCompany" class="control-label">Nhà xuất bản</label> 
				<select class="form-control" id="publishCompany" name="publishCompany">
					<c:forEach items ="${PCs}" var="PC" >
						<option value="${PC.id}" <c:if test="${comic.publishcompany.id == PC.id }"> selected </c:if> >${PC.name}</option>
					</c:forEach>
				</select> 
			</div>
				
			<div class="form-group">
				<label for="price" class="control-label">Giá</label>
				<input <c:if test="${mode == 'add' }"> value="0" </c:if> <c:if test="${mode == 'edit' }"> value="${comic.sale}" </c:if> type="number" id="price" name="price" min="0" class="form-control" required="required"  /> 
			</div>
			
			<div class="form-group">
				<label for="amount" class="control-label">Số lượng</label>
				<input <c:if test="${mode == 'add' }"> value="0" </c:if> <c:if test="${mode == 'edit' }"> value="${comic.amount}" </c:if> type="number" id="amount" name="amount" min="0" class="form-control" required="required" />  
			</div> 
			
			<div class="form-group">
				<label for="publishDate" class="control-label">Ngày phát hành</label> 
				<input <c:if test="${mode == 'add' }"> value="2019-05-01" </c:if> <c:if test="${mode == 'edit' }"> value="${comic.publishDate}" </c:if> type="date" class="form-control" required="required" id="publishDate" name="publishDate" />
			</div>
			<input id="temp" hidden value="${comic.publishDate}">
			<div class="form-group">
				<label for="image" class="control-label">Hình</label><br>
				<c:if test="${mode == 'edit' }">
					<img src="<c:url value="/images/products/${comic.image }" />" alt="${comic.name}" width=150px height=150px name="file" /><br><br>
				</c:if>
				<input type="file" name="file" id="image" value="${comic.image }" />
			</div>
			
			<div class="form-group">
				<label for="size" class="control-label">Kích thước</label>
				<input <c:if test="${mode == 'add' }"> value="11.3 x 17.6 cm" </c:if> <c:if test="${mode == 'edit' }"> value="${comic.size}" </c:if> type="text" id="size" name="size" class="form-control" /> 
			</div>
			 
			<div class="form-group">
				<label for="weight" class="control-label">Trọng lượng</label>
				<input <c:if test="${mode == 'add' }"> value="250" </c:if> <c:if test="${mode == 'edit' }"> value="${comic.weight}" </c:if> type="number" id="weight" name="weight" min="0" class="form-control"/> 
			</div>
					
			<div class="form-group">
				<label for="bookCover" class="control-label">Bìa sách</label> 
				<input <c:if test="${mode == 'add' }"> value="Bìa mềm" </c:if> <c:if test="${mode == 'edit' }"> value="${comic.bookCover}" </c:if> type="text" id="bookCover" name="bookCover" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="content" class="control-label">Nội dung truyện</label> 
				<textarea id="content" name="content" rows="20" class="ckeditor"/>${comic.content }</textarea>			
			</div>
			
			<button type="submit" class="btn btn-success" id="save-product">Lưu</button>		
		</form>
	</c:if>
	
	<c:if test="${sessionScope.account.role.id != 1 }">
		<table class="table">
		  <tbody>
		    <tr>
		      <td style="width:160px">Tên truyện</td>
		      <td>${comic.name }</td>
		    </tr>
		    <tr>
		      <td>Danh mục</td>
		      <td>${comic.category.name }</td>
		    </tr>
		    <tr>
		      <td>Tác giả</td>
		      <td>${comic.author.name }</td>
		    </tr>
		     <tr>
		      <td>Nhà xuất bản</td>
		      <td>${comic.publishcompany.name}</td>
		    </tr>
		     <tr>
		      <td>Giá</td>
		      <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${comic.sale}" /></td>
		    </tr>
		     <tr>
		      <td>Số lượng</td>
		      <td>${comic.amount }</td>
		    </tr>
		     <tr>
		      <td>Ngày phát hành</td>
		      <td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${comic.publishDate }" /></td>
		    </tr>
		     <tr>
		      <td>Hình</td>
		      <td><img src="<c:url value="/images/products/${comic.image }" />" alt="${comic.name}" width=150px height=150px name="file" /><br><br></td>
		    </tr>
		     <tr>
		      <td>Kích thước</td>
		      <td>${comic.size }</td>
		    </tr>
		     <tr>
		      <td>Trọng lượng</td>
		      <td>${comic.weight }</td>
		    </tr>
		     <tr>
		      <td>Bìa sách</td>
		      <td>${comic.bookCover }</td>
		    </tr>
		    <tr>
		      <td>Nội dung</td>
		      <td>${comic.content }</td>
		    </tr>
		  </tbody>
		</table>
	</c:if>
	
	<br>	
		
</div>