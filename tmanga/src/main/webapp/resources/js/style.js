$(document).ready(function(){
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
	
	//shipping page
//	$(".btnShipping").click(function(){
//		var id = $(this).attr('dataId');
//		var route = "customer/defaultAddress";
//		
//		$.ajax({
//			url : route,
//			type : 'GET',
//			data : {
//				id: id
//			},
//			success: function(data){
//				
//			}
//		})
//	})
	
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

	$('.guimail').click(function(){
      $('.guimail').attr('hidden','');
    });
	
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