
$(document).ready(function() {

    var specialBox = document.getElementById('eduBoxOverlay');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#edu_popup').popup(      
        );
    
});

$(document).ready(function() {

    var specialBox = document.getElementById('eduEditBoxOverlay');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#eduEdit_popup').popup(      
        );
    
});



//created by rk//
function showAddressDetails(userid){
    //alert("in Ajax call ");
    var url='../general/addressDetails.action?userid='+userid;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) 
            {
                //alert("in Ajax call "+req.responseText);
                var flagData=req.responseText.split('#');
                for(var i=1;i<flagData.length;i++)
                {
                    var actualData=flagData[i].split('&');
                    if(flagData[0]=="pc")
                    {
                        if(i==1){
                            document.getElementById("Address").value=actualData[0];
                            document.getElementById("Address2").value=actualData[1];
                            document.getElementById("City").value=actualData[2];
                            document.getElementById("State").value=actualData[3];
                            document.getElementById("Zip").value=actualData[4];
                            document.getElementById("Country").value=actualData[5];
                            document.getElementById("Phone").value=actualData[6];
                            
                            document.getElementById("CAddress").value=actualData[0];
                            document.getElementById("CAddress2").value=actualData[1];
                            document.getElementById("CCity").value=actualData[2];
                            document.getElementById("CState").value=actualData[3];
                            document.getElementById("CZip").value=actualData[4];
                            document.getElementById("CCountry").value=actualData[5];
                            document.getElementById("CPhone").value=actualData[6];
                        }
                        $("#checkAddress").attr('checked', true);
                        var cb1 = $('#checkAddress').is(':checked');        
                        $('#CAddress, #CAddress2, #CCity, #CState, #CZip, #CCountry, #CPhone').prop('disabled',cb1);
                    }
                    else
                    {
                        if(i==1){
                            document.getElementById("Address").value=actualData[0];
                            document.getElementById("Address2").value=actualData[1];
                            document.getElementById("City").value=actualData[2];
                            document.getElementById("State").value=actualData[3];
                            document.getElementById("Zip").value=actualData[4];
                            document.getElementById("Country").value=actualData[5];
                            document.getElementById("Phone").value=actualData[6];
                        }
                        if(i==2)
                        {
                            document.getElementById("CAddress").value=actualData[0];
                            document.getElementById("CAddress2").value=actualData[1];
                            document.getElementById("CCity").value=actualData[2];
                            document.getElementById("CState").value=actualData[3];
                            document.getElementById("CZip").value=actualData[4];
                            document.getElementById("CCountry").value=actualData[5];
                            document.getElementById("CPhone").value=actualData[6]; 
                        }
                    }
                }
                var headerBox = document.getElementById('spanId');
                if(headerBox.style.display == "none"){       
                    headerBox.style.display = "block";         
                }
                document.getElementById('spanId').innerHTML='';
                document.getElementById('spanId').innerHTML="Contact Information"+ document.getElementById('spanId').innerHTML;    
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




//contact information unpdate is started 
function permanentAddressUpdate(userId) //javascript by ramakrishna to update permanent address using ajax
{
    var url='';
    var address=$("#Address").val();
    var address2=$("#Address2").val();
    var City=$("#City").val();
    var State=$("#State").val();
    var Zip=$("#Zip").val();
    var Country=$("#Country").val();
    var Phone=$("#Phone").val();
    var address_flag='p';
    if($('#checkAddress').attr('checked')) 
    {
        $("#CAddress").val($("#Address").val());
        $("#CAddress2").val($("#Address2").val());
        $("#CCity").val($("#City").val());
        $("#CState").val($("#State").val());
        $("#CZip").val($("#Zip").val());
        $("#CCountry").val($("#Country").val());
        $("#CPhone").val($("#Phone").val());
        address_flag='pc';
        
    }
    re = /^[A-Za-z ]+$/;
    if(!re.test(State)||!re.test(Country)||address.length<3||City.length<2||State.length<2||Country.length<2)
    {
        $("j1").html("<font color='red'>Can't update invalid fields are there</font>");
        $("#spanUpdatep").css('visibility', 'hidden');
        $("#spanUpdatec").css('visibility', 'hidden');
        return false;
    }
    url='../general/updateAddressDetails.action?userid='+userId+'&address='+address+'&address2='+address2+'&city='+City+'&state='+State+'&zip='+Zip+'&country='+Country+'&phone='+Phone+'&address_flag='+address_flag;
    var req=initRequest(url);                                              
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
             
                //alert("PerminantAddress succesfully Updated")
                $("#updateResultp").css('visibility', 'visible');
                if($('#checkAddress').attr('checked')){
                    $("#updateResultc").css('visibility', 'visible');
                    $("#spanUpdatec").css('visibility', 'hidden');
                }
                else
                {
                    $("#updateResultc").css('visibility', 'hidden');    
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



function currentAddressUpdate(userId)//javascript by ramakrishna to update current address using ajax
{
    //alert("js called")
    var address=$("#CAddress").val();
    var address2=$("#CAddress2").val();
    var City=$("#CCity").val();
    var State=$("#CState").val();
    var Zip=$("#CZip").val();
    var Country=$("#CCountry").val();
    var Phone=$("#CPhone").val();
    var address_flag='c';
    var url='';
    //alert("js called2")
    re = /^[A-Za-z]+$/;
    if(!re.test(State)||!re.test(Country)||address.length<3||City.length<2||State.length<2||Country.length<2)
    {
        $("j2").html(" <font color='red'>Can't update invalid fields are there</font>");
        //$("#spanUpdatep").css('visibility', 'hidden');
        $("#spanUpdatec").css('visibility', 'hidden');
        return false;
    }
    
    url='../general/updateCAddressDetails.action?userid='+userId+'&address='+address+'&address2='+address2+'&city='+City+'&state='+State+'&zip='+Zip+'&country='+Country+'&phone='+Phone+'&address_flag='+address_flag;
    
    //alert(address+" "+address2+" "+City+" "+State+" "+Zip+" "+Country+" "+Phone)
    var req=initRequest(url);                                              
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
             
                //alert("CurrentAddress succesfully Updated")
                $("#updateResultc").css('visibility', 'visible');
                $("#updateResultp").css('visibility', 'hidden');
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



// validations for text boxes
function paddressValidation(){
    
    //alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
    var Address=document.getElementById("Address").value;
    re = /^[A-Za-z]+$/;
    if(Address.length<2)
    {
        $("#spanUpdatep").css('visibility', 'hidden');
        $("j1").html(" <font color='red'>Must be valid Address</font>");
        $("#Address").css("border", "1px solid red");
    }
    else
    {
        $("#spanUpdatep").css('visibility', 'visible');
        $("j1").html(" ");
        $("#Address").css("border", "1px solid green");
    }
    
}
function pcityValidation(){
    
    var City=document.getElementById("City").value;
    if(City.length<3)
    {
        $("#spanUpdatep").css('visibility', 'hidden');
        $("j1").html(" <font color='red'>Must be valid City</font>");
        $("#City").css("border", "1px solid red");
    }
    else
    {
        $("#spanUpdatep").css('visibility', 'visible');
        $("j1").html(" ");
        $("#City").css("border", "1px solid green");
    }
    
}
function pStateValidation(){
    
    //alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
    var State=document.getElementById("State").value;
    re = /^[A-Za-z ]+$/;
    if(!re.test(State)||City.length<3)
    {
        $("#spanUpdatep").css('visibility', 'hidden');
        $("j1").html(" <font color='red'>Must be valid State</font>");
        $("#State").css("border", "1px solid red");
    }
    else
    {
        $("#spanUpdatep").css('visibility', 'visible');
        $("j1").html(" ");
        $("#State").css("border", "1px solid green");
    }
}

function pZipValidation(){
    
    //alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
    var Zip=document.getElementById("Zip").value;
    if(Zip.length!=5)
    {
        $("#spanUpdatep").css('visibility', 'hidden');
        $("j1").html(" <font color='red'>Must be valid Zip</font>");
        $("#Zip").css("border", "1px solid red");
    }
    else
    {
        $("#spanUpdatep").css('visibility', 'visible');
        $("j1").html(" ");
        $("#Zip").css("border", "1px solid green");
    }
}

function pCountryValidation(){
    
    //alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
    var Country=document.getElementById("Country").value;
    re = /^[A-Za-z ]+$/;
    if(!re.test(Country)||Country.length<3)
    {
        $("#spanUpdatep").css('visibility', 'hidden');
        $("j1").html(" <font color='red'>Must be valid Country</font>");
        $("#Country").css("border", "1px solid red");
    }
    else
    {
        $("#spanUpdatep").css('visibility', 'visible');
        $("j1").html(" ");
        $("#Country").css("border", "1px solid green");
    }
}
function pPhoneValidation(){
    
    //alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
    var Phone=document.getElementById("Phone").value;
    if(Phone.length!=14)
    {
        $("#spanUpdatep").css('visibility', 'hidden');
        $("j1").html(" <font color='red'>Must be valid Phone</font>");
        $("#Phone").css("border", "1px solid red");
    }
    else
    {
        $("#spanUpdatep").css('visibility', 'visible');
        $("j1").html("");
        $("#Phone").css("border", "1px solid green");
    }
}

// validations for current address 



function pCAddressValidation(){
    
    //alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
    var CAddress=document.getElementById("CAddress").value;
    if(CAddress.length<2)
    {
        $("#spanUpdatec").css('visibility', 'hidden');
        $("j2").html(" <font color='red'>Must be valid Address</font>");
        $("#CAddress").css("border", "1px solid red");
    }
    else
    {
        $("#spanUpdatec").css('visibility', 'visible');
        $("j2").html(" ");
        $("#CAddress").css("border", "1px solid green");
    }
    
}
function pCCityValidation(){
    
    var CCity=document.getElementById("CCity").value;
    if(CCity.length<3)
    {
        $("#spanUpdatec").css('visibility', 'hidden');
        $("j2").html(" <font color='red'>Must be valid City</font>");
        $("#CCity").css("border", "1px solid red");
    }
    else
    {
        $("#spanUpdatec").css('visibility', 'visible');
        $("j2").html(" ");
        $("#CCity").css("border", "1px solid green");
    }
    
}
function pCStateValidation(){
    
    //alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
    var State=document.getElementById("CState").value;
    re = /^[A-Za-z ]+$/;
    if(!re.test(State)||City.length<3)
    {
        $("#spanUpdatec").css('visibility', 'hidden');
        $("j2").html("<font color='red'>Must be valid State</font>");
        $("#CState").css("border", "1px solid red");
    }
    else
    {
        $("#spanUpdatec").css('visibility', 'visible');
        $("j2").html(" ");
        $("#CState").css("border", "1px solid green");
    }
}

function pCZipValidation(){
    
    //alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
    var Zip=document.getElementById("CZip").value;
    
    if(Zip.length!=5)
    {
        $("#spanUpdatec").css('visibility', 'hidden');
        $("j2").html(" <font color='red'>Must be valid Zip</font>");
        $("#CZip").css("border", "1px solid red");
    }
    else
    {
        $("#spanUpdatec").css('visibility', 'visible');
        $("j2").html(" ");
        $("#CZip").css("border", "1px solid green");
    }
}

function pCCountryValidation(){
    
    //alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
    var Country=document.getElementById("CCountry").value;
    re = /^[A-Za-z ]+$/;
    if(!re.test(Country)||Country.length<3)
    {
        $("#spanUpdatec").css('visibility', 'hidden');
        $("j2").html(" <font color='red'>Must be valid Country</font>");
        $("#CCountry").css("border", "1px solid red");
    }
    else
    {
        $("#spanUpdatec").css('visibility', 'visible');
        $("j2").html(" ");
        $("#CCountry").css("border", "1px solid green");
    }
}
function pCPhoneValidation(){
    
    //alert("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
    var Phone=document.getElementById("CPhone").value;
    if(Phone.length!=14)
    {
        $("#spanUpdatec").css('visibility', 'hidden');
        $("j2").html(" <font color='red'>must be valid Phone</font>");
        $("#CPhone").css("border", "1px solid red");
    }
    else
    {
        $("#spanUpdatec").css('visibility', 'visible');
        $("j2").html("");
        $("#CPhone").css("border", "1px solid green");
    }
}

$(document).ready(function(){
    $(".showUpdatep :input").each(function () {
        $(this).change(function () {
            //alert("alert called")
            $("#spanUpdatep").css('visibility', 'visible');
        //$('#checkAddress').click();
        //$('#checkAddress').prop('checked', false);
        });
    });
        
    $(".showUpdatec :input").each(function () {
        $(this).change(function () {
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
            $("#CAddress").val($("#Address").val());
            $("#CAddress2").val($("#Address2").val());
            $("#CCity").val($("#City").val());
            $("#CState").val($("#State").val());
            $("#CZip").val($("#Zip").val());
            $("#CCountry").val($("#Country").val());
            $("#CPhone").val($("#Phone").val());
        }
        else
        {
            $("#spanUpdatec").css('visibility', 'visible');
        }
    });
   
});
//rk content end //           
var editCalendar,myCalendar,myCalendar1,eduAddStartyear, eduAddEndyear, eduEditStartYear, eduEditEndYear;
function doOnLoad() {
                
    editCalendar = new dhtmlXCalendarObject(["dob"]);
    editCalendar.setSkin('omega');
    editCalendar.setDateFormat("%m/%d/%Y");
    editCalendar.hideTime();
    // alert(editCalendar.getDate(true));
                
    editCalendar.setInsensitiveRange(editCalendar.getDate(true), null);
    
    // alert("hii");docdatepickerfrom","docdatepicker
    myCalendar = new dhtmlXCalendarObject(["year_start","year_end"]);
    // alert("hii1");
    myCalendar.setSkin('omega');
    // alert("hii2");
    //myCalendar.setDateFormat("%m/%d/%Y %H:%i");
    myCalendar.setDateFormat("%Y/%m/%d");
    // alert("hii3");
    //alert("myCalendar")
    myCalendar1 = new dhtmlXCalendarObject(["edu_start_year","edu_end_year"]);
    // alert("hii1");
    myCalendar1.setSkin('omega');
    // alert("hii2");
    myCalendar1.setDateFormat("%Y/%m/%d");
    /*start, add by aklakh for start and end year validation on add and edit qualification details */
    eduAddStartyear = new dhtmlXCalendarObject(["year_start","year_end"]);
    eduAddStartyear.setSkin('omega');
    eduAddStartyear.setDateFormat("%m/%d/%Y");
    eduAddStartyear.hideTime();
    
    eduEditStartYear = new dhtmlXCalendarObject(["edu_start_year","edu_end_year"]);
    eduEditStartYear.setSkin('omega');
    eduEditStartYear.setDateFormat("%m/%d/%Y");
    eduEditStartYear.hideTime();
/*end, add by aklakh for start and end year validation on add and edit qualification details */         
    
}
                
function enterDateRepository()
{
    // alert(document.documentForm.docdatepickerfrom.value);
    // document.documentForm.docdatepickerfrom.value="";
    document.getElementById('dob').value = "";
    
    //document.getElementById('end_date').value = "";
    //alert("Please select from the Calender !");
    return false;
}
/*Reporting Infpormation Start*/
function initReport(userId)
{
   
    //alert("yes initReport called bye "+userId)
    var url='../general/reportAction.action?userid='+userId;
    
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) 
            {
                //alert(req.responseText)
                var nameId=req.responseText.split('#');
                //alert(nameId.length)
                document.getElementById("primaryReport").value=nameId[0];
                document.getElementById("reportsToId").value=nameId[1];
            
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
            
    
    
function firstNameValidation(){
    
    
    var first_name=document.getElementById("first_name").value;
    re = /^[A-Za-z]+$/;
    if(!re.test(first_name)||first_name.length<3)
    {
        $("j").html("<font color='red'>Must be valid First Name</font>.");
        $("#first_name").css("border", "1px solid red");
    }
    else
    {
        $("j").html("");
        $("#first_name").css("border", "1px solid green");

    }
    
 
}
function lastNameValidation(){
    
    re = /^[A-Za-z]+$/;
    var last_name=document.getElementById("last_name").value;
    if(!re.test(last_name)||last_name.length<3)
    {
        $("j").html(" <font color='red'>Must be valid Last Name</font>.");
        $("#last_name").css("border", "1px solid red");
    }
    else
    {
        $("j").html("");
        $("#last_name").css("border", "1px solid green");
    }
}
function CountryValidation(){
   
    var country=document.getElementById("working_country").value;
    
    if(country.length=="")
    {
        $("j").html(" <font color='red'>Must be valid working country</font>.");
        $("#working_country").css("border", "1px solid red");
    }
    else
    {
        $("j").html("");
        $("#working_country").css("border", "1px solid green");
    }
}
function CountryValidation1(){
   
    var country=document.getElementById("living_country").value;
   
   
    if(country.length=="")
    {
        $("j").html(" <font color='red'>Must be valid living country</font>.");
        $("#living_country").css("border", "1px solid red");
    }
    else
    {
        $("j").html("");
        $("#living_country").css("border", "1px solid green");
    }
}



function StatusValidation(){
   
    var status=document.getElementById("current_status").value;
   
   
    if(status.length=="")
    {
        $("j").html(" <font color='red'>Must be valid status</font>.");
        $("#current_status").css("border", "1px solid red");
    }
    else
    {
        $("j").html("");
        $("#current_status").css("border", "1px solid green");
    }
}


function EmailValidation1(){
   
    var status=document.getElementById("email1").value;
   
    re=/[^@]+@[^@]+\.[a-zA-Z]{2,}/;
    if(!re.test(status))
    {
        $("j").html(" <font color='red'>Must be valid  corp.email</font>.");
        $("#email1").css("border", "1px solid red");
    }
    else
    {
        $("j").html("");
        $("#email1").css("border", "1px solid green");
    }
}

function EmailValidation2(){
   
    var status=document.getElementById("email2").value;
    re=/[^@]+@[^@]+\.[a-zA-Z]{2,}/;
   
   
    if(!re.test(status))
    {
        $("j").html(" <font color='red'>Must be valid email</font>.");
        $("#email2").css("border", "1px solid red");
    }
    else
    {
        $("j").html("");
        $("#email2").css("border", "1px solid green");
    }
}


function phoneValidation(){
   
    var status=document.getElementById("phone1").value;
    
   
   
    if(status.length!=14)
    {
        $("j").html(" <font color='red'>Must be valid phone Number</font>.");
        $("#phone1").css("border", "1px solid red");
    }
    else
    {
        $("j").html("");
        $("#phone1").css("border", "1px solid green");
    }
}
    
function showEducationDetails(userid){
    // alert("in Ajax call ");
    var url='../general/qualificationDetails.action?userid='+userid;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                
                populateTable(req.responseText);
                var headerBox = document.getElementById('spanId');
                if(headerBox.style.display == "none"){       
                    headerBox.style.display = "block";         
                }
                document.getElementById('spanId').innerHTML='';
                document.getElementById('spanId').innerHTML="Education Information"+ document.getElementById('spanId').innerHTML; 
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
//modified by shanker<smaddila@miraclesoft.com>
//function populateTable(response){
//    //alert(response);  
//    if(response!=""){
//       
//   
//        var eduList=response.split("^");
//        //    alert(eduList
//    
//        var RecordsData =' <a href="" class="edu_popup_open "style="float:right;  height="60px";" ><font color="#FF8A14" class="pull-right btn cssbutton" ><b>ADD NEW QUALIFICATION</b></font></a><span><addEdu></addEdu></span><br/><br/><br/><table id="edu_pagenav"  class="CSSTable_task responsive" border="5"cell-spacing="2" align="center"><tr><th>Qualification</th><th>University</th><th>Institute</th><th>Year start</th><th >Year end</th>'+
//        '<th >Percentage</th><th>Specialization</th></tr>';
//        for(var i=0;i<eduList.length-1;i++){        
//            var Values=eduList[i].split("|");
//            {  
//          
//                RecordsData = RecordsData+'<tr><td><a href="" class="eduEdit_popup_open " onclick=" showEditEduOverlayDetails('+Values[0]+');" >'+Values[1]+'</td></a>'+
//             
//                '<td>'+Values[2]+'</td>'+  
//                '<td>'+Values[3]+'</td>'+  
//                '<td>'+Values[4]+'</td>'+  
//                '<td>'+Values[5]+'</td>'+  
//                '<td>'+Values[6]+'</td>'+ 
//                '<td>'+Values[7]+'</td></tr>'
//                                
//            }
//        }
//        RecordsData=RecordsData+ ' </table>  <div id="edu_pageNavPosition" align="right" style="margin-right: 0vw;"/> '    
//        Educationslide.innerHTML=RecordsData; 
//        epager.init(); 
//        epager.showPageNav('epager', 'edu_pageNavPosition'); 
//        epager.showPage(1);
//    }
//    else{
//    
//        $("edu").html(" <b><font color='blue'><br><br>Record not found</font></b>"); 
//    }
//}

function populateTable(response){
    // alert(response);  
    var eduList=response.split("^");
    //    alert(eduList
    
    //  var RecordsData ='<table id="edu_pagenav"  class="CSSTable_task " border="5"cell-spacing="2" align="center"><tr><th>Qualification</th><th>University</th><th>Institute</th><th>Year start</th><th >Year end</th>'+
    //  '<th >Percentage</th><th>Specialization</th></tr>';
    //   while(table.rows.length==0) {
    //  table.deleteRow(0);
    //}
    // $("table").children().remove();
    // $("#edu_pagenav").html("");
    //$("#edu_pagenav tr").remove();
    //  var Table = document.getElementById("edu_pagenav");
    //Table.innerHTML = "";

    var table = document.getElementById("edu_pagenav");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    for(var i=0;i<eduList.length-1;i++){   
       
        var Values=eduList[i].split("|");
        {  
         
         
            var row = $("<tr />")
            //alert("row--?"+row);
      
            $("#edu_pagenav").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
            row.append($('<td><a href="" class="eduEdit_popup_open " onclick=" showEditEduOverlayDetails('+Values[0]+');" > ' + Values[1] + "</td>"));
            row.append($("<td>" + Values[2] + "</td>"));
            row.append($("<td>" + Values[3] + "</td>"));
            row.append($("<td>" + Values[4] + "</td>"));
            row.append($("<td>" + Values[5] + "</td>"));
            row.append($("<td>" + Values[6] + "</td>"));
            row.append($("<td>" + Values[7] + "</td>"));
                        
        }
    }
  
    epager.init(); 
    epager.showPageNav('epager', 'edu_pageNavPosition'); 
    epager.showPage(1);
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
/*  -- Start, Insert Qualification Details --- */
function addQualification()
{
    //  alert("called")
    var userid=$("#userid").val();
    var qualification= $("#qualification").val();
    //   alert(qualification)
    var percentage= $("#percentage").val();
    var university=$("#university").val();
    var year_start=$("#year_start").val();
    var year_end=$("#year_end").val();
    var institution=$("#institution").val();
    var specialization=$("#specialization").val();
    // alert(userid+" "+qualification+" "+percentage+" "+university+" "+year_start+" "+year_end+" "+institution+" "+specialization)
    //var url='../general/addQualification.action?userid='+userid+'&qualification='+qualification +'&year_start='+year_start +'&year_end='+year_end +'&percentage='+percentage +'&university='+university +'&institution='+institution +'&specialization='+specialization ;
    //var url='../general/addQualification.action?userid='+userid+'&qualification='+qualification+'&percentage='+percentage+'&university='+university+'&year_start='+year_start+'&year_end='+year_end+'&institution='+institution+'&specialization='+specialization;
    if(addQualificationValidate()){
        var url='../general/addQualification.action?userid='+userid+'&qualification='+qualification+'&percentage='+percentage+'&university='+university+'&institution='+institution+'&specialization='+specialization+'&year_start='+year_start+'&year_end='+year_end;
        //+'&year_start='+year_start+'&year_end='+year_end;
        // alert(url)

        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
                    $("errorEduAdd").html(" <font color='green'>Record successfully Added</font>");
                    document.forms["EduAddInfo"].reset();
                    showEducationDetails(userid);
                } 
                else
                {
                    $("edu").html(" <font color='red'>Record not Added</font>");
                //  alert("Error occured");
                }
            }
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    return false;
}

/*  -- start, EDIT Qualification Details --- */



function showEditEduOverlayDetails(eid){
   
    var userid=$("#userid").val();
     
    //alert("===>>>"+eid);
   
          
    var url='../general/editQualificationOverlay.action?userid='+userid+'&usr_edu_id='+eid;
    var myurl='<a href='+url+'>';
 
    var req=overlayRequest(myurl);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //   alert("here we print in req status......");
                populateoverlayEduDetails(req.responseText);
            } 
            
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateoverlayEduDetails(response)
{
    // alert("here we are in overlay...");
    var add=response.split("^");
    for(var i=0;i<add.length-1;i++){        
        var Values=add[i].split("|");
        {  
            // alert(Values[0]);
                
            document.getElementById("usr_edu_id").value=Values[0];
            document.getElementById("edu_qualification").value=Values[1];
            document.getElementById("edu_university").value=Values[2];
            document.getElementById("edu_institution").value=Values[3];
            document.getElementById("edu_start_year").value=Values[4];
            document.getElementById("edu_end_year").value=Values[5];
            document.getElementById("edu_percentage").value=Values[6];
            document.getElementById("edu_specialization").value=Values[7];
                  
                  
        }
    }
}

function overlayRequest(myurl) {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }
    else
    if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }    
}




function editQualificationDetails(){
    // alert("in Ajax call ");
    var userid=$("#userid").val();
    var usr_edu_id=$("#usr_edu_id").val();
    var edu_qualification=$("#edu_qualification").val();
    var edu_university=$("#edu_university").val();
    var edu_institution=$("#edu_institution").val();
    var edu_start_year=$("#edu_start_year").val();
    var edu_end_year=$("#edu_end_year").val();
    var edu_percentage=$("#edu_percentage").val();
    var edu_specialization=$("#edu_specialization").val();
    
    // alert("--------------------->"+edu_start_year);  
    if(editQualificationValidate()){
        
        var url='../general/UpdateQualificationDetails.action?userid='+ userid +'&usr_edu_id='+ usr_edu_id +'&qualification='+ edu_qualification +'&university='+edu_university +'&institution=' +edu_institution +'&start_year='+ edu_start_year +'&end_year='+ edu_end_year +'&percentage='+ edu_percentage +'&specialization=' +edu_specialization ;
        
        //alert("percent"+edu_percentage);
        
        var req=editinitialRequest(url);
  
        req.onreadystatechange = function() {
            //  alert("---->"+req.readyState);
            //         alert("---->"+req.status);
            if (req.readyState == 4) {
                if (req.status == 200) {
                    $("errorEduUpdate").html("<font color='green'>Record successfully Updated.</font>");

                    showEducationDetails(userid);
             
                               
                }
               
            } 
            else{
            // alert("error");
            //alert("=====>>>"+req.readyState);
            //  alert("=====>>>"+req.status);
            //alert("error");
            }
            
        
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    return false;
}



function editinitialRequest(url) {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }
    else
    if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }    
}

/*  -- End, EDIT Qualification Details --- */


/* skill details */

function showSkillDetails(userid){
    // alert("in Ajax call ");
    var url='../general/skillDetails.action?userid='+userid;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
             
                populateSkillTable(req.responseText);
                var headerBox = document.getElementById('spanId');
                if(headerBox.style.display == "none"){       
                    headerBox.style.display = "block";         
                }
                document.getElementById('spanId').innerHTML='';
                document.getElementById('spanId').innerHTML="Skill Information"+ document.getElementById('spanId').innerHTML;
            } 
            
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
//function populateSkillTable(response){
//    //alert(response); 
//    if(response!=""){
//        //alert(response);
//        var skillList=response.split("^");
//        var recordsData =' <a href="" class="skilledit_popup_open" style="float:right ; height="60px";" ><font color="#FF8A14" class="pull-right btn cssbutton"><b>ADD SKILL</b></font></a><span><addskill></addskill></span><br/><br/><br/><table id="skilpagenav"  class="CSSTable_task  responsive" border="5"cell-spacing="2" align="center"><tr><th>Skill Name</th><th>Rate your Skill</th><th>Comment</th><tr>';
//        // alert(response);
//        for(var i=0;i<skillList.length-1;i++){        
//            var Values=skillList[i].split("|");
//            {  
//           
//                recordsData = recordsData+'<tr><td><a href="" class="skilledit_popup_open" onclick=" showeditoverlayDetails('+Values[0]+');" >'+Values[1]+'</td></a>'+'<td>'+Values[2]+'</td>'+'<td>'+Values[3]+'</td>'+'</tr>'
//            }
//        }
//        recordsData=recordsData+ ' </table>  <div id="pageNavPosition" align="right" style="margin-right: 0vw;"/> '    
//        Skillslide.innerHTML=recordsData; 
//        pager.init();     
//        pager.showPageNav('pager','pageNavPosition'); 
//        pager.showPage(1);
//    }else{
//        $("skill").html(" <b><font color='blue'><br><br>Record not found</font></b>");
//    }
//
//}

function populateSkillTable(response){
    //  alert(response);  
    var skillList=response.split("^");
    

    var table = document.getElementById("skilpagenav");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    for(var i=0;i<skillList.length-1;i++){     
       
        var Values=skillList[i].split("|");
        {  
                                                         
            var skill_row = $("<tr />")
            $("#skilpagenav").append(skill_row); //this will append tr element to table... keep its reference for a while since we will add cels into it
            skill_row.append($('<td><a href="" class="skilledit_popup_open" onclick=" showeditoverlayDetails('+Values[0]+');" > ' + Values[1] + "</td>"));
            skill_row.append($("<td>" + Values[2] + "</td>"));
            skill_row.append($("<td>" + Values[3] + "</td>"));
                  
        }
    }

   
    pager.init();     
    pager.showPageNav('pager', 'pageNavPosition'); 
    pager.showPage(1);
}





function showeditoverlayDetails(sid){
   
    var userid=$("#userid").val();
    var skill_name=$("#skill_name").val();
   
   
          
    var url='../general/editoverlay.action?userid='+userid+'&skill_id='+sid;
    var myurl='<a href='+url+'>';
    //  alert("---->sid"+sid);
 
    var req=initRequest(myurl);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                populateoverlay(req.responseText);
            } 
            
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateoverlay(response)
{
    var add=response.split("^");
    for(var i=0;i<add.length-1;i++){        
        var Values=add[i].split("|");
        {  
            // alert(Values[0]);
                
            document.getElementById("skill_id").value=Values[0];
            document.getElementById("skill_name").value=Values[1];
            document.getElementById("skill_rate").value=Values[2];
            document.getElementById("comments").value=Values[3];
                  
        }
    }
}



function skillrateValid(){
   
   
    // var skill_rate=document.getElementById("skill_rate").value;
    var skill_name= $("#s_name").val();
    var skill_rate= $("#s_rate").val();
    var comments=$("#s_comments").val();
    
  
    
    if(skill_name=="" || skill_name==null){
        $("error").html(" <font color='red'>Field is Required</font>.");
        $("#s_name").css("border","1px solid red");
        return false;
    }
       
    if((skill_rate>10 || skill_rate < 0) || (skill_rate=="" || skill_rate==null) ) 
    {
        $("error").html("<font color='red'>skillrate must be in between 0 to 10</font>.");
        $("#s_rate").css("border","1px solid red");
        return false;
    }
    else
    {
        $("error").html("");
        $("#s_name").css("border", "1px solid #3BB9FF");
        $("#s_rate").css("border", "1px solid #3BB9FF");
        return true;
    }
}
function editskillrateValid(){
   
   
    // var skill_rate=document.getElementById("skill_rate").value;
    var skill_name= $("#skill_name").val();
    var skill_rate= $("#skill_rate").val();
    var comments=$("#s_comments").val();
    
  
    
    if(skill_name=="" || skill_name==null){
        $("error").html(" <font color='red'>Field is Required</font>.");
        $("#skill_name").css("border", "1px solid red");
        return false;
    }
       
    if((skill_rate>10 || skill_rate < 0) || (skill_rate=="" || skill_rate==null) ) 
    {
        $("error").html(" <font color='red'>Skillrate must be in between 0 to 10</font>.");
        $("#skill_rate").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("error").html("");
        $("#skill_name").css("border", "1px solid #3BB9FF");
        $("#skill_rate").css("border", "1px solid #3BB9FF");
        return true;
    }
}


 

function seteditSkillDetails(){
    // alert("in Ajax call ");
    var userid=$("#userid").val();
    var skill_id=$("#skill_id").val();
    var skill_name=$("#skill_name").val();
    var skill_rate=$("#skill_rate").val();
    var comments=$("#comments").val();
        
      
    if(editskillrateValid())
    {     
        var url='../general/seteditskillDetails.action?userid='+ userid +'&skill_id='+ skill_id +'&skill_name='+ skill_name +'&skill_rate='+ skill_rate +'&comments=' +comments ;
        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                //   location.reload(true);
                $("EditSkillOverlayResult").html(" <font color='Green'>Skill record successfully Updated.</font>");
               
                showSkillDetails(userid);
               
            } 
            else{
            }
            
        
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
   
    
    }
    return false;
}





function addSkills(){
    // alert("in Ajax call ");
    var userid=$("#userid").val();
    var skill_name= $("#s_name").val();
    var skill_rate= $("#s_rate").val();
    var comments=$("#s_comments").val();
    
    if(skillrateValid())
    {
    
        var url='../general/addSkills.action?userid='+userid +'&skill_name='+skill_name +'&skill_rate='+skill_rate +'&comments='+comments  ;
        // alert(url);
        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
                    //alert("Record Updated Successfully");
                    $("addSkillOverlayResult").html(" <font color='Green'>Skill Record successfully Added.</font>.");
                    document.forms["skillForm"].reset();

                    showSkillDetails(userid);
              
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
    return false;
}

function lenghtValidator(){
//alert("Name"+this.name());
//alert("Name"+this.value);
}

/* pagination */
 
function EmpPager(tableName, itemsPerPage) {
    //alert(tableName+" and "+itemsPerPage);
    this.tableName = tableName;
    this.itemsPerPage = itemsPerPage;
    this.currentPage = 1;
    this.pages = 0;
    this.inited = false;
    
    this.showRecords = function(from, to) {        
        var rows = document.getElementById(tableName).rows;
        // i starts from 1 to skip table header row
        for (var i = 1; i < rows.length; i++) {
            if (i < from || i > to)  
                rows[i].style.display = 'none';
            else
                rows[i].style.display = '';
        }
    }
   
    this.showPage = function(pageNumber) {
        

        var oldPageAnchor = document.getElementById('pg'+this.currentPage);
        oldPageAnchor.className = 'pg-normal';
        
        this.currentPage = pageNumber;
        var newPageAnchor = document.getElementById('pg'+this.currentPage);
        newPageAnchor.className = 'pg-selected';
        
        var from = (pageNumber - 1) * itemsPerPage + 1;
        var to = from + itemsPerPage - 1;
        this.showRecords(from, to);
    }   
    
    this.prev = function() {
        if (this.currentPage > 1){
            this.showPage(this.currentPage - 1);
        //  alert("prev"+this.currentPage)
        }
    }
    
    this.next = function() {
        if (this.currentPage < this.pages) {
            this.showPage(this.currentPage + 1);
        // alert("next"+this.currentPage)
        }
    }                        
    
    this.init = function() {
        var rows = document.getElementById(tableName).rows;
        //alert("rows"+rows);
        var records = (rows.length -1); 
        this.pages = Math.ceil(records / itemsPerPage);
        //alert("pages"+this.pages)
        this.inited = true;
    //alert("inited"+this.inited)
    }

    this.showPageNav = function(pagerName, positionId) {
        if (! this.inited) {
            // alert("not inited ");
            return;
        }
        //alert(positionId)
        //alert(pagerName)
        var element = document.getElementById(positionId);                                                                                  
        var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal" "> <font align="bottom" class="jumpbar"> Page: <i class="fa fa-chevron-circle-left"></i> </font></span> ';
        for (var page = 1; page <= this.pages; page++) 
            pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');" "><font color="black" face="verdana">' + page + '</font></span> ';
        pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"><font align="bottom" class="jumpbar"><i class="fa fa-chevron-circle-right"></i></font></span>';            
        //  alert(pagerName+"&"+positionId);
        // pagerHtml='<span style="margin-right:40vw;>'+pagerHtml+'</span>';
        element.innerHTML =pagerHtml ;
    }
}


function getTitles(){
    //alert("hello");
    var dept=document.getElementById("departments").value;
    var url='../general/getTitles.action?dept_id='+dept;
    var req=initRequest(url);
    req.onreadystatechange = function() {
       
        if (req.readyState == 4 && req.status == 200) {
            setTitles(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function setTitles(data){
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

function updateMiscellaneousInfo()
{
    //alert("in function updateMiscellaneousInfo");
    var report_id=document.getElementById("reportsToId").value
    var userid=document.getElementById("userid").value
    if(report_id=='undefined')
    {
        report_id=0;
    }
    var dept_id=document.getElementById("departments").value;
    var title_id=document.getElementById("titlesId").value;
    var isTeam;
    if($('#isTeam').prop('checked')) 
        isTeam=1;
    else
        isTeam=0;
    var isManager;
    if($('#isManager').prop('checked')) 
        isManager=1;
    else
        isManager=0;
    var isPmo;
    if($('#isPmo').prop('checked')) 
        isPmo=1;
    else
        isPmo=0;
    var isOpt;
    if($('#isOpt').prop('checked')) 
        isOpt=1;
    else
        isOpt=0;
    var isSbteam;
    if($('#isSbteam').prop('checked')) 
        isSbteam=1;
    else
        isSbteam=0;
    // var url='../general/addSkills.action?userid='+userid  ;
    //var url='../general/seteditskillDetails.action?userid='+ userid +'&skill_id='+ skill_id +'&skill_name='+ skill_name +'&skill_rate='+ skill_rate +'&comments=' +comments ;
       
    var url='../general/updateInfo.action?userid='+ userid +'&report_id='+ report_id +'&dept_id='+ dept_id +'&title_id='+ title_id +'&isTeam='+ isTeam +'&isManager='+ isManager +'&isPmo='+ isPmo +'&isOpt='+ isOpt +'&isSbteam='+ isSbteam;
    // alert(url)
    var req=initRequest(url)
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //alert("Record Updated Successfully");
               
                $("#MiscellaneousResult").css('visibility', 'visible');
            } 
            else
            {
               
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}   


/** to get the user security details **/
function getSecurityDetails(usrid){
     
    //alert("hiii-->"+usrid);
    var url='../general/getSecurityInfo.action?userid='+usrid;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                
                populateSecurityData(req.responseText);
                var headerBox = document.getElementById('spanId');
                if(headerBox.style.display == "none"){       
                    headerBox.style.display = "block";         
                }
                document.getElementById('spanId').innerHTML='';
                document.getElementById('spanId').innerHTML="Confidential Information"+ document.getElementById('spanId').innerHTML;
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
 
function populateSecurityData(response){
   
                                
       
    // alert(response);  
    var flag=response.split("FLAG");
    //var addList=response.split("^");
    //alert(flag[1])
           
    var Values=response.split("|");
        
    //alert(Values[0]);
    if(Values[1]==null)
    {
                    
    }
    else{
        document.getElementById("pan").value=Values[0];
        document.getElementById("name").value=Values[1];
        document.getElementById("bank").value=Values[2];
        document.getElementById("passport").value=Values[3];
        document.getElementById("banknum").value=Values[4];
        document.getElementById("hname").value=Values[5];
        document.getElementById("ifsc").value=Values[6];
        document.getElementById("uan").value=Values[7];
        document.getElementById("pf").value=Values[8];
        document.getElementById("pass").value=Values[9];
       
    }
     
}

function addSecurityInfo(){
    
  
    // alert("in Ajax call -- ");
    var userid=$("#userid").val();
    // alert(userid);
    var pan=$("#pan").val();
    var panonname=$("#name").val();
    var bank=$("#bank").val();
    var passport=$("#passport").val();
    var banknum=$("#banknum").val();
    var hname=$("#hname").val();
    var ifsc=$("#ifsc").val();
    var uan=$("#uan").val();
    var pf=$("#pf").val();
    var pass=$("#pass").val();
    var passportDate=$("#passport").val(); 
       
       
    var panpattern = /^[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}$|^\d{3}-\d{2}-\d{4}$/;

    var namepattern = /^[A-Za-z ]+$/;

    var bankpattern = /^[A-Za-z ]+$/;

    var banknumpattern=/^[a-zA-Z0-9](?=[\w.]{10,20}$)\w*\.?\w*$/i;

    var hnamepattern = /^[A-Za-z ]+$/;

    var ifscpattern = /^[A-Za-z]{4}[0-9]{7}$/;

    var uanpattern = /^[0-9]+$/;

    var pfpattern = /^[A-Za-z]{2}-[0-9]{5}-[0-9]{7}$/;

    var passpattern=/^[a-zA-Z0-9](?=[\w.]{7,16}$)\w*\.?\w*$/i;
      
      
      
      
    if(!panpattern.test(pan)||!namepattern.test(panonname)||!bankpattern.test(bank)||!banknumpattern.test(banknum)||!hnamepattern.test(hname)||!ifscpattern.test(ifsc)||!uanpattern.test(uan)||!pfpattern.test(pf)||!passpattern.test(pass)||passportDate=="")
    {
        $("securityinfo").html(" <font color='red'>Can't Save invalid fields</font>");
        $("#").css('visibility', 'hidden');
        $("#").css('visibility', 'hidden');
        return false;
    }

    
    
    
    var url='../general/updateSecurityDetails.action?userid='+userid +'&ssnPanNo='+pan +'&empBankName='+bank +'&panName='+panonname +'&empPassportExpDate='+passport+ '&empBankAccNo='+ banknum+'&empBankAccName='+hname +'&empBankIfsc='+ifsc +'&empUan='+uan +'&empPfNo='+ pf+'&empPassportNo='+pass;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //alert("Record Updated Successfully");
                document.getElementById("successInfo").innerHTML=req.responseText;
               
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
    return false;
}




function panValidation(){
    
    //alert("pan")
    var pancard=document.getElementById("pan").value;
    
    pattern = /^[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}$|^\d{3}-\d{2}-\d{4}$/;
   
    if(pancard=="" || pancard==null){
        $("securityinfo").html("<font color='red'>Field is required<br></font>");
        $("#pan").css("border", "1px solid red");
       
    }
    
    else if(!pattern.test(pancard))
    {
        //alert("hii")
        $("securityinfo").html(" <font color='red'>must be valid pancard number<br>Example:ABCde1234F/123-12-1234</font>");
        $("#pan").css("border", "1px solid red");
     
    }

    else
    {
        $("#pan").css("border", "1px solid green");
        $("securityinfo").html("");
       
    }
    
}

function nameValidation(){
    
    //alert("12")
    var Name=document.getElementById("name").value;
    pattern = /^[A-Za-z ]+$/;
    
    if(Name=="" || Name==null){
        $("securityinfo").html(" <font color='red'>field is required</font>");
        $("#name").css("border", "1px solid red");
    }
    else if(!pattern.test(Name))
    {
        $("securityinfo").html(" <font color='red'>must be valid name<br>Example:John</font>");
        $("#name").css("border", "1px solid red");
    }
    else
    {
        $("#name").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function banknameValidation(){
    
    //alert("12")
    var Bank=document.getElementById("bank").value;
    pattern = /^[A-Za-z ]+$/;
    if(Bank=="" || Bank==null){
        $("securityinfo").html(" <font color='red'>field is required</font>");
        $("#bank").css("border", "1px solid red");
    }
    else if(!pattern.test(Bank))
    {
        $("securityinfo").html(" <font color='red'>must be valid bank name<br>Example:SBI</font>");
        $("#bank").css("border", "1px solid red");
    }
    else
    {
        $("#bank").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function banAccknumValidation(){
    
    //alert("12")
    var Banknumber=document.getElementById("banknum").value;
    //pattern = /^[A-Za-z]{2}[0-9]{16}$/;
    pattern=/^[a-zA-Z0-9](?=[\w.]{10,20}$)\w*\.?\w*$/i;
    if(Banknumber=="" || Banknumber==null){
        $("securityinfo").html(" <font color='red'>field is required</font>");
        $("#banknum").css("border", "1px solid red");
    }
    else if(!pattern.test(Banknumber))
    {
        $("securityinfo").html(" <font color='red'>must be valid Bank Account Number<br>Example:A1234d567891234567</font>");
        $("#banknum").css("border", "1px solid red");
    }
    else
    {
        $("#banknum").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function b_holdnameValidation(){
    
    //alert("12")
    var Holdername=document.getElementById("hname").value;
    pattern = /^[A-Za-z ]+$/;
    if(Holdername=="" || Holdername==null){
        $("securityinfo").html(" <font color='red'>field is required</font>");
        $("#hname").css("border", "1px solid red");
    }
    else if(!pattern.test(Holdername))
    {
        $("securityinfo").html("<font color='red'>must be valid Account Holder Name<br>Example:John</font>");
        $("#hname").css("border", "1px solid red");
    }
    else
    {
        $("#hname").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function ifscValidation(){
    
    //alert("12")
    var IFSC=document.getElementById("ifsc").value;
    pattern = /^[A-Za-z]{4}[0-9]{7}$/;
    if(IFSC=="" || IFSC==null){
        $("securityinfo").html(" <font color='red'>field is required</font>");
        $("#ifsc").css("border", "1px solid red");
    }
    else if(!pattern.test(IFSC))
    {
        $("securityinfo").html(" <font color='red'>must be valid IFSC number<br>Example:ABcd0123456</font>");
        $("#ifsc").css("border", "1px solid red");
    }
    else
    {
        $("#ifsc").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function uanValidation(){
    
    //alert("12")
    var UAN=document.getElementById("uan").value;
    pattern = /^[0-9]+$/;
    if(UAN=="" || UAN==null){
        $("securityinfo").html(" <font color='red'>field is required</font>");
        $("#uan").css("border", "1px solid red");
    }
    else if(!pattern.test(UAN))
    {
        $("securityinfo").html(" <font color='red'>Must be valid UAN number<br>Example:123456</font>");
        $("#uan").css("border", "1px solid red");
    }
    else
    {
        $("#uan").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function pfValidation(){
    
    //alert("12")
    var PF=document.getElementById("pf").value;
    pattern = /^[A-Za-z]{2}-[0-9]{5}-[0-9]{7}$/;
    if(PF=="" || PF==null){
        $("securityinfo").html(" <font color='red'>Field is required</font>");
        $("#pf").css("border", "1px solid red");
    }
    else if(!pattern.test(PF))
    {
        $("securityinfo").html(" <font color='red'>Must be valid PF number<br>Example:Ab-12345-1234567</font>");
        $("#pf").css("border", "1px solid red");
    }
    else
    {
        $("#pf").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function passportnumValidation(){
    
    //alert("12")
    var PASS=document.getElementById("pass").value;
    //pattern = /^[A-Za-z]{1}[0-9]{7}$/;
    pattern=/^[a-zA-Z0-9](?=[\w.]{7,16}$)\w*\.?\w*$/i;
    if(PASS=="" || PASS==null){
        $("securityinfo").html(" <font color='red'>Field is required</font>");
        $("#pass").css("border", "1px solid red");
    }
    else if(!pattern.test(PASS))
    {
        $("securityinfo").html(" <font color='red'>Must be valid passport number<br>Example:A12a3455</font>");
        $("#pass").css("border", "1px solid red");
    }
    else
    {
        $("#pass").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}
/*   Add Qualification  validation, start */
function addQualificationValidate(){
   
   
    // var skill_rate=document.getElementById("skill_rate").value;
    var val_qualification= $("#qualification").val();
    var val_university= $("#university").val();
    var val_institution=$("#institution").val();
    var val_year_start= $("#year_start").val();
    var val_year_end= $("#year_end").val();
    var val_percentage=$("#percentage").val();
    var addStartDate = Date.parse(val_year_start);
    var addEndDate = Date.parse(val_year_end);
  
    
    if(val_qualification=="" || val_qualification==null){
        $("errorEduAdd").html(" <font color='red'>Qualification field is Required</font>.");
        $("#val_qualification").css("border", "1px solid red");
        $("#qualification").css("border","1px solid red");
        return false;
    }
    if(val_university=="" || val_university==null){
        $("errorEduAdd").html(" <font color='red'>University name is Required</font>.");
        $("#val_university").css("border", "1px solid red");
        $("#university").css("border","1px solid red");
        return false;
    }
    
    if(val_institution=="" || val_institution==null){
        $("errorEduAdd").html(" <font color='red'>Institution name is Required</font>.");
        $("#val_institution").css("border", "1px solid red");
        $("#institution").css("border","1px solid red");
        return false;
    }
    if(val_year_start=="" || val_year_start==null){
        $("errorEduAdd").html(" <font color='red'>Start year is Required</font>.");
        $("#val_year_start").css("border", "1px solid red");
        $("#year_start").css("border","1px solid red");
        return false;
    }
    if(val_year_end=="" || val_year_end==null){
        $("errorEduAdd").html(" <font color='red'>End year is Required</font>.");
        $("#val_year_end").css("border", "1px solid red");
        $("#year_end").css("border","1px solid red");
        return false;
    }
    if(val_percentage<1 || val_percentage>100 || val_percentage=="" ) {
      
        $("errorEduAdd").html(" <font color='red'>Percentage must be between 1 to 100</font>.");
        $("#val_percentage").css("border", "1px solid red");
        $("#percentage").css("border","1px solid red");
        return false;
    
    }
    var difference = (addEndDate - addStartDate) / (86400000 * 7);
    if (difference < 0) {
        // alert("The start date must come before the end date.");
        $("errorEduAdd").html(" <font color='red'>Start date must be less than end date</font>.");
        $("#val_year_start").css("border", "1px solid red");
        $("#year_start").css("border","1px solid red");
        $("#year_end").css("border","1px solid red");
        return false;
    }
    else
    {
        $("errorEduAdd").html("");
        $("#qualification").css("border", "1px solid #3BB9FF");
        $("#val_qualification").css("border", "1px solid #3BB9FF");
        $("#val_university").css("border", "1px solid #3BB9FF");
        $("#university").css("border", "1px solid #3BB9FF");
        $("#val_institution").css("border", "1px solid #3BB9FF");
        $("#institution").css("border", "1px solid #3BB9FF");
        $("#val_year_start").css("border", "1px solid #3BB9FF");
        $("#year_start").css("border", "1px solid #3BB9FF");
        $("#val_year_end").css("border", "1px solid #3BB9FF");
        $("#year_end").css("border", "1px solid #3BB9FF");
        $("#val_percentage").css("border", "1px solid #3BB9FF");
        $("#percentage").css("border", "1px solid #3BB9FF");
        return true;
    }
       
}
/*   Add Qualification  validation, end */
function editQualificationValidate(){
   
   
    // var skill_rate=document.getElementById("skill_rate").value;
    var val_qualification= $("#edu_qualification").val();
    var val_university= $("#edu_university").val();
    var val_institution=$("#edu_institution").val();
    var val_year_start= $("#edu_start_year").val();
    var val_year_end= $("#edu_end_year").val();
    var val_percentage=$("#edu_percentage").val();
    var editStartDate = Date.parse(val_year_start);
    var editEndDate = Date.parse(val_year_end);
  
    
    if(val_qualification=="" || val_qualification==null){
        $("errorEduUpdate").html(" <font color='red'>Qualification field is Required</font>.");
        $("#val_qualification").css("border", "1px solid red");
        $("#edu_qualification").css("border","1px solid red");
        return false;
    }
    if(val_university=="" || val_university==null){
        $("errorEduUpdate").html(" <font color='red'>University name is Required</font>.");
        $("#val_university").css("border", "1px solid red");
        $("#edu_university").css("border","1px solid red");
        return false;
    }
    
    if(val_institution=="" || val_institution==null){
        $("errorEduUpdate").html(" <font color='red'>Institution name is Required</font>.");
        $("#val_institution").css("border", "1px solid red");
        $("#edu_institution").css("border","1px solid red");
        return false;
    }
    if(val_year_start=="" || val_year_start==null){
        $("errorEduUpdate").html(" <font color='red'>Start year is Required</font>.");
        $("#val_year_start").css("border", "1px solid red");
        $("#edu_start_year").css("border","1px solid red");
        return false;
    }
    if(val_year_end=="" || val_year_end==null){
        $("errorEduUpdate").html(" <font color='red'>End year is Required</font>.");
        $("#val_year_end").css("border", "1px solid red");
        $("#edu_end_year").css("border","1px solid red");
        return false;
    }
    if(val_percentage<1 || val_percentage>100 || val_percentage=="" ) {
      
        $("errorEduUpdate").html(" <font color='red'>Percentage must be between 1 to 100</font>.");
        $("#val_percentage").css("border", "1px solid red");
        $("#edu_percentage").css("border","1px solid red");
        return false;
    
    }
    var difference = (editEndDate - editStartDate) / (86400000 * 7);
    if (difference < 0) {
        alert("The start date must come before the end date.");
        $("errorEduUpdate").html(" <font color='red'>start year must be less than end year</font>.");
        $("#edu_start_year").css("border","1px solid red");
        $("#edu_end_year").css("border","1px solid red");
        return false;
    }
    else
    {
        $("errorEduUpdate").html("");
        $("#edu_qualification").css("border", "1px solid #3BB9FF");
        $("#val_qualification").css("border", "1px solid #3BB9FF");
        $("#val_university").css("border", "1px solid #3BB9FF");
        $("#edu_university").css("border", "1px solid #3BB9FF");
        $("#val_institution").css("border", "1px solid #3BB9FF");
        $("#edu_institution").css("border", "1px solid #3BB9FF");
        $("#val_year_start").css("border", "1px solid #3BB9FF");
        $("#edu_start_year").css("border", "1px solid #3BB9FF");
        $("#val_year_end").css("border", "1px solid #3BB9FF");
        $("#edu_end_year").css("border", "1px solid #3BB9FF");
        $("#val_percentage").css("border", "1px solid #3BB9FF");
        $("#edu_percentage").css("border", "1px solid #3BB9FF");
        return true;
    }
       
}
/*   edit Qualification  validation, end */
/* Passport date validation */
function passportDateValidation()
{
    // alert(document.documentForm.docdatepickerfrom.value);
    // document.documentForm.docdatepickerfrom.value="";
    document.getElementById('passport').value = "";
    //document.getElementById('end_date').value = "";
    // alert("Please select from the Calender !");
    return false;
}
function removeResultMessage(){
   
    $("EditSkillOverlayResult").html(" ");
    $("addSkillOverlayResult").html(" ");
    $("errorEduUpdate").html(" ");
    $("errorEduAdd").html(" ");
    $("error").html(" ");
    $("#charNum").text(" ");
    $("#fromleave").css("border", "1px solid #53C2FF ");
    $("#toleave").css("border", "1px solid #53C2FF ");
    $("#leavetype").css("border", "1px solid #53C2FF ");
    $("#reason").css("border", "1px solid #53C2FF ");
   
}

function removeResultMessageAll()
{
    document.forms["skillForm"].reset();
     
    document.forms["EduAddInfo"].reset();
    
    
}

function removeDataAfterCloseOverlay()
{
    
    
    $("error").html(" ");
    $("EditSkillOverlayResult").html(" ");
    $("addSkillOverlayResult").html(" ");
    $("errorEduUpdate").html(" ");
    $("errorEduAdd").html(" ");
   
    document.forms["skillForm"].reset();
     
    document.forms["EduAddInfo"].reset();
    $("#charNum").text(" ");
    
}

  
function doOnLoadDatePicker() {
               
    myCalendar = new dhtmlXCalendarObject(["passport"]);
               
    myCalendar.setSkin('omega');
                
    myCalendar.setDateFormat("%m/%d/%Y ");
                    
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth(); 
    var yyyy = today.getFullYear();
                
    if(dd<10) {
        dd='0'+dd
    } 
    if(mm<10) {
        mm='0'+mm
    } 
                
    var from = yyyy+'/'+mm+'/01';
                
    mm=today.getMonth()+1;
    if(mm<10) {
        mm='0'+mm
    } 
    var to = yyyy+'/'+mm+'/'+dd;
    var odd=today.getDate()+1;
    var overlayDate=yyyy+'/'+mm+'/'+odd;
               
//document.getElementById("docdatepicker").value=to;
                  
}

function HandleSkillRateEdit()
{   
    var skillRate=document.getElementById("skill_rate").value;
    if(skillRate>10){
        var skillvalue=skillRate/10;
        document.getElementById("skill_rate").value=parseInt(skillvalue);
        
    }
}

function HandleSkillRateADD()
{
    
    var skillRate=document.getElementById("s_rate").value;
    if(skillRate>10){
        
        var skillvalue=skillRate/10;
        //  alert(skillvalue)
        document.getElementById("s_rate").value=parseInt(skillvalue);
        
    }
}

/* leave calender */

var leavecalender,overlayleave,leaveeditcalender;
function doOnLoadLeave() {
                
    
    leavecalender = new dhtmlXCalendarObject(["leavefrom","leaveto"]);
    //alert("hii1");
    leavecalender.setSkin('omega');
    // alert("hii2");
    
    leavecalender.setDateFormat("%m-%d-%Y");
                
    leaveeditcalender = new dhtmlXCalendarObject(["leaveEditFrmDate","leaveEditEndDate"]);
    // alert("hii1");
    leaveeditcalender.setSkin('omega');
    // alert("hii2");
    //leaveeditcalender.setDateFormat("%Y/%m/%d");
    leaveeditcalender.setDateFormat("%m-%d-%Y");
                
    againDate();
    //added by jagan           
    overlayleave= new dhtmlXCalendarObject(["fromleave","toleave"]);
    // alert("hii1");
    overlayleave.setSkin('omega');
    // alert("hii2");
    //myCalendar.setDateFormat("%m/%d/%Y %H:%i");
    overlayleave.setDateFormat("%m-%d-%Y");  
//overlayleaveTo= new dhtmlXCalendarObject(["toleave"]);              
                
    
}
function againDate(){
    overlayleave = new dhtmlXCalendarObject(["fleave","tleave"]);
    //alert("1")
    overlayleave.setSkin('omega');
    overlayleave.setDateFormat("%m/%d/%Y");
}

//function enterDateRepository()
//{
//    // alert(document.documentForm.docdatepickerfrom.value);
//    // document.documentForm.docdatepickerfrom.value="";
//    document.getElementById('leavefrom').value = "";
//    document.getElementById('fleave').value = "";
//    alert("Please select from the Calender !");
//    return false;
//}
            
function checkleaveRange() {
    var fromValue=$('#leavefrom').val();
    var toValue=$('#leaveto').val();
    var leaveStatus=$('#leaveStatus').val();
    var leaveType=$('#leaveType').val();
    var res = fromValue.split(" ");
    fromValue=res[0];
    var res1 = toValue.split(" ");
    toValue=res1[0];
                
    if(leaveStatus==-1)
    {
        $("leaveerror").html(" <font color='red'>  Status field is required</font>");
        $("#leaveStatus").css("border", "1px solid red");
        return false;
 
    }
    else{
        $("leaveerror").html("");
        $("#leaveStatus").css("border", "1px solid #3BB9FF");  
    }
    if(leaveType==-1)
    {
        $("leaveerror").html(" <font color='red'>  Leave Type field is required</font>");
        $("#leaveType").css("border", "1px solid red");
        return false;
 
    }
    else{
        $("leaveerror").html("");
        $("#leaveType").css("border", "1px solid #3BB9FF");  
    }
                
                
    //alert(fromValue+" and "+toValue)
    if(fromValue==""){
        // alert("from date is madatory")
        $("leaveerror").html(" <font color='red'>  From Date field is required</font>");
        $("#leavefrom").css("border", "1px solid red");
        return false;
    }else{
        $("leaveerror").html("");
        $("#leavefrom").css("border", "1px solid #3BB9FF");  
    }
                
    if(toValue==""){
        // alert("to date is madatory");
                     
        $("leaveerror").html(" <font color='red'>  To Date field is required</font>");
        $("#leaveto").css("border", "1px solid red");
        return false;

    }else{
        $("leaveerror").html("");
        $("#leaveto").css("border", "1px solid #3BB9FF"); 
    }
                
                
    //alert(fromValue+" and "+toValue)
    if (Date.parse(fromValue) > Date.parse(toValue)) {
        // alert("Invalid Date Range!\nFrrom Date cannot be after To Date!")
        $("leaveerror").html(" <font color='red'>Invalid Date Range!\nFrom Date cannot be after To Date!</font>");
        $("#leaveto").css("border", "1px solid red");
        $("#leavefrom").css("border", "1px solid red"); 
        return false;
    }
    else{
                    
                
        $("leaveerror").html("");
          
        $("#leaveto").css("border", "1px solid red");
        $("#leavefrom").css("border", "1px solid red"); 
          
                   
    }
    return true;
                
                
};
// for employee leaves           
function editleave() {
    var fromValue=$('#leaveEditFrmDate').val();
    var toValue=$('#leaveEditEndDate').val();
    //var reporsto=$('#reporsto').val();
    var reason=$('#alertMessage').val();
    var leavetype=$('#leavetype').val();
    
    var status=$('#status').val();
    //alert(fromValue+" and "+toValue)
    
    if(status=="-1")
    {
        $("editleaveerror").html(" <font color='red'>Status field is required</font>");
        $("#status").css("border", "1px solid red");
        return false; 
    }
    else
    {
        $("editleaveerror").html("");
        $("#status").css("border", "1px solid #3BB9FF");  
    }
    
    if(reason=="")
    {
        $("editleaveerror").html(" <font color='red'>Reason field is required</font>");
        $("#alertMessage").css("border", "1px solid red");
        return false; 
    }
    else
    {
        $("editleaveerror").html("");
        $("#alertMessage").css("border", "1px solid #3BB9FF");  
    }
    if(fromValue==""){
        //   alert("from date is madatory")
        $("editleaveerror").html(" <font color='red'>Start date field is required</font>");
        $("#leaveEditFrmDate").css("border", "1px solid red");
        return false;
    }
    else{
        $("editleaveerror").html("");
        $("#leaveEditFrmDate").css("border", "1px solid #3BB9FF");
    }
                
    if(toValue==""){
        //  alert("to date is madatory");
                    

        $("editleaveerror").html(" <font color='red'>Field is required</font>");
        $("#leaveEditEndDate").css("border", "1px solid red");
        return false;
    }
    else{
        $("editleaveerror").html("");
        $("#leaveEditEndDate").css("border", "1px solid #3BB9FF");
    }
    //    if(status=="O")
    //    {
    //       document.getElementById("update").disabled = false;        
    //    }
    //   else
    //    {      
    //       document.getElementById("update").disabled = true; 
    //       return false;
    //    }
                
    var res = fromValue.split(" ");
    fromValue=res[0];
    var res1 = toValue.split(" ");
    toValue=res1[0];
    //alert(fromValue+" and "+toValue)
    if (Date.parse(fromValue) > Date.parse(toValue)) {
        //alert("Invalid Date Range!\nFrrom Date cannot be after To Date!")
        $("editleaveerror").html(" <font color='red'>Invalid Date Range!\nFrrom Date cannot be after To Date!</font>");
        $("#leaveEditFrmDate").css("border", "1px solid red");
        $("#leaveEditEndDate").css("border", "1px solid red");

        return false;
    }else{
        $("editleaveerror").html("");
        $("#leaveEditFrmDate").css("border", "1px solid #3BB9FF");
           
        $("#leaveEditEndDate").css("border", "1px solid #3BB9FF");
            
    }
    if(leavetype==-1)
    {
        $("editleaveerror").html(" <font color='red'>StatusType field is required</font>");
        $("#leavetype").css("border", "1px solid red");
        return false;
 
    }else{
        $("editleaveerror").html("");
        $("#leavetype").css("border", "1px solid #3BB9FF");
            
    }
                
    if(reporsto=="")
    {
        $("editleaveerror").html(" <font color='red'>Field is required</font>");
        $("#reportsto").css("border", "1px solid red");  
        return false;
    }
    else
    {
        $("editleaveerror").html("");
                       
        $("#reportsto").css("border", "1px solid #3BB9FF");
                  
    }
    return true;
};
function searchleavesDateValidation(){
    document.getElementById('leavefrom').value = "";
    document.getElementById('leaveto').value = "";
    return false;
};
function editleavesDateValidation()
{

    document.getElementById('leaveEditFrmDate').value = "";
    document.getElementById('leaveEditEndDate').value = "";
    return false;
};   
function statusValid(){
    var status=$('#status').val();
    var manager=document.getElementById('isManager').value;
    var teamlead=document.getElementById('isTeamLead').value;
    var sessuser=document.getElementById('userId').value;
    var quser=$('#user').val();
    
   
    //alert('session manager val '+quser+'             ff'+sessuser);
    if(sessuser.match(quser))
    {
        if(status=="O")
        {
            document.getElementById("update").disabled = false;        
        }
        else
        {
            document.getElementById("update").disabled = true; 
            return false; 
        }   
    }

    else if(teamlead==1||manager==1){
    
        if(status=="O")
        {    
            document.getElementById("update").disabled = false; 
       
            document.getElementById("status").disabled = false;
     
        }       
     
        else
        {      
            document.getElementById("update").disabled = true; 
            return false; 
        }
    }
    else
    {
        document.getElementById("update").disabled = true; 
        return false; 
    }   
};

function leaveSearchDateValidation()
{
    var startDate=document.getElementById("leavefrom").value;
    var endDate=document.getElementById("leaveto").value;
    //alert(startDate)
    //alert(endDate)
    var addStartDate = Date.parse(startDate);
    var addEndDate = Date.parse(endDate);

    var difference = (addEndDate - addStartDate) / (86400000 * 7);
    if (difference < 0) {
        // alert("The start date must come before the end date.");
        $("leaveerror").html(" <font color='red'>Start date must be less than end date</font>.");
        $("#startDate").css("border", "1px solid red");
        // $("#val_fromleave").css("border","1px solid red");
        $("#endDate").css("border","1px solid red");
        
        return false;
    }
    return true;
}

function getEmployeeNames(){
    //alert("hello");
    var dept=document.getElementById("departmentid").value;
    var url='../general/getEmployeeNames.action?dept_id='+dept;
    var req=initRequest(url);
    // alert(url);
    req.onreadystatechange = function() {
       
        if (req.readyState == 4 && req.status == 200) {
            setEmployeeNames(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function setEmployeeNames(data){
    var topicId = document.getElementById("employeename");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#employeename');
    $select.find('option').remove();   
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}


function getStates()
{
    var countryId=document.getElementById("VendorCountry").value;
    //alert(countryId);
    var url='getStateByCountry.action?countryId='+countryId;
    var req=initRequest(url);
    // alert(url);
    req.onreadystatechange = function() {
       
        if (req.readyState == 4 && req.status == 200) {
            setStatesInvendor(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function setStatesInvendor(response)
{
    var topicId = document.getElementById("VendorState");
    var flag=response.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#VendorState');
    $select.find('option').remove();   
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("|");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}
function getVendorSearchDetails()
{
    // alert("div"); 
    var vendorName=document.getElementById("VendorName").value;
    
    var vendorURL=document.getElementById("VendorURL").value;
      
    var vendorPhone=document.getElementById("VendorPhone").value;
      
    var vendorState=document.getElementById("VendorState").value;
      
    var vendorCountry=document.getElementById("VendorCountry").value;
      
    var vendorStatus=document.getElementById("vendorStatus").value;
    
    var vendorFlag=document.getElementById("vendorFlag").value;
    
    
    if(vendorFlag=='Team'){
   
        var teamMembers=document.getElementById("teamMembers").value;
    }
    var url='getVendorSearchDetails.action?vendorName='+vendorName+'&vendorURL='+vendorURL+'&vendorPhone='
    +vendorPhone+'&vendorCountry='+vendorCountry+'&vendorState='+vendorState+
    '&vendorStatus='+vendorStatus+'&vendorFlag='+vendorFlag+'&teamMembers='+teamMembers;
    //   alert(url);
   
        
    var req=initRequest(url);
    req.onreadystatechange = function() {
       
        if (req.readyState == 4 && req.status == 200) {
            populateTableForVendorSearch(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
        
    return false;
}
function populateTableForVendorSearch(response){
    var eduList=response.split("^");
    var table = document.getElementById("vendorSearchResults");
    var venFlag="venDetails";
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    for(var i=0;i<eduList.length-1;i++){   
       
        var Values=eduList[i].split("|");
        {  
         
         
            var row = $("<tr />")
            //alert("row--?"+row);
            // alert(Values[0])
            $("#vendorSearchResults").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
            row.append($('<td><a href="vendorDetails.action?venFlag='+ venFlag +'&vendorId='+ Values[0]+'" > ' + Values[2] + "</td>"));
            row.append($('<td><a href="" class="" onclick="" > ' + Values[3] + "</td>"));
            row.append($("<td>" + Values[4] + "</td>"));
            row.append($("<td>" + Values[5] + "</td>"));
            row.append($("<td>" + Values[7] + "</td>"));
            row.append($("<td>" + Values[9] + "</td>"));
            row.append($("<td>" + Values[10] + "</td>"));
            row.append($("<td>" + Values[6] + "</td>"));
                        
        }
    }
  
    epager.init(); 
    epager.showPageNav('epager', 'edu_pageNavPosition'); 
    epager.showPage(1);
}

function employeeHeadingMessage(message)
{
    //alert(message.id);
    if(message.id=="contactDetails"){
   
        document.getElementById("employeeHeading").innerHTML="Contact Information";
    }

    if(message.id=="educationDetails"){
        document.getElementById("employeeHeading").innerHTML="Education Information";
    }
    if(message.id=="skillDetails"){
        document.getElementById("employeeHeading").innerHTML="Skill Information";
    }
    if(message.id=="confidentialInformation"){
        document.getElementById("employeeHeading").innerHTML="Confidential Information";
        showContacts();
    }
    
}

function openUploadFileDialogue(){
    // alert("hello")
    var specialBox = document.getElementById('imageupdateOverlay');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#imageupdate_popup').popup(      
        );

}
function openUploadFileDialogueClose(){
    $("imageErrorMsg").html("");
    var specialBox = document.getElementById('imageupdateOverlay');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#imageupdate_popup').popup(      
        );
}

function ValidateFileUpload() {
    var file = $("#imageupdate").val();
    
     if (file == '') {
        $("imageErrorMsg").html("<font color='red'>Please Upload an image</font>");
        return false;
    }
    
    if (file != '')
    {
        var size = document.getElementById('imageupdate').files[0].size;
        var leafname= file.split('\\').pop().split('/').pop();
        var extension = file.substring(file.lastIndexOf('.')+1);
        if(extension=="jpg"||extension=="png"||extension=="gif" ){
            var size = document.getElementById('imageupdate').files[0].size;
            if(leafname.length>30){
                document.getElementById('imageupdate').value = '';
                $("imageErrorMsg").html("<font color='red'>File name length must be less than 30 characters!</font>");
                // document.getElementById('InsertContactInfo').innerHTML = "<font color=red>File name length must be less than 30 characters!</font>"
                // showAlertModal("File size must be less than 2 MB");
                return false;
            }
            else 
            {
                if(parseInt(size)<2097152) {
                     $("imageErrorMsg").html("");
                }else {
                    document.getElementById('imageupdate').value = '';
                    $("imageErrorMsg").html("<font color='red'>File size must be less than 2 MB</font>");
                    // document.getElementById('InsertContactInfo').innerHTML = "<font color=red>File size must be less than 2 MB</font>"
                    // showAlertModal("File size must be less than 2 MB");
                    return false;
                }
            }
        }
        else 
        {
            document.getElementById('imageupdate').value = "";
            //document.getElementById('InsertContactInfo').innerHTML = "<font color=red>Invalid file extension!Please select pdf or doc or docx or gif or jpg or png or jpeg file.</font>"
            $("imageErrorMsg").html("<font color='red'>Invalid file extension!<br> Please select gif or jpg or png file</font>");
            // $("#InsertContactInfo").html(" <font color=red>Invalid file extension! Please select gif or jpg or png file</font>");
            return false;
        }
    }
    $("#imageErrorMsg").html("");
    return true;
}