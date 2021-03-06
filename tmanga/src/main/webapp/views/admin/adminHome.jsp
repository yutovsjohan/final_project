<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- !PAGE CONTENT! -->
<div class="w3-container">
<hr>
  <div class="w3-row-padding w3-margin-bottom">
    <div class="w3-quarter">
    	<a href="bill" title="<spring:message code='admin.home.title.billcount' />" >
	      <div class="w3-container w3-blue w3-padding-16">
	        <div class="w3-left"><i class="fa fa-shopping-cart w3-xxxlarge"></i></div>
	        <div class="w3-right">
	          <h3>${billUnView}</h3>
	        </div>
	        <div class="w3-clear"></div>
	        <h4><spring:message code="admin.home.billcount" /></h4>
	      </div>
	    </a>
    </div>
    
    <div class="w3-quarter">
    	<a href="contactAdmin" title="<spring:message code='admin.home.title.contactcount' />" >
	      <div class="w3-container w3-red w3-padding-16">
	        <div class="w3-left"><i class="fa fa-comment w3-xxxlarge"></i></div>
	        <div class="w3-right">
	          <h3>${contactUnView}</h3>
	        </div>
	        <div class="w3-clear"></div>
	        <h4><spring:message code="admin.home.contactcount" /></h4>
	      </div>
		</a>
    </div>
    
    <div class="w3-quarter">
    	<a href="userAdmin?action=staff" title="<spring:message code='admin.home.title.staffcount' />" >
	      <div class="w3-container w3-orange w3-text-white w3-padding-16">
	        <div class="w3-left"><i class="fa fa-user-circle w3-xxxlarge"></i></div>
	        <div class="w3-right">
	          <h3>${countStaff}</h3>
	        </div>
	        <div class="w3-clear"></div>
	        <h4><spring:message code="admin.home.staffcount" /></h4>
	      </div>
	    </a>
    </div>
    
    <div class="w3-quarter">
    	<a href="userAdmin?action=customer" title="<spring:message code='admin.home.title.customercount' />" >
	      <div class="w3-container w3-teal w3-padding-16">
	        <div class="w3-left"><i class="fa fa-share-alt w3-xxxlarge"></i></div>
	        <div class="w3-right">
	          <h3>${countCustomer }</h3>
	        </div>
	        <div class="w3-clear"></div>
	        <h5><spring:message code="admin.home.customercount" /> </h5>
	      </div>
	    </a>
    </div>  
    
  </div>
  
  <hr>
  <h2 style="color:red"><spring:message code="admin.home.viewcountbill" /></h2>
  <br>
  <div class="row">
		<div class="col-sm-5">
			<div class="form-group">
				<div class="col-sm-4">
					<label for="dateStart"><spring:message code="admin.home.dateStart" /></label>
				</div>
				<div class="col-sm-7">
					<input type="date" class="form-control" name="dateStart" required="required" id="dateStart" value="${dateStart }" />
				</div>
			</div>
		</div>
		<div class="col-sm-5">
			<div class="form-group">
				<div class="col-sm-4">
					<label for="dateEnd"><spring:message code="admin.home.dateEnd" /></label>
				</div>
				<div class="col-sm-7">
					<input type="date" class="form-control" name="dateEnd" required="required" id="dateEnd" value="${dateEnd }" />
				</div>
			</div>
		</div>
		<div class="col-sm-2">
			<div class="form-group">
				<div class="col-sm-4">
					<button class="btn btn-success" id="report" dataUrl="home"><spring:message code="admin.home.createchart" /></button>
				</div>
			</div>
		</div>
	</div>
  
  <hr>
	<div style="color: red">
		<center>
			<h3 id="title-chart"><spring:message code="admin.home.titlechart1" /> ${dateS } <spring:message code="admin.home.titlechart2" /> ${dateE }</h3>
		</center>
	</div>

	<div style="width: 900px; height: 900px" id="chart" data="${countBillDaily }">  	
		<canvas id="myChartHome" width="400" height="400"></canvas>	
  	</div>
  
  <div id="views" data=${views }></div>
  
 </div>