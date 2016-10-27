/*price range*/
/*
 $('#sl2').slider();

	var RGBChange = function() {
	  $('#RGB').css('background', 'rgb('+r.getValue()+','+g.getValue()+','+b.getValue()+')')
	};	
		
/*scroll to top*/

// $(document).ready(function(){
//
//    function putFooter(){
//        var wh = $(window).height();
//        var dh = $("#everything").height();
//        if(dh < wh - 104) { /*104: #thefooter height in px*/
//            $("#footer").addClass("footerissticky");
////            alert("add footerissticky");
//        }
//        else {
//            $("#footer").removeClass("footerissticky");
////             alert("no footerissticky")
//        }
//    }
//
//    putFooter();
//    $(window).click(function(){
//        putFooter();
//    });
//    $(document).click(function(){
//        alert("hi")
//        putFooter();
//    }); 
//    
//    $(body).resize(function(){
//        alert("resize")
//        putFooter();
//    });
//
//});
//  
//  
//  $("a").click("load",function(){
//      //alert("tab")
//    putFooter1();
//});
//function putFooter1(){
//        var wh = $(window).height();
//        var dh = $("#everything").height();
//        if(dh < wh - 104) { /*104: #thefooter height in px*/
//            $("#footer").addClass("footerissticky");
////            alert("add footerissticky");
//        }
//        else {
//            $("#footer").removeClass("footerissticky");
////             alert("no footerissticky")
//        }
//    }



$(document).ready(function(){
    $(function () {
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
    });
});
jQuery.validator.setDefaults({
    debug: true,
    success: "valid"
});
$( "#loginForm" ).validate({
    rules: {
        logiId: {
            required: true,
            email: true
        } 
    }
});


// Add by Aklakh
function checkEmailIdExistance(){
    $("resultMessage").html(" ");
    $("resetMessage").html(" ");
    var emailId=document.getElementById("email").value;
   // var pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}";
    //  alert(document.getElementById('email').value);
    if(emailId==""){
        $("resetMessage").html("<font color='red'>Email field is required</font>");
        return false;
    }
//    if(pattern!=emailId){
//        $("resetMessage").html("<font color='red'>Please enter a valid Email</font>");
//        document.getElementById("email").value="";
//        return false;
//    }
    var url=CONTENXT_PATH+'/general/resetEmailVerify.action?emailId='+emailId;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //          alert(req.responseText);
                //  alert("2");
                emailResponse(req.responseText);
               
            } 
            else
            {
                            
            }
        }
    };
    
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
// Add by Aklakh
function emailResponse(response){
    if(response=="success"){
        $("resetMessage").html("  <font color='red'>No user with this email !</font>");
        email.value='';
        return false;
    }
    else{
        return true;
    }
}
function loadPopup(){
    //  $('#grayBG_default').addClass('fadeOutLeftBig');
    $("#grayBG_default").show().delay(10000).fadeOut();
//  $( "grayBG_default" ).hide( 3000 );
               
}
function closePopup(){
   
    $('#grayBG_default').addClass('animated  fadeOutLeftBig');
}

function toggleContent(id) {
    // Get the DOM reference

    var contentId = document.getElementById(id);
    // Toggle

    if(contentId.style.display == "none")
    {
        contentId.style.display = "";
        document.getElementById("updownArrow").className="fa fa-minus";
    }
    else
    {
        contentId.style.display = "none";
       
        document.getElementById("updownArrow").className="fa fa-plus";
         
    }

} 


function toggleContent1(id) {
    // Get the DOM reference

    var contentId = document.getElementById(id);
    // Toggle

    if(contentId.style.display == "none")
    {
        contentId.style.display = "";
        document.getElementById("updownArrow1").className="fa fa-minus";
    }
    else
    {
        contentId.style.display = "none";
         
        document.getElementById("updownArrow1").className="fa fa-plus";
         
    }

}
function toggleContentLeft(id) {
    // Get the DOM reference

    var contentId = document.getElementById(id);
    // Toggle

    if(contentId.style.display == "none")
    {
        contentId.style.display = "";
        document.getElementById("updownArrowLeft").className="fa fa-angle-up";
    }
    else
    {
        contentId.style.display = "none";
         
        document.getElementById("updownArrowLeft").className="fa fa-angle-down";
         
    }

}
function checkPasswordMatch() {
    //   alert("pwd");
    $("#resMessage").html(' ');
    
    var curPassword = $("#curPwd").val();
    var newPassword = $("#pwd1").val();
               
    if (curPassword == newPassword){
        //      alert(curPassword+"   "+newPassword);
        if(curPassword != ""){
            $("resetMessage").html("<font  color='red'> Current password and new password should be different.</font>");
            $("resetMessage").show().delay(5000).fadeOut();
        }
        return false;
    } else{
        $("resetMessage").html("");
        return true;
    }
}
function matchCurrentPassword(){
                
    // alert("in Ajax call ");
    var currentPwd=document.getElementById("curPwd").value;
    var url=CONTENXT_PATH+'/general/verfiyCurrentPassword.action?currentPwd='+currentPwd;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //   alert(req.responseText);
                //  alert("2");
                verifyPasswordResponce(req.responseText);
               
            } 
            else
            {
                            
            }
        }
    };
    
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function verifyPasswordResponce(response){
    if(response=="success"){
       
        return true;
    }
    else{
        $("resetMessage").html("<font  color='red'> Sorry, Current password is wrong, try again.</font>");
          $("resetMessage").show().delay(5000).fadeOut();
        // curPwd.value='';
        document.getElementById("curPwd").value='';
          $("resetMessage").show().delay(2000).fadeOut();
        return false;
    }
}
// Add By Aklakh
var timeoutId;
function initSessionTimer() {
    // alert("init...")
	

    function setSessionTimeout(){

        timeoutId = setTimeout(                

            function()

            {             
                history.pushState(null, null, 'pagename');
                window.addEventListener('popstate', function(event) {
                    history.pushState(null, null, 'pagename');
                });
                alert('You\'re session has timed out. Please re-login.');
                window.location = "../general/logout.action";
            }, 1800000);   //1800000 -->30 min
    }

    document.onclick = function resetTimeout(){ 
        clearTimeout(timeoutId);
        setSessionTimeout();
    };
}



       
       

 
            
            
//function sidepopup(){
//    $(".popup_block").animate({
//        width: 'toggle'
//    });
//}
            
function toggleContentRequirement(id)
{
    var contentId = document.getElementById(id);
    if(contentId.style.display == "none")
    {
        contentId.style.display = "";
        document.getElementById("updownArrowVen").className="fa fa-sort-asc";
    }
    else
    {
        contentId.style.display = "none";
        document.getElementById("updownArrowVen").className="fa fa-sort-desc";
    }

}
 function toggleContentRequirementSubmittedList(id)
{
    var contentId = document.getElementById(id);
    if(contentId.style.display == "none")
    {
        contentId.style.display = "";
        document.getElementById("updownArrowSub").className="fa fa-sort-asc";
    }
    else
    {
        contentId.style.display = "none";
        document.getElementById("updownArrowSub").className="fa fa-sort-desc";
    }

}             
function toggleContentAccount(id) {
    // Get the DOM reference

    var contentId = document.getElementById(id);
    // Toggle

    if(contentId.style.display == "none")
    {
        contentId.style.display = "";
        document.getElementById("updownArrowAccount").className="fa fa-angle-up";
    }
    else
    {
        contentId.style.display = "none";
       
        document.getElementById("updownArrowAccount").className="fa fa-angle-down";
         
    }

} 