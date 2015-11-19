//jQuery(function($){var doCommas = function(id){
//    // console.log("value from input := "+$('#'+id+'').val());
//    var tempHolder="";
//    for(var i = 0 ;i< $('#'+id+'').val().length;i++){
//        if($('#'+id+'').val().charAt(i)===','){
//
//        } else {
//            tempHolder+=$('#'+id+'').val().charAt(i);
//        }
//    }
//    // console.log("value after removing commas := "+tempHolder);
//    var mask="";
//    for(var i = 1 ;i< tempHolder.length+1;i++){
//        if((i%3)===0&&(i!=0)&&(i!=tempHolder.length)){
//            mask=",9"+mask;
//        }else{
//            mask="9"+mask;
//        }
//    }
//    //  console.log("the mask := "+mask);
//    $('#'+id+'').mask(mask);
//    $('#'+id+'').unmask();
//}
//var checkForIllegalCharacters = function(id){
//    var tempHolder="";
//    var mask="";
//    for(var i = 0 ;i< $('#'+id+'').val().length;i++){
//         if($('#'+id+'').val().charAt(i)===' '){
//            mask=mask+" ";
//        } else {
//            mask=mask+"*";
//        }
//    }
//    $('#'+id+'').mask(mask);
//    $('#'+id+'').unmask();
//}
//$('#account_city').blur(function() {
//    alert("in account city");
//    checkForIllegalCharacters('account_city');
//    if($(this).val()===""){
//         $(this).css("border","1px solid red");
//    }
//    else{
//        $(this).css("border","1px solid green");
//    }
//});
//$('#account_taxid').blur(function() {
//    checkForIllegalCharacters('account_taxid');
//
//});
//$('#account_region').blur(function() {
//    checkForIllegalCharacters('account_region');
//
//});
//
//$('#account_territory').blur(function() {
//    checkForIllegalCharacters('account_territory');
//});
//$('#account_address1').blur(function() {
//    checkForIllegalCharacters('account_address1');
//    if($(this).val()===""){
//         $(this).css("border","1px solid red");
//    }
//    else{
//        $(this).css("border","1px solid green");
//    }
//});
//$('#account_address2').blur(function() {
//    checkForIllegalCharacters('account_address2');
//
//});
//$('#stock_symbol').blur(function() {
//    checkForIllegalCharacters('stock_symbol');
//    if($(this).val()===""){
//         $(this).css("border","1px solid red");
//    }
//    else{
//        $(this).css("border","1px solid green");
//    }
//});
//$('#account_revenue').blur(function(){
//    doCommas("account_revenue");
//
//});
//
//
//$('#account_noemp').blur(function(){
//    doCommas("account_noemp");
//    if($(this).val()===""){
//         $(this).css("border","1px solid red");
//    }
//    else{
//        $(this).css("border","1px solid green");
//    }
//});
//
//$('#account_budget').blur(function(){
//    doCommas("account_budget");
//
//});
//
//
//$("#account_description").blur(function(){
//    if(this.value.length>200){
//        //TOO LARGE OF A DESCRIPTION
//        $('#descriptionError').html("TOO LARGE OF A DESCRIPTION");
//    }
//    else{
//        $('#descriptionError').html("");
//    }
//});
//
//$("#account_address1").blur(function(){
//    if(document.getElementById('account_address1').value===""){
//        //CANNOT BE NULL
//        $('#address1Error').css("color", "red");
//        $('#address1Error').html('FILL OUT THIS FIELD');
//    }
//});
//$("#account_state").blur(function(){
//    if(document.getElementById('account_state').value==-1){
//        $(this).css("border","1px solid red");
//        $('#stateError').css("color","red");
//        $('#stateError').html("SELECT A STATE");
//    }
//    else{
//        $(this).css("border","1px solid green");
//        $('#stateError').html("");
//    }
//});
//$("#account_country").blur(function(){
//    if(document.getElementById('account_country').value==-1){
//        // alert("PLEASE SELECT A COUTNRY");
//        $('#countryError').css("color","red");
//        $('#countryError').html("SELECT A COUNTRY");
//        $(this).css("border","1px solid red");
//    }
//    else{
//        $(this).css("border","1px solid green");
//        $('#countryError').html("");
//    }
//    getStates($(this).val(),"account_state");
//});
//$("#account_type").blur(function(){
//    if(document.getElementById('account_type').value==-1){
//        $(this).css("border","1px solid red");
//        $('#typeError').css("color","red");
//        $('#typeError').html("SELECT A TYPE");
//    }
//    else{
//        $(this).css("border","1px solid green");
//        $('#typeError').html("");
//    }
//});
//$("#status").blur(function(){
//    if(document.getElementById('status').value==-1){
//        $(this).css("border","1px solid red");
//        $('#statusError').css("color","red");
//        $('#statusError').html("SELECT A STATUS");
//    }
//    else{
//        $(this).css("border","1px solid green");
//        $('#statusError').html("");
//    }
//});
//$("#account_industry").blur(function(){
//    if(document.getElementById('account_industry').value==-1){
//        $(this).css("border","1px solid red");
//        $('#industryError').css("color","red");
//        $('#industryError').html("SELECT AN INDUSTRY");
//    }
//    else{
//        $(this).css("border","1px solid green");
//        $('#industryError').html("");
//    }
//});
//$('#phone1').mask("(999)-999-9999");
//$('#fax').mask("(999)-999-9999");
//$("#account_zip").mask('99999');
//$("#account_zip").blur(function(){
//    if(this.value.length==5){
//        //MAKE BORDER GREEN
//        $(this).css("border","1px solid green");
//    }
//    else{
//        //PRINT PLEASE INSERT 5 DIGIT ZIP
//        $(this).css("border","1px solid red");
//    }
//});
//$('#detailsFormSubmit').on('click',function() {
//    var x = true;
//      var notRequired = ['account_address2','fax','account_region','account_territory','account_budget','account_taxid','account_revenue'];
//    $(':input', '#accountDetailsForm').each(function() {
//        //check to see if all fields are filled in
//        if(this.value == "" && notRequired.indexOf(this.id) < 0){
//            //alert("Form cotains empty field(s) please fill out all fields");
//            if(this.id != 'account_vendor'){
//              console.log(this.id);
//              x=false;//return false
//            }else {
//              // if vendor_type is = "" and account type is vendor don't submit form'
//              if($("#account_type").val() == 5){
//
//                console.log(this.id+" account_type value: "+$("#account_type").val());
//                x=false;
//              }
//            }
//        }
//    });
//    //do other checks
//    if($('#account_country').val()==="-1") {
//        $('#countryError').css("color","red");
//        $('#countryError').html("SELECT A COUNTRY");
//        x=false;
//    }
//    if($('#status').val()==="-1") {
//        $('#statusError').css("color","red");
//        $('#statusError').html("SELECT A STATUS");
//        x=false;
//    }
//    if($('#account_type').val()==="-1") {
//        $('#typeError').css("color","red");
//        $('#typeError').html("SELECT A TYPE");
//        x=false;
//    }
//    if($('#account_state').val()==="-1") {
//        $('#stateError').css("color","red");
//        $('#stateError').html("SELECT A STATE");
//        x=false;
//    }
//    if($('#account_industry').val()==="-1") {
//        $('#industryError').css("color","red");
//        $('#industryError').html("SELECT AN INDUSTRY");
//        x=false;
//    }
//    if(x) {//if all passed submit
//        var url="ajaxAccountUpdate";
//        $.ajax({
//            type: "POST",
//            url: url,
//            data: $("#accountDetailsForm").serialize(), // serializes the form's elements.
//            success: function(data)
//            {
//                location.reload();
//            },
//            failure: function(data){
//            }
//        });
//    }
//});});
//

// Add By Aklakh
function editAccountValidation()
{
    var accType=document.getElementById('account_type').value;
    var accStatus=document.getElementById('status').value;
    var accCity=document.getElementById('account_city').value;
    var accCountry=document.getElementById('account_country').value;
    var accState=document.getElementById('account_state').value;
    var accPhone1=document.getElementById('phone1').value;
    var accIndustry=document.getElementById('account_industry').value;
  

    if(accType== -1)
    {
        $("editAccountError").html(" <b><font color='red'>Account type is required</font></b>");
        $("#account_type").css("border", "1px solid red");
        return false;
    }
    if(accStatus==-1)
    {
       // $("editAccountError").html(" <b><font color='red'>Account status is required</font></b>");
        $("#status").css("border", "1px solid red");
        $('#statusError').css("color","red");
        $('#statusError').html("SELECT A STATUS");
        return false;
    }
    if(accCity=="")
    {
        $("editAccountError").html(" <b><font color='red'>City is required</font></b>");
        $("#account_city").css("border", "1px solid red");
        return false;
    }
    if(accCountry==-1)
    {
       $('#countryError').css("color","red");
        $('#countryError').html("SELECT A COUNTRY");
        $("#account_country").css("border", "1px solid red");
        return false;
    }
    if(accState==-1 || accState=="")
    {
        $('#stateError').css("color","red");
        $('#stateError').html("SELECT A STATE");
        $("#account_state").css("border", "1px solid red");
        return false;
    }
   // alert(document.getElementById('phone1').length);
    if(accPhone1=="")
    {
        $('#phoneError').css("color","red");
        $("#phoneError").html("Phone number is required");
        $("#phone1").css("border", "1px solid red");
        return false;
    }
    if(accIndustry==-1)
    {
        $('#industryError').css("color","red");
        $('#industryError').html("SELECT AN INDUSTRY");
        $("#account_industry").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("editAccountError").html("");
        $("#account_type").css("border", "1px solid #3BB9FF"); 
        $("#status").css("border", "1px solid #3BB9FF"); 
        $("#account_city").css("border", "1px solid #3BB9FF");
        $("#account_country").css("border", "1px solid #3BB9FF"); 
        $("#account_state").css("border", "1px solid #3BB9FF"); 
        $("#phone1").css("border", "1px solid #3BB9FF"); 
        $("#account_industry").css("border", "1px solid #3BB9FF");
        return true;
    }     
}
// Add By Aklakh
//function accCityValidate(){
//    //alert("city");
//    var accCity=document.getElementById('account_city').value;
//    re=/^[a-zA-Z]+(?:[\s-][a-zA-Z]+)*$/;
//    if(!re.test(accCity))
//    {
//        $("#account_city").css("border", "1px solid red");
//        $('#cityError').css("color","red");
//        $("#cityError").html("Must be valid  city name");
//        
//    }
//    else
//    {
//        $("#cityError").html("");
//        $("#account_city").css("border", "1px solid green");
//    }
//}

 function accStatusValidate(){
    // alert("hi");
     if(document.getElementById('status').value==-1){
        $("#status").css("border","1px solid red");
        $('#statusError').css("color","red");
        $('#statusError').html("SELECT A STATUS");
        return false;
    }
    else{
       $("#status").css("border","1px solid green");
        $('#statusError').html("");
        return true;
    }
 }
 function accCountryValidate(){
     if(document.getElementById('account_country').value==-1){
        // alert("PLEASE SELECT A COUTNRY");
        $('#countryError').css("color","red");
        $('#countryError').html("SELECT A COUNTRY");
        $("#account_country").css("border", "1px solid red");
    }
    else{
        $("#account_country").css("border", "1px solid green");
        $('#countryError').html("");
    }
    getStates($(this).val(),"account_state");
     
 }
 function accStateValidate(){
      if(document.getElementById('account_state').value==-1){
        $("#account_state").css("border", "1px solid red");
        $('#stateError').css("color","red");
        $('#stateError').html("SELECT A STATE");
    }
    else{
       $("#account_state").css("border","1px solid green");
        $('#stateError').html("");
    }
 }
 function accPhoneValidate(){
     if(document.getElementById('phone1').length==10){
          $("#phone1").css("border", "1px solid red");
        $('#phoneError').css("color","red");
        $('#phoneError').html("SELECT AN INDUSTRY");
     }
     else{
         $("#phone1").css("border","1px solid green");
        $('#phoneError').html("");
     }
 }
 function accIndustryValidate(){
      if(document.getElementById('account_industry').value==-1){
        $("#account_industry").css("border", "1px solid red");
        $('#industryError').css("color","red");
        $('#industryError').html("SELECT AN INDUSTRY");
    }
    else{
       $("#account_industry").css("border","1px solid green");
        $('#industryError').html("");
    }
 }
//function accNoOfEmpValidate(evt){
//    // alert("Account validate");
//   //  var emp=document.getElementById('account_noemp').value;
//   //  re=/^[0-9]*$/;
//     var iKeyCode = (evt.which) ? evt.which : evt.keyCode
//    if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
//        {
//        $("#account_noemp").css("border", "1px solid red");
//        $('#noempError').css("color","red");
//        $('#noempError').html("Must be  numeric value");
//       return false; 
//    }
//    else{
//       $("#account_noemp").css("border","1px solid green");
//        $('#noempError').html("");
//        return true;
//    }
// }
// function accDescriptionValidate(){
//     var desc=document.getElementById('account_description').value;
//       if(desc.length>100){
//        //TOO LARGE OF A DESCRIPTION
//        $('#ResponsecharNum').html("TOO LARGE OF A DESCRIPTION");
//    }
//    else{
//        $('#ResponsecharNum').html("");
//    }
// }


// function territoryValidate()
//{
//
//  $("#account_territory").keypress(function(event){
//        var inputValue = event.charCode;
//        if((inputValue > 32 && inputValue < 65 || inputValue > 90 && inputValue < 97 || inputValue > 122 && inputValue < 128) && (inputValue != 32)){
//            event.preventDefault();
//        $("#account_territory").css("border", "1px solid red");
//        $('#territoryError').css("color","red");
//        $("#territoryError").html("Must be valid Territory");
//        }
//        else
//            {
//        $("#territoryError").html("");
//        $("#account_territory").css("border", "1px solid green"); 
//                //alert("must be valid")
//            }
//    });
// 
//}
//
// function regionValidate()
//{
//
//  $("#account_region").keypress(function(event){
//        var inputValue = event.charCode;
//        if((inputValue > 32 && inputValue < 65 || inputValue > 90 && inputValue < 97 || inputValue > 122 && inputValue < 128) && (inputValue != 32)){
//            event.preventDefault();
//        $("#account_region").css("border", "1px solid red");
//        $('#regionError').css("color","red");
//        $("#regionError").html("Must be valid Region");
//        }
//        else
//            {
//        $("#regionError").html("");
//        $("#account_region").css("border", "1px solid green"); 
//                //alert("must be valid")
//            }
//    });
// 
//}
//
//function accCityValidate(){
//    //alert("city");
//     $("#account_city").keypress(function(event){
//        var inputValue = event.charCode;
//        if((inputValue > 32 && inputValue < 65 || inputValue > 90 && inputValue < 97 || inputValue > 122 && inputValue < 128) && (inputValue != 32)){
//            event.preventDefault();
//        $("#account_city").css("border", "1px solid red");
//        $('#cityError').css("color","red");
//        $("#cityError").html("Must be valid City");
//        }
//        else
//            {
//        $("#cityError").html("");
//        $("#account_city").css("border", "1px solid green"); 
//                //alert("must be valid")
//            }
//    });
//}

//function taxValidate(evt){
//    //removeErrorMessages();
//    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
//    if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
//    {
//        $("#account_taxid").css("border", "1px solid red");
//        $('#taxError').css("color","red");
//        $("#taxError").html("Must be valid Tax Id");
// 
//        return false;
//    }
//    else
//    {
//                    
//        $("#taxError").html("");
//        $("#account_taxid").css("border", "1px solid green");
//        return true;
//    }
//};

function revenueValidate(evt){
    //removeErrorMessages();
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
    {
        //$("#account_revenue").css("border", "1px solid red");
        $('#revenueError').css("color","red");
        $("#revenueError").html("Must be valid Revenue");
        $("#revenueError").show().delay(2000).fadeOut();
        return false;
    }
    else
    {
                    
        $("#revenueError").html("");
        $("#account_revenue").css("border", "1px solid green");
        return true;
    }
};

//function faxValidate(evt){
//    //removeErrorMessages();
//    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
//    if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
//    {
//         $("#fax").css("border", "1px solid red");
//        $('#faxError').css("color","red");
//        $("#faxError").html("Must be valid Fax number");
// 
//        return false;
//    }
//    else
//    {
//                    
//        $("#faxError").html("");
//        $("#fax").css("border", "1px solid green");
//        return true;
//    }
//};

//$("#account_territory").on("keypress", function(event) {
//
//    var character = /[A-Za-z ]/g;
//   
//    var key = String.fromCharCode(event.which);
//   
//    if (event.keyCode == 8 || event.keyCode == 37 || event.keyCode == 39 || character.test(key))
//    {
//        $("#territoryError").html("");
//        $("#account_territory").css("border", "1px solid green"); 
//        return true;
//    }
//    else
//    {
//           
//        $("#account_territory").css("border", "1px solid red");
//        $('#territoryError').css("color","red");
//        $("#territoryError").html("Must be valid Territory");
//        return false;
//    }
//   
//   
//});
//
//$('#account_territory').on("paste",function(e)
//{
//    e.preventDefault();
//});


//$("#account_region").on("keypress", function(event) {
//
//    var character = /[A-Za-z ]/g;
//   
//    var key = String.fromCharCode(event.which);
//   
//    if (event.keyCode == 8 || event.keyCode == 37 || event.keyCode == 39 || character.test(key))
//    {
//        $("#regionError").html("");
//        $("#account_region").css("border", "1px solid green"); 
//        return true;
//    }
//    else
//    {
//           
//        $("#account_region").css("border", "1px solid red");
//        $('#regionError').css("color","red");
//        $("#regionError").html("Must be valid Region");
//        return false;
//    }
//   
//   
//});
//
//$('#account_region').on("paste",function(e)
//{
//    e.preventDefault();
//});


//$("#account_city").on("keypress", function(event) {
//
//    var character = /[A-Za-z ]/g;
//   
//    var key = String.fromCharCode(event.which);
//   
//    if (event.keyCode == 8 || event.keyCode == 37 || event.keyCode == 39 || character.test(key))
//    {
//        $("#cityError").html("");
//        $("#account_city").css("border", "1px solid green"); 
//        return true;
//    }
//    else
//    {
//           
//        $("#account_city").css("border", "1px solid red");
//        $('#cityError').css("color","red");
//        $("#cityError").html("Must be valid City");
//        return false;
//    }
//   
//   
//});
//
//$('#account_city').on("paste",function(e)
//{
//    e.preventDefault();
//});

function cityValidate(event)
{

    var inputValue = (event.which) ? event.which : event.keyCode
    if((inputValue > 32 && inputValue < 48 || inputValue > 57 && inputValue < 65 || inputValue > 90 && inputValue < 97 || inputValue > 122 && inputValue < 128) && (inputValue != 32) )
    {
            //$("#acc_city").css("border", "1px solid red");
            $('#cityError').css("color","red");
            $("#cityError").html("Must be valid City");
            $("#cityError").show().delay(2000).fadeOut()
        if(inputValue == 8)
        {
            return true;       
        }
        else
        {
            return false;
        }
    }
}

  function cdigitValidate(){

     var city=document.getElementById("account_city").value;
   //  alert(acc_city)
    var isNumber =  /^\d+$/.test(city); 
    //alert(isNumber)
    if(isNumber==true)
        {
           //$("#cityError").html("Must be Character also");
            $("#account_city").val("");
             $('#cityError').css("color","red");
            $("#cityError").html("Must be valid City");
            $("#cityError").show().delay(2000).fadeOut()
           // alert("hello")
           return false;
            
        }
    
  }




function zipValidate(event)
{

    var inputValue = (event.which) ? event.which : event.keyCode
    if((inputValue > 32 && inputValue < 48 || inputValue > 57 && inputValue < 65 || inputValue > 90 && inputValue < 97 || inputValue > 122 && inputValue < 128) && (inputValue != 32) )
    {
            $('#zipError').css("color","red");
            $("#zipError").html("Must be valid Zip");
            $("#zipError").show().delay(2000).fadeOut()
           if(inputValue == 8)
        {
            return true;       
        }
        else
        {
            return false;
        }
    }
}

function zipcharValidate(){

     var zip=document.getElementById("account_zip").value;
   //  alert(acc_city)
    var isCharacter =  /^\D+$/.test(zip); 
    //alert(isNumber)
    if(isCharacter==true)
        {
           //$("#cityError").html("Must be Character also");
            $("#account_zip").val("");
             $('#zipError').css("color","red");
            $("#zipError").html("Must be valid Zip");
            $("#zipError").show().delay(2000).fadeOut()
           // alert("hello")
           return false;
            
        }
    
  }



function regionValidate(event)
{

   var inputValue = (event.which) ? event.which : event.keyCode
    if((inputValue > 32 && inputValue < 48 || inputValue > 57 && inputValue < 65 || inputValue > 90 && inputValue < 97 || inputValue > 122 && inputValue < 128) && (inputValue != 32) )
    {
            $('#regionError').css("color","red");
            $("#regionError").html("Must be valid Region");
            $("#regionError").show().delay(2000).fadeOut()
           if(inputValue == 8)
        {
            return true;       
        }
        else
        {
            return false;
        }
    }
}

function rdigitValidate(){

     var region=document.getElementById("account_region").value;
   //  alert(acc_city)
    var isNumber =  /^\d+$/.test(region); 
    //alert(isNumber)
    if(isNumber==true)
        {
           //$("#cityError").html("Must be Character also");
            $("#account_region").val("");
             $('#regionError').css("color","red");
            $("#regionError").html("Must be valid Region");
            $("#regionError").show().delay(2000).fadeOut()
           // alert("hello")
           return false;
            
        }
    
  }



function territoryValidate(event)
{

     var inputValue = (event.which) ? event.which : event.keyCode
    if((inputValue > 32 && inputValue < 48 || inputValue > 57 && inputValue < 65 || inputValue > 90 && inputValue < 97 || inputValue > 122 && inputValue < 128) && (inputValue != 32) )
    {
            $('#territoryError').css("color","red");
            $("#territoryError").html("Must be valid Territory");
            $("#territoryError").show().delay(2000).fadeOut()
           if(inputValue == 8)
        {
            return true;       
        }
        else
        {
            return false;
        }
    }
}

function tdigitValidate(){

     var territory=document.getElementById("account_territory").value;
   //  alert(acc_city)
    var isNumber =  /^\d+$/.test(territory); 
    //alert(isNumber)
    if(isNumber==true)
        {
           //$("#cityError").html("Must be Character also");
            $("#account_territory").val("");
             $('#territoryError').css("color","red");
            $("#territoryError").html("Must be valid Territory");
            $("#territoryError").show().delay(2000).fadeOut()
           // alert("hello")
           return false;
            
        }
    
  }

function accNoOfEmpValidate(evt){
    // alert("Account validate");
   //  var emp=document.getElementById('account_noemp').value;
   //  re=/^[0-9]*$/;
     var inputValue = (evt.which) ? evt.which : evt.keyCode
    if((inputValue > 32 && inputValue < 48 || inputValue > 57 && inputValue < 91 || inputValue > 90 && inputValue < 123 || inputValue > 122 && inputValue < 128) && (inputValue != 32) )
        {
        //$("#account_noemp").css("border", "1px solid red");
        $('#noempError').css("color","red");
        $('#noempError').html("Must be  numeric value");
        $("#noempError").show().delay(2000).fadeOut();
       return false; 
    }
    else{
      // $("#account_noemp").css("border","1px solid green");
       // $('#noempError').html("");
        return true;
    }
 }
 function bankZipcharValidate(){
     var zip=document.getElementById("bankZip").value;
    var isCharacter =  /^\D+$/.test(zip); 
    if(isCharacter==true)
        {
            $("#bankZip").val("");
             $('#bankZipError').css("color","red");
            $("#bankZipError").html("Must be valid Zip");
            $("#bankZipError").show().delay(2000).fadeOut()
           // alert("hello")
           return false;
        }
  }
