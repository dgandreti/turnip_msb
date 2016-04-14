 function validateForm1() {
//                alert("validateForm1");
                $("#succMessage").html(""); 
                var account_name=document.getElementById("account_name").value;
               var account_url=document.getElementById("account_url").value;
               var account_type=document.getElementById("account_type").value;
               var email_ext=document.getElementById("email_ext").value;
                if(account_name=="")
                {
                   
                  // alert(account_name);
                   $("#resultMessage").html("<font color='red'>Enter account name</font>");  
                    $("#resultMessage").show().delay(5000).fadeOut();
                    return false;
                }
                if(account_url=="")
                {
                   //  alert(account_url);
                    $("#resultMessage").html("<font color='red'>Enter account url</font>"); 
                    $("#resultMessage").show().delay(5000).fadeOut();
                    return false;
                }
                if(account_type=="")
                {
                   //alert(account_type);
                    $("#resultMessage").html("<font color='red'>Select account type</font>");  
                    $("#resultMessage").show().delay(5000).fadeOut();
                    return false;
                }
                //alert(email_ext)
                if(email_ext=="")
                {
                   //  alert(email_ext);
                    $("#resultMessage").html("<font color='red'>Enter email extension</font>");   
                    $("#resultMessage").show().delay(5000).fadeOut();
                    return false;
                } 
                return true;
              
              
            }

function ResponseCheckCharacters(id){
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 200){
            el.val( el.val().substr(0, 200) );
        } else {
            $("#ResponsecharNum").text(200-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==200)
        {
            $("#ResponsecharNum").text(' Cannot enter  more than 200 Characters .');
        }

    })
    return false;
};

/*
 * Numeric Validation and Comma Appends
 */

var revenueFlag=true;
var nOEFlag=true;

//function isNumber(n) {
//  return !isNaN(parseFloat(n)) && isFinite(n);
//}
//
//function addCommas(input, id){
//  nStr = input.value;
//  if(!isNumber(nStr))
//  {
//    document.getElementById(id).innerHTML = "This is not a valid entry";
//    return;
//  }
//  document.getElementById(id).innerHTML = "";
//  document.getElementById("testRealValue").value = nStr;
//  var offset = nStr.length % 3;
//  if (offset == 0)
//    input.value = nStr.substring(0, offset)
//    + nStr.substring(offset).replace(/([0-9]{3})(?=[0-9]+)/g, "$1,");
//  else
//    input.value = nStr.substring(0, offset)
//    + nStr.substring(offset).replace(/([0-9]{3})/g,    ",$1");
//}
//
//function isDecimal(input, id)
//{
//  if(input.value % 1 != 0)
//  {
//    document.getElementById(id).innerHTML = "No.Of.Employees must be numeric";
//    return;
//  }
//  else
//  {
//    addCommas(input, id);
//  }
//}

/*
 * Account Information
 */
function validateDropDown(id, validId)
{
  var e = document.getElementById(id);
  var strUser = e.options[e.selectedIndex].value;
  var isValid = true;
  if(strUser==0)

  {
    //document.getElementById(validId).innerHTML = "Please make a selection";
    isValid = false;
  }else{
    document.getElementById(validId).innerHTML = "";

  }
  return isValid;
}

/*
 * Checking empty form fields
 */
function validateForm() {
  var hasNullField = false;
  //var notRequired = ['acc_city','address1','acc_zip','address2','fax','reqion','acc_territory','acc_budget','acc_tax_id','acc_revenue','testRealValue','acc_no_of_employees','description'];
  var notRequired = ['address1','address2','acc_city','acc_zip','acc_country','acc_state','phone1','fax','acc_industry','reqion','acc_territory','acc_no_of_employees','acc_tax_id','acc_stock_symbol','description','acc_revenue'];
  $(':input', '#acc_form').each(function() {

    if((this.value === '' || this.value === '-1' || this.value == null) && notRequired.indexOf(this.id) < 0){
      if(this.id ==='vendorType'){
        if($('#account_type').val() === '5'){
          hasNullField=true;
          return;
        }
      } else{
        hasNullField = false;
        return;
      }
    }
  });
  if(hasNullField){
    console.log('nil');
     $('#succMessage').html('');
    $('#resultMessage').html('<span>You Must Complete All required Fields *</span>');
    $('#errorMessage').html('<span>You Must Complete All required Fields *</span>');
  }
  else if(!hasNullField)
    {
        $('#resultMessage').html('');
        $('#errorMessage').html('');
    }
    if(revenueFlag==false || nOEFlag==false){
        //alert("in if false flag")
        //$('#resultMessage').html('<span>Invalid data present in the fields!</span>');
        return false;
    }
    document.getElementById("addaccountsave").disabled=true;
  return !hasNullField;
}

/*
 *
 * Alphanumeric Restriction
 *
 */
function alphanumeric(){
  var region=document.getElementById("acc_reqion"); //acc_reqion-->ID of textbox
  var taxID=document.getElementById("acc_tax_id"); //acc_reqion-->ID of textbox
  var alphanum=/^[0-9a-bA-B]+$/; //This contains A to Z , 0 to 9 and A to B
  if(!region.value.match(alphanum) || !taxID.value.match(alphanum)){
    console.log('Not alahpanumeric');
    return false;
  }
  return true;
}
function urlCheck(textBoxId,errorTextId){
  $(textBoxId).css('border', '');
  if($(textBoxId).val() == ''){
    $(errorTextId).html('<span>You must enter an URL</span>');
    $(textBoxId).css('border', '1px solid red');
    setTimeout(function(){
      $(textBoxId).css('border', '');
      $(errorTextId).children().remove();
    },3000);
    return;
  }
  $.ajax({
    type:'POST',
    url: 'MSB/acc/ajaxAccountURLCheck?accountURLCheck='+$(textBoxId).val(),
    dataType:'text',
    success:function(data,stat,xhr){
      console.log('RESPONSE SAYS '+data+" " + xhr.getResponseHeader('urlexists'));
      if(xhr.getResponseHeader('urlexists')==='free'  && $(textBoxId).val() != ''){
        $(textBoxId).css('border', '1px solid green')
        $(errorTextId).children().remove();

      }else{
        $(errorTextId).children().remove();
        if($(textBoxId).val() != ''){
          $(errorTextId).html('<span>This Url already exists</span>');
        } else {
          $(errorTextId).html('<span>You must enter an URL</span>');
        }

        $(textBoxId).css('border', '1px solid red');
        $(textBoxId).val('');
        setTimeout(function(){
          $(textBoxId).css('border', '');
          $(errorTextId).children().remove();
        },3000);

      }
    },
    error: function(data,stat,xhr){
      $(textBoxId).css('border', '1px solid red');

    }

  })
}

function nameCheck(textBoxId,errorTextId){
  if($(textBoxId).val() == ''){
    $(errorTextId).html('<span>You must enter an name</span>');
    $(textBoxId).css('border', '1px solid red');
    setTimeout(function(){
      $(textBoxId).css('border', '');
      $(errorTextId).children().remove();
    },3000);
    return;
  }
  $.ajax({
    type:'POST',
    url: 'MSB/acc/ajaxAccountNameCheck?accountNameCheck='+$(textBoxId).val(),
    dataType:'text',
    success:function(data,stat,xhr){
      console.log('RESPONSE SAYS '+data+" " + xhr.getResponseHeader('exists'));
      if(xhr.getResponseHeader('exists')==='free' && $(textBoxId).val() != ''){
        $(textBoxId).css('border', '1px solid green')
        $(errorTextId).children().remove();
      }else{
        $(errorTextId).children().remove();
        if($(textBoxId).val() != ''){
          $(errorTextId).html('<span>This name already exists</span>');
        } else {
          $(errorTextId).html('<span>You must enter an name</span>');
        }
        $(textBoxId).css('border', '1px solid red');
        $(textBoxId).val('');
        setTimeout(function(){
          $(textBoxId).css('border', '');
          $(errorTextId).children().remove();
        },3000);

      }
    },
    error: function(data,stat,xhr){
      //console.log('RESPONSE SAYS '+data+" " + xhr.getResponseHeader('urlexists'));
      $(textBoxId).css('border', '1px solid red');

    }
  })

};

//var vendorHidden = true;
//
//function AccountTypeDropDown(){
//  if($('#account_type').val() === '5' && vendorHidden){
//
//    $('#vendorType').show();
//    vendorHidden = false;
//  }else if($('#account_type').val() === ""){
//    if(!vendorHidden){
//      $('#vendorType').hide();
//      vendorHidden = true;
//    }
//  }else{
//    if(!vendorHidden){
//      $('#vendorType').hide();
//      vendorHidden = true;
//    }
//  }
//};

function getValidExtention(){
  var mailExtention=document.getElementById("email_ext").value;
  if(mailExtention==""){
      return false;
  }else{
      //alert(mailExtention)
  }
  var emailExp = /^[a-zA-Z]+[a-z]+[.]+[a-zA-z]{2,4}$/;
  if(mailExtention.match(emailExp))
      {
          //alert("fine")
               var url="MSB/acc/mailext_Check.action?email_ext="+mailExtention;
       // alert("url-->"+url);
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
                   // alert(req.responseText);
                    if(req.responseText=="Not Exist"){
                        $("#orgExtCheckSpan").html("  <b><font color='green'><br>Mail Extension is Available</font></b>");
                        $("#email_ext").css("border", "1px solid green");
                        setTimeout(function(){
                            $("#email_ext").css('border', '');
                            $("#orgExtCheckSpan").children().remove();
                        },3000);
                    }
                    else{
                        document.getElementById("email_ext").value="";
                        $("#orgExtCheckSpan").html(" <b><font color=red><br>Mail Extension Already Exists !</font></b>");
                        $("#email_ext").css("border", "1px solid red");
                        setTimeout(function(){
                            $("#email_ext").css('border', '');
                            $("#orgExtCheckSpan").children().remove();
                        },3000);
                        return false;
                    }
                } 
            }
        };
        //  alert(url)
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
      }
      else{
           $("#orgExtCheckSpan").html("  <font color='red'><br>Please enter valid email extension!</font>");
           
             document.getElementById("email_ext").value="";
            $("#orgExtCheckSpan").show().delay(3000).fadeOut();
          mailExtention.focus();
          return false;
      }
}

// function revenueValidate(){
//
//    var rev=document.getElementById('acc_revenue').value;
//    Exp=/^[0-9]*$/;
//    if(!Exp.test(rev))
//    {
//        $("#acc_revenue").css("border", "1px solid red");
//        $('#revenueValidation').css("color","red");
//        $("#revenueValidation").html("Must be valid Revenue");
//        revenueFlag=false;
//        return false;
//    }
//    else(revenueFlag=true)
//    {
//        //revenueFlag=true;
//        $("#revenueValidation").html("");
//        $("#acc_revenue").css("border", "1px solid green");
//        return true;
//    }
//};

function revenueValidate(evt){
    //removeErrorMessages();
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
    {
       // $("#acc_revenue").css("border", "1px solid red");
        $('#revenueValidation').css("color","red");
        $("#revenueValidation").html("Must be valid Revenue");
        $("#revenueValidation").show().delay(2000).fadeOut();
        revenueFlag=false;
        return false;
    }
    else
    {
                    
        $("#revenueValidation").html("");
        //$("#acc_revenue").css("border", "1px solid green");
        return true;
    }
};


//function noeValidate(){
//  
//    var emp=document.getElementById('acc_no_of_employees').value;
//    Exp=/^[0-9]*$/;
//    if(!Exp.test(emp))
//    {
//        $("#acc_no_of_employees").css("border", "1px solid red");
//        $('#employeeValidation').css("color","red");
//        $("#employeeValidation").html("Must be valid employee number");
//        nOEFlag=false;
//        return false;
//    }
//    else(nOEFlag=true)
//    {
//        //nOEFlag=true;
//        $("#employeeValidation").html("");
//        $("#acc_no_of_employees").css("border", "1px solid green");
//        return true;
//    }
//};

//function noeValidate(evt){
//    //removeErrorMessages();
//    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
//    if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
//    {
//        //$("#acc_no_of_employees").css("border", "1px solid red");
//        $('#employeeValidation').css("color","red");
//        $("#employeeValidation").html("Must be numeric value");
//        $("#employeeValidation").show().delay(2000).fadeOut();
//        nOEFlag=false;
//        return false;
//    }
//    else
//    {
//                    
//        $("#employeeValidation").html("");
//        //$("#acc_no_of_employees").css("border", "1px solid green");
//        return true;
//    }
//};


 function cityValidate(event)
{
   
    var inputValue = (event.which) ? event.which : event.keyCode
    if((inputValue > 32 && inputValue < 48 || inputValue > 57 && inputValue < 65 || inputValue > 90 && inputValue < 97 || inputValue > 122 && inputValue < 128) && (inputValue != 32) )
    {
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
 
  function digitValidate(){

     var acc_city=document.getElementById("acc_city").value;
   //  alert(acc_city)
    var isNumber =  /^\d+$/.test(acc_city); 
    //alert(isNumber)
    if(isNumber==true)
        {
           //$("#cityError").html("Must be Character also");
            $("#acc_city").val("");
             $('#cityCoError').css("color","red");
            $("#cityCoError").html("Must be valid City");
            $("#cityCoError").show().delay(2000).fadeOut()
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

     var zip=document.getElementById("acc_zip").value;
   //  alert(acc_city)
    var isCharacter =  /^\D+$/.test(zip); 
    //alert(isNumber)
    if(isCharacter==true)
        {
           //$("#cityError").html("Must be Character also");
            $("#acc_zip").val("");
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
            $('#regionValidation').css("color","red");
            $("#regionValidation").html("Must be valid Region");
            $("#regionValidation").show().delay(2000).fadeOut()
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

     var region=document.getElementById("reqion").value;
   //  alert(acc_city)
    var isNumber =  /^\d+$/.test(region); 
    //alert(isNumber)
    if(isNumber==true)
        {
           //$("#cityError").html("Must be Character also");
            $("#reqion").val("");
             $('#regionValidation').css("color","red");
            $("#regionValidation").html("Must be valid Region");
            $("#regionValidation").show().delay(2000).fadeOut()
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

     var territory=document.getElementById("acc_territory").value;
   //  alert(acc_city)
    var isNumber =  /^\d+$/.test(territory); 
    //alert(isNumber)
    if(isNumber==true)
        {
           //$("#cityError").html("Must be Character also");
            $("#acc_territory").val("");
             $('#territoryError').css("color","red");
            $("#territoryError").html("Must be valid Territory");
            $("#territoryError").show().delay(2000).fadeOut()
           // alert("hello")
           return false;
            
        }
    
  }


function noeValidate(event)
{
   
    var inputValue = (event.which) ? event.which : event.keyCode
    if((inputValue > 32 && inputValue < 48 || inputValue > 57 && inputValue < 91 || inputValue > 90 && inputValue < 123 || inputValue > 122 && inputValue < 128) && (inputValue != 32) )
    {
         $('#employeeValidation').css("color","red");
          $('#employeeValidation').css("display","inline-block");
            $("#employeeValidation").html("Must be numeric value");
            $("#employeeValidation").show().delay(2000).fadeOut()
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

function clearform() {
//    alert("hi clearform");
    $('#resultMessage').html(" ");
    $('#errorMessage').html(" ");
    $("#revenueValidation").html("");
    $("#employeeValidation").html("");
    $("#acc_revenue").css("border", "1px solid green");
    $("#acc_no_of_employees").css("border", "1px solid green");
    $('#succMessage').html(" ");
   // document.getElementById('acc_form').value="";
    document.getElementById('account_name').value="";
    document.getElementById('account_url').value="";
    document.getElementById('account_type').value="";
    document.getElementById('email_ext').value="";
    document.getElementById('address1').value="";
    document.getElementById('address2').value="";
    document.getElementById('acc_city').value="";
    document.getElementById('acc_zip').value="";
    document.getElementById('acc_country').value="";
    document.getElementById('acc_state').value="";
    document.getElementById('phone1').value="";
    document.getElementById('fax').value="";
    document.getElementById('acc_industry').value="";
    document.getElementById('reqion').value="";
    document.getElementById('acc_territory').value="";
    document.getElementById('acc_no_of_employees').value="";
    document.getElementById('acc_tax_id').value="";
    document.getElementById('acc_stock_symbol').value="";
    document.getElementById('acc_revenue').value="";
    document.getElementById('description').value="";
        
}
