$(document).ready(function(){
	//edit product (show date)
	var views = $("#views").val();
	var mode = $("#mode").val();
	if(views == 'ProductMan' && mode == 'edit'){
		var temp = $("#temp").val(); 
		var publishDate =  temp.split(" "); 
		$("#publishDate").val(publishDate[0]);
	}
	
//	$("#save-product").click(function(){
//		var publishDate = $("#publishDate").val();
//		var idCategory = $("#idCategory").val();
//		var idAuthor = $("#idAuthor").val();
//		var idPublishCompany = $("#idPublishCompany").val();
//		
//		var route = "comic";
//		$.ajax({
//			url : route,
//			type : 'POST',
//			data : {
//				publishDate: publishDate,
//				idCategory: idCategory,
//				idAuthor: idAuthor,
//				idPublishCompany: idPublishCompany
//			},
//			success: function(data){	
//				
//			}
//		})
//	})
	
	//page home admin
	var views = $("#views").attr('data');
	if(views == 'adminHome'){
		var ctx = document.getElementById('myChartHome');
		var data = $("#chart").attr('data');
		var temp = data.split(";"); 
		var label = temp[0].split(",");
		var value = temp[1].split(",");
		
		myChart = new Chart(ctx, {
		    type: 'bar',
		    data: {
		        labels: label,
		        datasets: [{
		            label: 'Số đơn hàng',
		            data: value,
		            backgroundColor: [
		            	'rgba(75, 192, 192, 0.2)',
		            	'rgba(75, 192, 192, 0.2)',
		            	'rgba(75, 192, 192, 0.2)',
		            	'rgba(75, 192, 192, 0.2)',
		            	'rgba(75, 192, 192, 0.2)',
		            	'rgba(75, 192, 192, 0.2)',
		            	'rgba(75, 192, 192, 0.2)',
		            ],
		            borderColor: [
		            	'rgba(75, 192, 192, 0.2)',
		            	'rgba(75, 192, 192, 0.2)',
		            	'rgba(75, 192, 192, 0.2)',
		            	'rgba(75, 192, 192, 0.2)',
		            	'rgba(75, 192, 192, 0.2)',
		            	'rgba(75, 192, 192, 0.2)',
		            	'rgba(75, 192, 192, 0.2)',
		            ],
		            borderWidth: 1
		        }]
		    },
		    options: {
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero: true
		                }
		            }]
		        }
		    }
		});
	}
	
	//comic list management
	
	$('.showHideComic').click(function(){
		var id= $(this).attr("dataId");
		var action = parseInt($(this).attr("action"));
		
		if(action == 0){
			$(this).attr("action","1");
			$(this).html('<i class="fa fa-eye" title="Bấm để ẩn trên trang web">');
		}
		else if(action == 1){
			$(this).attr("action","0");
			$(this).html('<i class="fa fa-eye-slash" title="Bấm để hiện lên trên trang web">');
		}
		
		$.ajax({
			url : "showHideComic",
			type : 'POST',
			data : {
				id: id
			},
			success: function(data){					
				
			}
		})
	})
	
	$("#confirm-change-comic").click(function(){
		var count = parseInt($("#select-all").attr("dataCount"));
		var price = $("#price").val();
		var amount = $("#amount").val();
		if(count != 0){
			if(price.length != 0){
				var route = "change-price";
				var idComic = 0;
				$('#table-list tr').each(function() {
					if($(this).attr("dataAction") == 1){
						$(this).find(".price").text((price/1000).toFixed(3));
						idComic = $(this).attr('dataId');
						$.ajax({
							url : route,
							type : 'POST',
							data : {
								price: price,
								idComic: idComic
							},
							success: function(data){	
								if(data == "fail"){
									alert("Cập nhật thất bại");
								}
							}
						})
					}
				})
			}
			if(amount.length != 0){
				var route = "change-amount";
				var idComic = 0;
				$('#table-list tr').each(function() {
					if($(this).attr("dataAction") == 1){
						$(this).find(".amount").text(amount);
						idComic = $(this).attr('dataId');
						$.ajax({
							url : route,
							type : 'POST',
							data : {
								amount: amount,
								idComic: idComic
							},
							success: function(data){	
								if(data == "fail"){
									alert("Cập nhật thất bại");
									$(this).find(".amount").text("");
								}
							}
						})
					}
				})
			}
		}
	})
	
	$("#change-comic").change(function(){
		var idCate = $("#select-option-change-category").val();
		if(parseInt(idCate) != 0){
			var name = $("#select-option-change-category option:selected").text();						
			var count = parseInt($("#select-all").attr("dataCount"));
			if(count != 0){
				var route = "change-category";
				var idComic = 0;
				$('#table-list tr').each(function() {
					if($(this).attr("dataAction") == 1){
						$(this).find(".categoryName").text(name);						
						idCate = $("#select-option-change-category").val();
						idComic = $(this).attr('dataId');
						$.ajax({
							url : route,
							type : 'POST',
							data : {
								idCategory: idCate,
								idComic: idComic
							},
							success: function(data){	
								if(data == "fail"){
									alert("Cập nhật thất bại");
								}
							}
						})
					}	
				});					
			}
			
		}
	})
	
	$("#select-all").click(function(){		
		if($("#select-all").prop("checked")){
			var count = 0;
			$("#table-list tr").each(function() {
				count++;
				$(this).css({
				    "background-color": "cyan"
				})
				$(this).attr("dataAction", "1");
			})
			
			$('.checkbox').each(function(){
				$(this).attr( "checked", "true");
				$(this).prop( "checked", "true");	
			})
			$("#select-all").attr("dataCount", count);
		}
		else{
			$("#table-list tr").each(function() {
				$(this).css({
				    "background-color": "rgb(240, 240, 250)"
				})
				$(this).attr("dataAction", "0");
			})
			
			$('.checkbox').each(function(){
				$(this).removeAttr( "checked");
				$(this).removeProp( "checked");			
			})
			$("#select-all").attr("dataCount", "0");
		}
		
		if( parseInt($("#select-all").attr("dataCount")) > 0){			
			$("#change-comic").removeAttr("hidden");
		}
		else{			
			$("#change-comic").attr("hidden","");
		}
		
	})
	
	$("#table-list tr").click(function() {
		if($(this).attr("dataAction") == 0){
			$(this).css({
			    "background-color": "cyan"
			})
			$(this).attr("dataAction", "1");
		}
		else if($(this).attr("dataAction") == 1){
			$(this).css({
			    "background-color": "rgb(240, 240, 250)"
			})
			$(this).attr("dataAction", "0");
		}
		
		var idComic = $(this).attr('dataId');
		var f = true;
		var countComic = -1;
		$('.checkbox').each(function(){
			countComic++;
			if($(this).attr('dataId') == idComic){				
				if($(this).attr("checked")){
					$(this).removeAttr( "checked");
					$(this).removeProp( "checked");
					f = false;
				}
				else{					
					$(this).attr( "checked", "true");
					$(this).prop( "checked", "true");
				}
			}
		})
		var count = parseInt($("#select-all").attr("dataCount"));
		if(f){
			count++;
		}
		else{
			count--;
		}
		$("#select-all").attr("dataCount", count);
		if(count < countComic){
			$("#select-all").removeProp("checked");
		}
		else if(count == countComic){
			$("#select-all").prop("checked", "true");
		}
		
		if(count > 0){
			$("#change-comic").removeAttr("hidden");
		}
		else if(count == 0){
			$("#change-comic").attr("hidden","");
		}
	})
	
	//search (bill management page)
	$(document).ready(function(){
	  $("#search").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#table-list tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
	
	//sort admin page
	$("#sort").change(function(){
		var sort = $("#sort").val();
		//id
		if(sort == 1){
			sortTable(0, "int", true);
		}
		else if(sort == 2){
			sortTable(0, "int", false);
		}
		//name category, author, publishing company; email (user management page)
		else if(sort == 3){
			sortTable(1, "string", true);
		}
		else if(sort == 4){
			sortTable(1, "string", false);
		}
		//amount comic (category, author, publishing company); amount comic (product management page)
		else if(sort == 5){
			sortTable(3, "int", true);
		}
		else if(sort == 6){
			sortTable(3, "int", false);
		}
		//order date(bill order management page)
		else if(sort == 7){
			sortTable(1, "date", true);
		}
		else if(sort == 8){
			sortTable(1, "date", false);
		}
		//delivery date(bill order management page)
		else if(sort == 9){
			sortTable(3, "date", true);
		}
		else if(sort == 10){
			sortTable(3, "date", false);
		}
		//delivery(bill order management page) ; name category (product management page)
		else if(sort == 11){
			sortTable(4, "string", true);
		}
		else if(sort == 12){
			sortTable(4, "string", false);
		}
		//email contact management page
		else if(sort == 13){
			sortTable(2, "string", true);
		}
		else if(sort == 14){
			sortTable(2, "string", false);
		}
		//name user management page
		else if(sort == 15){
			sortTable(0, "string", true);
		}
		else if(sort == 16){
			sortTable(0, "string", false);
		}
		//role user management page
		else if(sort == 17){
			sortTable(3, "string", true);
		}
		//price comic management page
		else if(sort == 18){
			sortTable(5, "int", true);
		}
		else if(sort == 19){
			sortTable(5, "int", false);
		}
	})
	
	function sortTable(j, type, isAsc) {
	  var count = 1; var dateA, dateB;
	  var table, rows, switching, i, x, y, shouldSwitch, value, temp;
	  table = document.getElementById("myTable");
	  switching = true;
	  /*Make a loop that will continue until
	  no switching has been done:*/
	  while (switching) {
	    //start by saying: no switching is done:
	    switching = false;
	    rows = table.rows;
	    /*Loop through all table rows (except the
	    first, which contains table headers):*/
	    for (i = 1; i < (rows.length - 1); i++) {
	      //start by saying there should be no switching:
	      shouldSwitch = false;
	      /*Get the two elements you want to compare,
	      one from current row and one from the next:*/
	      x = rows[i].getElementsByTagName("TD")[j];
	      y = rows[i + 1].getElementsByTagName("TD")[j];
	      
	      //check if the two rows should switch place:
	      if(type == 'int'){
	    	  if(isAsc){
	    		  if (parseInt(x.innerText) > parseInt(y.innerText)) {
	  	  	        //if so, mark as a switch and break the loop:
	  	  	        shouldSwitch = true;
	  	  	        break;
	  	  	      }
	    	  }
	    	  else{
	    		  if (parseInt(x.innerText) < parseInt(y.innerText)) {
	  	  	        //if so, mark as a switch and break the loop:
	  	  	        shouldSwitch = true;
	  	  	        break;
	  	  	      }
	    	  }
	    	  
	      }
	      else if(type == "string"){	    	  
	    	  if(isAsc){
	    		  if (x.innerText.toLowerCase() > y.innerText.toLowerCase()) {
	  	  	        //if so, mark as a switch and break the loop:
	  	  	        shouldSwitch = true;
	  	  	        break;
	  	  	      }
	    	  }
	    	  else{
	    		  if (x.innerText.toLowerCase() < y.innerText.toLowerCase()) {
	  	  	        //if so, mark as a switch and break the loop:
	  	  	        shouldSwitch = true;
	  	  	        break;
	  	  	      }
	    	  }
	    	  
	      }
	      else if(type == "date"){
	    	  if(count == 200){
	    		  return;
	    	  }
	    	  count++;
	    	  value = x.innerText;
	    	  temp = value.split('-');
	    	  value = temp.reverse().join('-');
			  
	    	  dateA = new Date(value);
	    	  	    	 	    	  	    	  
	    	  value = y.innerText;
	    	  temp = value.split('-');
	    	  value = temp.reverse().join('-');
			  
	    	  dateB = new Date(value);
	    	  
	    	  if(isAsc){
	    		  if (dateA - dateB > 0) {
	  	  	        //if so, mark as a switch and break the loop:
	  	  	        shouldSwitch = true;
	  	  	        break;
	  	  	      }
	    	  }
	    	  else{
	    		  if (dateA - dateB < 0) {
	  	  	        //if so, mark as a switch and break the loop:
	  	  	        shouldSwitch = true;
	  	  	        break;
	  	  	      }
	    	  }
	      }
	      
	    }
	    if (shouldSwitch) {
	      /*If a switch has been marked, make the switch
	      and mark that a switch has been done:*/
	      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
	      switching = true;
	    }
	  }
	}
	
	//report
	$("#report").click(function(){
		var dateStart = $('#dateStart').val();
		if(dateStart == ''){
			alert('Ngày bắt đầu không hợp lệ');
		}
		else{
			var dateEnd = $('#dateEnd').val();
			if(dateEnd == ''){
				alert('Ngày kết thúc không hợp lệ');
			}
			else{
				var pt = $('#pt').val();
				var title = 'Biểu đồ ';
				var label_chart = "Doanh thu";
				
				var dataUrl = $("#report").attr('dataUrl');
				if(dataUrl == 'home'){
					pt = 4;
					title += 'số lượng đơn hàng từ ngày ' + dateStart.split('-').reverse().join('/') + ' đến ngày ' + dateEnd.split('-').reverse().join('/');
					label_chart = "Số đơn hàng";
				}		
				
				if(pt == 1){
					title += 'doanh thu từ ngày ' + dateStart.split('-').reverse().join('/') + ' đến ngày ' + dateEnd.split('-').reverse().join('/');
				}
				else if(pt == 2){
					var temp = dateStart.split('-');
					var month = temp[1];
					var year = temp[0];
					title += 'doanh thu từ tháng ' + month + "/" + year; 
					
					temp = dateEnd.split('-');
					month = temp[1];
					year = temp[0];
					title += ' đến tháng ' + month + "/" + year;
				}
				else if(pt == 3){
					var temp = dateStart.split('-');
					var year = temp[0];
					title += 'doanh thu từ năm ' + year; 
					
					temp = dateEnd.split('-');
					year = temp[0];
					title += " đến năm " + year;
				}
								
				var route = "create-report";
				var myChart;				
					
				$.ajax({
					url : route,
					type : 'POST',
					data : {
						dateStart: dateStart,
						dateEnd: dateEnd,
						pt: pt
					},
					success: function(data){		
						if(data == 'fail'){
							alert('Ngày kết thúc phải lớn hơn ngày bắt đầu');
						}
						else{
							$("#chart").html('<canvas id="myChart" width="400" height="400"></canvas>');
							$("#title-chart").text(title);
							var temp = data.split(";"); 
							var label = temp[0].split(",");
							var value = temp[1].split(",");
							
							var ctx = document.getElementById('myChart');
													
							myChart = new Chart(ctx, {
							    type: 'bar',
							    data: {
							        labels: label,
							        datasets: [{
							            label: label_chart,
							            data: value,
							            backgroundColor: [
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)'
							            ],
							            borderColor: [
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)',
							            	'rgba(75, 192, 192, 0.2)'					            	
							            ],
							            borderWidth: 1
							        }]
							    },
							    options: {
							        scales: {
							            yAxes: [{
							                ticks: {
							                    beginAtZero: true
							                }
							            }]
							        }
							    }
							});
						}
										
					}
				})
			}
		}
	});
		
	//user admin page
	$('.setup-pw').click(function(){		
		if(confirm("Bạn có muốn đặt lại mật khẩu cho tài khoản này không?")){
			var id = $(this).attr('dataId');
			var route = "setup-pw";
			
			$.ajax({
				url : route,
				type : 'POST',
				data : {
					id: id
				},
				success: function(data){
					if(data == 'success'){
						alert('Đặt lại mật khẩu thành công');
					}			
					else if(data == 'fail'){
						alert('Đặt lại mật khẩu thất bại');
					}
				}
			})
			
		}
	})
	
	$('.ban-acc').click(function(){
		var id = $(this).attr('dataId');
		var route = "ban-acc";
		var active = parseInt($(this).attr('dataActive'));
		
		if(active == 1){
			$(this).html("<button class='btn btn-warning'>Mở tài khoản</button>");
			$(this).attr('dataActive','0');
		}
		else if(active == 0){
			$(this).html("<button class='btn btn-danger'>Cấm tài khoản</button>");
			$(this).attr('dataActive','1');
		}	
		
		$.ajax({
			url : route,
			type : 'POST',
			data : {
				id: id
			},
			success: function(data){
							
			}
		})
	})
	
	//bill detail admin page
	$('.change-status').click(function(){
		var id = $(this).attr('dataId');
		var str = $(this).text();
		if(confirm("Bạn có muốn chuyển sang trạng thái " + str + " không ?")){
			window.location.href = "add-order-status?id=" + id;
		}
	})
	
	$('.return-status').click(function(){
		var id = $(this).attr('dataId');
		if(confirm("Bạn có quay lại trạng thái trước đó không ?")){
			window.location.href = "come-back-order-status?id=" + id;
		}
	})
	
	//admin page
	$('.showHideNews').click(function(){
		var id= $(this).attr("dataId");
		var action = parseInt($(this).attr("action"));
		
		if(action == 0){
			$(this).attr("action","1");
			$(this).html('<i class="fa fa-eye" title="Bấm để ẩn trên trang web">');
		}
		else if(action == 1){
			$(this).attr("action","0");
			$(this).html('<i class="fa fa-eye-slash" title="Bấm để hiện lên trên trang web">');
		}
		
		$.ajax({
			url : "showHideNews",
			type : 'POST',
			data : {
				id: id
			},
			success: function(data){					
				
			}
		})
	})
	
	$('.removeNews').click(function(){
		if(confirm("Bạn có thực sự muốn xóa ?")){
			var buttonRemoveId = parseInt($(this).attr('dataId'));			
			var route = "removeNews";
			
			$.ajax({
				url : route,
				type : 'POST',
				data : {
					id: buttonRemoveId
				},
				success: function(data){
					if(data == 'success'){
						var divId;
						$('.newsList').each(function () {
							divId = parseInt($(this).attr('dataId'));
							if(buttonRemoveId == divId){
								$(this).remove();
							}
						})
					}
					else{
						alert('Xóa thất bại');
					}
				}
			})
		 }
	     else{
	        return false;
	     }
	})
	
	//role management page
	$('.role').click(function(){
	  var mode = $(this).attr("dataMode");
	  var name = prompt("Nhập tên chức vụ:");
	  if (name == null || name == "") {
	    
	  }
	  else {
		  var route = "role";
		  var id = 0;
		 
		  if(mode == "edit"){
			  id = $(this).attr("dataId");
			  $(".name").each(function(){
					if($(this).attr("dataId") == id){
						$(this).text(name);	
					}						
			  })
		  }
		 $.ajax({
			url : route,
			type : 'POST',
			data : {
				mode: mode,
				name: name,
				id: id
			},
			success: function(data){
				
			}
		})
	  }
	})
	
	$('.removeRole').click(function(){
		if(confirm("Bạn có thực sự muốn xóa ?")){
			var buttonRemoveId = parseInt($(this).attr('dataId'));			
			var route = "removeRole";
			
			$.ajax({
				url : route,
				type : 'POST',
				data : {
					id: buttonRemoveId
				},
				success: function(data){
					if(data == 'success'){
						var divId;
						$('.roleList').each(function () {
							divId = parseInt($(this).attr('dataId'));
							if(buttonRemoveId == divId){
								$(this).remove();
							}
						})
					}
					else{
						alert('Xóa thất bại');
					}
				}
			})
		 }
	     else{
	        return false;
	     }
	})
	
	//author management page
	$('.author').click(function(){
	  var mode = $(this).attr("dataMode");
	  var name = prompt("Nhập tên tác giả:");
	  if (name == null || name == "") {
	    
	  }
	  else {
		  var route = "author";
		  var id = 0;
		 
		  if(mode == "edit"){
			  id = $(this).attr("dataId");
			  $(".name").each(function(){
					if($(this).attr("dataId") == id){
						$(this).text(name);	
					}						
			  })
		  }
		 $.ajax({
			url : route,
			type : 'POST',
			data : {
				mode: mode,
				name: name,
				id: id
			},
			success: function(data){
				
			}
		})
	  }
	})
	
	$('.showHideAuthor').click(function(){
		var id= $(this).attr("dataId");
		var action = parseInt($(this).attr("action"));
		
		if(action == 0){
			$(this).attr("action","1");
			$(this).html('<i class="fa fa-eye" title="Bấm để ẩn trên trang web">');
		}
		else if(action == 1){
			$(this).attr("action","0");
			$(this).html('<i class="fa fa-eye-slash" title="Bấm để hiện lên trên trang web">');
		}
		
		$.ajax({
			url : "showHideAuthor",
			type : 'POST',
			data : {
				id: id
			},
			success: function(data){					
				
			}
		})
	})
	
	$('.removeAuthor').click(function(){
		if(confirm("Bạn có thực sự muốn xóa ?")){
			var buttonRemoveId = parseInt($(this).attr('dataId'));			
			var route = "removeAuthor";
			
			$.ajax({
				url : route,
				type : 'POST',
				data : {
					id: buttonRemoveId
				},
				success: function(data){
					if(data == 'success'){
						var divId;
						$('.authorList').each(function () {
							divId = parseInt($(this).attr('dataId'));
							if(buttonRemoveId == divId){
								$(this).remove();
							}
						})
					}
					else{
						alert('Xóa thất bại');
					}
				}
			})
		 }
	     else{
	        return false;
	     }
	})
	
	//category management page
	$('.category').click(function(){
	  var mode = $(this).attr("dataMode");
	  var name = prompt("Nhập tên tác giả:");
	  if (name == null || name == "") {
	    
	  }
	  else {
		  var route = "category";
		  var id = 0;
		 
		  if(mode == "edit"){
			  id = $(this).attr("dataId");
			  $(".name").each(function(){
					if($(this).attr("dataId") == id){
						$(this).text(name);	
					}						
			  })
		  }
		 $.ajax({
			url : route,
			type : 'POST',
			data : {
				mode: mode,
				name: name,
				id: id
			},
			success: function(data){
				
			}
		})
	  }
	})
	
	$('.showHideCate').click(function(){
		var id= $(this).attr("dataId");
		var action = parseInt($(this).attr("action"));
		
		if(action == 0){
			$(this).attr("action","1");
			$(this).html('<i class="fa fa-eye" title="Bấm để ẩn trên trang web">');
		}
		else if(action == 1){
			$(this).attr("action","0");
			$(this).html('<i class="fa fa-eye-slash" title="Bấm để hiện lên trên trang web">');
		}
		
		$.ajax({
			url : "showHideCate",
			type : 'POST',
			data : {
				id: id
			},
			success: function(data){					
				
			}
		})
	})
	
	$('.removeCategory').click(function(){
		if(confirm("Bạn có thực sự muốn xóa ?")){
			var buttonRemoveId = parseInt($(this).attr('dataId'));			
			var route = "removeCate";
			
			$.ajax({
				url : route,
				type : 'POST',
				data : {
					id: buttonRemoveId
				},
				success: function(data){
					if(data == 'success'){
						var divId;
						$('.cateList').each(function () {
							divId = parseInt($(this).attr('dataId'));
							if(buttonRemoveId == divId){
								$(this).remove();
							}
						})
					}
					else{
						alert('Xóa thất bại');
					}
				}
			})
		 }
	     else{
	        return false;
	     }
	})
	
	//publishing company management page
	$('.publishing').click(function(){
	  var mode = $(this).attr("dataMode");
	  var name = prompt("Nhập tên nhà xuất bản:");
	  if (name == null || name == "") {
	    
	  }
	  else {
		  var route = "publishCompany";
		  var id = 0;
		 
		  if(mode == "edit"){
			  id = $(this).attr("dataId");
			  $(".name").each(function(){
					if($(this).attr("dataId") == id){
						$(this).text(name);	
					}						
			  })
		  }
		 $.ajax({
			url : route,
			type : 'POST',
			data : {
				mode: mode,
				name: name,
				id: id
			},
			success: function(data){
				
			}
		})
	  }
	})
	
	$('.showHidePC').click(function(){
		var id= $(this).attr("dataId");
		var action = parseInt($(this).attr("action"));
		
		if(action == 0){
			$(this).attr("action","1");
			$(this).html('<i class="fa fa-eye" title="Bấm để ẩn trên trang web">');
		}
		else if(action == 1){
			$(this).attr("action","0");
			$(this).html('<i class="fa fa-eye-slash" title="Bấm để hiện lên trên trang web">');
		}
		
		$.ajax({
			url : "showHidePublishing",
			type : 'POST',
			data : {
				id: id
			},
			success: function(data){					
				
			}
		})
	})
	
	$('.removePC').click(function(){
		if(confirm("Bạn có thực sự muốn xóa ?")){
			var buttonRemoveId = parseInt($(this).attr('dataId'));			
			var route = "removePublishing";
			
			$.ajax({
				url : route,
				type : 'POST',
				data : {
					id: buttonRemoveId
				},
				success: function(data){
					if(data == 'success'){
						var divId;
						$('.PCList').each(function () {
							divId = parseInt($(this).attr('dataId'));
							if(buttonRemoveId == divId){
								$(this).remove();
							}
						})
					}
					else{
						alert('Xóa thất bại');
					}
				}
			})
		 }
	     else{
	        return false;
	     }
	})
	
	//bill admin page
	$(".confirm-delivery-date").click(function(){
		var route = "select-delivery-date";
		var idBill = $(this).attr('dataId');
		var date = "";
		$(".select-delivery-date").each(function(){
			if(idBill == parseInt($(this).attr("dataId"))){
				date = $(this).val();
			}
		})
		$.ajax({
			url : route,
			type : 'POST',
			data : {
				idBill: idBill,
				date: date
			},
			success: function(data){
				if(data != 'fail'){						
					$(".delivery-date").each(function(){
						if(idBill == parseInt($(this).attr("dataId"))){
							$(this).removeAttr('hidden');
							$(this).text(data);
						}
					})
					
					$(".form-delivery-date").each(function(){
						if(idBill == parseInt($(this).attr("dataId"))){
							$(this).attr('hidden','');
						}
					})
				}
				else{
					alert('Ngày giao hàng phải lớn hơn ngày đặt hàng');
				}
			}
		})
	})
	
	$(".delivery-date").click(function(){
		$(this).attr("hidden","");
		var id = parseInt($(this).attr("dataId"));
		$(".form-delivery-date").each(function(){
			if(id == parseInt($(this).attr("dataId"))){
				$(this).removeAttr("hidden");
			}			
		})
	})
	
	$(".cancel-delivery-date").click(function(){
		var id = parseInt($(this).attr("dataId"));
		$(".form-delivery-date").each(function(){
			if(id == parseInt($(this).attr("dataId"))){
				$(this).attr("hidden","");
			}			
		})
		$(".delivery-date").each(function(){
			if(id == parseInt($(this).attr("dataId"))){
				$(this).removeAttr("hidden");
			}			
		})
	})	
	
	$(".select-delivery").click(function(){
		$(this).attr("hidden","");
		var id = parseInt($(this).attr("dataId"));
		$(".delivery").each(function(){
			if(id == parseInt($(this).attr("dataId"))){
				$(this).removeAttr("hidden");
			}			
		})
	})
	
	$(".delivery").mouseleave(function(){
		$(this).attr("hidden","");
		var id = parseInt($(this).attr("dataId"));
		$(".select-delivery").each(function(){
			if(id == parseInt($(this).attr("dataId"))){
				$(this).removeAttr("hidden");
			}			
		})
	})	
	
	$(".delivery").change(function(){
		var idBill = $(this).attr("dataId");
		var idUser = $(this).val();
		var route = "select-delivery";
		
		$(this).attr("hidden","");
		if(parseInt(idUser) == 0){
			$(".select-delivery").each(function(){
				$(this).removeAttr('hidden');
			})
		}
		else{		
			$.ajax({
				url : route,
				type : 'POST',
				data : {
					idBill: idBill,
					idUser: idUser
				},
				success: function(data){
					if(data != 'fail'){						
						$(".select-delivery").each(function(){
							if(idBill == parseInt($(this).attr("dataId"))){
								$(this).removeAttr('hidden');
								$(this).text(data);
							}
						})
					}
				}
			})
		}
		
	})
	
	//payment page
	$("#pttt").change(function() {
		var pttt = parseInt($('#pttt').val());
		if(pttt == -1){
			$('#order').attr('disabled', 'disabled');
			$('#note').removeAttr('hidden');
		}
		else{
			$('#order').removeAttr('disabled');
			$('#note').attr('hidden','');
		}
	})
	
	//cancel order
	$(".cancelOrder").click(function(){
		if(confirm("Bạn có thực sự muốn hủy đơn hàng ?")){
			var idBill = $("#idBill").text();
			var email = $("#email").text();
						
			var route = "cancel-order";
			var href = $("#href").text();
			if(href == 'customerOrderDetail'){
				route = "../cancel-order";
			}
			
			$.ajax({
				url : route,
				type : 'POST',
				data : {
					idBill: idBill,
					email: email
				},
				success: function(data){
					document.write(data);
				}
			})
		}
		else{
			return false;
		}
	});

	$("#send-email-confirm-bill").click(function(){
		var idBill = $("#idBill").text();
		var email = $("#email").text();
					
		var route = "send-email-confirm-bill";
		var href = $("#href").text();
		if(href == 'customerOrderDetail'){
			route = "../send-email-confirm-bill";
		}
		
		$.ajax({
			url : route,
			type : 'POST',
			data : {
				idBill: idBill,
				email: email
			},
			success: function(data){
				document.write(data);
			}
		})
	});
	
	//delete address
	$(".removeAddress").click(function(){
		if(confirm("Bạn có thực sự muốn xóa ?")){
			var buttonRemoveId = parseInt($(this).attr('dataId'));			
			var route = "removeAddress";
			
			$.ajax({
				url : route,
				type : 'POST',
				data : {
					id: buttonRemoveId
				},
				success: function(data){
					if(data == 'success'){
						var divId;
						$('.addressBook').each(function () {
							divId = parseInt($(this).attr('dataId'));
							if(buttonRemoveId == divId){
								$(this).remove();
							}
						})
					}
					else{
						alert('Xóa thất bại');
					}
				}
			})
			
			
	    }
	    else{
	        return false;
	    }
	});
	
	//validate page create address
	$("form").keyup(function() {
		$("input[type=submit]").removeAttr('disabled');
		$("#save").css({
			"cursor": "pointer",
		    "background-color": "#5cb85c"
		});
		$("#save").prop("type","submit");
	});
	
	$("form").change(function() {
		$("input[type=submit]").removeAttr('disabled');
		$("#save").css({
			"cursor": "pointer",
			"background-color": "#5cb85c"
		});
		$("#save").prop("type","submit");
	});
	
	$("#chooseDefault").change(function(){
		if ($('#chooseDefault').is(":checked")){
			$('#choose').val('1');
		}
		else{
			$('#choose').val('0');
		}
	});
	
	$("#save").click(function(){
		var city = parseInt($('#city').val());
		var district = parseInt($('#district').val());
		var phone = $('#phone').val();
				 
		var f = true;
		
		if(city == 0){
			alert('Bạn chưa chọn Tỉnh / Thành phố');
			f = false;
		}
		
		else if(district == 0){
			alert('Bạn chưa chọn Quận / Huyện');
			f = false;
		}
		else if(!$.isNumeric(phone) || phone.length < 10){
			alert('Điện thoại không hợp lệ');
			f = false;
		}
				
		if(!f){
			$("input[type=submit]").attr('disabled','disabled');
			$("#save").css({
				"cursor": "default",
				"background-color": "grey"
			});
			$("#save").prop("type","disable");
		}
	});
	
	//create address book, select district from city
	$('#city').change(function(){
		var city = parseInt($('#city').val());
		var route = "getDistrict";
		
		$.ajax({
			url : route,
			type : 'GET',
			data : {
				city: city
			},
			success: function(data){
				$('#district').html(data);
			}
		})
	});
	
//favorite list
	$('.productList').click(function(){
		var route = $("#route").val();
		var key = 1;
		var id = $(this).attr('dataId');		
//		if(parseInt($(this).attr('data')) == 0){		
//			$(this).removeClass("btn btn-warning favoritelist");
//			$(this).addClass("btn btn-danger favoritelist");
//			$(this).html('<i class="fa fa-heart" aria-hidden="true" title="Hủy yêu thích"></i>');
//			$(this).attr('data','1');
//			key = 1;
//		}
//		else{
//			$(this).removeClass("btn btn-danger favoritelist");
//			$(this).addClass("btn btn-warning favoritelist");
//			$(this).html('<i class="fa fa-heart-o" aria-hidden="true" title="Thêm vào danh sách yêu thích"></i>');
//			$(this).attr('data','0');
//			key = 2;
//		}
		
		$.ajax({
			url : route,
			type : 'POST',
			data : {
				key : key,
				id : id
			},
			success: function(data){
//				var str = "";
//				if(key == 1){
//					str = "Thêm";
//				}
//				else {
//					str = "Hủy";
//				}
//				if(data == 'fail'){					
//					$('#modal-da-them-vao-gio-hang .modal-body p').html(str + " thất bại");
//					$('#modal-da-them-vao-gio-hang').modal('show');					
//				}
//				else if(data == 'success'){
//					$('#modal-da-them-vao-gio-hang .modal-body p').html(str + " thành công");
//					$('#modal-da-them-vao-gio-hang').modal('show');	
//				}
//				else if(data == 'exist'){
//					$('#modal-da-them-vao-gio-hang .modal-body p').html("Sản phẩm này đã được thêm");
//					$('#modal-da-them-vao-gio-hang').modal('show');
//				}
			}
		});
	});	
	
//page login
	$('.forget-password').click(function(){
		$('.form-forget-password').removeAttr('hidden');
	})

	// $('.change-password').click(function(){
	// 	$('.form-change-password').removeAttr('hidden');
	// })

//	$('.oneClick').click(function(){
//      $('.oneClick').attr("disabled","disabled");
//    });
	
	$("#changePassword").change(function(){
      if($(this).is(":checked")){
        $(".password").removeAttr('disabled');
      }
      else{
        $(".password").attr('disabled',''); 
      }
    });
	
	$("#showpw").mouseover(function(){
		$("#password" ).prop("type","text");
		$("#repassword" ).prop("type","text");		
	});
	
	$("#showpw").mouseout(function(){
		$("#password" ).prop("type", "password");
		$("#repassword" ).prop("type","password");	
	});
	
//validate email, password, phone (login, register page)
	$("form").keyup(function() {
		$("input[type=submit]").removeAttr('disabled');
		$("#submit").css({
			"cursor": "pointer"
		});
		$("#submit").prop("type","submit");
	});
	
	$("form").change(function() {
		$("input[type=submit]").removeAttr('disabled');
		$("#submit").css({
			"cursor": "pointer"
		});
		$("#submit").prop("type","submit");
	});
	
	$("#email").change(function(){
		var email = $("#email").val();
		route = "check-email";
		$.ajax({
			url : route,
			type : 'GET',
			data : {
				email: email
			},
			success: function(data){
				if(data == 'success'){
					$("#note").attr("hidden","");
				}			
				else if(data == 'fail'){
					$("#note").removeAttr("hidden");
					$("input[type=submit]").attr('disabled','disabled');
					$("#submit").css({
						"cursor": "default"
					});
					$("#submit").prop("type","disable");
				}
			}
		})
	})
	
	$("#submit").click(function(){
		var ps = $("#password").val();
		var phone = $("#phone").val();
		var email = $("#email").val();
		var f = true;
		
		if(!isValidEmailAddress(email)) {
			alert('Email không hợp lệ');	
			f = false;				
		}
		
		if( $("#repassword").length ) {
			var reps = $("#repassword").val();
			
			if(ps != reps){
				alert('Mật khẩu không khớp');
				f = false;				
			}
		}	
		
		if( $("#phone").length ) {
			if(!$.isNumeric(phone) || phone.length < 10){
				alert('Điện thoại không hợp lệ');
				f = false;
			}
		}
				
		if(!f){
			$("input[type=submit]").attr('disabled','disabled');
			$("#submit").css({
				"cursor": "default"				
			});
			$("#submit").prop("type","disable");
		}
	});
	
	function isValidEmailAddress(email) {
		var regex = /^[a-zA-Z0-9._]+@[a-zA-Z0-9]+(\.[a-zA-Z0-9]{2,4}){1,2}$/;
		 return regex.test(email);
	};
	
//validate password (customer edit info)
	$("#save").click(function(){
		var ps = $("#password").val();
		var reps = $("#repassword").val();
		
		if(ps != reps){
			alert('Mật khẩu không khớp');
			
			$("#save").attr('disabled','disabled');
			$("#save").css({
				"cursor": "default"				
			});
			$("#save").prop("type","disable");
		}
	});
	
	$("form").keyup(function() {
		$("#save").removeAttr('disabled');
		$("#save").css({
			"cursor": "pointer"
		});
		$("#save").prop("type","submit");
	});
	
	$("form").change(function() {
		$("#save").removeAttr('disabled');
		$("#save").css({
			"cursor": "pointer"
		});
		$("#save").prop("type","submit");
	});
	
//page view product detail

	$("#hinh").elevateZoom({
        zoomType: "inner",
        scrollZoom : true,
        cursor: "crosshair",
        zoomWindowFadeIn: 500,
        zoomWindowFadeOut: 750                
    });    
    
	// button add to cart
    $('.them-vao-gio-hang').click(function(){		
		var itemcount = parseInt($("#itemcount").text());
		
		var route = "addtocart";
		if($('#fl').val() != null){
			route = "../addtocart";
		}
		
		
		var action = 'add';
		
		var amount = 1;
		var id=$(this).attr('dataId');
		var name=$(this).attr('dataName');
		
		if(amount != 1){
			return;
		}
		
		//ajax
		$.ajax({
			url : route,
			type : 'GET',
			data : {
				id : id,
				amount : amount,
				action : action
			},
			success: function(data){
				//console.log(data); 
				if(data=="added"){
					$('#modal-da-them-vao-gio-hang').modal('show');
					$('#modal-da-them-vao-gio-hang .modal-body p').html("Đã thêm <b></b> vào giỏ hàng.");
					$('#modal-da-them-vao-gio-hang .modal-body b').html(name);
					
					itemcount++;
					console.log(itemcount); 
					$("#itemcount").text(itemcount);
					
					var str = '<i class=\"fa fa-shopping-cart\"></i>Giỏ hàng <span class="itemcount"> ' + itemcount + '</span';
					$('#gio-hang').html(str);
				}
				else{
					$('#modal-da-them-vao-gio-hang').modal('show');
					$('#modal-da-them-vao-gio-hang .modal-body p').html(data);
				}
			}
		})
	});
    
	// add to cart (detail product)
    $('.addtocart').click(function(){
    	var itemcount = parseInt($("#itemcount").text());
		var route = "addtocart";
		
		var amount = parseInt($('#sl').val());
		var id = parseInt($('#idComic').val());
		var name = $('#nameComic').text();
		var action = 'add';
		
		if(amount == 0){
			return;
		}
		else if(amount < 0){
			$('#modal-da-them-vao-gio-hang .modal-body p').html("Số lượng không thể âm");
			$('#modal-da-them-vao-gio-hang').modal('show');			
			return;
		}
		
		//ajax
		$.ajax({
			url : route,
			type : 'GET',
			data : {
				id : id,
				amount : amount,
				action : action
			},
			success: function(data){
				//console.log(data); 
				if(data=="added"){
					$('#modal-da-them-vao-gio-hang').modal('show');
					$('#modal-da-them-vao-gio-hang .modal-body').html("Đã thêm <b></b> vào giỏ hàng.");
					$('#modal-da-them-vao-gio-hang .modal-body b').html(name);
					
					itemcount += amount;
					console.log(itemcount); 
					$("#itemcount").text(itemcount);
					
					var str = '<i class=\"fa fa-shopping-cart\"></i>Giỏ hàng <span class="itemcount"> ' + itemcount + '</span';
					$('#gio-hang').html(str);
				}
				else{
					$('#modal-da-them-vao-gio-hang').modal('show');
					$('#modal-da-them-vao-gio-hang .modal-body p').html(data);
				}
			}
		})
    }); 
  
})