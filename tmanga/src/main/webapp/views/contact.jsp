<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-md-9 ">
	<!--contact-page-->
	<div class="row">    		
	    <div class="col-sm-12">
	    	<c:if test="${mes != null }">
				<div class="alert alert-${alert }" role="alert">
					${mes }
				 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
			</c:if> 
			<br> 
			   			   			
	        <h2 class="text-center" style="color:orange">Liên hệ với chúng tôi</h2>    			    				    				
	        <p>Hãy gọi liên hệ với chúng tôi để được trợ giúp:</p>
	        <p><b>Số điện thoại:</b> 0126 737 5655</p>
	        <p><b>Thời gian làm việc:</b> 7 ngày/tuần  từ 8:00 đến 22:00</p>
	        <p><b>Địa chỉ email:</b> tmangvn@gmail.com</p>
	        <p><b>Fanpage Facebook:</b> <a href="https://www.facebook.com/TMangavn/">https://www.facebook.com/TMangavn/</a></p>
	    </div>
	    <div class="col-sm-12">                         
	        <h2 class="text-center" style="color:orange">Văn phòng T-Manga</h2>                                                        
	        <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d15679.092136884758!2d106.6468489!3d10.7519678!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752e8ba79b8509%3A0xc49e52790e5f0585!2zOTYgUGjhuqFtIMSQw6xuaCBI4buVLCBwaMaw4budbmcgMiwgUXXhuq1uIDYsIEjhu5MgQ2jDrSBNaW5oLCBWaeG7h3QgTmFt!5e0!3m2!1svi!2sin!4v1501519936708" width="830" height="385" frameborder="0" style="border:0" allowfullscreen=""></iframe>
	    </div>                  
	
	</div>    	
	<div class="row">  	
	    <div class="col-sm-12">
	        <div class="contact-form">
	            <br><br><br><br>
	            <h2 class="text-center" style="color:orange">Gửi tin nhắn cho chúng tôi</h2>
	
	            <div class="status alert alert-success" style="display: none"></div>
	            <form id="main-contact-form" class="contact-form row" name="contact-form" method="post" action="${pageContext.request.contextPath}/controller/contact">	               
	                <div class="form-group col-md-12">
	                    <input type="email" name="email" class="form-control" placeholder="Nhập email (bắt buộc)" required="required">
	                </div>
	                <div class="form-group col-md-12">
	                    <input type="text" name="title" class="form-control" placeholder="Nhập tên tiêu đề (bắt buộc)" required="required">
	                </div>
	                <div class="form-group col-md-12">
	                    <textarea class="form-control" name="content" rows="7" placeholder="Nhập tin nhắn (bắt buộc)" required="required"></textarea>
	                </div>                        
	                <div class="form-group col-md-12">
	                    <input type="submit" name="submit" class="btn btn-success pull-right" value="Gửi">
	                </div>
	            </form>
	        </div>
	    </div>   			
	</div><!--/#contact-page-->
</div>