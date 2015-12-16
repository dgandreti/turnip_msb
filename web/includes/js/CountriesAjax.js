/*
 * Does an ajax call to get the states for a given country.
 */

function getStates(countryName, stateDropDownId)
{
  $.ajax({
    type: "POST",
    url:"../location/getStates",
    data: {
      countryId: countryName
    },
    contentType: "application/x-www-form-urlencoded; charset=utf-8",
    success:function(data){
//    console.log(data);
    content='<option value="">Select State</option>';;
    data.forEach(function(state){
      content+='<option value=\''+state.id+'\'>'+state.name+'</option>';
    });
    $(stateDropDownId).children().remove();
    $(stateDropDownId).append(content);


  }
  });
}

function getStockSymbol(countryId,stateName)
{
  $.ajax({
    type: "POST",
    url:"../location/getStockSymbol",
    data: {
      countryId: countryId
    },
    contentType: "application/x-www-form-urlencoded; charset=utf-8",
    success:function(data){
//    console.log(data);
      $('#acc_stock_symbol').val(data);
     $('#stock_symbol').val(data);
  }
  });

}

function jumper() {
            
		$.scrollUp({
	        scrollName: 'scrollUp', // Element ID
	        scrollDistance: 300, // Distance from top/bottom before showing element (px)
	        scrollFrom: 'top', // 'top' or 'bottom'
	        scrollSpeed: 300, // Speed back to top (ms)
	        easingType: 'linear', // Scroll to top easing (see http://easings.net/)
	        animation: 'fade', // Fade, slide, none
	        animationSpeed: 200, // Animation in speed (ms)
	        scrollTrigger: false, // Set a custom triggering element. Can be an HTML string or jQuery object
					//scrollTarget: false, // Set a custom target element for scrolling to the top
	        scrollText: '<i class="fa fa-angle-up"></i>', // Text for element, can contain HTML
	        scrollTitle: false, // Set a custom <a> title if required.
	        scrollImg: false, // Set true to use image
	        activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
	        zIndex: 2147483647 // Z-Index for the overlay
		});
	};