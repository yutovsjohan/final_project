<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- !PAGE CONTENT! -->
<div class="w3-container">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i> Trang chủ</b></h5>
  </header>

  <div class="w3-row-padding w3-margin-bottom">
    <div class="w3-quarter">
    	<a href="bill">
	      <div class="w3-container w3-blue w3-padding-16">
	        <div class="w3-left"><i class="fa fa-shopping-cart w3-xxxlarge"></i></div>
	        <div class="w3-right">
	          <h3>${billUnView}</h3>
	        </div>
	        <div class="w3-clear"></div>
	        <h4>Đơn hàng</h4>
	      </div>
	    </a>
    </div>
    
    <div class="w3-quarter">
    	<a href="contactAdmin">
	      <div class="w3-container w3-red w3-padding-16">
	        <div class="w3-left"><i class="fa fa-comment w3-xxxlarge"></i></div>
	        <div class="w3-right">
	          <h3>${contactUnView}</h3>
	        </div>
	        <div class="w3-clear"></div>
	        <h4>Tin nhắn</h4>
	      </div>
		</a>
    </div>
    
    <div class="w3-quarter">
    	<a href="userAdmin">
	      <div class="w3-container w3-orange w3-text-white w3-padding-16">
	        <div class="w3-left"><i class="fa fa-user-circle w3-xxxlarge"></i></div>
	        <div class="w3-right">
	          <h3>${countStaff}</h3>
	        </div>
	        <div class="w3-clear"></div>
	        <h4>Nhân viên</h4>
	      </div>
	    </a>
    </div>
    
    <div class="w3-quarter">
      <div class="w3-container w3-teal w3-padding-16">
        <div class="w3-left"><i class="fa fa-share-alt w3-xxxlarge"></i></div>
        <div class="w3-right">
          <h3>${countCustomer }</h3>
        </div>
        <div class="w3-clear"></div>
        <h4>Khách hàng</h4>
      </div>
    </div>  
    
  </div>
  
  <hr>
 
  <div style="width: 700px; height: 700px" id="chart" data="${countBillDaily }">
	<canvas id="myChartHome" width="400" height="400"></canvas>
  </div>
	
  <!-- <div class="w3-container">
    <h5>Recent Users</h5>
    <ul class="w3-ul w3-card-4 w3-white">
      <li class="w3-padding-16">
        <img src="/w3images/avatar2.png" class="w3-left w3-circle w3-margin-right" style="width:35px">
        <span class="w3-xlarge">Mike</span><br>
      </li>
      <li class="w3-padding-16">
        <img src="/w3images/avatar5.png" class="w3-left w3-circle w3-margin-right" style="width:35px">
        <span class="w3-xlarge">Jill</span><br>
      </li>
      <li class="w3-padding-16">
        <img src="/w3images/avatar6.png" class="w3-left w3-circle w3-margin-right" style="width:35px">
        <span class="w3-xlarge">Jane</span><br>
      </li>
    </ul>
  </div> -->
  
 </div>