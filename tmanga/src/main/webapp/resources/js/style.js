$(document).ready(function(){
//favorite list
	$('.favoritelist').click(function(){
		if(parseInt($(this).attr('data')) == 0){
			$(this).html('<i class="fa fa-heart" aria-hidden="true" title="Hủy yêu thích"></i>');
			$(this).attr('data','1');
		}
		else{
			$(this).html('<i class="fa fa-heart-o" aria-hidden="true" title="Thêm vào danh sách yêu thích"></i>');
			$(this).attr('data','0');
		}
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
	
//page register
	$("#submit").click(function(){
		var ps = $("#password").val();
		var reps = $("#repassword").val();
		var email = $("#email").val();
		
		if(isValidEmailAddress(email)) {
			alert('Email không hợp lệ');
			$("form").submit(function(e){
		        e.preventDefault();
		    });
			return;
		}
		
		if(ps != reps){
			alert('Mật khẩu không khớp');
			$("form").submit(function(e){
		        e.preventDefault();
		    });
			return;
		}
	});
	
	function isValidEmailAddress(emailAddress) {
	    var pattern = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
	    return pattern.test(emailAddress);
	};
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
    
//    $('.pagination li').click( function() {
//    	var data = parseInt($(this).attr('data'));
//    	reset();  
//    	$(this).addClass('active');
//    	
//    	var page = parseInt($("#page").val());
//    	var totalpage = parseInt($("#totalpage").val());
//    	
//    	if(data == -1){
//    		page = 1;
//    	}
//    	else if(data == -2){
//    		page--;
//    	}
//    	else if(data == -3){
//    		page++;
//    	}
//    	else if(data == -4){
//    		page = totalpage;
//    	}
//    	else {
//    		page = data;
//    	}
//    	    	
//    	$.ajax({
//    			url : "pagination",
//    			type : 'GET',
//    		data : {
//    			page : page,
//    			totalpage : totalpage
//    		},
//    		success: function(data){
//    			$('.pagination').html(data);
//    		}
//    	})
//    	
//    	
//    });
//    
//    function reset(){
//    	$('.pagination li').each(function () {
//    		$(this).removeClass('active');
//    	})
//    	$('.listcomic').each(function () {
//    		$(this).attr('hidden','');
//    	})
//    }
   
})