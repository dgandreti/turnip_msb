//$('#account_name').blur(function(){
//    console.log($(this).val());
//    $.ajax({
//        type:'POST',
//        url: 'MSB/acc/ajaxAccountNameCheck?accountNameCheck='+$(this).val(),
//        dataType:'text',
//        success:function(data,stat,xhr){
//            console.log('RESPONSE SAYS '+data+" " + xhr.getResponseHeader('exists'));
//            if(xhr.getResponseHeader('exists')==='free'){
//                $('#account_name').css('border', '1px solid green')
//            }else{
//                $('#account_name').css('border', '1px solid red')
//            }
//        },
//        error: function(data,stat,xhr){
//            $('#account_url').css('border', '1px solid red')
//
//        }
//    })
//});
//$('#account_url').blur(function(){
//    console.log($(this).val());
//    $.ajax({
//        type:'POST',
//        url: 'MSB/acc/ajaxAccountURLCheck?accountURLCheck='+$(this).val(),
//        dataType:'text',
//        success:function(data,stat,xhr){
//console.log('RESPONSE SAYS '+data+" " + xhr.getResponseHeader('urlexists'));
//            if(xhr.getResponseHeader('urlexists')==='free'){
//                $('#account_url').css('border', '1px solid green')
//            }else{
//                $('#account_url').css('border', '1px solid red')
//            }
//        },
//        error: function(data,stat,xhr){
//            //console.log('RESPONSE SAYS '+data+" " + xhr.getResponseHeader('urlexists'));
//            $('#account_url').css('border', '1px solid red')
//
//        }
//
//    })
//});
//});
                                                                                            
//});
        

function initRequest(url) {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }
    else
    if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }    
}


//function headingMessage(message)
//{
//    // alert(message.id);
//    if(message.id=="accountdetailshead"){
//   
//        document.getElementById("headingmessage").innerHTML="Account Details";
//    }
//
//    if(message.id=="softwareshead"){
//        document.getElementById("headingmessage").innerHTML="Account Softwares";
//    }
//    if(message.id=="assignteamhead"){
//        document.getElementById("headingmessage").innerHTML="Assign Team";
//    }
//    if(message.id=="contactshead"){
//        document.getElementById("headingmessage").innerHTML="Contacts";
//        showContacts();
//    }
//    if(message.id=="acitivitieshead"){
//        document.getElementById("headingmessage").innerHTML="Activities";
//    }
//    if(message.id=="requirementshead"){
//        document.getElementById("headingmessage").innerHTML="Account Requirements";
//    }
//
//    if(message.id=="opportunitieshead"){
//        document.getElementById("headingmessage").innerHTML="Opportunities";
//    }
//
//    if(message.id=="projectshead"){
//        document.getElementById("headingmessage").innerHTML="Projects";
//    }
//    if(message.id=="greensheetshead"){
//        document.getElementById("headingmessage").innerHTML="Green Sheets";
//    }
//    if(message.id=="vendors"){
//        document.getElementById("headingmessage").innerHTML="Vendor Tier's";
//    }
//    if(message.id=="csrAccounts"){
//        document.getElementById("headingmessage").innerHTML="Csr Account";
//        getCsrDetailsTable();
//    }
//
//}
function headingMessage(message)
{
    initSessionTimer();
    // alert(message.id);
    if(message.id=="accountdetailshead"){
   
        document.getElementById("headingmessage").innerHTML="Account Details";
    }

    if(message.id=="softwareshead"){
        document.getElementById("headingmessage").innerHTML="Account Softwares";
    }
    if(message.id=="assignteamhead"){
        document.getElementById("headingmessage").innerHTML="Assign Team";
    }
    if(message.id=="contactshead"){
        document.getElementById("headingmessage").innerHTML='Contacts <i  class="fa fa-angle-up " id="updownArrowAccount" onclick="toggleContentAccount(\'contactDiv\')" style="margin-top: 0vw;position:absolute;color:#56a5ec"> </i>';
        showContacts();
    }
    if(message.id=="vendorFormsHead"){
        document.getElementById("headingmessage").innerHTML='Vendor Forms <i  class="fa fa-angle-up " id="updownArrowAccount" onclick="toggleContentAccount(\'VendorFormstDiv\')" style="margin-top: 0vw;position:absolute;color:#56a5ec"> </i>';
        showAttachments();
    }
    if(message.id=="acitivitieshead"){
        document.getElementById("headingmessage").innerHTML="Activities";
    }
    if(message.id=="requirementshead"){

        document.getElementById("headingmessage").innerHTML='Account Requirements <i  class="fa fa-angle-up" id="updownArrowAccount" onclick="toggleContentAccount(\'requirementDiv\')" style="margin-top: 0vw;position:absolute;color:#56a5ec"> </i>';
    }

    if(message.id=="opportunitieshead"){
        document.getElementById("headingmessage").innerHTML="Opportunities";
    }

    if(message.id=="projectshead"){
        document.getElementById("headingmessage").innerHTML="Projects";
    }
    if(message.id=="greensheetshead"){
        document.getElementById("headingmessage").innerHTML="Green Sheets";
    }
    if(message.id=="vendors"){
        document.getElementById("headingmessage").innerHTML='Vendor Tier'+"'"+'s <i  class="fa fa-angle-up" id="updownArrowAccount" onclick="toggleContentAccount(\'vendorDiv\')" style="margin-top: 0vw;position:absolute;color:#56a5ec"> </i> ';
    }
    if(message.id=="csrAccounts"){
        document.getElementById("headingmessage").innerHTML='Csr Account <i  class="fa fa-angle-up" id="updownArrowAccount" onclick="toggleContentAccount(\'csrAssignDiv\')" style="margin-top: 0vw;position:absolute;color:#56a5ec"> </i>';
        getCsrDetailsTable();
    }
    if(message.id=="location"){
   
        document.getElementById("headingmessage").innerHTML='Locations<i  class="fa fa-angle-up" id="updownArrowAccount" onclick="toggleContentAccount(\'locationSearchDiv\')" style="margin-top: 0vw;position:absolute;color:#56a5ec"> </i>';
        showLocations();
    }
    
}
function showLocations()
{
    var orgId= document.getElementById("accountSearchID").value;  
    
    var country=document.getElementById("locationSearchCountry").value;
    //alert(country)
    var state= document.getElementById("locationSearchState").value;
    //alert(state)
    var city=  document.getElementById("locationSearchCity").value;
    //alert(city)
    var phone=document.getElementById("locationSearchPhone").value;
    var locationSearchStatus=document.getElementById("locationSearchStatus").value;
    var locationSearchName=document.getElementById("locationSearchName").value;
    //alert(phone)
    //    var rndNo = Math.random();
    var url='../acc/getLocations.action?accountSearchOrgId='+orgId+'&accCity='+city+'&accCountry='+country+'&accState='+state+'&accPhone='+phone+'&locationStatus='+locationSearchStatus+'&locationName='+locationSearchName;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingLocation').style.display = 'block';
        if (req.readyState == 4) {
            
            if (req.status == 200) {
                $('#loadingLocation').hide();
                
                populateLocationTable(req.responseText);
                
            } 
            else
            {
                alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateLocationTable(response){
    // alert(response);  
    $(".page_option").css('display', 'block');
    var eduList=response.split("^");
    var OrgID= document.getElementById("accountSearchID").value;
    var table = document.getElementById("LocationPageNav");
 
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        for(var i=0;i<eduList.length-1;i++){   
       
            var Values=eduList[i].split("|");
            {  
         
         
                var row = $("<tr />")
                //alert("row--?"+row);
      
                $("#LocationPageNav").append(row);
                row.append($('<td><a href="" class="addLocation_popup_open" onclick="addLocationOverlay();addorEditLocationOverlay(\'Edit\');editLocations('+Values[0]+');" > ' + Values[7] + "</td>"));
                row.append($("<td>" + Values[4] + "</td>"));
                row.append($("<td>" + Values[3] + "</td>"));
                row.append($("<td>" + Values[2] + "</td>"));
                
                row.append($("<td>" + Values[5] + "</td>"));
                row.append($("<td>" + Values[6] + "</td>"));
               
            }
        }
    }
    else {
        var row = $("<tr />")
        $("#LocationPageNav").append(row);
        row.append($('<td colspan="6"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
    }
    $('#LocationPageNav').tablePaginate({
        navigateType:'navigator'
    },recordPage);
    acPager.init(); 
  
}
function addorUpdateLocation(val)
{
    //alert(val)
    var locationFlag=val;
    var locationAddress1=document.getElementById("locationAddress1").value;
    var locationAddress2=document.getElementById("locationAddress2").value;
    var locationCity    =document.getElementById("locationCity").value;
    var locationCountry = document.getElementById("locationCountry").value;
    var locationState  =  document.getElementById("locationState").value;  
    var locationPhone  =  document.getElementById("locationPhone").value; 
    var locationZip =   document.getElementById("locationZip").value; 
    var locationFax =   document.getElementById("locationFax").value;
    var locationStatus =   document.getElementById("locationStatus").value;
    var orgId= document.getElementById("accountSearchID").value; 
    var locationId= document.getElementById("accountAddress").value;
    var locationName= document.getElementById("locationName").value;
    //alert(locationId)
    //var rndmNo=Math.random();
    if(locationValidation(locationCity,locationCountry,locationState,locationName))
    { 
        
        //alert("Add")   
        if(val=="Add")
        {
          
        
            var url='../acc/addOrUpdateLocations.action?accountSearchOrgId='+orgId+'&accCity='+locationCity+
            '&accCountry='+locationCountry+'&accState='+locationState+'&accPhone='+locationPhone+'&locationFlag='+locationFlag+
            '&locationAddress1='+locationAddress1+'&locationAddress2='+locationAddress2+'&locationZip='+locationZip+
            '&locationFax='+locationFax+'&locationName='+locationName;     
        }
        else 
        {
            
          
            url='../acc/addOrUpdateLocations.action?accountSearchOrgId='+orgId+'&accCity='+locationCity+
            '&accCountry='+locationCountry+'&accState='+locationState+'&accPhone='+locationPhone+'&locationFlag='+locationFlag+
            '&locationAddress1='+locationAddress1+'&locationAddress2='+locationAddress2+'&locationZip='+locationZip+
            '&locationFax='+locationFax+'&locationStatus='+locationStatus+'&locationId='+locationId+'&locationName='+locationName;      
           
        }
        var req=initRequest(url);
        //alert(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
            
                if (req.status == 200) {
               
                    if(req.responseText=="Successfully inserted"){
                    
                        showLocations();
                        $("locationValidation").html("<b><font color='green'>Successfully Added</font></b>");
                        $("locationValidation").show().delay(5000).fadeOut();
                        clearLocationFields();
                    }
                    else if(req.responseText=="Successfully Updated"){
                    
                        showLocations();
                        $("locationValidation").html(" <b><font color='green'>Successfully Updated</font></b>");   
                        $("locationValidation").show().delay(5000).fadeOut();
                    } 
                    else
                    {
                        alert("error")        
                    }
                } 
                else
                {
                    alert("Error occured");
                }
            }
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
}
function locationValidation(city,country,state,name)
{   
    if(name=="")
    {
        $("locationValidation").html("<b><font color='red'>Enter Name</font></b>");  
        $("locationValidation").show().delay(5000).fadeOut();
        return false;
    }
    if(country=="")
    {
        $("locationValidation").html("<b><font color='red'>Select Country</font></b>"); 
        $("locationValidation").show().delay(5000).fadeOut();
        return false;
    }
    if(state=="")
    {
        $("locationValidation").html("<b><font color='red'>Select State</font></b>");  
        $("locationValidation").show().delay(5000).fadeOut();
        return false;
    }
    //alert(state)
    if(city=="")
    {
        $("locationValidation").html("<b><font color='red'>Enter City</font></b>");   
        $("locationValidation").show().delay(5000).fadeOut();
        return false;
    } 
    return true;
}
function clearLocationFields()
{
    //alert("clear")
    $('#locationName').val("");
    $('#locationAddress1').val("");
    $('#locationAddress2').val("");
              
    $('#locationCity').val("");
    $('#locationCountry').val(3);
    $('#locationState').val("");
    $('#locationPhone').val("");
    $('#locationZip').val("");
    $('#locationFax').val("");
    $('#locationStatus').val("");
}
function editLocations(id)
{
    //alert(id)
    var locationId= id;
    document.getElementById("accountAddress").value=id;
    var url='../acc/editLocations.action?locationId='+locationId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
       
        if (req.readyState == 4) {
            
            if (req.status == 200) {
             
                populateLocationsEdit(req.responseText);
            } 
            else
            {
                alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function populateLocationsEdit(response)
{
    var eduList=response.split("^");
    for(var i=0;i<eduList.length-1;i++){   
       
        var Values=eduList[i].split("|");
        {  
               
            $('#locationName').val(Values[9]);
            $('#locationAddress1').val(Values[0]);
            $('#locationAddress2').val(Values[1]);
              
            $('#locationCity').val(Values[2]);
            $('#locationCountry').val(Values[3]);
               
            $('#locationPhone').val(Values[5]);
            $('#locationZip').val(Values[6]);
            $('#locationFax').val(Values[7]);
            $('#locationStatus').val(Values[8]);
                
            getLocationStates($('#locationCountry').val(),'#locationState',Values[4])
            $('#locationState').val(Values[4]);
                
        }
    }
}
function getLocationStates(countryName, stateDropDownId,val)
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
            content='<option value="">Select State</option>';
            ;
            data.forEach(function(state){
                content+='<option value=\''+state.id+'\'>'+state.name+'</option>';
            });
            $(stateDropDownId).children().remove();
            $(stateDropDownId).append(content);
            $('#locationState').val(val);
    
        }
    });
}

function showContacts()
{
    //alert("In ");
    //alert(document.getElementById("accountsearchid").value);
    var orgId= document.getElementById("accountSearchID").value;
    //alert(orgId);
    var url='../acc/getContacts.action?accountSearchOrgId='+orgId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingContact').style.display = 'block';
        if (req.readyState == 4) {
            
            if (req.status == 200) {
                $('#loadingContact').hide();
                
                populateContactTable(req.responseText);
                
            } 
            else
            {
                alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function populateContactTable(response){
    // alert(response);  
    $(".page_option").css('display', 'block');  
    var eduList=response.split("^");
    var OrgID= document.getElementById("accountSearchID").value;
    var table = document.getElementById("contactPageNav");
    var accName =  document.getElementById("account_name").value;
    var accountType =  document.getElementById("account_type").value; 
    
    // var name =  document.getElementById("account_name").value;
    //var table = document.getElementById("contactPageNav");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        for(var i=0;i<eduList.length-1;i++){   
       
            var Values=eduList[i].split("|");
            {  
         
         
                var row = $("<tr />")
                //alert("row--?"+row);
      
                $("#contactPageNav").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                // row.append($('<td><a href="" class="eduEdit_popup_open " onclick=" showEditEduOverlayDetails('+Values[0]+');" > ' + Values[1] + "</td>"));
                //row.append($("<td>" + Values[0] + "</td>"));
                //reqRow.append($('<td><a href="../Requirements/requirementedit.action?requirementId='+Values[0]+'" > ' + Values[1] + "</td>"));
                row.append($('<td><a href="../acc/accountcontactedit.action?accountType='+ accountType +'&account_name='+ accName +'&accountSearchID='+ OrgID +'&contactId='+Values[0]+'" > '+ Values[1] + "</td>"));
                // row.append($('<td><a href="../acc/accountcontactedit.action?accountSearchID='+OrgID+'&account_name='+name+'&contactId='+Values[0]+'" > '+ Values[1] + "</td>"));

                /* if(Values[2]=='null'){
                    row.append($("<td>" +'-----' + "</td>"));
                }else{
                    row.append($("<td>" + Values[2] + "</td>"));
                }*/
                row.append($("<td>" + Values[3] + "</td>"));
                row.append($("<td>" + Values[5] + "</td>"));
                row.append($("<td>" + Values[4] + "</td>"));
                row.append($("<td>" + Values[2] + "</td>"));
                //row.append($('<td><center><a href="../users/general/getUserRoles.action?userid='+Values[0]+'&&accountSearchID='+OrgID+'" >'+"<img src='./../includes/images/roleImage.png' height='20' width='30' >" + "</center></td>"));

                //                row.append($('<td><a href="javascript:contactLoginOverlay('+Values[0]+','+ OrgID +',\'' + Values[3] + '\')"  class="contactLoginOverlay_popup_open" >'  + "<img src='./../includes/images/user-login.png' height='20' width='30' >" + "</td>"));
                row.append($('<td><a href="#" class="contactLoginOverlay_popup_open" onclick="contactLoginOverlay('+Values[0]+','+ OrgID +',\'' + Values[3] + '\');">'  + "<img src='./../includes/images/user-login.png' height='20' width='30' >" + "</td>"));
                

            //row.append($("<td>" + Values[4] + "</td>"));
            //row.append($("<td>" + Values[7] + "</td>"));
            //onclick="saveContactDetails(' + Values[0] +');" > '
            }
        }
    }
    else {
        var row = $("<tr />")
        $("#contactPageNav").append(row);
        row.append($('<td colspan="6"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
    }
    $('#contactPageNav').tablePaginate({
        navigateType:'navigator'
    },recordPage);
 
    acPager.init(); 
   
}

function saveContactDetails()
{
    var usrid= document.getElementById("contactId").value;
    var orgid= document.getElementById("orgId").value;
    // var orgId= document.getElementById("accountsearchid").value;
    //alert(usrid);
    // alert(orgid);
    var url='../acc/saveContacts.action?accountSearchOrgId='+ orgid +'&orgUserId='+usrid;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //alert()
                document.getElementById("outputMessage").innerHTML=req.responseText;
                if(req.responseText=="Login credentials Sent Succesfully to email")
                {
                    document.getElementById('contactSend').style.display="none";   
                    document.getElementById('contactCancel').style.display="none";       
                }
                    
                    
            } 
            else
            {
            //alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

    return false;
}


function getContactSearchResults(){
    initSessionTimer();
    var firstName=$("#firstNameContacts").val();
    // alert(userid);
    var lastName=$("#lastNameContacts").val();
    var email=$("#emailContacts").val();
    var status=$("#statusContacts").val();
    var phone=$("#phoneContacts").val();
    
    var orgId= document.getElementById("accountSearchID").value;
    //alert(orgId);
    //var rno=Math.random();
    var url='../acc/getContactSearchResults.action?accountSearchOrgId='+orgId +'&firstName='+firstName +'&lastName='+lastName +'&email='+email +'&status='+status+ '&phone='+ phone;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingContact').style.display = 'block';
        if (req.readyState == 4) {
            if (req.status == 200) {
                $('#loadingContact').hide();
                //alert("in ajax-->" +(req.responseText))
                //alert("Record Updated Successfully");
                populateContactTable(req.responseText);
               
            //$("securityinfo").html(" <b><font color='green'>Confidential information Saved Successfully</font></b>");
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
    
                 
function FillContactAddress() {
    if(document.getElementById("checkAddress").checked == true) {
        //alert(checkAddress.checked);
        document.getElementById("conCAddress").value= document.getElementById("conAddress").value;
        document.getElementById("conCAddress").disabled = true;
        document.getElementById("conCAddress2").value= document.getElementById("conAddress2").value;
        document.getElementById("conCAddress2").disabled = true;
        document.getElementById("conCCity").value=document.getElementById("conCity").value;
        document.getElementById("conCCity").disabled = true;
        document.getElementById("conCCountry").value=document.getElementById("conCountry").value;        
        document.getElementById("conCCountry").disabled = true;  
        
        var $options = $("#conState > option").clone();
        $('#conCState').append($options);
        document.getElementById("conCState").value=document.getElementById("conState").value; 
        document.getElementById("conCState").disabled = true; 
        document.getElementById("conCZip").value=document.getElementById("conZip").value;
        document.getElementById("conCZip").disabled = true;
        document.getElementById("conCPhone").value=document.getElementById("conPhone").value;
        document.getElementById("conCPhone").disabled = true;
    }
    if(document.getElementById("checkAddress").checked == false) {
        document.getElementById("conCAddress").disabled = false;
        document.getElementById("conCAddress").value='';
        document.getElementById("conCAddress2").disabled = false;
        document.getElementById("conCAddress2").value='';
        document.getElementById("conCCity").disabled = false;
        document.getElementById("conCCity").value='';
        document.getElementById("conCCountry").disabled = false;
        document.getElementById("conCCountry").value='';
        document.getElementById("conCState").disabled = false;
        document.getElementById("conCState").value='';
        document.getElementById("conCZip").disabled = false;
        document.getElementById("conCZip").value='';
        document.getElementById("conCPhone").disabled = false;
        document.getElementById("conCPhone").value='';

    }
}
function FillContactAddressAdding() {
    if(document.getElementById("add_checkAddress.checked") == true) {
        //alert(checkAddress.checked);
        
        
        document.getElementById('add_checkAddress').value=true;
        document.getElementById("conCAddress").value= document.getElementById("conAddress").value;
        document.getElementById("conCAddress").disabled = true;
        document.getElementById("conCAddress2").value= document.getElementById("conAddress2").value;
        document.getElementById("conCAddress2").disabled = true;
        document.getElementById("conCCity").value=document.getElementById("conCity").value;
        document.getElementById("conCCity").disabled = true;
        document.getElementById("conCCountry").value=document.getElementById("conCountry").value;        
        document.getElementById("conCCountry").disabled = true;  
        
        var $options = $("#conState > option").clone();
        $('#conCState').append($options);
        document.getElementById("conCState").value=document.getElementById("conState").value; 
        document.getElementById("conCState").disabled = true; 
        document.getElementById("conCZip").value=document.getElementById("conZip").value;
        document.getElementById("conCZip").disabled = true;
        document.getElementById("conCPhone").value=document.getElementById("conPhone").value;
        document.getElementById("conCPhone").disabled = true;
    }
    if(document.getElementById("add_checkAddress").checked == false) {
        document.getElementById('add_checkAddress').value=false;
 
        document.getElementById("conCAddress").disabled = false;
        document.getElementById("conCAddress").value='';
        document.getElementById("conCAddress2").disabled = false;
        document.getElementById("conCAddress2").value='';
        document.getElementById("conCCity").disabled = false;
        document.getElementById("conCCity").value='';
        document.getElementById("conCCountry").disabled = false;
        document.getElementById("conCCountry").value='';
        document.getElementById("conCState").disabled = false;
        document.getElementById("conCState").value='';
        document.getElementById("conCZip").disabled = false;
        document.getElementById("conCZip").value='';
        document.getElementById("conCPhone").disabled = false;
        document.getElementById("conCPhone").value='';

    }
}


function contactInfoValidation(){
    var contactSkillArry = [];    
    $("#skillListValue :selected").each(function(){
        contactSkillArry.push($(this).text()); 
    });
    document.getElementById("contactSkillValues").value=contactSkillArry;
    var first_name=document.getElementById("ContactFname").value;
    re = /^[A-Za-z\s]+$/;
    if(!re.test(first_name)||first_name.length<3)
    {
        $("#InsertContactInfo").html(" <b><font color='red'>must be valid First Name</font></b>.");
        $("#ContactFname").css("border", "1px solid red");
        return false;
    }
    re = /^[A-Za-z0-9\s]+$/;
    var last_name=document.getElementById("ContactLname").value;
    if(!re.test(last_name)||last_name.length<3)
    {
        $("#InsertContactInfo").html(" <b><font color='red'>must be valid Last Name</font></b>.");
        $("#ContactLname").css("border", "1px solid red");
        return false;
    }
    var domain=document.getElementById("email_ext").value;
    var status=document.getElementById("ContactEmail").value;
    var email=status+'@'+domain;
    // // re=/^[a-z0-9][-a-z0-9_\+\.]/;
    re=/^[a-zA-Z0-9\.'\-\+\_\%\$]+$/;
    if(!re.test(status))
    {
        //$("#addContactError").html("");
        $("#InsertContactInfo").html(" <b><font color='red'>must be valid  corp.email</font></b>.");
        $("#ContactEmail").css("border", "1px solid red");
        return false;
    }
    var officePhone =document.getElementById("Officephone").value;
    
    if(officePhone==' ' || officePhone==null)
    {
        $("#InsertContactInfo").html(" <b><font color='red'>must be valid phone number</font></b>.");
        $("#Officephone").css("border", "1px solid red");
        return false;
    }
    var contactTitle=document.getElementById("contactTitle").value;
    if(contactTitle==' ' || contactTitle==null)
    {
        $("#addContactError").html(" <b><font color='red'>must be valid title</font></b>.");
        $("#contactTitle").css("border", "1px solid red");
        return false;
    } 
    var file=$("#taskAttachment").val();
    //alert(file);
    if(file!='')
    {
        var allowed_extensions = new Array("jpg","gif","png");
        var file_extension = file.split('.').pop(); // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.

        for(var i = 0; i < allowed_extensions.length; i++)
        {
            if(allowed_extensions[i]==file_extension)
            {
                return true; // valid file extension
            }
        }
        $("#InsertContactInfo").html(" <b><font color='red'>The file uploaded is invalid type</font></b>");

        return false;
    }
    var altemail=document.getElementById("ContactAEmail").value;
    repattern= /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if(altemail=='')
    {
        return true;
    }
    else if(!repattern.test(altemail))
    {
        //$("#addContactError").html("");
        $("#InsertContactInfo").html(" <b><font color='red'>Please enter valid e-mail.</font></b>.");
        $("#ContactAEmail").css("border", "1px solid red");
        return false;
    }

}

    
   

function contactFirstNameValidation(){
    
    $("#actionMessage").html(""); 
    var first_name=document.getElementById("ContactFname").value;
    re = /^[A-Za-z\s]+$/;
    if(!re.test(first_name)||first_name.length<3)
    {
        $("#InsertContactInfo").html(" <b><font color='red'>must be valid First Name</font></b>.");
        $("#ContactFname").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#InsertContactInfo").html("");
        $("#ContactFname").css("border", "1px solid green");

    }
    return true;
}

function contactLastNameValidation(){
    $("#actionMessage").html("");
    re = /^[A-Za-z0-9\s]+$/;
    var last_name=document.getElementById("ContactLname").value;
    if(!re.test(last_name)||last_name.length<3)
    {
        $("#InsertContactInfo").html(" <b><font color='red'>must be valid Last Name</font></b>.");
        $("#ContactLname").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#InsertContactInfo").html("");
        $("#ContactLname").css("border", "1px solid green");
        return true;
    }
}





function ContactEmailValidate(){
    $("#actionMessage").html(""); 
    var ContactEmail = document.getElementById("ContactEmail").value;
    var url='../acc/contactEmailCheck.action?ContactEmail='+ContactEmail;
    // alert(ContactEmail);
    // alert(url);
    
   
    var req=initRequest(url);
    
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                // alert(req.responseText);
                resultEmail(req.responseText);
               
            } 
            else
            {
                            
            }
        }
    };
    
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
//    function EmailValidation1(){
//   
//    var status=document.getElementById("email1").value;
//   
//    re=/[^@]+@[^@]+\.[a-zA-Z]{2,}/;
//    if(!re.test(status))
//    {
//        $("j").html(" <b><font color='red'>must be valid  corp.email</font></b>.");
//        $("#email1").css("border", "1px solid red");
//    }
//    else
//    {
//        $("j").html("");
//        $("#email1").css("border", "1px solid green");
//    }
//}
}
function resultEmail(response){
    if(response=="success"){
        $("#InsertContactInfo").html("  <b><font color='green'>E-mail is Available</font></b>");
        return true;
    }
    else{
        document.getElementById("ContactEmail").value="";
        $("#InsertContactInfo").html(" <b><font color=#B20000>E-mail  Already Exists !</font></b>");
        return false;
    }
}

function ContactAddressValidation()
{   
    // alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ");
    var OfficeAddress=document.getElementById("OfficeAddress").value;
    letters = /^[0-9A-Za-z]+$/;  
    if(OfficeAddress.value.match(letters))  
    {  
        alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ");
        return true;  
    }  
    else  
    {  
        $("InsertContactInfo").html(" <b><font color=#B20000>User address must have alphanumeric characters only</font></b>");
        //uadd.focus(); 
        $("#OfficeAddress").css("border", "1px solid red");
        return false;  
    }  
}
function paddresValidation(){
    
    //alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
    var Address=document.getElementById("conAddress").value;
    re = /^[A-Za-z]+$/;
    if(Address.length<2)
    {
        $("#spanUpdatep").css('visibility', 'hidden');
        $("errmsg").html(" <b><font color='red'>must be valid Address</font></b>");
        $("#conAddress").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#spanUpdatep").css('visibility', 'visible');
        $("errmsg").html(" ");
        $("#conAddress").css("border", "1px solid green");
    }
    
}


function CaddresValidation(){

    var Address=document.getElementById("conCAddress").value;
    re = /^[A-Za-z]+$/;
    if(Address.length<2)
    {
        $("#spanUpdatep").css('visibility', 'hidden');
        $("errmsgc").html(" <b><font color='red'>must be valid Address</font></b>");
        $("#Address").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#spanUpdatep").css('visibility', 'visible');
        $("errmsgc").html(" ");
        $("#Address").css("border", "1px solid green");
    }
}


function removeContactoverlay(){                        
    //alert("hi");
    document.forms["contactform"].reset(); 
    $("ContactEmail").html(" ");
    //$('InsertContactInfo').html(" ");
    $('InsertContactInfo').html(" ");  
    $("#conZip").css("border", "1px solid #53C2FF ");
    $("#ContactFname").css("border", "1px solid #53C2FF ");
    $("#ContactLname").css("border", "1px solid #53C2FF ");
    $("errmsg").html(" ");
    $("#conCZip").css("border", "1px solid #53C2FF ");
}


function removebordercolor(){
    $("#ContactFname").css("border", "1px solid #53C2FF ");
    $("#conZip").css("border", "1px solid #53C2FF ");
    $('InsertContactInfo').html(" ");   

}


function Removebordercolor(){
    $("#ContactLname").css("border", "1px solid #53C2FF "); 
    //  $('InsertContactInfo').html(" ");
    // $('UpdateNoteInfo').html(" ");
    $('InsertContactInfo').html(" ");
}



function ConPermanentStateChange()
{
    
    var id=document.getElementById('conCountry').value;
    //alert(id);
    var url='../acc/getConState.action?id='+id;
   
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            // alert(req.responseText);
           
            ConPermanentState(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function ConPermanentState(data){
    //alert(data);
    var topicId = document.getElementById("conState");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#conState');
    $select.find('option').remove();
    $('<option>').val(-1).text('Select State').appendTo($select);
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}
function ConCurrentStateChange()
{
    //alert("Consultant ajax");
    var id=document.getElementById('conCCountry').value;
    //alert(id);
    var url='../acc/getConState.action?id='+id;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText);
           
            ConCurrentState(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function ConCurrentState(data){
    //alert(data);
    var topicId = document.getElementById("conCState");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#conCState');
    $select.find('option').remove();   
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}

function contactPZipValidation(){
    
    //alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
    var conZip=document.getElementById("conZip").value;
    //var Zip_C=document.getElementById("conCZip").value;
    if( isNaN($('#conZip').val()))
    {
        $("#spanUpdatep").css('visibility', 'hidden');
        $("errmsg").html(" <b><font color='red'>must be valid Zip</font></b>");
        $("#conZip").css("border", "1px solid red");
        document.getElementById("conZip").value=""; 
        return false;
    }
    else if(conZip.length!=5)
    {
        $("#spanUpdatep").css('visibility', 'hidden');
        $("errmsg").html(" <b><font color='red'>must be valid Zip</font></b>");
        $("#conZip").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#spanUpdatep").css('visibility', 'visible');
        $("errmsg").html(" ");
        $("#conZip").css("border", "1px solid green");
    }

    
}

function contactCZipValidation(){
    
    //alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
   
    var conCZip=document.getElementById("conCZip").value;
    if(isNaN($('#conCZip').val()))
    {
        $("#showUpdatec").css('visibility', 'hidden');
        $("errmsgc").html(" <b><font color='red'>must be valid Zip</font></b>");
        $("#conCZip").css("border", "1px solid red");
        document.getElementById("conCZip").value=""; 
    }
    
    else if(conCZip.length!=5)
    {
        $("#showUpdatec").css('visibility', 'hidden');
        $("errmsgc").html(" <b><font color='red'>must be valid Zip</font></b>");
        $("#conCZip").css("border", "1px solid red");
        return false;
    }
    
    else
    {
        $("#showUpdatec").css('visibility', 'visible');
        $("errmsgc").html(" ");
        $("#conCZip").css("border", "1px solid green");
    }
    
}


function initRequest(url) {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }
    else
    if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }    
}


function ccityValidation(){
    
    var City=document.getElementById("conCCity").value;
    if(City.length<3)
    {
        $("#spanUpdatep").css('visibility', 'hidden');
        $("errmsgc").html(" <b><font color='red'>must be valid City</font></b>");
        $("#conCCity").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#spanUpdatep").css('visibility', 'visible');
        $("errmsgc").html(" ");
        $("#conCCity").css("border", "1px solid green");
    }
    
}


function contactPcityValidation(){
    $("#actionMessage").html(""); 
    var City=document.getElementById("conCity").value;
    if(City.length<3)
    {
        // $("#spanUpdatep").css('visibility', 'hidden');
        $("#InsertContactInfo").html(" <b><font color='red'>must be valid City</font></b>");
        $("#conCity").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#spanUpdatep").css('visibility', 'visible');
        $("#InsertContactInfo").html(" ");
        $("#conCity").css("border", "1px solid green");
    }
    
}

$("#conZip").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
        //display error message
        $("#errmsg").html("Digits Only").show().fadeOut("slow");
        return false;
    }
});
   
$("#conCZip").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
        //display error message
        $("#errmsg").html("Digits Only").show().fadeOut("slow");
        return false;
    }
});
   
function removeDataAfterContactOverlay()
{
    document.forms["contactform"].reset(); 
    document.getElementById("conCAddress").disabled = '';
      
        
    document.getElementById("conCAddress2").disabled = '';
        
    document.getElementById("conCCity").disabled = '';
        
    document.getElementById("conCCountry").disabled = '';
        
    document.getElementById("conCState").disabled =  '';
    document.getElementById("conCountry").disabled = '';
        
    document.getElementById("conState").disabled =  '';
       
    document.getElementById("conCZip").disabled =  '';
        
    document.getElementById("conCPhone").disabled = '';
        
     
    // alert("hi");
    document.getElementById('ContactFname').value='';
   
    document.getElementById('ContactEmail').value='';
    document.getElementById('ContactLname').value='';
    document.getElementById('ContactMname').value='';
    document.getElementById('Officephone').value='';
    document.getElementById('conAddress').value='';
    document.getElementById('conAddress2').value='';
    document.getElementById('conCity').value='';
    document.getElementById('conZip').value='';
    document.getElementById('conCountry').value='';
    document.getElementById('conState').value='';
    document.getElementById('conPhone').value='';
    document.getElementById('conCAddress').value='';
    document.getElementById('conCAddress2').value='';
    document.getElementById('conCCity').value='';
    document.getElementById('conCZip').value='';
    document.getElementById('conCCountry').value='';
    document.getElementById('conCState').value='';
    document.getElementById('conCPhone').value='';
    document.getElementById('add_checkAddress').value='';
    document.getElementById('taskAttachment').value='';
    //$('checkAddress').html(" ");
    // $("#addContactError").html(" ");
    $("#InsertContactInfo").html(" ");
    
    $("#ContactLname").css("border", "1px solid #53C2FF ");
    $("#ContactFname").css("border", "1px solid #53C2FF ");
    $("#conAddress").css("border", "1px solid #53C2FF ");
    $("#conCAddress").css("border", "1px solid #53C2FF ");
    $("#conCCity").css("border", "1px solid #53C2FF ");
    $("#ContactEmail").css("border", "1px solid #53C2FF ");
    
    $('errmsgc').html(" ");
    $('errmsg').html(" ");
}


function EmailValidation(){
   
    var domain=document.getElementById("email_ext").value;
    var status=document.getElementById("ContactEmail").value;
    var email=status+'@'+domain;
    // // re=/^[a-z0-9][-a-z0-9_\+\.]/;
    re=/^[a-zA-Z0-9\.'\-\+\_\%\$]+$/;
    if(!re.test(status))
    {
        //$("#addContactError").html("");
        $("#InsertContactInfo").html(" <b><font color='red'>must be valid  corp.email</font></b>.");
        $("#ContactEmail").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#InsertContactInfo").html("");
        $("#ContactEmail").css("border", "1px solid green");
        var url='../acc/contactEmailCheck.action?ContactEmail='+email;
        // alert(ContactEmail);
        // alert(url);
    
   
        var req=initRequest(url);
    
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
                    //                 alert(req.responseText);
                    resultEmail(req.responseText);
               
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
}
function clearContactOverlay(){
    document.forms["contactform"].reset();
    document.getElementById("conState").value='';
    document.getElementById("conCState").value='';
    // $("#addContactError").html(" ");
    $("#InsertContactInfo").html(" ");
    $("#ContactLname").css("border", "1px solid #53C2FF ");
    $("#ContactFname").css("border", "1px solid #53C2FF ");
    $("#conAddress").css("border", "1px solid #53C2FF ");
    $("#conCAddress").css("border", "1px solid #53C2FF ");
    $("#conCCity").css("border", "1px solid #53C2FF ");
    $("#conCity").css("border", "1px solid #53C2FF ");
    $("#ContactEmail").css("border", "1px solid #53C2FF ");
    $('errmsgc').html(" ");
    $('errmsg').html(" ");
}
$(document).ready(function() {

    var specialBox = document.getElementById('ContactBoxOverlay');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#Contact_popup').popup(      
        );
    
});
function lockScreen(){
       
    document.getElementById('jquery-msg-overlay').style.display="block";
//  $.msg();   
}
function unlockScreen(){
  
    document.getElementById('jquery-msg-overlay').style.display="none";
}
 
function getTitlesForDepatments(){
    //alert("hello");
    var dept=document.getElementById("departments").value;
    var url='../acc/getTitlesForDepartments.action?dept_id='+dept;
    //alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
       
        if (req.readyState == 4 && req.status == 200) {
            setTitle(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function setTitle(data){
    var topicId = document.getElementById("titlesId");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#titlesId');
    $select.find('option').remove();   
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}
function contactLoginOverlay(usrid,orgid,emailId)
{      
    
    document.getElementById("contactId").value=usrid;
    document.getElementById("orgId").value=orgid;
    document.getElementById("contactEmail").innerHTML="("+emailId+")";
    //     var url='../acc/getContactEmail.action?userId='+usrid;
    //     var req=initRequest(url);
    //    req.onreadystatechange = function() {
    //        if (req.readyState == 4) {
    //            if (req.status == 200) {
    //                //alert(req.responseText)
    //                document.getElementById("contactEmail").innerHTML="("+req.responseText+")";
    //               
    //            } 
    //            else
    //            {
    //               // alert("Error occured");
    //            }
    //        }
    //    };
    //    req.open("GET",url,"true");
    //    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    //    req.send(null);
    //    
    contactOverlayLogin();
    contactCheck(usrid);
       
   
}
function contactCheck(usrid)
{
    var url='../acc/checkContactExist.action?orgUserId='+usrid;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                // alert(req.responseText)
                document.getElementById("outputMessage").innerHTML=req.responseText;
                if(req.responseText=="User contact is already Registered Please Check Email")
                {
                    document.getElementById('contactSend').style.display="none";   
                    document.getElementById('contactCancel').style.display="none";   
                }
                if(req.responseText=="Do you want to send Login Credentials To Email?")  
                {
                    document.getElementById('contactSend').style.display="";   
                    document.getElementById('contactCancel').style.display="";   
                    
                }
            } 
            else
            {
            // alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
 
}
function contactOverlayLogin()
{
    initSessionTimer();
    var specialBox = document.getElementById('contactLoginBox');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#contactLoginOverlay_popup').popup(      
        );  
}

function contactDesignationValidation(){
    
    $("#actionMessage").html("");  
    var designation=document.getElementById("designation").value;
    re = /^[A-Za-z\s]+$/;
    if(!re.test(designation)||designation.length<2)
    {
        $("#InsertContactInfo").html(" <b><font color='red'>must be valid designation</font></b>.");
        $("#designation").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#InsertContactInfo").html("");
        $("#designation").css("border", "1px solid green");

    }
    return true;
}

function removeActionMessage(){
    $("#actionMessage").html("");  
} 

function getCsrDetailsTable()
{
    initSessionTimer();
    var org_id=document.getElementById("viewAccountID").value;
    //alert(org_id);
    var csrName=document.getElementById("csrName").value;
    var csrEmail=document.getElementById("csrEmail").value;
    var csrphone=document.getElementById("csrPhone").value;
    var csrStatus=document.getElementById("csrStatus").value;
    //var randomNumber = Math.random();
    var url='../acc/getCsrDetailsTable.action?orgUserId='+org_id+'&csrName='+csrName+'&csrEmail='+csrEmail+'&csrphone='+csrphone+'&csrStatus='+csrStatus;
    // alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //alert(req.responseText)
                populateCsrTable(req.responseText)
            } 
            else
            {
            // alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
 
}
function populateCsrTable(response){
    $(".page_option").css('display','block');
    var eduList=response.split("^");
    
    var table = document.getElementById("csrDetailsTable");
    // var name =  document.getElementById("account_name").value;
    //var table = document.getElementById("contactPageNav");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        for(var i=0;i<eduList.length-1;i++){   
       
            var Values=eduList[i].split("|");
            {  
                //alert(Values[6])
         
                var row = $("<tr />")
                //alert("row--?"+row);
      
                $("#csrDetailsTable").append(row); 
                row.append($('<td><a href="#" class="csraccountspopup_open" onclick="csraccountspopup('+Values[0]+')">' + Values[1] + "</a></th>"));
                row.append($("<td>" + Values[3] + "</td>"));
                row.append($("<td>" + Values[4] + "</td>"));
                row.append($("<td>" + Values[5] + "</td>"));
                row.append($("<td>" + Values[6] + "</td>"));
                row.append($("<td>" + Values[2] + "</td>"));
            }
        }
    }
    else {
        var row = $("<tr />")
        $("#csrDetailsTable").append(row);
        row.append($('<td colspan="6"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display','none');
        
    }
    $('#csrDetailsTable').tablePaginate({
        navigateType:'navigator'
    },recordPage);
    reqPager.init(); 
  
}
   

function doAddAccountToCsr()
{
    //    alert("hello")
    var org_id=document.getElementById("orgUId").value;
    var csrId=document.getElementById("csrId").value;
    var url='../acc/doAddAccountToCsr.action?orgUserId='+org_id+'&csrId='+csrId;
    //alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                if(req.responseText>0){
                    $("csrResult").html(" <b><font color='green'>Account successfully added to CSR</font></b>.");
                }
                else
                {
                    $("csrResult").html(" <b><font color='red'>Account Already Exist for CSR</font></b>.");
                }
                document.getElementById("csrName").value="";
                document.getElementById("csrId").value="";
            } 
            else
            {
            // alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
  
}
function csrStatusChange(){
    var csrId=document.getElementById("csrIdInOverlay").value;
    var csrStatus=document.getElementById("csrStatusOverlay").value;
    var orgUserId=document.getElementById("accountSearchID").value;
    //var randomNo=Math.random();
    var url='../acc/csrStatusChange?csrId='+csrId+'&csrStatus='+csrStatus+'&orgUserId='+orgUserId;
    
    // alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //alert(req.responseText)
                $("#updateCsr").html(" <b><font color='green'>Succesfully Updated</font></b>.");
                getCsrDetailsTable();
            }
            else
            {
            // alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function csraccountspopup(id){
    // alert(id);
    document.getElementById("updateCsr").innerHTML="";
    document.getElementById("csrIdInOverlay").value=id;
    setupOverlay('csraccoutsoverlay','#csraccountspopup');
}
function csraccountspopupclose(){
 
    setupOverlay('csraccoutsoverlay','#csraccountspopup');
}
function setupOverlay(overlayBox, popupId){
    var specialBox = document.getElementById(overlayBox);
    if(specialBox.style.display == "block"){
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    // Initialize the plugin
    $(popupId).popup();
};
function setTransferCopy(){
    var type=$('#typeTransfer').val();
    document.getElementById("fromCSR").value="";
    document.getElementById("toCSR").value="";
    document.getElementById("fromCSRID").value="";
    document.getElementById("toCSRID").value="";
    document.getElementById("validationMessage").value="";
    
//    if(type=='TA'){
//        $("#transfer").css('visibility', 'visible');
//        $("#copy").css('visibility', 'hidden');
//    }
//    else{
//         $("#transfer").css('visibility', 'hidden');
//        $("#copy").css('visibility', 'visible');
//    }

};
function validateTranferAccounts(){
    var fromCSR=$("#fromCSRID").val();
    var toCSR=$("#toCSRID").val();
    if(fromCSR==""){
        $("#validationMessage").html("<font color='red'>Please enter existing fromCSR</font>");
        $("#fromCSR").css('border','1px solid red');
        return false;
    }  
    else{
        $("#validationMessage").html("");
        $("#fromCSR").css('border','1px solid #ccc');
       
    }
    if(toCSR==""){
        $("#validationMessage").html("<font color='red'>Please enter existing toCSR</font>");
        $("#toCSR").css('border','1px solid red');
        return false;
    }  
    else{
        $("#validationMessage").html("");
        $("#toCSR").css('border','1px solid #ccc');
        
    }
    return true;
}
function transferAccounts(){
    initSessionTimer();
    var fromCSRID=$("#fromCSRID").val();
    var toCSRID=$("#toCSRID").val();
    var typeTransfer=$("#typeTransfer").val();
    var fromCSR=$("#fromCSR").val();
    var toCSR=$("#toCSR").val();
    var title1;
    if(validateTranferAccounts()){
        if(typeTransfer=='TA')    
            title1="Are you sure to Transfer?";
        else
            title1="Are you sure to Copy?";
    
        swal({
    
            title: title1,
  
            //text: "Tranfering csr",
            textSize:"170px",
            type: "warning",
  
            showCancelButton: true,
            confirmButtonColor: "#3498db",
  
            //cancelButtonColor: "#56a5ec",
            cancelButtonText: "No",
            confirmButtonText: "Yes",
            closeOnConfirm: false,
            closeOnCancel: false
 
        },
        function(isConfirm){
            if (isConfirm) {
                var url='../acc/assignAccount.action?fromCSRID='+fromCSRID+'&toCSRID='+toCSRID+'&typeTransfer='+typeTransfer;
                //alert(url)
                var req=initRequest(url);
                //alert("hih")
                req.onreadystatechange = function() {
                    if (req.readyState == 4) {
                        // alert("hi res")
                        if (req.status == 200) {
                            //alert(req.responseText)
                            if(req.responseText=="success"){
                                if(typeTransfer=='TA')
                                    document.getElementById("validationMessage").innerHTML="<font color='green'>Accounts Transfer successfully</font>";
                                else
                                    document.getElementById("validationMessage").innerHTML="<font color='green'>Accounts Copied successfully</font>";
                            }
                            else
                            {
                                if(typeTransfer=='TA')      
                                    document.getElementById("validationMessage").innerHTML="<font color='green'>no Accounts to Transfer</font>";
                                else
                                    document.getElementById("validationMessage").innerHTML="<font color='green'>no Accounts to Copy</font>";  
                            }
                        } 
                    }
                };
                req.open("GET",url,"true");
                req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                req.send(null);
                $('button').each(function(){
                    $('button').click(function(){
                        $('input[type="text"]').val("");
                    });
                });
            
                if(typeTransfer=='TA')
                    swal("Transfered!", "Accounts Transferred....", "success");
                else
                    swal("Copied!","Accounts Copied....","success");    
            } else {
                if(typeTransfer=='TA')
                    swal("Cancelled", "Transferring cancelled ", "error");
                else
                    swal("Cancelled","Copping cancelled","error") ;
            }
        });

    } 
    return false;
   
}
function getCSR(){
    //alert("csr");
    var fromCSR=$("#fromCSR").val();
    //var toCSR=$("#toCSR").val();
    if(fromCSR =="")
    {
        clearTable();
    }
    else if(fromCSR!=""){
        if(fromCSR.length>=1)
        {
            url=CONTENXT_PATH+"/getCsrNamesAutoPopulate.action?csrName="+fromCSR;
            //alert(url)
            //            }
            //alert("url-->"+url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        //alert(v_empName)
                        //alert("jagan"+req.responseXML);
                        parseCsrFromname(req.responseXML);
                    //alert("hafan");
                    } else if (req.status == 204){
                        clearTable();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }    
}

function parseCsrFromname(responseXML) {
    //alert("hii");
    clearTable();
    //alert("hii");
    var consultants = responseXML.getElementsByTagName("EMPLOYEES")[0];
    //alert(consultants.childNodes.length);
    if (consultants.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        clearTable();
    }
    //alert("Hello"+consultants.childNodes.length)
 

    //alert("Hello")
    
    var consultant = consultants.childNodes[0];
    var chk=consultant.getElementsByTagName("VALID")[0];
    isExist = chk.childNodes[0].nodeValue;
    if(chk.childNodes[0].nodeValue =="true") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < consultants.childNodes.length; loop++) {
            var consultant = consultants.childNodes[loop];
            var loginId = consultant.getElementsByTagName("EMPID")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];
            //alert(empName.childNodes[0].nodeValue)
            //alert(loginId.childNodes[0].nodeValue)

            appendEmpFromCsrname(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue);
        }
        var position;
        position = findPosition(document.getElementById("fromCSR"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "<font color=red>  Employee doesn't Exists </font>";
    //document.getElementById('validationMessage').innerHTML = "<font color=red> Employee doesn't Exists!</font>";
    }
    if(consultants.childNodes.length<10) {
        // autorow1.style.overflowY = "hidden";
        autorow.style.overflowY = "hidden";
    }
    else {
        // autorow1.style.overflowY = "scroll";
        autorow.style.overflowY = "scroll";
    }
}
function appendEmpFromCsrname(empName,loginId) {
    //alert("deaswwwasfd")
    var row;
    var nameCell;
    if (!isIE) {
        row = completeTable.insertRow(completeTable.rows.length);
        nameCell = row.insertCell(0);
    } else {
        row = document.createElement("tr");
        nameCell = document.createElement("td");
        row.appendChild(nameCell);
        completeTable.appendChild(row);
    }
    row.className = "popupRow";
    nameCell.setAttribute("bgcolor", "#fff");
    var linkElement = document.createElement("a");
    linkElement.className = "popupItem";


    linkElement.setAttribute("href",
        "javascript:setEmpFromCsrName('"+empName +"','"+ loginId +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function setEmpFromCsrName(empName,loginId){
    //alert("setemp")
    clearTable();
    // alert("in set_cust");
    var csrTo=document.getElementById("toCSRID").value
    if(loginId==csrTo)
    {
        document.getElementById("validationMessage").innerHTML="<font color='red'>From Csr and To csr Should not be same</>"        
        document.getElementById("fromCSR").value ="";
        document.getElementById("fromCSRID").value ="";
          
    }
    else
    {
        document.getElementById("fromCSR").value =empName;
        document.getElementById("fromCSRID").value =loginId;
        
    }    
   
}
function clearTable() {
    //alert("clearTable")
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = " ";
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}




function getToCSR(){
    //alert("csr");
    var fromCSR=$("#toCSR").val();
    //var toCSR=$("#toCSR").val();
    if(fromCSR =="")
    {
        clearTable();
    }
    else if(fromCSR!=""){
        if(fromCSR.length>=1)
        {
            url=CONTENXT_PATH+"/getCsrNamesAutoPopulate.action?csrName="+fromCSR;
            //alert(url)
            //            }
            //alert("url-->"+url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        //alert(v_empName)
                        //alert(req.responseXML);
                        parseToCsrname(req.responseXML);
                    } else if (req.status == 204){
                        clearTable();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }    
}
function parseToCsrname(responseXML) {
    //alert("hii");
    clearTable();
    //alert("hii");
    var consultants = responseXML.getElementsByTagName("EMPLOYEES")[0];
    //alert(consultants.childNodes.length);
    if (consultants.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        clearTable();
    }
    //alert("Hello"+consultants.childNodes.length)
 

    //alert("Hello")
    
    var consultant = consultants.childNodes[0];
    var chk=consultant.getElementsByTagName("VALID")[0];
    isExist = chk.childNodes[0].nodeValue;
    if(chk.childNodes[0].nodeValue =="true") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < consultants.childNodes.length; loop++) {
            var consultant = consultants.childNodes[loop];
            var loginId = consultant.getElementsByTagName("EMPID")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];
            //alert(empName.childNodes[0].nodeValue)
            //alert(loginId.childNodes[0].nodeValue)

            appendEmpForToCsrname(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue);
        }
        var position;
        position = findPosition(document.getElementById("toCSR"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "<font color=red>  Employee doesn't Exists </font>";
    //document.getElementById('validationMessage').innerHTML = "<font color=red> Employee doesn't Exists!</font>";
    }
    if(consultants.childNodes.length<10) {
        // autorow1.style.overflowY = "hidden";
        autorow.style.overflowY = "hidden";
    }
    else {
        // autorow1.style.overflowY = "scroll";
        autorow.style.overflowY = "scroll";
    }
}
function appendEmpForToCsrname(empName,loginId) {
    //alert("deaswwwasfd")
    var row;
    var nameCell;
    if (!isIE) {
        row = completeTable.insertRow(completeTable.rows.length);
        nameCell = row.insertCell(0);
    } else {
        row = document.createElement("tr");
        nameCell = document.createElement("td");
        row.appendChild(nameCell);
        completeTable.appendChild(row);
    }
    row.className = "popupRow";
    nameCell.setAttribute("bgcolor", "#fff");
    var linkElement = document.createElement("a");
    linkElement.className = "popupItem";


    linkElement.setAttribute("href",
        "javascript:setEmpForToCsrName('"+empName +"','"+ loginId +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function setEmpForToCsrName(empName,loginId){
    clearTable();
    // alert("in set_cust");
  
    var fromCsr=document.getElementById("fromCSRID").value
    if(fromCsr == loginId)
    {
        document.getElementById("validationMessage").innerHTML="<font color='red'>From Csr and To csr Should not be same</>"        
        document.getElementById("toCSR").value ="";
    }
    else
    {
        document.getElementById("toCSR").value =empName;
        document.getElementById("toCSRID").value =loginId;        
    }
}

function csrTerminateOverlay(status)
{
    document.getElementById("status").value=status;
    var specialBox = document.getElementById('csrTerminateBox');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#csrTerminateOverlay_popup').popup(      
        );  
    $("#outputMessageOfUpdate").html("");
}

function csrTerminateOverlayValue(name,usrId){
    // alert(name);
    //alert(usrId);
    document.getElementById('csrName').innerHTML=name;
    document.getElementById('userId').value=usrId;
}
//function csrTermination(usrId){
//    //alert(usrId);
//    // var x;
//    if (confirm("Terminate the CSR?") == true) {
//        // x = "You pressed OK!";
//        var url='users/general/csrTermination.action?userid='+usrId;
//        alert(url);
//        var req=initRequest(url);
//        req.onreadystatechange = function() {
//            if (req.readyState == 4) {
//                if (req.status == 200) {
//                    //document.getElementById("demo").value =responseText;
//                    populateCsrSearchTable(req.responseText);
//                } 
//                else
//                {
//                    alert("Error occured");
//                }
//            }
//        };
//        req.open("GET",url,"true");
//        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//        req.send(null);
//       
//    } 
////document.getElementById("demo").innerHTML = x;
//}
function csrTermination(usrId){
    swal({
    
        title: "Terminate the CSR?",
  
        //text: "Tranfering csr",
        textSize:"170px",
        type: "warning",
  
        showCancelButton: true,
        confirmButtonColor: "#3498db",
  
        //cancelButtonColor: "#56a5ec",
        cancelButtonText: "No",
        confirmButtonText: "Yes",
        closeOnConfirm: false,
        closeOnCancel: false
 
    },
    function(isConfirm){
        if (isConfirm) {
            var url='users/general/csrTermination.action?userid='+usrId;
            //alert(url);
            var req=initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        //document.getElementById("demo").value =responseText;
                        populateCsrSearchTable(req.responseText);
                        swal("Terminated!", "Terminated Successfully....", "success");
                    } 
                    else
                    {
                        swal("notTerminated!", "Termination Not Done....", "error");
                    }
                }
            };
            req.open("GET",url,"true");
            req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            req.send(null);
       
   
   
    
        } else {
     
            swal("Cancelled", "Termination cancelled ", "error");
 
      
        }
    });
}


function populateCsrSearchTable(response){
    // alert(response);  
    var eduList=response.split("^");
    //var OrgID= document.getElementById("accountSearchID").value;
    var table = document.getElementById("csrResults");
    
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        for(var i=0;i<eduList.length-1;i++){   
       
            var Values=eduList[i].split("|");
            {  
                var row = $("<tr />")
                //alert("row--?"+row);
                $("#csrResults").append(row); 
                // row.append($("<td>" + Values[0] + "</td>"));
                row.append($("<td>" + Values[1] + "</td>"));
                row.append($("<td>" + Values[2] + "</td>"));
                row.append($("<td>" + Values[3] + "</td>"));
                //row.append($("<td>" + Values[4] + "</td>"));
                row.append($('<td><a href="getCsrAccounts.action?userId='+ Values[0] +'&csrName='+ Values[1] +'" > '+ Values[4] + "</td>"));
                row.append($("<td><a href='#' onclick=csrTermination(" + Values[0] + ")><img src='../../includes/images/delete.png' height=20 width=20 ></td>"));
            // attach_row.append($("<td><figcaption><button type='button' id='id' value="+ Values[4] +" onclick=doConsultAttachmentDownload("+ Values[4] +")><img src='../../includes/images/download.png' height=20 width=20 ></button></figcaption></td>"));
            }
        }
    }
    else {
        var row = $("<tr />")
        $("#csrResults").append(row);
        row.append($('<td colspan="5"><font style="color: red;font-size: 15px;">No Records to display</font></td>'))
    }
  
//   
}
function csrStatusValue(orgId,userId){
    // alert(userId);
    // alert(orgId);
    document.getElementById("overlayUserId").value=userId;
    document.getElementById("overlayOrgId").value=orgId;
}

function changeCsrAccountStatus(){
    
    var orgId= document.getElementById("overlayOrgId").value;
    var usrId= document.getElementById("overlayUserId").value;
    var status=document.getElementById("status").value;
    // alert(usrId);
    //alert(orgId); 
    //alert(status);
    var url='users/general/changeCsrAccountStatus.action?userid='+usrId+'&orgId='+orgId+'&status='+status;
    var req=initRequest(url);
    //alert(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            
            if (req.status == 200) {
                document.getElementById("outputMessageOfUpdate").innerHTML="Status is Updated"
                populateCsrAccountsTable(req.responseText);
            } 
            else
            {
                alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateCsrAccountsTable(response){
     
    $(".page_option").css('display','block');
    $(".pagination").css('display','block');
    var eduList=response.split("^");
    var table = document.getElementById("csrAccountResults");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        for(var i=0;i<eduList.length-1;i++){   
       
            var Values=eduList[i].split("|");
            {  
         
         
                var row = $("<tr />")
                //alert("row--?"+row);
      
                $("#csrAccountResults").append(row);
                //row.append($("<td>" + Values[0] + "</td>")); //csrId
                // row.append($("<td>" + Values[1] + "</td>"));//csrOrgid
                row.append($("<td>" + Values[2] + "</td>"));
                //row.append($("<td>" + Values[3] + "</td>"));//status
                row.append($('<td><a href="#" class="csrTerminateOverlay_popup_open" onclick="csrTerminateOverlay(\''+Values[3]+'\');csrStatusValue('+Values[1]+','+Values[0]+')">'  + Values[3] + "</td>"));
            //row.append($("<td>" + Values[4] + "</td>"));
            //row.append($("<td>" + Values[7] + "</td>"));
            //onclick="saveContactDetails(' + Values[0] +');" > '
            }
        }
    }
    else {
        
        var row = $("<tr/>");
        $("#csrAccountResults").append(row);
        row.append($('<td colspan="2"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display','none');
        $(".pagination").css('display','none');  
    }
    $('#csrAccountResults').tablePaginate({
        navigateType:'navigator'
    },recordPage);

   
  
}
function accountSearch(){
    
    var status= document.getElementById("csrStatus").value;
    var usrId= document.getElementById("csrUserId").value;
    var accountName= document.getElementById("accountName").value;
    //alert(usrId);
    //alert(orgId); 
    //alert(accountName);
    //alert(status);
    var url='users/general/getCsrAccount.action?userid='+usrId+'&accountName='+accountName+'&status='+status;
    var req=initRequest(url);
    //alert(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            
            if (req.status == 200) {
                populateCsrAccountsTable(req.responseText);
            } 
            else
            {
                alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function removeErrorMsg()
{
    var toCSR=$("#toCSR").val();
    var fromCSR=$("#fromCSR").val();
    if(toCSR==""){
        document.getElementById("toCSRID").value=""; 
        $('button').each(function(){
            $('button').click(function(){
                $('input[type="text"]').val("");
            });
        });
    }
    if(fromCSR==""){
        document.getElementById("fromCSRID").value=""; 
        $('button').each(function(){
            $('button').click(function(){
                $('input[type="text"]').val("");
            });
        });
    }
    //alert("hello jagan")
    $("#validationMessage").html("");
    $("#fromCSR").css('border','1px solid #ccc');
    $("#toCSR").css('border','1px solid #ccc');
    return false;
}

function getEmpCategories(){
    initSessionTimer();
    // var userId=document.getElementById("userId").value;
    var empStatus= document.getElementById("empStatus").value;
    
    var empName= document.getElementById("empName").value;

    // var empName= document.getElementById("teamMemberNamePopup").value;
    var empId= document.getElementById("teamMemberId").value;

    var empCategoryType= document.getElementById("categoryType").value;
    
    // var empCategoryName= document.getElementById("categoryNames").value;

    // alert(userId);
    // alert(empStatus); 
    // alert(empName);
    // alert(empCategoryType);
    var url='users/general/getEmpCategories.action?empId='+empId+'&empName='+empName+'&status='+empStatus+'&categoryType='+empCategoryType;
    var req=initRequest(url);
    //alert(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            
            if (req.status == 200) {
                populateEmpCategoriesTable(req.responseText);
            } 
            else
            {
                alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateEmpCategoriesTable(response){
    //  alert(response);  
    $(".page_option").css('display','block');
    var addOrUpdate="update";
    var eduList=response.split("^");
    var table = document.getElementById("empCategorizationResults");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        for(var i=0;i<eduList.length-1;i++){   
       
            var Values=eduList[i].split("|");
            {  
         
                
                var row = $("<tr />")
                //alert("row--?"+row);
                
                var x=Values[4]; 
                $("#empCategorizationResults").append(row);
                //row.append($("<td>" + Values[0] + "</td>")); //groupingId
                // row.append($("<td>" + Values[1] + "</td>"));//usrOrgid
                row.append($("<td><a href='getUserGroping.action?addOrUpdate="+addOrUpdate+"&groupingId="+Values[0]+"'>" + Values[5] + "</a></td>"));
                // row.append($('<td><a href="#" class="csrTerminateOverlay_popup_open" onclick="csrTerminateOverlay();csrStatusValue('+Values[1]+','+Values[0]+')">'  + Values[3] + "</td>"));

                row.append($('<td><a href="#" class="categorizationOverlay_popup_open" onclick="categorizationOverlay(); getEmpCategoryNames('+ x +')">' + Values[2] + "</td>"));
                row.append($("<td>" + Values[7] + "</td>"));
                row.append($("<td>" + Values[9] + "</td>"));
                row.append($("<td>" + Values[6] + "</td>"));
                row.append($("<td>" + Values[8] + "</td>"));
                row.append($("<td><a href='#' onclick=empCategoryTermination(" + Values[0] + ")><img src='../../includes/images/deleteImage.png' height=25 width=25 ></td>"));
            //row.append($("<td>" + Values[4] + "</td>"));
            //row.append($("<td>" + Values[7] + "</td>"));
            //onclick="saveContactDetails(' + Values[0] +');" > '
            }
        }
    }
    else {
        var row = $("<tr />")
        $("#empCategorizationResults").append(row);
        row.append($('<td colspan="7"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
    }
    $('#empCategorizationResults').tablePaginate({
        navigateType:'navigator'
    },recordPage);
    pager.init(); 
  
}
//function empCategoryTermination(groupingId){
//    
//    // alert(groupingId);
//    if (confirm("Do you want delete Employee in this category") == true) {
//    var url='users/general/empCategoryTermination.action?groupingId='+groupingId;
//    var req=initRequest(url);
//    //alert(url);
//    req.onreadystatechange = function() {
//        if (req.readyState == 4) {
//            
//            if (req.status == 200) {
//                populateEmpCategoriesTable(req.responseText);
//            } 
//            else
//            {
//                alert("Error occured");
//            }
//        }
//    };
//    req.open("GET",url,"true");
//    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//    req.send(null);
//    }
//}
function empCategoryTermination(groupingId){
    initSessionTimer();
    swal({
    
        title: "Do you want delete Employee in this category",
  
        //text: "Tranfering csr",
        textSize:"170px",
        type: "warning",
  
        showCancelButton: true,
        confirmButtonColor: "#3498db",
  
        //cancelButtonColor: "#56a5ec",
        cancelButtonText: "No",
        confirmButtonText: "Yes",
        closeOnConfirm: false,
        closeOnCancel: false
 
    },
    function(isConfirm){
        if (isConfirm) {
            var url='users/general/empCategoryTermination.action?groupingId='+groupingId;
            var req=initRequest(url);
            //alert(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
            
                    if (req.status == 200) {
                        //populateEmpCategoriesTable(req.responseText);
                        getEmpCategories();
                        swal("Deleted!", "Deleted Successfully....", "success");
                    } 
                    else
                    {
                        swal("Sorry Not Deleted", "Deletion not done ", "error");
                    }
                }
            };
            req.open("GET",url,"true");
            req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            req.send(null);
   
   
    
        } else {
     
            swal("Cancelled", "Deleted cancelled ", "error");
 
      
        }
    });
}

function categorizationOverlay()
{
    // alert("overlay");
    var specialBox = document.getElementById('categorizationBox');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#categorizationOverlay_popup').popup(      
        );  
}
function getEmpCategoryNames(categoryList){
    //alert(categoryList);
    if(categoryList==""){
        categoryList=null;
    }
    //alert(categoryList);

    var url='users/general/getEmpCategoryNames.action?categoryNamesList='+categoryList;//+'&empName='+empName+'&status='+empStatus+'&categoryType='+empCategoryType;
    var req=initRequest(url);
    //alert(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            
            if (req.status == 200) {
                // alert(req.responseText);
                populateOverLayCategoriesTable(req.responseText);
            } 
            else
            {
                alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateOverLayCategoriesTable(response){
    // alert(response);  
    var dashBoardReq=response.split("^");
    var table = document.getElementById("empCategorizationTableOverlay");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){

        var total=0;
        for(var i=0;i<dashBoardReq.length-1;i++){   
            //alert(techReviewList[0])
            var Values=dashBoardReq[i].split("|");
            {  
                var row = $("<tr />")
                $("#empCategorizationTableOverlay").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                row.append($("<td>" + Values[1] + "</td>"));
            }
        }
       
    }
    else{
        var row = $("<tr />")
        $("#empCategorizationTableOverlay").append(row);
        row.append($('<td colspan="1"><font style="color: red;font-size: 15px;">No Assigned Groups</font></td>'))
    }

}
function fileFormatValidation(){
    var file=$("#taskAttachment").val();
    alert(file);
    if(file!='')
    {
        var allowed_extensions = new Array("jpg","gif","png");
        var file_extension = file.split('.').pop(); // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.

        for(var i = 0; i < allowed_extensions.length; i++)
        {
            if(allowed_extensions[i]==file_extension)
            {
                $("#InsertContactInfo").html("");
                return true; // valid file extension
            }
        }
        $("#InsertContactInfo").html(" <b><font color='red'>The file uploaded is invalid type</font></b>");

        return false;
    }
    return true;
}
   
function middlename(event)
{
    var inputValue = (event.which) ? event.which : event.keyCode
    if((inputValue > 32 && inputValue < 65 || inputValue > 90 && inputValue < 97 || inputValue > 122 && inputValue < 128) && (inputValue != 32))
    {
  
        //$("#ContactMname").css("border", "1px solid red");
        $('#mnameError').css("color","red");
        $("#mnameError").html("Must be valid Middle Name");
        $("#mnameError").show().delay(2000).fadeOut()
        return false;
    }
    else
    {
        return true;    
    }
}
function invoceYear(evt){
    
    var  minRate=document.getElementById("invoiceYear").value;
    //alert(minRate)
    var rate=(minRate.toString()).length;
    //alert(rate)
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if ( iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57) )
    {
        //alert("enter only numbers ")
        if(rate!=4){
            $("#invoiceValidation").html(" <b><font color='red'>enter only numbers</font></b>");  
            $("#invoiceValidation").show().delay(4000).fadeOut();
        }
        if(iKeyCode == 8)
        {
            return true;       
        }
        else
        {
            return false;
        }
    }
    else if( rate >= 4)
    {
            
        if(iKeyCode == 8)    
        {
            return true; 
        }
        else
        {
            return false;       
        }
            
           
    }
   
        
    else 
    {
        //alert("not allow")            
        $("#invoiceValidation").html("");
        return true;
    }
    
};
function invoceOverlayYear(evt){
    
    var  minRate=document.getElementById("invoiceYearOver").value;
    //alert(minRate)
    var rate=(minRate.toString()).length;
    //alert(rate)
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if ( iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57) )
    {
        //alert("enter only numbers ")
        if(rate!=4){
            $("invoiceGenerarionMessage").html(" <b><font color='red'>enter only numbers</font></b>");  
            $("invoiceGenerarionMessage").show().delay(4000).fadeOut();
        }
        if(iKeyCode == 8)
        {
            return true;       
        }
        else
        {
            return false;
        }
    }
    else if( rate >= 4)
    {
            
        if(iKeyCode == 8)    
        {
            return true; 
        }
        else
        {
            return false;       
        }
            
           
    }
   
        
    else 
    {
        //alert("not allow")            
        $("invoiceGenerarionMessage").html("");
        $("#invoiceYearOver").css("border","1px solid #CCCCCC");
        return true;
    }
    
};

function checkExtention(){
    //alert("hi")
    var FileUploadPath = document.getElementById('file').value;
    //alert(FileUploadPath);
    var res;
    if (FileUploadPath == '') {
       
        $("fileuplaoderror").html("<font color='red'>Please upload a file</font>");
        res= false;
    }
    else if(FileUploadPath.length>34){
        $("fileuplaoderror").html("<font color='red'>File Name Length Should be less than 30 Characters</font>");
        res= false;
    }
    else {
        var Extension = FileUploadPath.substring(
            FileUploadPath.lastIndexOf('.') + 1).toLowerCase();

        if (Extension == "xls") {
            $("fileuplaoderror").html(" ");
            //    if(contentAddings()){
            res= true;
        //            }else
        //            {
        //                res= false;
        //            }
            
        } 
        else {
            $("fileuplaoderror").html("<font color='red'> Allows only Xls type.</font>");
            res= false;
        }
    }
    //    alert(res)
    //    if(res == true) 
    //      var file = FileUploadPath.split(".");
    //    checkFileName(file[0]);
    //alert(res);
    return res;
}

function checkFileName()
{
    var FileUploadPath = document.getElementById('file').value;
    // var rVal;
    if(true){
        //alert(FileUploadPath)
        var url='users/general/checkFileNameExistOrNot.action?fileUploadPath='+FileUploadPath;
        //alert(url)
        var req=initRequest(url);
        //alert(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                
                if (req.status == 200) {
                    if(req.responseText=="Exist"){
                        $("fileuplaoderror").html(" <b><font color='red'>Name Already Exists</font></b>");  
                        document.getElementById('file').value="";
                        document.getElementById('rValue').value="false";
                    }
                    else {
                        $("fileuplaoderror").html("");
                        document.getElementById('rValue').value="true";
                    //              alert(req.responseText);
                       
                    // document.getElementById('myform').submit();
                    }
                } 
                else
                {
                    alert("Error occured");
                    document.getElementById('rValue').value="false";
                }
            }
            
        };
        // alert(returnValue)
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    //alert()
    return true;
}

function downloadExcel(){
    window.location = '../general/downloadExcel.action';
    
}
var myCalendar;
function doOnLoadExcel() {
    myCalendar = new dhtmlXCalendarObject(["created_Date"]);
    myCalendar.setSkin('omega');
    myCalendar.setDateFormat("%m-%d-%Y");
    myCalendar.hideTime();
    var date = new Date(); 
    var dd= date.getDate();
    var mm=date.getMonth()+1;
    var yyyy=date.getFullYear();
    var newDate = mm+"-"+dd+"-"+yyyy;
// document.getElementById("created_Date").value=newDate;
}
function enterDateRepository()
{
    document.getElementById('created_Date').value = "";
    alert("Please select from the Calender !");
    return false;
}

function contentAddings(){
    var res ;
    swal({
        title:"Are you sure to Add Accounts...?",
        textSize:"170px",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3498db",
        cancelButtonText: "No",
        confirmButtonText: "Yes",
        closeOnConfirm: true,
        closeOnCancel: true
    },
    function(isConfirm){
        if (isConfirm) {
            return true;
        } else {
            return false;
        }
    });
        
       
}
function removeErrMsg(){
    $("#InsertContactInfo").html(" ");
    $("#ContactAEmail").css("border", "");
}
function alternateMailValidation(){
    var email=document.getElementById("ContactEmail2").value;
    //alert(email);
    repattern= /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    //    repa= /[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}/igm;
    //^[a-zA-Z0-9\.'\-\+\_\%\$]+$/;
    if(email==''){
        //  alert("true");
        return true;  
    }
    else if(!repattern.test(email))
    {
        //$("#addContactError").html("");
        $("#InsertContactInfo").html(" <b><font color='red'>Please enter valid e-mail.</font></b>.");
        $("#ContactEmail2").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#InsertContactInfo").html(" ");
        $("#ContactEmail2").css("border", "1px solid green");
        return true;  
    }
}
function showAttachments()
{
    //  alert("Attachments ");
    //alert(document.getElementById("accountsearchid").value);
    var orgId= document.getElementById("viewAccountID").value;
    //alert(orgId);
    var url='../acc/getAttachments.action?viewAccountID='+orgId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingAttachments').style.display = 'block';
        if (req.readyState == 4) {
            
            if (req.status == 200) {
                $('#loadingAttachments').hide();
                //   alert("req.responseText"+req.responseText)
                populateVendorFormsTable(req.responseText);
                
            } 
            else
            {
                alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}




function populateVendorFormsTable(response){
    // alert("populateVendorFormsTable");  
    $(".page_option").css('display', 'block');
    var orgId= document.getElementById("viewAccountID").value;
    var vendorDocs= document.getElementById("vendorDocs").value;
    var eduList=response.split("^");
   
    var table = document.getElementById("vendorFormPageNav");
    
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        for(var i=0;i<eduList.length-1;i++){   
       
            var Values=eduList[i].split("|");
            {  
         
         
                var row = $("<tr />")
                $("#vendorFormPageNav").append(row);
                
                // row.append($('<td><a href="../acc/accountcontactedit.action?accountType='+ accountType +'&account_name='+ accName +'&accountSearchID='+ OrgID +'&contactId='+Values[0]+'" > '+ Values[1] + "</td>"));
                //                row.append($('<td><a class="editAttachment_popup_open" href="#"  onclick="vendorFormOverlayEdit();setAttachmentValues(\''+ Values[0] +'\',\''+Values[2]+'\');">'+Values[0]+"</td>"));
                //               row.append($("<td>" + Values[0] + "</td>"));
                row.append($('<td><a class="editAttachment_popup_open" href="#"  onclick="vendorFormOverlayEdit();setAttachmentValues(\''+ Values[5] +'\',\''+Values[2]+'\',\''+Values[4]+'\',\''+ Values[6] +'\',\''+ Values[7] +'\',\''+ Values[0] +'\');">'+Values[6]+"</td>"));
                if(Values[1]=='W9F'){
                    row.append($("<td>W-9 Form</td>"));     
                }
                else if(Values[1]=='MIN'){
                    row.append($("<td>Minority Certification</td>"));     
                }
                else if(Values[1]=='LIA'){
                    row.append($("<td>Liability Insurance Certificate</td>"));     
                }
                else {
                    row.append($("<td>MSA Document</td>"));     
                }
                
                row.append($("<td>" + Values[2] + "</td>"));
                row.append($("<td>" + Values[4] + "</td>"));
                row.append($("<td>" + Values[3] + "</td>"));
                // alert("Values[5]"+Values[1]);
                row.append($("<td><center><figcaption><button type='button' value="+ Values[5] +" onclick=doVendorFormAttachmentDownload("+ Values[5] +")><i class='fa fa-download ' ></i></button></figcaption></center></td>"));
            //  row.append($('<td><a href="#" class="contactLoginOverlay_popup_open" onclick="contactLoginOverlay('+Values[0]+','+ OrgID +',\'' + Values[3] + '\')">'  + "<img src='./../includes/images/user-login.png' height='20' width='30' >" + "</td>"));
            //document.getElementById("attachment_id_edit").value=Values[5];    
            }
        }
    }
    else {
        var row = $("<tr />")
        $("#vendorFormPageNav").append(row);
        row.append($('<td colspan="6"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
    }
    $('#vendorFormPageNav').tablePaginate({
        navigateType:'navigator'
    },recordPage);
    acPager.init(); 

}
function doVendorFormAttachmentDownload(acc_attachment_id)
{
    $("#resume").html("");
    var orgId= document.getElementById("viewAccountID").value;
    window.location = '../acc/doVendorFormAttachmentDownload.action?acc_attachment_id='+acc_attachment_id+'&accountSearchID='+orgId;
}
function setAttachmentValues(attachment_id,validity,date,title,comments,name){
    //   alert(attachment_id);
    //  alert(validity);
    
    document.getElementById("attachment_id_edit").value=attachment_id;
    document.getElementById("editValidity").value=date;
    document.getElementById("editTitle").value=title;
    // alert(comments) 
    if(comments!="null")
    {
        document.getElementById("editattachmentComments").value=comments;      
    }
    
    //$("#edValidity").val(validity);
   
    //document.getElementById("edValidity").value=validity;
    document.getElementById("file").value=attachment_id;

}
function setEditAttachmentValues(response){
    var Values=response.split("|");
    document.getElementById("file").value=Values;
    document.getElementById("editValidity").value=Values[1];
}
function enterDateRepositoryAttachment()
{
    document.getElementById('validFrom').value = "";
    document.getElementById('validTo').value = "";
    //    document.getElementById('validFrom1').value = "";
    //    document.getElementById('validTo1').value = "";
    return false;
}
function getVendorFormDetails(){
    initSessionTimer();
    //alert("attachments");
    var vendorDocs=$("#vendorDocs").val();
    var validFrom=$("#validFrom").val();
    var validTo=$("#validTo").val();
    var attachmentTitle=$("#attachmentTitle").val();
    if(validFrom!="")
    {
        if (validTo=="")
        {
            // alert("Please select End date")
            $("#formValidationMsg").html("<font color='red'>Please Select End Date</font>");
            $("#formValidationMsg").show().delay(4000).fadeOut();
            // $("#validTo").css('border','1px solid red');
            return false;
        }
    }
        
    if(validTo!="")
    {
        if (validFrom=="")
        {
            $("#formValidationMsg").html("<font color='red'>Please Select Start Date</font>");
            $("#formValidationMsg").show().delay(4000).fadeOut();
            // $("#validFrom").css('border','1px solid red');
            // alert("Please select Start date")
             
            return false;
        }
    }
    //    if(validFrom!= '' && validTo!= '' )
    //    {
    //        var splitReqStartDate = validFrom.split('-');
    //        var reqAddStartDate = new Date(splitReqStartDate[2], splitReqStartDate[0]-1 , splitReqStartDate[1]); //Y M D 
    //        var splitReqEndDate = validTo.split('-');
    //        var reqAddtargetDate = new Date(splitReqEndDate[2], splitReqEndDate[0]-1, splitReqEndDate[1]); //Y M D
    //        var reqStartDate = Date.parse(reqAddStartDate);
    //        var reqTargetDate= Date.parse(reqAddtargetDate);
    //        var  difference=(reqTargetDate - reqStartDate) / (86400000 * 7);
    //        if(difference<=0)
    //        {
    //             
    //       
    //            $(formValidationMsg).html(" <b><font color='red'>Start date Must be less than End date</font></b>");
    //
    //            $(formValidationMsg).show().delay(4000).fadeOut();
    //
    //            return false;
    //        }
    //    
    //    }
    
    var orgId= document.getElementById("viewAccountID").value;
    var url='../acc/getAttachmentsSearchDetails.action?viewAccountID='+orgId +'&vendorDocs='+vendorDocs +'&validFrom='+validFrom +'&validTo='+validTo+'&attachmentTitle='+attachmentTitle ;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                populateVendorFormsTable(req.responseText);
               
            //$("securityinfo").html(" <b><font color='green'>Confidential information Saved Successfully</font></b>");
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


function addVendorFormDetails(){
    //alert("attachments");
    var validity=$("#validity").val();
    var file=$("#file").val();
    var title=$("#attachmentTitle").val();
    // alert("file"+file);
    // alert(formAttachment);
    if(title=="")
    {
        $("#formValidationMsg1").html("<font color='red'>Enter The Title</font>");
        //$("#attachmentTitle").css('border','1px solid red');
        return false; 
    }
    var validTo1=$("#validTo1").val();
    if(file=="")
    {
        $("#formValidationMsg1").html("<font color='red'>Please Upload Form</font>");
        // $("#file").css('border','1px solid red');
        return false; 
    }
    if(validity=="")
    {
        $("#formValidationMsg1").html("<font color='red'>Please select Date</font>");
        // $("#validity").css('border','1px solid red');
        return false; 
    }
    
  
     
  
}
function accAttachmentValidation(){
    //alert("hello")
    var file=$('#file').val();
    //alert(file)
    var p=file.lastIndexOf(".");
    var e=file.substring(p+1, file.length);
    //alert(e)
    var rVal=false;
    if(e=="pdf" || e=="doc" || e=="docx"){
        rVal=true;
    }else{
        // alert("error")
        $("#formValidationMsg1").html(" <b><font color='red'>only pdf or doc files allowed!</font></b>");
        document.getElementById("file").value="";
    //$("#formValidationMsg1").show().delay(5000).fadeOut();
    }
    return rVal;
}
function vendorFormOverlay() {
    initSessionTimer();
    document.getElementById("formValidationMsg1").innerHTML="";
    document.getElementById("attachmentTitle").value="";
    document.getElementById("vendorDocs").value="W9F";
    document.getElementById("file").value=""; 
    document.getElementById("validity").value=""; 
    document.getElementById("attachmentComments").value=""; 
    document.getElementById("charRemainAttach").innerHTML="";
    var specialBox = document.getElementById("attachmentBox");

    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#addAttachment_popup').popup(      
        );
}


function vendorFormOverlayEdit() {
    initSessionTimer();
    document.getElementById("formValidationMsg2").innerHTML="";
    document.getElementById("charRemainEditAttach").innerHTML="";
    var specialBox = document.getElementById("editAttachmentBox");

    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#editAttachment_popup').popup(      
        );
}

function attachmentsValidFromToOverlay(){
    myCalendar = new dhtmlXCalendarObject(["validity"]);
    myCalendar.setSkin('omega');
    myCalendar.setDateFormat("%m-%d-%Y ");
    myCalendar.hideTime();
    var today = new Date();
    var maxFurureDate = new Date(today.getFullYear()+5,today.getMonth(),today.getDate());
    myCalendar.setSensitiveRange(today,maxFurureDate);
//  document.getElementById("validity").value=overlayDate;
                
                
                
                
}
function editAttachmentvalidity()
{
    // alert("hi");
    myCalendar1 = new dhtmlXCalendarObject(["editValidity"]);
    myCalendar1.setSkin('omega');
    myCalendar1.setDateFormat("%m-%d-%Y");
    myCalendar1.hideTime();
    var today = new Date();
    var maxFurureDate = new Date(today.getFullYear()+5,today.getMonth(),today.getDate());
    myCalendar1.setSensitiveRange(today,maxFurureDate);
//  document.getElementById("editValidity").value=overlayDate;
}
 
function checkAttachmentEditValidations(){
    var editValidity= document.getElementById("editValidity").value;
    var file= document.getElementById("file").value;
    if(editValidity==""){
        $("#formValidationMsg2").html("<font color='red'>Please select Date</font>");
        $("#editValidity").css('border','1px solid red');
        return false; 
    }
    if(file==""){
        $("#formValidationMsg2").html("<font color='red'>Please select Attachment</font>");
        $("#file").css('border','1px solid red');
        return false; 
    }
        
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

function checkAttachmentValidation()
{
    
    if(document.getElementById("editTitle").value=="" || document.getElementById("editTitle").value==" ")
    {
            
       
        document.getElementById("formValidationMsg2").innerHTML="<font color='red'>Enter Title</font>";    
        return false;
    }
    if(document.getElementById("editValidity").value=="" || document.getElementById("editValidity").value==" ")
    {
        document.getElementById("formValidationMsg2").innerHTML="<font color='red'>Enter validity</font>";    
        return false;
    }    
}

function CheckRemainingAttachCharacters(id){
    $(id).keyup(function(){
        el = $(this);
        e2 = $(this);
        var count = el.val().length + ((el.val().split('\n').length)*5);
        if(count >= 250){
            e2.val( e2.val().substr(0, 500-((el.val().split('\n').length)*5)) );
        } else {
            $("#charRemainAttach").text(250-count+' Characters remaining . ');
        }
        if(count==250)
        {
            $("#charRemainAttach").text(' Cannot enter  more than 1000 Characters .');    
        }
        
    })
    return false;
};

function CheckRemainingEditAttachCharacters(id){
    $(id).keyup(function(){
        el = $(this);
        e2 = $(this);
        var count = el.val().length + ((el.val().split('\n').length)*5);
        if(count >= 250){
            e2.val( e2.val().substr(0, 500-((el.val().split('\n').length)*5)) );
        } else {
            $("#charRemainEditAttach").text(250-count+' Characters remaining . ');
        }
        if(count==250)
        {
            $("#charRemainEditAttach").text(' Cannot enter  more than 1000 Characters .');    
        }
        
    })
    return false;
};