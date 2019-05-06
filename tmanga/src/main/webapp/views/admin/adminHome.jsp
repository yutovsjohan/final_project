<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- !PAGE CONTENT! -->
<div class="w3-container">
<hr>
  <div class="w3-row-padding w3-margin-bottom">
    <div class="w3-quarter">
    	<a href="bill" title="Bấm vào để xem các đơn hàng">
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
    	<a href="contactAdmin" title="Bấm vào để xem các tin nhắn">
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
    	<a href="userAdmin?action=staff" title="Bấm vào để xem danh sách nhân viên">
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
    	<a href="userAdmin?action=customer" title="Bấm vào để xem danh sách khách hàng">
	      <div class="w3-container w3-teal w3-padding-16">
	        <div class="w3-left"><i class="fa fa-share-alt w3-xxxlarge"></i></div>
	        <div class="w3-right">
	          <h3>${countCustomer }</h3>
	        </div>
	        <div class="w3-clear"></div>
	        <h5>Khách hàng</h5>
	      </div>
	    </a>
    </div>  
    
  </div>
  
  <hr>
  <h2 style="color:red">Xem số đơn hàng theo từng ngày</h2>
  <br>
  <div class="row">
		<div class="col-sm-5">
			<div class="form-group">
				<div class="col-sm-4">
					<label for="dateStart">Ngày bắt đầu</label>
				</div>
				<div class="col-sm-7">
					<input type="date" class="form-control" name="dateStart" required="required" id="dateStart" value="${dateStart }" />
				</div>
			</div>
		</div>
		<div class="col-sm-5">
			<div class="form-group">
				<div class="col-sm-4">
					<label for="dateEnd">Ngày kết thúc</label>
				</div>
				<div class="col-sm-7">
					<input type="date" class="form-control" name="dateEnd" required="required" id="dateEnd" value="${dateEnd }" />
				</div>
			</div>
		</div>
		<div class="col-sm-2">
			<div class="form-group">
				<div class="col-sm-4">
					<button class="btn btn-success" id="report" dataUrl="home">Tạo biểu đồ</button>
				</div>
			</div>
		</div>
	</div>
  
  <hr>
	<div style="color: red">
		<center>
			<h3 id="title-chart">Biểu đồ số lượng đơn hàng từ ngày ${dateS } đến ngày ${dateE }</h3>
		</center>
	</div>

	<div style="width: 900px; height: 900px" id="chart" data="${countBillDaily }">  	
		<canvas id="myChartHome" width="400" height="400"></canvas>	
  	</div>
  
  <div id="views" data=${views }></div>
  
 </div>