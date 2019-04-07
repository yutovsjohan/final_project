
$(document).ready(function() {
 
/* Set rates + misc */
var taxRate = 0;
var shippingRate = 15.00; 
var fadeTime = 300;
 
 
/* Assign actions */
//change quantity
$('.product-quantity input').change( function() {
	var id=$(this).attr('dataId');
	var amount = parseInt($(this).val());
	
	var route = "addtocart";
	var itemcount = parseInt($("#itemcount").text());
	var action = 'update';
		
	$.ajax({
			url : route,
			type : 'GET',
		data : {
			id : id	,
			amount : amount,
			action : action
		},
		success: function(data){
			//console.log(data);
			if(data == "updated" || data == "removed"){
				itemcount = 0;
				
				$('.product-quantity input').each(function () {
					itemcount += parseInt($(this).val());
				});
				
				$("#itemcount").text(itemcount);				
				
				if(itemcount == 0){
					$(".shopping-cart").fadeOut(fadeTime);
					$("#mycart").html('<p>Giỏ hàng trống. <a href="index"> Quay lại để mua </a>.</p>');
					$('#gio-hang').html('<i class=\"fa fa-shopping-cart\"></i>Giỏ hàng trổng');
				}
				else{
					var str = "<i class=\"fa fa-shopping-cart\"></i>Giỏ hàng (có " + itemcount + " sản phẩm)";
					$('#gio-hang').html(str);
				}
			}			
		}
	})
	
	
	if(amount == 0){
		removeItem(this);
	}
	else{
		updateQuantity(this);
	}
	
	
	
});

// button remove item
$('.product-removal button').click( function() {
	var id=$(this).attr('dataId');
	var route = "removeitem";
	
	$.ajax({
			url : route,
			type : 'GET',
		data : {
			id : id			
		},
		success: function(data){
			//console.log(data); 
			if(data=="removed"){								
				
			}
		}
	})
	removeItem(this);
	
	var itemcount = 0;
	$('.product-quantity input').each(function () {
		if($(this).attr('dataId') != id){
			itemcount += parseInt($(this).val());
		}
	});
	
	$("#itemcount").text(itemcount);
	
	if(itemcount == 0){
		$(".shopping-cart").fadeOut(fadeTime);
		$("#mycart").html('<p>Giỏ hàng trống. <a href="index"> Quay lại để mua </a>.</p>');
		$('#gio-hang').html('<i class=\"fa fa-shopping-cart\"></i>Giỏ hàng trổng');
	}
	else{
		var str = "<i class=\"fa fa-shopping-cart\"></i>Giỏ hàng (có " + itemcount + " sản phẩm)";
		$('#gio-hang').html(str);
	}
	
});
 
 
/* Recalculate cart */
function recalculateCart()
{
  var subtotal = 0;
   
  /* Sum up row totals */
  $('.product').each(function () {
    subtotal += parseInt($(this).children('.product-line-price').text());
  });
   
  /* Calculate totals */
  var tax = subtotal * taxRate;
  var shipping = (subtotal > 0 ? shippingRate : 0);
  var total = subtotal + tax + shipping;
   
  /* Update totals display */
  $('.totals-value').fadeOut(fadeTime, function() {
    $('#cart-subtotal').html(subtotal.toFixed(3));
    $('#cart-tax').html(tax.toFixed(3));
    $('#cart-shipping').html(shipping.toFixed(3));
    $('#cart-total').html(total.toFixed(3));
    if(total == 0){
      $('.checkout').fadeOut(fadeTime);
    }else{
      $('.checkout').fadeIn(fadeTime);
    }
    $('.totals-value').fadeIn(fadeTime);
  });
}
 
 
/* Update quantity */
function updateQuantity(quantityInput)
{
  /* Calculate line price */
  var productRow = $(quantityInput).parent().parent();
  var price = parseInt(productRow.children('.product-price').text());
  var quantity = $(quantityInput).val();
  var linePrice = price * quantity;
   
  /* Update line price display and recalc cart totals */
  productRow.children('.product-line-price').each(function () {
    $(this).fadeOut(fadeTime, function() {
      $(this).text(linePrice.toFixed(3));
      recalculateCart();
      $(this).fadeIn(fadeTime);
    });
  });  
}
 
 
/* Remove item from cart */
function removeItem(removeButton)
{
  /* Remove row from DOM and recalc cart total */
  var productRow = $(removeButton).parent().parent();
  productRow.slideUp(fadeTime, function() {
    productRow.remove();
    recalculateCart();
  });
  
  

}
 
});  