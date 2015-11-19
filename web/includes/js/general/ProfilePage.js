/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


jQuery(function($){
                
    $("#dob").mask("99/99/9999");
    $("#phone1").mask("(999)-999-9999");
    $("#fax").mask("(999)-999-9999");
    $("#corp_phone").mask("(999)-999-9999");
    $("#Phone").mask("(999)-999-9999");
    $("#CPhone").mask("(999)-999-9999");
    $("#Zip").mask("99999");
    $("#CZip").mask("99999");
    $("#percentage").mask("99.99");
    $("#edu_percentage").mask("99.99");
    $("#vendorFax").mask("(999)-999-9999");
    $("#vendorPhone").mask("(999)-999-9999");
    $("#vendorZip").mask("99999");
    /*for registration page*/
    $("#office_Phone").mask("(999)-999-9999");
    $("#phone").mask("(999)-999-9999");
    
    
    
});
            
$(document).ready(function(){
    $(".showUpdatep :input").each(function () {
        $(this).mousedown(function () {
            //alert("alert called")
            $("#spanUpdatep").css('visibility', 'visible');
        //$('#checkAddress').click();
        //$('#checkAddress').prop('checked', false);
        });
    });
        
    $(".showUpdatec :input").each(function () {
        $(this).mousedown(function () {
            //alert("alert called")
            $("#spanUpdatec").css('visibility', 'visible');
        //$('#checkAddress').click();
        //$('#checkAddress').prop('checked', false);
        });
            
    });
        
    $('#checkAddress').change(function() {
        if ($(this).is(':checked')) {
            $("#spanUpdatep").css('visibility', 'visible');
            $("#spanUpdatec").css('visibility', 'hidden');
        }
        else
        {
            $("#spanUpdatec").css('visibility', 'visible');
        }
    });
   
});
$(function() {
    $('#checkAddress').click(function() {
        var cb1 = $('#checkAddress').is(':checked');        
        $('#CAddress, #CAddress2, #CCity, #CState, #CZip, #CCountry, #CPhone').prop('disabled',cb1);    
                    
    });
});
      
$(document).ready(function(){
    
   
    $('#skilledit_popup').popup(); 
});
$(document).ready(function(){
  
    $('#skilladd_popup').popup(); 
});
//The below function checks the characters remaining in the textarea.
function checkCharacters(id){
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 200){
            el.val( el.val().substr(0, 200) );
        } else {
            $("#charNum").text(200-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==200)
        {
            $("#charNum").text(' Cannot enter  more than 200 Characters .');    
        }
        
    })
    return false;
}