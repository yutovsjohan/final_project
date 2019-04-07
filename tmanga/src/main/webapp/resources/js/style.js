
$(document).ready(function(){
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
				amount : amount
			},
			success: function(data){
				//console.log(data); 
				if(data=="added"){
					$('#modal-da-them-vao-gio-hang').modal('show');
					$('#modal-da-them-vao-gio-hang .modal-body').html("<p>Đã thêm <b></b> vào giỏ hàng.</p>");
					$('#modal-da-them-vao-gio-hang .modal-body b').html(name);
					
					itemcount++;
					console.log(itemcount); 
					$("#itemcount").text(itemcount);
					
					var str = "<i class=\"fa fa-shopping-cart\"></i>Giỏ hàng (có " + itemcount + " sản phẩm)";
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
		
		if(amount == 0){
			return;
		}
		else if(amount < 0){
			$('#modal-da-them-vao-gio-hang').modal('show');
			$('#modal-da-them-vao-gio-hang .modal-body p').html("Số lượng không thể âm");
			return;
		}
		
		//ajax
		$.ajax({
			url : route,
			type : 'GET',
			data : {
				id : id,
				amount : amount
			},
			success: function(data){
				//console.log(data); 
				if(data=="added"){
					$('#modal-da-them-vao-gio-hang').modal('show');
					$('#modal-da-them-vao-gio-hang .modal-body').html("<p>Đã thêm <b></b> vào giỏ hàng.</p>");
					$('#modal-da-them-vao-gio-hang .modal-body b').html(name);
					
					itemcount += amount;
					console.log(itemcount); 
					$("#itemcount").text(itemcount);
					
					var str = "<i class=\"fa fa-shopping-cart\"></i>Giỏ hàng (có " + itemcount + " sản phẩm)";
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
